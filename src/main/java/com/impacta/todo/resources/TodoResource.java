package com.impacta.todo.resources;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.impacta.todo.domain.Todo;
import com.impacta.todo.services.TodoService;

@RestController
@RequestMapping(value =  "/todos")
public class TodoResource {
	
	@Autowired
	private TodoService service;
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<List<Todo>> delete(@PathVariable Integer id){
		Boolean result = service.delete(id);
		if(result)
			return listAll();
		else 
			return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "")
	public ResponseEntity<Todo> save(@RequestBody Todo todo){
		try {
			todo.setId(null);
			String dataParse = todo.getDataParaFinalizarStr();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			LocalDateTime dateTime = LocalDateTime.parse(dataParse, formatter);
			todo.setDataParaFinalizar(dateTime);
			todo = service.save(todo);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todo.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping(value="")
	public ResponseEntity<List<Todo>> listAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Todo> findById(@PathVariable Integer id){
		Todo t1 = service.findById(id);
		if(t1 == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(t1);
		}
	}
	
	@GetMapping(value="/open")
	public ResponseEntity<List<Todo>> findOpen(){
		List<Todo> t1 = service.findFinalizado(false);
		if(t1 == null || t1.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(t1);
		}
	}
	
	@GetMapping(value="/close")
	public ResponseEntity<List<Todo>> findClose(){
		List<Todo> t1 = service.findFinalizado(true);
		if(t1 == null || t1.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(t1);
		}
	}
	
	@GetMapping(value="/open2")
	public ResponseEntity<List<Todo>> findOpen2(){
		List<Todo> t1 = service.findOpen2();
		if(t1 == null || t1.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(t1);
		}
	}
}
