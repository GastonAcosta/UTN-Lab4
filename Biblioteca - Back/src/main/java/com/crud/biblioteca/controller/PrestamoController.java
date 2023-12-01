
package com.crud.biblioteca.controller;

import com.crud.biblioteca.model.Libro;
import com.crud.biblioteca.model.Prestamo;
import com.crud.biblioteca.service.IprestamoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prestamos")
public class PrestamoController {
    
    @Autowired IprestamoService prestaServi;
    
    @GetMapping("/listar")
    @ResponseBody
    public List<Prestamo> getPrestamos(){
    
     return prestaServi.getPrestamo();
    }
    
    @PostMapping("/crear")
    @ResponseBody
    public String crearPrestamo(@RequestBody Prestamo p){
        prestaServi.crearPrestamo(p);
        
        return "Prestamo creado";
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public String elimnarPrestamo(@PathVariable Long id){
        prestaServi.eliminarPrestamo(id);
        return "Prestamo eliminado";
    }

    @PutMapping("/modificar/{id}")
    @ResponseBody
    public Prestamo modificarPrestamo(@PathVariable Long id, @RequestBody Prestamo prestamo){

        prestaServi.editarPrestamo(id, prestamo);

        Prestamo p = prestaServi.BuscarPrestamo(id);

        return p;
    }

    @GetMapping("/buscar/{id}")
    @ResponseBody
    public Prestamo buscarPrestamo(@PathVariable Long id){
        prestaServi.BuscarPrestamo(id);

        Prestamo p = prestaServi.BuscarPrestamo(id);

        return p;
    }
}
