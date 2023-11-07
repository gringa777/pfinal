package com.example.pfinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pfinal.dao.Operaciones;
import com.example.pfinal.entity.Producto;
import com.example.pfinal.repository.ProductoRepository;

@Service
public class ProductoService implements Operaciones<Producto>{
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Producto create(Producto t) {
		// TODO Auto-generated method stub
		return productoRepository.save(t);
	}

	@Override
	public Producto update(Producto t) {
		// TODO Auto-generated method stub
		return productoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		productoRepository.deleteById(id);
		
	}

	@Override
	public Optional<Producto> read(Long id) {
		// TODO Auto-generated method stub
		return productoRepository.findById(id);
	}

	@Override
	public List<Producto> readAll() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

}
