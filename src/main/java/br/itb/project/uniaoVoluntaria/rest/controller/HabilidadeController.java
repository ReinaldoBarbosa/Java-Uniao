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

import br.itb.project.uniaoVoluntaria.model.entity.Habilidade;
import br.itb.project.uniaoVoluntaria.service.HabilidadeService;

@RestController
@RequestMapping("/habilidade/")
public class HabilidadeController {
	HabilidadeService habilidadeService;
	
	
	public HabilidadeController(HabilidadeService habilidadeService) {
		super();
		this.habilidadeService = habilidadeService;
	}

	@GetMapping("findAll")
	public ResponseEntity<List<Habilidade>> findAll() {
		List<Habilidade> habilidades = habilidadeService.findAll();
		
		return new ResponseEntity<List<Habilidade>> (habilidades, HttpStatus.OK);
		}
	
	@GetMapping("findById/{id}")
	public ResponseEntity<Habilidade> findById(@PathVariable long id) {
		Habilidade habilidade = habilidadeService.findById(id);
		
		return new ResponseEntity<Habilidade>(habilidade, HttpStatus.OK);
	}
	
	@PostMapping("create")
	public ResponseEntity<Habilidade> create (
			@RequestBody Habilidade habilidade    ){
		
		Habilidade _habilidade = habilidadeService.create(habilidade);
		return new ResponseEntity<Habilidade> (_habilidade, HttpStatus.OK);
	}
	
	@PutMapping("inativar/{id}")
	public ResponseEntity<Habilidade> inativar(@PathVariable long id) {
		Habilidade habilidade = habilidadeService.inativar(id);
		
		return new ResponseEntity<Habilidade> (habilidade, HttpStatus.OK);
	}
	@PutMapping("alterarInformacoes/{id}")
	public ResponseEntity<Habilidade> alterarInformacoes(@PathVariable long id, @RequestBody Habilidade habilidade) {
		Habilidade _habilidade = habilidadeService.alterarInformacoes(id, habilidade);
		
		return new ResponseEntity<Habilidade> (_habilidade, HttpStatus.OK);
	}
	
}
