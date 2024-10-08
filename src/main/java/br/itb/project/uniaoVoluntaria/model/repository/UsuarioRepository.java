package br.itb.project.uniaoVoluntaria.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.itb.project.uniaoVoluntaria.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	
	Usuario findByEmail(String email);

	Usuario findAllById(long id);
}
	

