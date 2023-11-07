package com.example.pfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.pfinal.dao.Operaciones;
import com.example.pfinal.entity.Categoria;
import com.example.pfinal.repository.CategoriaRepository;

@Service
public class CategoriaService implements Operaciones<Categoria>{
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Override
	public Categoria create(Categoria t) {
		// TODO Auto-generated method stub
		return categoriaRepository.save(t);
	}

	@Override
	public Categoria update(Categoria t) {
		// TODO Auto-generated method stub
		return categoriaRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		categoriaRepository.deleteById(id);
		
	}

	@Override
	public Optional<Categoria> read(Long id) {
		// TODO Auto-generated method stub
		return categoriaRepository.findById(id);
	}

	@Override
	public List<Categoria> readAll() {
		// TODO Auto-generated method stub
		return categoriaRepository.findAll();
	}

}
