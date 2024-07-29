package com.mokcoding.mysite;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mokcoding.mysite.domain.AttachDTO;
import com.mokcoding.mysite.domain.BoardDTO;
import com.mokcoding.mysite.service.BoardService;
import com.mokcoding.mysite.service.BoardServiceImple;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class MyWebSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyWebSiteApplication.class, args);
//		BoardService boardService = 
//		SpringApplication.run(MyWebSiteApplication.class, args)
//		.getBean(BoardServiceImple.class);
//		
//		BoardDTO boardDTO = new BoardDTO();
//		List<AttachDTO> attachList = new ArrayList<>();
//		boardDTO.setBoardTitle("title");
//		boardDTO.setBoardContent("content");
//		boardDTO.setMemberId("user");
//		
//		AttachDTO attachDTO = new AttachDTO();
//		attachDTO.setAttachRealName("realname");
//		attachDTO.setAttachPath("24/07/25");
//		attachDTO.setAttachChgName(UUID.randomUUID().toString());
//		attachDTO.setAttachExtension(".txt");
//		attachList.add(attachDTO);
//		
//		boardDTO.setAttachList(attachList);
//		
//		int result = boardService.createBoard(boardDTO);
//		log.info(result);
		
	}

}
