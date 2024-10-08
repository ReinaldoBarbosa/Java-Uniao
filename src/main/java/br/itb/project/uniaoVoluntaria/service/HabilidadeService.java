package br.itb.project.uniaoVoluntaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.itb.project.uniaoVoluntaria.model.entity.Habilidade;
import br.itb.project.uniaoVoluntaria.model.repository.HabilidadeRepository;
import jakarta.transaction.Transactional;

@Service
public class HabilidadeService {
	HabilidadeRepository habilidadeRepository;

	public HabilidadeService(HabilidadeRepository habilidadeRepository) {
		super();
		this.habilidadeRepository = habilidadeRepository;
	}
	public List<Habilidade> findAll() {
		List<Habilidade> habilidades = habilidadeRepository.findAll();
		
		return habilidades;
	}
	
	public Habilidade findById(long id) {
    	Habilidade habilidade = habilidadeRepository.findById(id).orElseThrow();
    	
    	return habilidade;
    }
	
	@Transactional
	public Habilidade create(Habilidade habilidade) {
		
		habilidade.setStatusHabilidade("ATIVO");

		return habilidadeRepository.save(habilidade);
	}
	
	@Transactional
	public Habilidade inativar(long id) {
		Optional<Habilidade> _habilidade = habilidadeRepository.findById(id);
		if (_habilidade.isPresent()) {
			Habilidade habilidadeAtualizada = _habilidade.get();
			habilidadeAtualizada.setStatusHabilidade("INATIVO");
			
			return habilidadeRepository.save(habilidadeAtualizada);
		}
		return null;
	}
	
	@Transactional
	public Habilidade alterarInformacoes(long id,Habilidade habilidade) {
		Optional<Habilidade> _habilidade = habilidadeRepository.findById(id);
		if (_habilidade.isPresent()) {
			Habilidade habilidadeAtualizada = _habilidade.get();
			
			String nome = habilidade.getNome();
			habilidadeAtualizada.setNome(nome);
			
			String descricao = habilidade.getDescricao();
			habilidadeAtualizada.setDescricao(descricao);
			
			return habilidadeRepository.save(habilidadeAtualizada);
		}
		return null;
	}

}
