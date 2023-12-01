package com.crud.biblioteca.service;

import com.crud.biblioteca.model.Libro;
import com.crud.biblioteca.model.Usuarios;
import com.crud.biblioteca.repository.IrepositoryLibro;
import com.crud.biblioteca.repository.IusuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService implements IusuariosService{
    @Autowired
    IusuariosRepository usuariosRepo;

    @Override
    public List<Usuarios> getUsuarios() {
        return usuariosRepo.findAll();
    }

    @Override
    public void RegistrarUsuarios(Usuarios u) {
        usuariosRepo.save(u);
    }

    @Override
    public void EliminarUsuarios(Long id) {
        usuariosRepo.deleteById(id);
    }

    @Override
    public Usuarios EditarUsuario(Long id, Usuarios u) {
        Usuarios usuarios = usuariosRepo.findById(id).orElse(null);
        usuarios.setNombre(u.getNombre());
        usuarios.setContraseña(u.getContraseña());

        usuariosRepo.save(usuarios);
        return usuarios;
    }

    @Override
    public Usuarios BuscarUsuario(Long id) {
        return usuariosRepo.findById(id).orElse(null);
    }


}
