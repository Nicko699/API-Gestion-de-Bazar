package org.bazarsecurity.Mapper;

import org.bazarsecurity.Dto.Usuario.Crear.UsuarioCrearCuentaRequestDto;
import org.bazarsecurity.Dto.Usuario.Crear.UsuarioCrearCuentaResponseDto;
import org.bazarsecurity.Dto.Usuario.Editar.UsuarioEditarCuentaRequestDto;
import org.bazarsecurity.Dto.Usuario.Editar.UsuarioEditarCuentaResponseDto;
import org.bazarsecurity.Dto.Usuario.Obtener.UsuarioResponseDto;
import org.bazarsecurity.Model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
// Mapper para convertir entre entidades de Usuario y sus DTOs correspondientes
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsuarioMapper {

    //UsuarioMapper Crear cuenta- Response/Request
    @Mapping(target = "listaRol", ignore = true)
    Usuario usuarioCrearCuentaRequestDtoToUsuario(UsuarioCrearCuentaRequestDto crearCuentaRequestDto);

    UsuarioCrearCuentaResponseDto usuarioToCrearCuentaResponseDto(Usuario usuario);
   //UsuarioMapper obtenerUsuario/s
    UsuarioResponseDto usuarioToObtenerUsuarioResponseDto(Usuario usuario);
    //UsuarioMapper editarCuenta-Response/Request
    Usuario usuarioEditarCuentaRequestDtoToUsuario(UsuarioEditarCuentaRequestDto editarCuentaRequestDto,@MappingTarget  Usuario usuario);

    UsuarioEditarCuentaResponseDto usuarioToUsuarioEditarCuentaResponseDto(Usuario usuario);




}
