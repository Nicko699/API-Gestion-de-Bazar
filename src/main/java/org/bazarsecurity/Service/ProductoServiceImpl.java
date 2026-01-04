package org.bazarsecurity.Service;

import org.bazarsecurity.Dto.Producto.Editar.ProductoEditarRequestDto;
import org.bazarsecurity.Dto.Producto.Editar.ProductoEditarResponseDto;
import org.bazarsecurity.Dto.Producto.Obtener.ProductoAdminResponseDto;
import org.bazarsecurity.Dto.Producto.Obtener.ProductoClienteResponseDto;
import org.bazarsecurity.Dto.Producto.Crear.ProductoCrearRequestDto;
import org.bazarsecurity.Dto.Producto.Crear.ProductoCrearResponseDto;
import org.bazarsecurity.Exception.NotFoundException;
import org.bazarsecurity.Mapper.ProductoMapper;
import org.bazarsecurity.Model.Producto;
import org.bazarsecurity.Repository.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// Implementación del servicio para gestionar las operaciones relacionadas con productos
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    //Obtener producto por el id para el cliente
    @Transactional(readOnly = true)
    @Override
    public ProductoClienteResponseDto obtenerProductoCliente(Long id) throws NotFoundException {

        Optional<Producto>producto=productoRepository.findById(id);
  // Verificar si el producto existe y está activo
        if (producto.isPresent() && producto.get().isActivo()){

            Producto productoGet=producto.get();
         
        return productoMapper.productoToProductoClienteResponseDto(productoGet);
// Si el producto no existe o no está activo, lanzamos una excepción
        }
        else {

            throw new NotFoundException("Producto inactivo o no existe con el id: "+id);
        }

    }

    //Obtener lista de productos al cliente
    @Transactional(readOnly = true)
    @Override
    public Page<ProductoClienteResponseDto> obtenerProductosCliente(Pageable pageable) {
        
        List<ProductoClienteResponseDto>productosDto=new ArrayList<>();
     //Encontramos la lista de productos activos paginados
     Page<Producto>listaProductos=productoRepository.findProductosByActivo(true,pageable);
// Recorremos la lista de productos encontrados en la bd
     for (Producto producto:listaProductos.getContent()){

         ProductoClienteResponseDto productoDto=productoMapper.productoToProductoClienteResponseDto(producto);

         productosDto.add(productoDto);
     }
// Retornamos una nueva paginacion con los productosDto
     return new PageImpl<>(productosDto,pageable,listaProductos.getTotalElements());

    }

    //Obtener producto por el id por el admin
    @Transactional(readOnly = true)
    @Override
    public ProductoAdminResponseDto obtenerProductoAdmin(Long id) throws NotFoundException {

        Optional<Producto>producto=productoRepository.findById(id);
       
        if (producto.isPresent()){

            Producto productoGet=producto.get();

            return productoMapper.productoToProductoAdminResponseDto(productoGet);
        }
        else {

            throw new NotFoundException("Producto inactivo o no existe con el id: "+id);
        }

    }

    //Obtener lista de productos pro el admin
    @Transactional(readOnly = true)
    @Override
    public Page<ProductoAdminResponseDto> obtenerProductosAdmin(Pageable pageable) {

        List<ProductoAdminResponseDto>productosDto=new ArrayList<>();
        //Encontramos la lista de productos paginados
        Page<Producto>listaProductos=productoRepository.findAll(pageable);
        //Recorremos la lista de productos encontrados en la bd
        for (Producto producto:listaProductos.getContent()){
            //Trasformamos cada producto a un productoDto
            ProductoAdminResponseDto productoDto=productoMapper.productoToProductoAdminResponseDto(producto);
            //Agregamos el productoDto a una lista de productosDto
            productosDto.add(productoDto);

        }
        //Retornamos una nueva paginacion con los productosDto
        return new PageImpl<>(productosDto,pageable,listaProductos.getTotalElements());
    }

    //Metodo crear producto
    @Transactional
    @Override
    public ProductoCrearResponseDto crearProducto(ProductoCrearRequestDto productoCrearRequestDto) {
//Mapeamos el productoCrearRequestDto a un objeto Producto
        Producto producto=productoMapper.productoCrearRequestDtoToProducto(productoCrearRequestDto);

        LocalDateTime fechaCreacion=LocalDateTime.now();

        producto.setActivo(true);
        producto.setFechaCreacion(fechaCreacion);

        Producto productoGuardado=productoRepository.save(producto);
//Retornamos el producto guardado mapeado a un productoCrearResponseDto
        return productoMapper.productoToProductoCrearResponseDto(productoGuardado);
    }

    //metodo de editar producto
    @Transactional
    @Override
    public ProductoEditarResponseDto editarProducto(Long id, ProductoEditarRequestDto productoEditarRequestDto) throws NotFoundException {

        Optional<Producto>producto=productoRepository.findById(id);

        if (producto.isPresent()){

            Producto productoGet=producto.get();


        Producto productoMap=productoMapper.ProductoEditarRequestDtoToProducto(productoEditarRequestDto,productoGet);

        LocalDateTime fechaActualizacion=LocalDateTime.now();

        productoMap.setFechaActualizacion(fechaActualizacion);

      Producto productoActualizado=productoRepository.save(productoMap);
//Retornamos el producto actualizado mapeado a un productoEditarResponseDto
        return productoMapper.productoToProductoEditarResponseDto(productoActualizado);

        }
// Si el producto no existe, lanzamos una excepción
        else {
throw new NotFoundException("Producto a editar no encontrado en el sistema con el id: "+id);

        }
    }

    //Metodo desactivar producto
    @Transactional
    @Override
    public void desactivarProducto(Long id) throws NotFoundException {

        Optional<Producto>producto=productoRepository.findById(id);

        if (producto.isPresent()){

            Producto productoGet=producto.get();

            productoGet.setActivo(false);

            productoRepository.save(productoGet);

        }
        else {
            throw new NotFoundException("El producto a eliminar no se encuentra en nuestro sistema con el id: "+id);
        }

    }
//Metodo eliminar producto
    @Override
    public void eliminarProducto(Long id) throws NotFoundException {

        Optional<Producto>producto=productoRepository.findById(id);
        if (producto.isPresent()){
            Producto productoGet=producto.get();

            productoRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Producto no encontrado a eliminar con el id: " +id);
        }

    }
//Metodo obtener productos con cantidad menor a 5
    @Transactional(readOnly = true)
    @Override
    public Page<ProductoAdminResponseDto> obtenerProductosCant_Menor5(Pageable pageable) {

        List<ProductoAdminResponseDto>productos=new ArrayList<>();
//Obtenemos la lista de productos con cantidad menor a 5 paginados
        Page<Producto>listaProductos=productoRepository.findProductosByCantidadDisponibleLessThan(5,pageable);

        for (Producto producto:listaProductos.getContent()){

            ProductoAdminResponseDto productoAdminResponseDto=productoMapper.productoToProductoAdminResponseDto(producto);

            productos.add(productoAdminResponseDto);

        }
//Retornamos una nueva paginacion con los productosDto
        return new PageImpl<>(productos,pageable,listaProductos.getTotalElements());

    }


}

