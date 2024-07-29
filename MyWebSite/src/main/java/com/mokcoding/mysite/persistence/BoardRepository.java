package com.mokcoding.mysite.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mokcoding.mysite.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

	
}
