package com.mikke.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikke.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
