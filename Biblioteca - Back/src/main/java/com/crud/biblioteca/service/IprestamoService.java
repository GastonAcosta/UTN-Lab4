
package com.crud.biblioteca.service;

import com.crud.biblioteca.model.Prestamo;
import java.util.List;


public interface IprestamoService {
    
    public List<Prestamo> getPrestamo();
    
    public void crearPrestamo(Prestamo p);
    
    public void eliminarPrestamo(Long id);
    
    public Prestamo editarPrestamo(Long id, Prestamo p);
    
    public Prestamo BuscarPrestamo(Long id);
    
}
