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
import com.example.pfinal.entity.Producto;
import com.example.pfinal.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	@GetMapping
	public ResponseEntity<List<Producto>> listar() {
		try {
		      List<Producto> car = productoService.readAll();
		      if (car.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(car, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping
    public ResponseEntity<Producto> crear(@Valid @RequestBody Producto cat){
        try {
        	Producto _aut = productoService.create(cat);
            return new ResponseEntity<Producto>(_aut, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable("id") Long id){
		Optional<Producto> carData = productoService.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Producto>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Producto> delete(@PathVariable("id") Long id){
		try {
			productoService.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProducto(@PathVariable("id") Long id, @Valid @RequestBody Producto producto){
		Optional<Producto> carData = productoService.read(id);
	      if (carData.isPresent()) {
	    	  Producto dbpro = carData.get();
	    	  dbpro.setId(producto.getId());
	    	  dbpro.setNombre(producto.getNombre());
	    	  dbpro.setPrecio(producto.getPrecio());
	    	  dbpro.setCantidad(producto.getCantidad());
	    	  dbpro.setEstado(producto.getEstado());
	             
	        return new ResponseEntity<Producto>(productoService.update(dbpro), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
}


}
