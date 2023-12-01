
package com.crud.biblioteca.service;

import com.crud.biblioteca.model.Libro;
import com.crud.biblioteca.repository.IrepositoryLibro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService implements IlibroService {

    @Autowired IrepositoryLibro libroRepo;
    @Override
    public List<Libro> getLibros() {
        return libroRepo.findAll();
    }

    @Override
    public void CrearLibro(Libro l) {
        libroRepo.save(l);
    }

    @Override
    public void EliminarLibro(Long id) {
        libroRepo.deleteById(id);
    }

    @Override
    public Libro EditarLibro(Long id, Libro l) {
        Libro libro = libroRepo.findById(id).orElse(null);
        libro.setTitulo(l.getTitulo());
        libro.setAutor(l.getAutor());
        libro.setGenero(l.getGenero());
        libro.setCantPaginas(l.getCantPaginas());
        libro.setSinopsis(l.getSinopsis());
        
        libroRepo.save(libro);
        return libro;
    }

    @Override
    public Libro BuscarLibro(Long id) {

        return libroRepo.findById(id).orElse(null);
    }

    @Override
    public Libro BuscarNombre(String titulo) {
        return libroRepo.findByTittle(titulo);
    }


}
