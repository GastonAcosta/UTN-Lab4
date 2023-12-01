package com.crud.biblioteca.repository;

import com.crud.biblioteca.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IusuariosRepository extends JpaRepository<Usuarios, Long> {

}
