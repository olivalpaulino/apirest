package br.com.olival.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.olival.apirest.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
