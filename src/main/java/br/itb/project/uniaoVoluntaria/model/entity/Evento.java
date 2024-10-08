package br.itb.project.uniaoVoluntaria.model.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Evento")
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataEvento;
	private int vagas;
	private byte[] fotoEvento;
	private String horaInicio;
	private String infos;
	private String cep;
	private long numero;
	private String statusEvento;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario ong;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getVagas() {
		return vagas;
	}
	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
	
	public byte[] getFotoEvento() {
		return fotoEvento;
	}
	public void setFotoEvento(byte[] fotoEvento) {
		this.fotoEvento = fotoEvento;
	}
	
	public LocalDate getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(LocalDate dataEvento) {
		this.dataEvento = dataEvento;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getInfos() {
		return infos;
	}
	public void setInfos(String infos) {
		this.infos = infos;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public String getStatusEvento() {
		return statusEvento;
	}
	public void setStatusEvento(String statusEvento) {
		this.statusEvento = statusEvento;
	}
	public Usuario getOng() {
		return ong;
	}
	public void setOng(Usuario ong) {
		this.ong = ong;
	}
	
	
	
	
}
