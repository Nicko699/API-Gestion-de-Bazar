package org.bazarsecurity.Service;

import org.bazarsecurity.Dto.DetalleVenta.DetalleVentaRequestDto;
import org.bazarsecurity.Dto.Venta.*;
import org.bazarsecurity.Exception.BadRequestException;
import org.bazarsecurity.Exception.NotFoundException;
import org.bazarsecurity.Mapper.VentaMapper;
import org.bazarsecurity.Model.DetalleVenta;
import org.bazarsecurity.Model.Producto;
import org.bazarsecurity.Model.Usuario;
import org.bazarsecurity.Model.Venta;
import org.bazarsecurity.Repository.ProductoRepository;
import org.bazarsecurity.Repository.UsuarioRepository;
import org.bazarsecurity.Repository.VentaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// Implementación del servicio para gestionar las operaciones relacionadas con ventas
@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    private final UsuarioRepository usuarioRepository;

    private final ProductoRepository productoRepository;

    private final VentaMapper ventaMapper;

    public VentaServiceImpl(VentaRepository ventaRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository, VentaMapper ventaMapper) {
        this.ventaRepository = ventaRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.ventaMapper = ventaMapper;
    }
// Método para obtener una venta realizada por un cliente específico
    @Transactional(readOnly = true)
    @Override
    public VentaResponseDto obtenerVentaCliente(Long id, UserDetails userDetails) throws NotFoundException {

        Optional<Venta> venta = ventaRepository.findVentaByUsuario_EmailAndId(userDetails.getUsername(), id);

        if (venta.isPresent()) {

            Venta ventaGet = venta.get();

            return ventaMapper.ventaToVentaResponseDto(ventaGet);

        } else {
            throw new NotFoundException("Venta no encontrada con el id: " + id);
        }

    }
// Método para obtener una venta por ID para administradores
    @Transactional(readOnly = true)
    @Override
    public VentaResponseDto obtenerVentaAdmin(Long id) throws NotFoundException {

        Optional<Venta> venta = ventaRepository.findById(id);

        if (venta.isPresent()) {

            Venta ventaGet = venta.get();

            return ventaMapper.ventaToVentaResponseDto(ventaGet);
        } else {
            throw new NotFoundException("Venta no encontrada con el id: " + id);
        }

    }
// Método para obtener una lista paginada de ventas realizadas por un cliente específico
    @Transactional(readOnly = true)
    @Override
    public Page<VentaResponseDto> obtenerVentasCliente(Pageable pageable, UserDetails userDetails) {

        Page<Venta> ventas = ventaRepository.findVentasByUsuario_Email(userDetails.getUsername(), pageable);

        List<VentaResponseDto> listaVentas = new ArrayList<>();

        for (Venta venta : ventas.getContent()) {

            VentaResponseDto ventaDto = ventaMapper.ventaToVentaResponseDto(venta);

            listaVentas.add(ventaDto);
        }

        return new PageImpl<>(listaVentas, pageable, ventas.getTotalElements());
    }
// Método para obtener una lista paginada de todas las ventas para administradores
    @Transactional(readOnly = true)
    @Override
    public Page<VentaResponseDto> obtenerVentasAdmin(Pageable pageable) {

        Page<Venta> ventas = ventaRepository.findAll(pageable);

        List<VentaResponseDto> listaVentas = new ArrayList<>();

        for (Venta venta : ventas.getContent()) {

            VentaResponseDto ventaDto = ventaMapper.ventaToVentaResponseDto(venta);

            listaVentas.add(ventaDto);
        }

        return new PageImpl<>(listaVentas, pageable, ventas.getTotalElements());

    }

    //Metodo crear cuenta
    @Transactional
    @Override
    public VentaResponseDto crearVenta(VentaCrearRequestDto ventaCrearRequestDto, UserDetails userDetails) throws NotFoundException, BadRequestException {

        //Inicializamos venta
        Venta venta = new Venta();
        //Encontramos el usuario en la bd
        Optional<Usuario> usuario = usuarioRepository.findUsuarioByEmail(userDetails.getUsername());
        //Si el usuario existe lo agregamos a la venta para saber quien la hizo
        if (usuario.isPresent()) {

            Usuario usuarioGet = usuario.get();

            venta.setUsuario(usuarioGet);
        }

        //Inicializamos variables
        double subTotal;
        double total = 0;

        //Inicializamos una lista detalleVenta
        List<DetalleVenta> detalleVentas = new ArrayList<>();

        //Recorremos las compras de productos que hizo el usuario
        for (DetalleVentaRequestDto detalleVentaDto : ventaCrearRequestDto.getListadetalleVentas()) {

            //verificamos que la cantidad ingresada por el usuario no es negativo o 0
            if (detalleVentaDto.getCantidad() <= 0) {

                throw new BadRequestException("La cantidad ingresada debe ser mayor a 0");

            }

            //Encontramos el producto comprado por el usuario en la Bd
            Optional<Producto> producto = productoRepository.findById(detalleVentaDto.getProductoId());

            //Verificamos que el producto que exista
            if (producto.isPresent()) {

                Producto productoGet = producto.get();

                //Consultamos que siempre la cantidad proporcionada por el usuario debe ser menor a la cantidad disponible en la bd
                if (detalleVentaDto.getCantidad() > productoGet.getCantidadDisponible()) {

                    throw new BadRequestException("Cantidad de productos en el stock insuficiente. CantidadDisponible: " + productoGet.getCantidadDisponible());
                }

                //Calculamos el subTotal de cada detalle venta
                subTotal = detalleVentaDto.getCantidad() * productoGet.getPrecio();

                //Calculamos el total de la venta
                total += subTotal;

                //Inicializamos detalle venta
                DetalleVenta detalleVenta = new DetalleVenta();

                detalleVenta.setNombreProducto(productoGet.getNombre());
                detalleVenta.setMarcaProducto(productoGet.getMarca());
                detalleVenta.setPrecioUnitario(productoGet.getPrecio());
                detalleVenta.setCantidad(detalleVentaDto.getCantidad());
                detalleVenta.setSubTotal(subTotal);
                detalleVenta.setProducto(productoGet);
                detalleVenta.setVenta(venta);
                detalleVentas.add(detalleVenta);

                //Descontamos los productos comprados del usuario en la bd
                productoGet.setCantidadDisponible(productoGet.getCantidadDisponible() - detalleVentaDto.getCantidad());
                //Guardamos los cambios
                productoRepository.save(productoGet);
            } else {

                throw new NotFoundException("El producto no existe en nuestro sistema con el id: " + detalleVentaDto.getProductoId());
            }
        }

        //Creamos la fecha de la venta
        LocalDateTime fechaVenta = LocalDateTime.now();

        venta.setFechaVenta(fechaVenta);
        venta.setTotalVenta(total);
        venta.setListadetalleVentas(detalleVentas);

        //Guardamos la venta guardada
        Venta ventaGuardada = ventaRepository.save(venta);

        //Retornamos la venta realizada al usuario
        return ventaMapper.ventaToVentaResponseDto(ventaGuardada);
    }
// Metodo eliminar venta
    @Override
    public void eliminarVenta(Long id) throws NotFoundException {

        Optional<Venta> venta = ventaRepository.findById(id);

        if (venta.isPresent()) {
            Venta ventaGet = venta.get();

            ventaRepository.deleteById(ventaGet.getId());
        } else {
            throw new NotFoundException("Venta no encontrada con el id: " + id);
        }

    }
// Metodo obtener productos por venta
   @Transactional(readOnly = true)
    @Override
    public VentaResponseProductoDto obtenerProductosPorVenta(Long id) throws NotFoundException {

        Optional<Venta> venta = ventaRepository.findById(id);

        if (venta.isPresent()) {

            Venta ventaGet = venta.get();

            return ventaMapper.ventaToVentaResponseProductoDto(ventaGet);

        } else {
            throw new NotFoundException("Venta no encontrada con el id: " + id);
        }

    }
    //metodo obtener ventas por fecha
   @Transactional(readOnly = true)
    @Override
    public VentasDiaResponseDto obtenerVentasPorFecha(LocalDate fecha) throws NotFoundException {

        LocalDateTime inicio=fecha.atStartOfDay();
        LocalDateTime fin=fecha.atTime(23,59,59);

        List<Venta>ventas=ventaRepository.findVentasByFechaVentaBetween(inicio,fin);
//Verificamos que la lista no este vacia
        if (ventas!=null && !ventas.isEmpty()) {

            double totalDia = 0;

            for (Venta venta : ventas) {
//Sumamos el total de las ventas realizadas en esa fecha
                totalDia += venta.getTotalVenta();

            }
            //Retornamos la respuesta con la fecha, cantidad de ventas y el total del dia
            return new VentasDiaResponseDto(fecha,ventas.size(),totalDia);
        }
        else {
            throw new NotFoundException("No se encontraron ventas realizadas en la fecha: "+fecha);
        }


    }
// Metodo obtener top 5 mayores ventas
    @Transactional
    @Override
    public List<VentaTop5ResponseDto> obtenerTo5MayorVenta() {

        List<VentaTop5ResponseDto>listaVentaTop5ResponseDto=new ArrayList<>();
//Obtenemos las 5 ventas con mayor totalVenta
       List<Venta> ventas=ventaRepository.findTop5ByOrderByTotalVentaDesc();

       for (Venta venta:ventas){

           VentaTop5ResponseDto ventaTop5ResponseDto=ventaMapper.ventaToVentaTop5ResponseDto(venta);

           ventaTop5ResponseDto.setCantidadProductos(venta.getListadetalleVentas().size());

           listaVentaTop5ResponseDto.add(ventaTop5ResponseDto);
       }

        return listaVentaTop5ResponseDto;
    }
}