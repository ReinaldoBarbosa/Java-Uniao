package br.itb.project.uniaoVoluntaria.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.itb.project.uniaoVoluntaria.model.entity.Evento;
import br.itb.project.uniaoVoluntaria.model.entity.Usuario;
import br.itb.project.uniaoVoluntaria.model.repository.EventoRepository;
import br.itb.project.uniaoVoluntaria.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class EventoService {
	private EventoRepository eventoRepository;
	private UsuarioRepository usuarioRepository;

	
	
	public EventoService(EventoRepository eventoRepository, UsuarioRepository usuarioRepository) {
		super();
		this.eventoRepository = eventoRepository;
		this.usuarioRepository = usuarioRepository;
	}

	public List<Evento> findAll() {
		List<Evento> eventos = eventoRepository.findAll();
		
		return eventos;
	}
	
	public Evento findById(long id) {
    	Evento evento = eventoRepository.findById(id).orElseThrow();
    	
    	return evento;
    }
	
	
	@Transactional
	public Evento updateFoto(MultipartFile file, long id, Evento evento) {
		Optional<Evento> _evento = eventoRepository.findById(id);
		if (_evento.isPresent()) {
			Evento eventoAtualizado = _evento.get();
			if (file != null && file.getSize() > 0) {
				try {
					eventoAtualizado.setFotoEvento(file.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return eventoRepository.save(eventoAtualizado);
		}
		return null;
	}
 
 
	public Evento create(MultipartFile file, Evento evento, String email) {
 
		if (file != null && file.getSize() > 0) {
			try {
				evento.setFotoEvento(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			evento.setFotoEvento(null);
		}
 
		Usuario usuario = usuarioRepository.findByEmail(email);
		evento.setOng(usuario);
		evento.setStatusEvento("ATIVO");
		
		return eventoRepository.save(evento);
	}
	
	@Transactional
	public Evento inativar(long id) {
		Optional<Evento> _evento = eventoRepository.findById(id);
		if (_evento.isPresent()) {
			Evento eventoAtualizado = _evento.get();
			eventoAtualizado.setStatusEvento("INATIVO");
			
			return eventoRepository.save(eventoAtualizado);
		}
		return null;
	}
	@Transactional
	public Evento alterarInfo(long id,Evento evento) {
		Optional<Evento> _evento = eventoRepository.findById(id);
		if (_evento.isPresent()) {
			Evento eventoAtualizado = _evento.get();
			
			String infos = evento.getInfos();
			
			eventoAtualizado.setNome(evento.getNome());
			
			String cep = evento.getCep();
			eventoAtualizado.setCep(cep);
			
			int vagas = evento.getVagas();
			eventoAtualizado.setVagas(vagas);
			
			LocalDate dataEvento = evento.getDataEvento();
			eventoAtualizado.setDataEvento(dataEvento);		
			
			long numero = evento.getNumero();
			eventoAtualizado.setNumero(numero);
			
			eventoAtualizado.setHoraInicio(evento.getHoraInicio());
			eventoAtualizado.setInfos(infos);
			
			return eventoRepository.save(eventoAtualizado);
		}
		return null;
	}
}
