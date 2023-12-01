
package com.crud.biblioteca.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Prestamo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    
    
   @OneToOne
   @JoinColumn(name = "fk_libro", referencedColumnName = "id")
    private Libro libro;
    
}
