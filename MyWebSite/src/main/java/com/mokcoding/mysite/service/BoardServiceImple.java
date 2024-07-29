package com.mokcoding.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokcoding.mysite.domain.Attach;
import com.mokcoding.mysite.domain.AttachDTO;
import com.mokcoding.mysite.domain.Board;
import com.mokcoding.mysite.domain.BoardDTO;
import com.mokcoding.mysite.persistence.AttachRepository;
import com.mokcoding.mysite.persistence.BoardRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BoardServiceImple implements BoardService{

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private AttachRepository attachRepository;
	
	@Transactional(value = "transactionManager")
	@Override
	public int createBoard(BoardDTO boardDTO) {
		log.info("createBoard()");
		
		Board board = boardRepository.save(toEntity(boardDTO));
		log.info(board);
		
		for(AttachDTO attachDTO : boardDTO.getAttachList()) {
			attachDTO.setBoardId(board.getBoardId());
			Attach attach = attachRepository.save(toEntity(attachDTO));
			log.info(attach);
		}
		
		return 1;
	}

	@Override
	public List<BoardDTO> getAllBoards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO getBoardById(int boardId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateBoard(BoardDTO boardDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(int boardId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	 // Board 데이터를 BoardDTO에 적용하는 메서드
	   public BoardDTO toDTO(Board board) {
	      BoardDTO boardDTO = new BoardDTO();
	      boardDTO.setBoardId(board.getBoardId());
	      boardDTO.setBoardTitle(board.getBoardTitle());
	      boardDTO.setBoardContent(board.getBoardContent());
	      boardDTO.setMemberId(board.getMemberId());
	      boardDTO.setBoardDateCreated(board.getBoardDateCreated());
	      boardDTO.setReplyCount(board.getReplyCount());
	      return boardDTO;
	   } // end toDTO()
	   
	   // BoardDTO 데이터를 Board에 적용하는 메서드
	   public Board toEntity(BoardDTO boardDTO) {
	      Board board = new Board();
	      board.setBoardId(boardDTO.getBoardId());
	      board.setBoardTitle(boardDTO.getBoardTitle());
	      board.setBoardContent(boardDTO.getBoardContent());
	      board.setMemberId(boardDTO.getMemberId());
	      board.setReplyCount(boardDTO.getReplyCount());
	      return board;
	   } // end toEntity()
	   
	    // AttachDTO를 Attach로 변환하는 메서드
	    private Attach toEntity(AttachDTO attachDTO) {
	        Attach attach = new Attach();
	        attach.setAttachId(attachDTO.getAttachId());
	        attach.setBoardId(attachDTO.getBoardId());
	        attach.setAttachPath(attachDTO.getAttachPath());
	        attach.setAttachRealName(attachDTO.getAttachRealName());
	        attach.setAttachChgName(attachDTO.getAttachChgName());
	        attach.setAttachExtension(attachDTO.getAttachExtension());
	        attach.setAttachDateCreated(attachDTO.getAttachDateCreated());
	        return attach;
	    }
	    
	    // Attach를 AttachDTO로 변환하는 메서드
	    private AttachDTO toDTO(Attach attach) {
	       AttachDTO attachDTO = new AttachDTO();
	        attachDTO.setAttachId(attach.getAttachId());
	        attachDTO.setBoardId(attach.getBoardId());
	        attachDTO.setAttachPath(attach.getAttachPath());
	        attachDTO.setAttachRealName(attach.getAttachRealName());
	        attachDTO.setAttachChgName(attach.getAttachChgName());
	        attachDTO.setAttachExtension(attach.getAttachExtension());
	        attachDTO.setAttachDateCreated(attach.getAttachDateCreated());
	        return attachDTO;
	    }
	
}
