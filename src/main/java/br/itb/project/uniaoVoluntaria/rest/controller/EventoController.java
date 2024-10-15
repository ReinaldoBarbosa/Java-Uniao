package br.itb.project.uniaoVoluntaria.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.itb.project.uniaoVoluntaria.model.entity.Evento;
import br.itb.project.uniaoVoluntaria.service.EventoService;

@RestController
@RequestMapping("/evento/")
public class EventoController {
	
	private EventoService eventoService;

	public EventoController(EventoService eventoService) {
		super();
		this.eventoService = eventoService;
	}

	@GetMapping("findAll")
	public ResponseEntity<List<Evento>> findAll() {
		List<Evento> eventos = eventoService.findAll();
		
		return new ResponseEntity<List<Evento>> (eventos, HttpStatus.OK);
		}
	
	@GetMapping("findById/{id}")
	public ResponseEntity<Evento> findById(@PathVariable long id) {
		Evento evento = eventoService.findById(id);
		
		return new ResponseEntity<Evento>(evento, HttpStatus.OK);
	}
	
	@PostMapping("create/{email}")
	public ResponseEntity<Evento> create (
			@RequestParam(value = "file", required = false) MultipartFile file, 
			@ModelAttribute("evento") Evento evento,@PathVariable String email   ){
		
		System.out.println(evento.getDataEvento());
		Evento _evento = eventoService.create(file, evento, email);
		return new ResponseEntity<Evento> (_evento, HttpStatus.OK);
	}
	
	// Mesmo processo, cópia do update com alteração apenas em inativar
		@PutMapping("inativar/{id}")
		public ResponseEntity<Evento> inativar(@PathVariable long id) {
			Evento evento = eventoService.inativar(id);
			
			return new ResponseEntity<Evento> (evento, HttpStatus.OK);
		}
		
		@PutMapping("alterarInfo/{id}")
		public ResponseEntity<Evento> alterarInfo(@PathVariable long id, @RequestBody Evento evento) {
			Evento _evento = eventoService.alterarInfo(id, evento);
			
			return new ResponseEntity<Evento> (_evento, HttpStatus.OK);
		}
	
}
