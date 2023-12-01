
package com.crud.biblioteca.service;

import com.crud.biblioteca.model.Libro;
import java.util.List;


public interface IlibroService {
    public List<Libro> getLibros();
    
    public void CrearLibro(Libro l);
    
    public void EliminarLibro(Long id);
    
    public Libro EditarLibro(Long id, Libro l);
    
    public Libro BuscarLibro(Long id);

    public Libro BuscarNombre(String titulo);
}
