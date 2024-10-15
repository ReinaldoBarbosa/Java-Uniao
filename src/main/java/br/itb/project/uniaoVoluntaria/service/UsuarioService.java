package br.itb.project.uniaoVoluntaria.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.itb.project.uniaoVoluntaria.model.entity.Evento;
import br.itb.project.uniaoVoluntaria.model.entity.Usuario;
import br.itb.project.uniaoVoluntaria.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	private UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		// generate constructors using fields
		super();
		this.usuarioRepository = usuarioRepository;
	}

	public List<Usuario> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();

		return usuarios;
	}

	public Usuario findById(long id) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow();

		return usuario;
	}

	public Usuario findByEmail(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);

		return usuario;
	}

	@Transactional
	public Usuario create(Usuario usuario) {

		String senha = Base64.getEncoder().encodeToString(usuario.getSenha().getBytes());

		usuario.setSenha(senha);
		usuario.setDataCadastro(LocalDateTime.now());
		usuario.setStatusUsuario("ATIVO");

		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public Usuario createImage(MultipartFile file,Usuario usuario) {
		
		if (file != null && file.getSize() > 0) {
			try {
				usuario.setFotoPerfil(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			usuario.setFotoPerfil(null);
		}
		
		return usuarioRepository.save(usuario);
	}

	@Transactional
	public Usuario update(long id, Usuario usuario) {
		// Verifica se o usuário existe no banco de dados
		Optional<Usuario> _usuario = usuarioRepository.findById(id);

		System.out.println("Aqui " + usuario.getNome());
		if (_usuario.isPresent()) {
			Usuario usuarioAtualizado = _usuario.get();

			usuarioAtualizado.setNome(usuario.getNome());

			usuarioAtualizado.setTelefone(usuario.getTelefone());

			//String senha = Base64.getEncoder().encodeToString(usuario.getSenha().getBytes());
			//usuarioAtualizado.setSenha(senha);

			// Atualiza o usuário no banco de dados
			return usuarioRepository.save(usuarioAtualizado);
		}

		// Se o usuário não for encontrado, retorna null ou lança uma exceção
		return null;
	}
	
	@Transactional
	public Usuario updateComImagem(long id,MultipartFile file, Usuario usuario) {
		Optional<Usuario> _usuario = usuarioRepository.findById(id);

		if (_usuario.isPresent()) {
			Usuario usuarioAtualizado = _usuario.get();

			usuarioAtualizado.setNome(usuario.getNome());

			usuarioAtualizado.setTelefone(usuario.getTelefone());
	        
	        // Atualiza a foto de perfil se o arquivo for enviado
	        if (file != null && !file.isEmpty()) {
	            try {
	                usuarioAtualizado.setFotoPerfil(file.getBytes());
	            } catch (IOException e) {
	                e.printStackTrace();
	                throw new RuntimeException("Erro ao salvar a imagem", e);
	            }
	        }

	        
	        return usuarioRepository.save(usuarioAtualizado);
	    }
		return null; 
	}


	@Transactional
	public Usuario signin(String email, String senha) {
		Usuario usuario = usuarioRepository.findByEmail(email);

		if (usuario.getStatusUsuario().equals("ATIVO")) {
			byte[] decodePass = Base64.getDecoder().decode(usuario.getSenha());
			if (new String(decodePass).equals(senha)) {
				return usuario;
			}
		}
		return null;
	}

	@Transactional
	public Usuario inativar(long id) {
		Optional<Usuario> _usuario = usuarioRepository.findById(id);
		if (_usuario.isPresent()) {
			Usuario usuarioAtualizado = _usuario.get();
			usuarioAtualizado.setStatusUsuario("INATIVO");

			return usuarioRepository.save(usuarioAtualizado);
		}
		return null;
	}

	@Transactional
	public Usuario alterarSenha(long id, Usuario usuario) {
		Optional<Usuario> _usuario = usuarioRepository.findById(id);
		if (_usuario.isPresent()) {
			Usuario usuarioAtualizado = _usuario.get();
			String senha = Base64.getEncoder().encodeToString(usuario.getSenha().getBytes());
			usuarioAtualizado.setSenha(senha);

			return usuarioRepository.save(usuarioAtualizado);
		}
		return null;
	}
}
