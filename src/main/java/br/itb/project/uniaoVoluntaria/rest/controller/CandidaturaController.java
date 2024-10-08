package br.itb.project.uniaoVoluntaria.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itb.project.uniaoVoluntaria.model.entity.Candidatura;
import br.itb.project.uniaoVoluntaria.model.entity.Evento;
import br.itb.project.uniaoVoluntaria.service.CandidaturaService;

@RestController
@RequestMapping("/candidatura/")
public class CandidaturaController {
	private CandidaturaService candidaturaService;

	public CandidaturaController(CandidaturaService candidaturaService) {
		super();
		this.candidaturaService = candidaturaService;
	}
	@GetMapping("findAll")
	public ResponseEntity<List<Candidatura>> findAll() {
		List<Candidatura> candidaturas = candidaturaService.findAll();
		
		return new ResponseEntity<List<Candidatura>> (candidaturas, HttpStatus.OK);
		}
	@GetMapping("findById/{id}")
	public ResponseEntity<Candidatura> findById(@PathVariable long id) {
		Candidatura candidatura = candidaturaService.findById(id);
		
		return new ResponseEntity<Candidatura>(candidatura, HttpStatus.OK);
	}
	@PostMapping("create")
	public ResponseEntity<Candidatura> create (
			@RequestBody Candidatura candidatura    ){
		
		Candidatura _candidatura = candidaturaService.create(candidatura);
		return new ResponseEntity<Candidatura> (_candidatura, HttpStatus.OK);
	}
	
	@PutMapping("inativar/{id}")
	public ResponseEntity<Candidatura> inativar(@PathVariable long id) {
		Candidatura _candidatura = candidaturaService.inativar(id);
		
		return new ResponseEntity<Candidatura> (_candidatura, HttpStatus.OK);
	}
	
	@PutMapping("ativar/{id}")
	public ResponseEntity<Candidatura> ativar(@PathVariable long id) {
		Candidatura _candidatura = candidaturaService.ativar(id);
		
		return new ResponseEntity<Candidatura> (_candidatura, HttpStatus.OK);
	}
	
	@PutMapping("reportar/{id}")
	public ResponseEntity<Candidatura> reportar(@PathVariable long id) {
		Candidatura _candidatura = candidaturaService.reportar(id);
		
		return new ResponseEntity<Candidatura> (_candidatura, HttpStatus.OK);
	}
	
	@PutMapping("admitido/{id}")
	public ResponseEntity<Candidatura> admitido(@PathVariable long id) {
		Candidatura candidatura = candidaturaService.admitido(id);
		
		return new ResponseEntity<Candidatura> (candidatura, HttpStatus.OK);
	}
	
}
