package edu.unc.academico.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
//import lombok.Data;

@Entity
//@Data
public class Departamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDpto;
	@Column(unique = true)
	private String nombreDpto;
	@OneToMany(mappedBy = "departamento")//Igual a como se escribió en la clase Investigador: private Departamento departamento;
	@JsonManagedReference //Para evitar el bucle cuando se haga referencias con la relación que tiene con Investigador
	private List<Investigador> investigadores = new ArrayList<>();
	
	public Long getIdDpto() {
		return idDpto;
	}
	public void setIdDpto(Long idDpto) {
		this.idDpto = idDpto;
	}
	public String getNombreDpto() {
		return nombreDpto;
	}
	public void setNombreDpto(String nombreDpto) {
		this.nombreDpto = nombreDpto;
	}
	public List<Investigador> getInvestigadores() {
		return investigadores;
	}
	public void setInvestigadores(List<Investigador> investigadores) {
		this.investigadores = investigadores;
	}
}