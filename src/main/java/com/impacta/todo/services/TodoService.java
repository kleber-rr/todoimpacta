package com.impacta.todo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impacta.todo.domain.Todo;
import com.impacta.todo.repositories.TodoRepository;
import com.impacta.todo.services.exception.ObjectNotFoundException;


@Service
public class TodoService {
	
	@Autowired
	private TodoRepository repo;
	
	public Todo findById(Integer id) {
		Optional<Todo> t1 = repo.findById(id);
		return t1.orElseThrow(()-> new ObjectNotFoundException("Objeto nao encontrado!! Id: " + id));
	}
	
	public List<Todo> findFinalizado(Boolean finalizado) {
		List<Todo> lista = repo.findByFinalizadoOrderByDataParaFinalizarDesc(finalizado);
		return lista;
	}
	
	public List<Todo> findOpen2() {
		List<Todo> lista = repo.findAllOpen(false);
		return lista;
	}
	
	public List<Todo> findAll() {
		List<Todo> lista = repo.findAll();
		return lista;
	}

	public Todo save(Todo todo) {
		todo = repo.save(todo);
		return todo;
	}

	public Boolean delete(Integer id) {
		Todo obj = findById(id);
		repo.delete(obj);
		return true;
		
	}
}
