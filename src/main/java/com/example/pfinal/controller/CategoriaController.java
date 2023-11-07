package com.example.pfinal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pfinal.entity.Categoria;
import com.example.pfinal.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		try {
		      List<Categoria> car = categoriaService.readAll();
		      if (car.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(car, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping
    public ResponseEntity<Categoria> crear(@Valid @RequestBody Categoria cat){
        try {
            Categoria _aut = categoriaService.create(cat);
            return new ResponseEntity<Categoria>(_aut, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable("id") Long id){
		Optional<Categoria> carData = categoriaService.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Categoria>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Categoria> delete(@PathVariable("id") Long id){
		try {
			categoriaService.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategoria(@PathVariable("id") Long id, @Valid @RequestBody Categoria categoria){
		Optional<Categoria> carData = categoriaService.read(id);
	      if (carData.isPresent()) {
	        Categoria dbcat = carData.get();
	        dbcat.setNombre(categoria.getNombre());
	        dbcat.setId(categoria.getId());
	        return new ResponseEntity<Categoria>(categoriaService.update(dbcat), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
}


}
