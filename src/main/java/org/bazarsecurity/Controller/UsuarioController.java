package org.bazarsecurity.Controller;

import jakarta.validation.Valid;
import org.bazarsecurity.Dto.Usuario.Crear.UsuarioCrearCuentaRequestDto;
import org.bazarsecurity.Dto.Usuario.Crear.UsuarioCrearCuentaResponseDto;
import org.bazarsecurity.Dto.Usuario.Editar.UsuarioEditarCuentaRequestDto;
import org.bazarsecurity.Dto.Usuario.Editar.UsuarioEditarCuentaResponseDto;
import org.bazarsecurity.Dto.Usuario.Login.UsuarioLoginRequestDto;
import org.bazarsecurity.Dto.Usuario.Login.UsuarioLoginResponseDto;
import org.bazarsecurity.Dto.Usuario.Obtener.UsuarioResponseDto;
import org.bazarsecurity.Exception.BadRequestException;
import org.bazarsecurity.Exception.NotFoundException;
import org.bazarsecurity.Service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
// Controlador REST para la gestión de usuarios
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
  // Servicio de usuarios inyectado
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
   // Endpoint para crear una nueva cuenta de usuario
    @PostMapping("/crearCuenta")
    public ResponseEntity<UsuarioCrearCuentaResponseDto> crearCuenta(@Valid @RequestBody UsuarioCrearCuentaRequestDto crearCuentaRequestDto) throws BadRequestException {

        UsuarioCrearCuentaResponseDto crearCuentaResponseDto = usuarioService.crearCuenta(crearCuentaRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(crearCuentaResponseDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(crearCuentaResponseDto);
    }

  // Endpoint para iniciar sesión de usuario
    @PostMapping("/iniciarSesion")
    public ResponseEntity<UsuarioLoginResponseDto> iniciarSesion(@Valid @RequestBody UsuarioLoginRequestDto usuarioLoginRequestDto) throws NotFoundException {

        UsuarioLoginResponseDto usuarioLoginResponseDto = usuarioService.iniciarSesion(usuarioLoginRequestDto);

        return ResponseEntity.ok(usuarioLoginResponseDto);
    }
// Endpoint para obtener los detalles de un usuario por email
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/obtenerUsuario/{email}")
    public ResponseEntity<UsuarioResponseDto> obtenerUsuario(@PathVariable String email) throws NotFoundException {

        UsuarioResponseDto usuarioResponseDto = usuarioService.obtenerUsuario(email);

        return ResponseEntity.ok(usuarioResponseDto);
    }
  // Endpoint para obtener una lista paginada de usuarios, con filtro opcional por estado activo
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/obtenerUsuarios")
    public ResponseEntity<Page<UsuarioResponseDto>> obtenerListaUsuarios(@RequestParam(required = false) Boolean activo, Pageable pageable) {

        Page<UsuarioResponseDto> usuarioResponseDtos = usuarioService.obtenerListaUsuarios(activo, pageable);

        return ResponseEntity.ok(usuarioResponseDtos);

    }
// Endpoint para editar la cuenta del usuario autenticado
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PatchMapping("/admin/editarUsuario")
    public ResponseEntity<UsuarioEditarCuentaResponseDto> editarUsuario(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UsuarioEditarCuentaRequestDto editarCuentaRequestDto) throws BadRequestException {

        UsuarioEditarCuentaResponseDto usuarioEditarCuentaResponseDto = usuarioService.editarUsuario(userDetails, editarCuentaRequestDto);

       return ResponseEntity.ok(usuarioEditarCuentaResponseDto);
    }
// Endpoint para eliminar/desactivar un usuario por ID
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/user/eliminarUsuario/{id}")
    public ResponseEntity<Map<String,String>> eliminarUsuario(@PathVariable  Long id) throws NotFoundException{

        Map<String,String>mensaje=new HashMap<>();

        usuarioService.eliminarUsuario(id);

        mensaje.put("mensaje","tu usuario fue desactivado correctamente");

        return ResponseEntity.ok(mensaje);

    }
  // Endpoint para reactivar la cuenta de un usuario por ID
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/admin/reactivarCuenta/{id}")
    public ResponseEntity<Map<String,String>>reactivarCuenta(@PathVariable Long id) throws NotFoundException, BadRequestException{

        Map<String,String>mensaje=new HashMap<>();

        usuarioService.reactivarCuenta(id);

        mensaje.put("mensaje","¡¡¡cuenta reactivada con exito!!!");

        return ResponseEntity.ok(mensaje);
    }

}
