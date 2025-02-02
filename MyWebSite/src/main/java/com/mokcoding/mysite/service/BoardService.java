package com.mokcoding.mysite.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mokcoding.mysite.domain.BoardDTO;
import com.mokcoding.mysite.domain.Paginator;

public interface BoardService {

	int createBoard(BoardDTO boardDTO); // 게시글 등록

	List<BoardDTO> getAllBoards(); // 전체 게시글 조회

	BoardDTO getBoardById(int boardId); // 특정 게시글 조회

	int updateBoard(BoardDTO boardDTO); // 특정 게시글 수정

	int deleteBoard(int boardId); // 특정 게시글 삭제
	
	Page<BoardDTO> getPagingBoards(Paginator paginator); // 게시글 페이징 처리 조회
	
} // end BoardService
