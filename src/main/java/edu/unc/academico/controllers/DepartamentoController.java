package edu.unc.academico.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.academico.domain.Departamento;
import edu.unc.academico.services.DepartamentoService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/v1/departamentos")
public class DepartamentoController {

	 @Autowired
	 private DepartamentoService dptoService;
	 
	 @GetMapping
	 public List<Departamento> listarDptos(){
		 return dptoService.listarDptos();
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<?> listarPorIdDpto(@PathVariable Long id) {
		 Optional<Departamento> dptoOptional = dptoService.buscarPorIdDpto(id);
		 if (dptoOptional.isPresent()) {
			 return ResponseEntity.ok(dptoOptional.get());
		 }
		 return ResponseEntity.notFound().build(); 
	 }
	 
	 @PostMapping
	 public ResponseEntity<?> crearDpto(@RequestBody Departamento dpto){
			return ResponseEntity.status(HttpStatus.CREATED).body(dptoService.grabarDpto(dpto));
		}
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<?> editarDpto(@PathVariable Long id, @RequestBody Departamento dpto) {
		 Optional<Departamento> o = dptoService.buscarPorIdDpto(id);
			if (o.isPresent()) {
				Departamento dptoDB = o.get();
				dptoDB.setNombreDpto(dpto.getNombreDpto());
				return ResponseEntity.status(HttpStatus.CREATED).body(dptoService.grabarDpto(dptoDB));
			}
			return ResponseEntity.notFound().build();
	 }
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<?> eliminarDpto(@PathVariable Long id){
		 Optional<Departamento> o = dptoService.buscarPorIdDpto(id);
			if (o.isPresent()) {
				dptoService.eliminarDpto(id);
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
	}
}
