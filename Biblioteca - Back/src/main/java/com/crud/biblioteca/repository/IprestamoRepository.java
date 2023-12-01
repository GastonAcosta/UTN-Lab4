
package com.crud.biblioteca.repository;

import com.crud.biblioteca.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IprestamoRepository extends JpaRepository <Prestamo, Long>{
    
}
