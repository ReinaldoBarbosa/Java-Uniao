package br.itb.project.uniaoVoluntaria.rest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import br.itb.project.uniaoVoluntaria.model.entity.Mensagem;
import br.itb.project.uniaoVoluntaria.model.entity.Usuario;
import br.itb.project.uniaoVoluntaria.model.repository.UsuarioRepository;
import br.itb.project.uniaoVoluntaria.rest.exception.ResourceNotFoundException;
import br.itb.project.uniaoVoluntaria.rest.response.MessageResponse;
import br.itb.project.uniaoVoluntaria.service.UsuarioService;

@RestController
@RequestMapping("/usuario/")
public class UsuarioController {
	private UsuarioRepository usuarioRepository;
	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		// Generate constructors using fields
		super();
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("findAll")
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> usuarios = usuarioService.findAll();
		
		return new ResponseEntity<List<Usuario>> (usuarios, HttpStatus.OK);
		}
	
	@GetMapping("findById/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable long id) {
		Usuario usuario = usuarioService.findById(id);
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@GetMapping("findByEmail")
	public ResponseEntity<Usuario> findByEmail(@RequestParam String email) {
		
		Usuario usuario = usuarioService.findByEmail(email);
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@PostMapping("create")
	public ResponseEntity<Usuario> create (
			@RequestBody Usuario usuario    ){
		
		Usuario _usuario = usuarioService.create(usuario);
		return new ResponseEntity<Usuario> (_usuario, HttpStatus.OK);
	}
	
	

	@PutMapping("update/{id}")
	public ResponseEntity<?> update(
			@PathVariable long id, @RequestBody Usuario usuario) {

		Usuario _usuario = usuarioService.update(id, usuario);

		return new ResponseEntity<Usuario> (_usuario, HttpStatus.OK);

	}
	
	@PutMapping("updateComImg/{id}")
	public ResponseEntity<Usuario> updateComimagem(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@PathVariable long id, @ModelAttribute("usuario") Usuario usuario) {

		
		Usuario _usuario = usuarioService.updateComImagem(id, file, usuario);
		return new ResponseEntity<Usuario> (_usuario, HttpStatus.OK);

	}
 	
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody Usuario usuario) {

		Usuario _usuario = usuarioService
				.signin(usuario.getEmail(), usuario.getSenha());

		if (_usuario == null) {
			throw new ResourceNotFoundException("*** Dados Incorretos! *** ");
		}

		return ResponseEntity.ok(_usuario);
	}
	
	@PutMapping("inativar/{id}")
	public ResponseEntity<Usuario> inativar(@PathVariable long id) {
		Usuario usuario = usuarioService.inativar(id);
		
		return new ResponseEntity<Usuario> (usuario, HttpStatus.OK);
	}

	@PutMapping("alterarSenha/{id}")
	public ResponseEntity<Usuario> alterarSenha(@PathVariable long id, @RequestBody Usuario usuario) {
		Usuario _usuario = usuarioService.alterarSenha(id, usuario);
		
		return new ResponseEntity<Usuario> (_usuario, HttpStatus.OK);
	}
	
    }
