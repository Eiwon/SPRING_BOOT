package com.mokcoding.mysite.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokcoding.mysite.domain.Attach;
import com.mokcoding.mysite.domain.AttachDTO;
import com.mokcoding.mysite.domain.Board;
import com.mokcoding.mysite.domain.BoardDTO;
import com.mokcoding.mysite.domain.Paginator;
import com.mokcoding.mysite.repository.AttachRepository;
import com.mokcoding.mysite.repository.BoardRepository;

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
		log.info("boardDTO = " + boardDTO);
		
		Board board = boardRepository.save(toEntity(boardDTO));
		log.info(board);
	
		List<AttachDTO> attachList = boardDTO.getAttachList();


		for(AttachDTO attachDTO : attachList) {
			attachDTO.setBoardId(board.getBoardId());
			Attach attach = attachRepository.save(toEntity(attachDTO));
			log.info(attach);
		}
		
		return 1;
	} // end createBoard()



	@Override
	public List<BoardDTO> getAllBoards() {
		log.info("getAllBoards()");
		List<Board> list = boardRepository.findAll();
		
		return list.stream().map(this::toDTO).collect(Collectors.toList());
	} // end getAllBoards()
  

	@Override
	public BoardDTO getBoardById(int boardId) {
		log.info("getBoardById()");
		log.info("boardId = " + boardId);
		
		Optional<Board> board = boardRepository.findById(boardId);
		List<Attach> list = attachRepository.findByBoardId(boardId);
		BoardDTO boardDTO = toDTO(board.get());
		
		List<AttachDTO> attachList = list.stream().map(this::toDTO).collect(Collectors.toList());
		
		boardDTO.setAttachList(attachList);
		return boardDTO;
	} // end getBoardById()


	@Transactional(value = "transactionManager") 
	@Override
	public int updateBoard(BoardDTO boardDTO) {
		log.info("updateBoard()");
		log.info("boardDTO = " + boardDTO);
		
		Optional<Board> boardOption = boardRepository.findById(boardDTO.getBoardId());
		Board board = boardOption.get();
		board.setBoardTitle(boardDTO.getBoardTitle());
		board.setBoardContent(boardDTO.getBoardContent());
		board.setBoardDateCreated(LocalDateTime.now());
		boardRepository.save(board);

		attachRepository.deleteByBoardId(boardDTO.getBoardId());

		List<AttachDTO> attachList = boardDTO.getAttachList();
		
		for(AttachDTO attachDTO : attachList) {
			attachDTO.setBoardId(boardDTO.getBoardId()); // 게시글 번호 적용
			Attach attach = attachRepository.save(toEntity(attachDTO));
			log.info(attach);
		}
		
		return 1;
	} // end updateBoard()


	@Transactional(value = "transactionManager") 
	@Override
	public int deleteBoard(int boardId) {
		log.info("deleteBoard()");
		log.info("boardId = " + boardId);
		
		boardRepository.deleteById(boardId);
		attachRepository.deleteById(boardId);

		return 1;
	} // end deleteBoard()
	
	
	@Override
	public Page<BoardDTO> getPagingBoards(Paginator paginator) {
		log.info("getPagingBoards()");
	      Pageable pageable = PageRequest.of(paginator.getNumber(), paginator.getSize(), Sort.by(Sort.Direction.DESC, "boardId"));
	      
	      Page<Board> list = null;
	      String type = paginator.getType();
	      String keyword = paginator.getKeyword();
	      if(type == null || type.equals("")) {
	         list = boardRepository.findAll(pageable);         
	      } else if(type.equals("title")) {
	         list = boardRepository.findByBoardTitleContaining(keyword, pageable);
	      } else if(type.equals("content")) {
	         list = boardRepository.findByBoardContentContaining(keyword, pageable);
	      } else if(type.equals("writer")) {
	         list = boardRepository.findByMemberIdContaining(keyword, pageable);
	      }

	      log.info(list.getContent());


		return list.map(this::toDTO);
	} // end getPagingBoards()
	
	
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
	
} // end BoardServiceImple
