package br.itb.project.uniaoVoluntaria.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.itb.project.uniaoVoluntaria.model.entity.Candidatura;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {

}
