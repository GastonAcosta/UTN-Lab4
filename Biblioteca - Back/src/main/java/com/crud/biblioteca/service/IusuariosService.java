package com.crud.biblioteca.service;

import com.crud.biblioteca.model.Libro;
import com.crud.biblioteca.model.Prestamo;
import com.crud.biblioteca.model.Usuarios;

import java.util.List;

public interface IusuariosService {
    public List<Usuarios> getUsuarios();

    public void RegistrarUsuarios(Usuarios u);

    public void EliminarUsuarios(Long id);

    public Usuarios EditarUsuario(Long id, Usuarios u);

    public Usuarios BuscarUsuario(Long id);
}
