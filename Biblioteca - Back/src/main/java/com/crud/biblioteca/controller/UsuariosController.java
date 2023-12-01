package com.crud.biblioteca.controller;

import com.crud.biblioteca.model.Libro;
import com.crud.biblioteca.model.Prestamo;
import com.crud.biblioteca.model.Usuarios;
import com.crud.biblioteca.service.IusuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    private IusuariosService usuariosService;

    @GetMapping("/listar")
    @ResponseBody
    public List<Usuarios> getUsuarios() {
        return usuariosService.getUsuarios();
    }

    @PostMapping("/registrar")
    @ResponseBody
    public String registrarUsuario(@RequestBody Usuarios usuarios) {
        usuariosService.RegistrarUsuarios(usuarios);
        return "Usuario registrado exitosamente";
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public String EliminarUsuarios(@PathVariable Long id){
        usuariosService.EliminarUsuarios(id);
        return "Usuario eliminado";
    }

    @PutMapping("/modificar/{id}")
    @ResponseBody
    public Usuarios modificarUsuarios(@PathVariable Long id, @RequestBody Usuarios usuarios){
        usuariosService.EditarUsuario(id, usuarios);

        Usuarios u = usuariosService.BuscarUsuario(id);

        return u;
    }
}
