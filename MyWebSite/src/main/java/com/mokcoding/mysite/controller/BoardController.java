package com.mokcoding.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mokcoding.mysite.domain.BoardDTO;
import com.mokcoding.mysite.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Controller // @Component
//* 표현 계층(Presentation Layer)
//- 클라이언트(JSP 페이지 등)와 service를 연결하는 역할
@RequestMapping(value = "/board") 
@Log4j2
public class BoardController {

	  @Autowired
	   private BoardService boardService;
	   
	   @GetMapping("/register")
	   public void registerGET(@ModelAttribute("boardDTO") BoardDTO boardDTO) {
	      log.info("registerGET()");
	   } // end registerGET()

	   // 게시글 데이터를 form-data를 전송받아 BoardService로 전송
	   @PostMapping("/register")
	   public String registerPOST(BoardDTO boardDTO, RedirectAttributes reAttr) {
	      log.info("registerPOST()");
	      log.info(boardDTO.toString());

	      int result = boardService.createBoard(boardDTO);
	      log.info(result + "행 등록");
	      return "redirect:/board/list";
	   } // end registerPOST()

	
	
}
