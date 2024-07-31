package com.mokcoding.mysite.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokcoding.mysite.domain.Board;
import com.mokcoding.mysite.domain.Reply;
import com.mokcoding.mysite.domain.ReplyDTO;
import com.mokcoding.mysite.repository.BoardRepository;
import com.mokcoding.mysite.repository.ReplyRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ReplyServiceImple implements ReplyService{
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	@Transactional(value = "transactionManager") // transactionManager가 관리
	@Override
	public int createReply(ReplyDTO replyDTO){
		log.info("createReply()");
		log.info("replyDTO = " + replyDTO);
		Reply reply = replyRepository.save(toEntity(replyDTO));
		log.info(reply);
		
		Board board = boardRepository.findById(replyDTO.getBoardId()).get();
		board.setReplyCount(board.getReplyCount() + 1);
		
		return 1;
	}

	@Override
	public List<ReplyDTO> getAllReply(int boardId) {
		log.info("getAllReply()");
		log.info("boardId = " + boardId);
		List<Reply> list = replyRepository.findByBoardId(boardId);
		
		
		return list.stream().map(this::toDTO).collect(Collectors.toList());
	}


	@Override
	public int updateReply(int replyId, String replyContent) {
		log.info("updateReply()");
		log.info("replyId = " + replyId);
		log.info("replyContent = " + replyContent);
		
		Optional<Reply> replyOptional = replyRepository.findById(replyId);
		Reply reply = replyOptional.get();
		reply.setReplyContent(replyContent);
		reply.setReplyDateCreated(LocalDateTime.now());
		replyRepository.save(reply);
		
		return 1;
	}

	@Transactional(value = "transactionManager")
	@Override
	public int deleteReply(int replyId, int boardId) {
		log.info("deleteReply()");
		log.info("replyId = " + replyId);
		replyRepository.deleteById(replyId);
		
		Board board = boardRepository.findById(boardId).get();
		board.setReplyCount(board.getReplyCount() - 1);
		return 1;
	}
	
	public ReplyDTO toDTO(Reply reply) {
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setBoardId(reply.getBoardId());
		replyDTO.setReplyId(reply.getReplyId());
		replyDTO.setMemberId(reply.getMemberId());
		replyDTO.setReplyContent(reply.getReplyContent());
		replyDTO.setReplyDateCreated(reply.getReplyDateCreated());
		return replyDTO;
	} // end toDTO()
	
	public Reply toEntity(ReplyDTO replyDTO) {
		Reply reply = new Reply();
		reply.setBoardId(replyDTO.getBoardId());
		reply.setReplyId(replyDTO.getReplyId());
		reply.setMemberId(replyDTO.getMemberId());
		reply.setReplyContent(replyDTO.getReplyContent());
		reply.setReplyDateCreated(replyDTO.getReplyDateCreated());
		return reply;
	} // end toEntity()

} // end ReplyServiceImple
