package com.mokcoding.mysite.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mokcoding.mysite.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	@Query("SELECT b FROM Board b WHERE b.boardTitle LIKE %:keyword%")
	Page<Board> findByBoardTitleContaining(String keyword, Pageable paegPageable);
	
	@Query("SELECT b FROM Board b WHERE b.boardContent LIKE %:keyword%")
	Page<Board> findByBoardContentContaining(String keyword, Pageable paegPageable);
	
	@Query("SELECT b FROM Board b WHERE b.memberId LIKE %:keyword%")
	Page<Board> findByMemberIdContaining(String keyword, Pageable paegPageable);
	
}

