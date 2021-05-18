package com.impacta.todo.services;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impacta.todo.domain.Todo;
import com.impacta.todo.repositories.TodoRepository;

@Service
public class DBService {
	@Autowired
	private TodoRepository repo;
	
	public void popular() {
		Todo t1 = new Todo(null, "Teste", "descr teste", LocalDateTime.now().plusHours(3L), false);
		Todo t2 = new Todo(null, "Teste2", "descr2 teste", LocalDateTime.now().plusHours(4L), true);
		Todo t3 = new Todo(null, "Teste3", "descr3 teste", LocalDateTime.now().plusHours(5L), true);
		Todo t4 = new Todo(null, "Teste4", "descr4 teste", LocalDateTime.now().plusHours(6L), false);
		repo.saveAll(Arrays.asList(t1,t2,t3,t4));
	}
}
