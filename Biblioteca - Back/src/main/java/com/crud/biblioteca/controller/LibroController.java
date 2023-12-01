
package com.crud.biblioteca.controller;

import com.crud.biblioteca.model.Libro;
import com.crud.biblioteca.service.IlibroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private IlibroService libroService;

    @GetMapping("/listar")
    @ResponseBody
    public List<Libro> getLibros() {
        return libroService.getLibros();
    }

    @PostMapping("/crear")
    @ResponseBody
    public String crearLibro(@RequestBody Libro libro) {
        libroService.CrearLibro(libro);
        return "Libro creado exitosamente";
    }
    
    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public String EliminarLibro(@PathVariable Long id){
        libroService.EliminarLibro(id);
        return "Libro eliminado";
    }
    
    @PutMapping("/modificar/{id}")
    @ResponseBody
    public Libro modificarLibro(@PathVariable Long id, @RequestBody Libro libro){
       
        libroService.EditarLibro(id, libro);
        
        Libro l = libroService.BuscarLibro(id);
        
        return l;
    }
    
    @GetMapping("/buscar/{id}")
    @ResponseBody
    public Libro buscarLibro(@PathVariable Long id){
        libroService.BuscarLibro(id);
        
        Libro l = libroService.BuscarLibro(id);
        
        return l;
    }

    @GetMapping("/buscarNombre/{titulo}")
    @ResponseBody
    public Libro buscarTitulo(@PathVariable String titulo){

        libroService.BuscarNombre(titulo);

        Libro l = libroService.BuscarNombre(titulo);

        return l;
    }
}