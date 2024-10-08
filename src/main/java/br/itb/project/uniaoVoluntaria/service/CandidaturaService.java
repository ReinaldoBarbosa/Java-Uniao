package br.itb.project.uniaoVoluntaria.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import br.itb.project.uniaoVoluntaria.model.entity.Candidatura;
import br.itb.project.uniaoVoluntaria.model.entity.Evento;
import br.itb.project.uniaoVoluntaria.model.repository.CandidaturaRepository;
import jakarta.transaction.Transactional;

@Service
public class CandidaturaService {
	private CandidaturaRepository candidaturaRepository;

	public CandidaturaService(CandidaturaRepository candidaturaRepository) {
		super();
		this.candidaturaRepository = candidaturaRepository;
	}
	public List<Candidatura> findAll() {
		List<Candidatura> candidaturas = candidaturaRepository.findAll();
		
		return candidaturas;
	}
	public Candidatura findById(long id) {
    	Candidatura candidatura = candidaturaRepository.findById(id).orElseThrow();
    	
    	return candidatura;
    }
	
	@Transactional
	public Candidatura create(Candidatura candidatura) {
		
		candidatura.setStatusCadastro("PENDENTE");
		candidatura.setDataCadastro(LocalDateTime.now());

		return candidaturaRepository.save(candidatura);
	}
	@Transactional
	public Candidatura inativar(long id) {
		Optional<Candidatura> _candi = candidaturaRepository.findById(id);
		if (_candi.isPresent()) {
			Candidatura candidaturaAtualizado = _candi.get();
			candidaturaAtualizado.setStatusCadastro("INATIVO");
			
			return candidaturaRepository.save(candidaturaAtualizado);
		}
		return null;
	}
	
	@Transactional
	public Candidatura ativar(long id) {
		Optional<Candidatura> _candi = candidaturaRepository.findById(id);
		if (_candi.isPresent()) {
			Candidatura candidaturaAtualizado = _candi.get();
			candidaturaAtualizado.setStatusCadastro("ATIVO");
			
			return candidaturaRepository.save(candidaturaAtualizado);
		}
		return null;
	}
	
	@Transactional
	public Candidatura reportar(long id) {
		Optional<Candidatura> _candi = candidaturaRepository.findById(id);
		if (_candi.isPresent()) {
			Candidatura candidaturaAtualizado = _candi.get();
			candidaturaAtualizado.setStatusCadastro("REPORT");
			
			return candidaturaRepository.save(candidaturaAtualizado);
		}
		return null;
	}
	
	@Transactional
	public Candidatura admitido(long id) {
		Optional<Candidatura> _candidatura = candidaturaRepository.findById(id);
		if (_candidatura.isPresent()) {
			Candidatura candidaturaAtualizada = _candidatura.get();
			candidaturaAtualizada.setStatusCadastro("ADMITIDO");;
			
			return candidaturaRepository.save(candidaturaAtualizada);
		}
		return null;
	}
	}
