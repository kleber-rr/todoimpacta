package com.impacta.todo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.impacta.todo.domain.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
	
	List<Todo> findByFinalizadoOrderByDataParaFinalizarDesc(Boolean finalizado);
	
	@Query("SELECT obj FROM Todo obj WHERE obj.finalizado = :finalizado ORDER BY obj.dataParaFinalizar")
	List<Todo> findAllOpen(Boolean finalizado);

}
