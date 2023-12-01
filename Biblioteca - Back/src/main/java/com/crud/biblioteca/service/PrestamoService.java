
package com.crud.biblioteca.service;

import com.crud.biblioteca.model.Prestamo;
import com.crud.biblioteca.repository.IprestamoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamoService  implements IprestamoService{
    
    @Autowired IprestamoRepository prestaRepo; 
    
    @Override
    public List<Prestamo> getPrestamo() {
       
        return prestaRepo.findAll();
        
    }

    @Override
    public void crearPrestamo(Prestamo p) {
       
        prestaRepo.save(p);
        
    }

    @Override
    public void eliminarPrestamo(Long id) {
        
        prestaRepo.deleteById(id);
    }

    @Override
    public Prestamo editarPrestamo(Long id, Prestamo p) {
        
        //el id lo uso para buscar en la base de datos
        Prestamo prestamo =  prestaRepo.findById(id).orElse(p);
        prestamo.setFechaPrestamo(p.getFechaPrestamo());
        prestamo.setFechaDevolucion(p.getFechaDevolucion());
        prestamo.setLibro(p.getLibro());

        prestaRepo.save(prestamo);
        return prestamo;
    }

    @Override
    public Prestamo BuscarPrestamo(Long id) {
        return prestaRepo.findById(id).orElse(null);
    }
    
}
