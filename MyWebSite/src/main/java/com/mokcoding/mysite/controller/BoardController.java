package com.mokcoding.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mokcoding.mysite.domain.BoardDTO;
import com.mokcoding.mysite.domain.Paginator;
import com.mokcoding.mysite.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Controller // @Component
// * 표현 계층(Presentation Layer)
// - 클라이언트(JSP 페이지 등)와 service를 연결하는 역할
@RequestMapping(value = "/board") 
@Log4j2
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 페이지 번호 및 페이지 사이즈를 전송받아 게시글 페이징 처리
	// list.jsp 페이지로 페이징 처리된 게시글 데이터 및 페이지 번호 구성 데이터 전송
	@GetMapping("/list")
	public void list(Model model, @ModelAttribute("paginator")Paginator paginator) { // pagination을 클라이언트에서 전송
		log.info("list()");
		Page<BoardDTO> boardList = boardService.getPagingBoards(paginator);
		model.addAttribute("boardList", boardList);

	} // end list()

	@GetMapping("/register")
	public void registerGET(@ModelAttribute BoardDTO boardDTO) {
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

	@GetMapping("/detail")
	public void detail(Model model, Integer boardId, @ModelAttribute("paginator") Paginator paginator) {
		log.info("detail()");
		log.info("boardId = " + boardId);
		log.info("paginator = " + paginator);
		BoardDTO boardDTO = boardService.getBoardById(boardId);

		model.addAttribute("boardDTO", boardDTO);
	} // end detail()

	// 선택된 게시글을 modify.jsp로 전송
	@GetMapping("/modify")
	public void modifyGET(Model model, Integer boardId, @ModelAttribute("paginator") Paginator paginator) {
		log.info("modifyGET()");
		log.info("boardId = " + boardId);
		log.info("paginator = " + paginator);
		BoardDTO boardDTO = boardService.getBoardById(boardId);
		model.addAttribute("boardDTO", boardDTO);
	} // end updateGET()

	// modify.jsp에서 수정할 데이터를 전송받아 게시글 데이터 수정
	// @PreAuthorize("isAuthenticated() and (authentication.name == #boardDTO.memberId)")
	@PostMapping("/modify")
	public String modifyPOST(BoardDTO boardDTO, Paginator paginator, RedirectAttributes reAttr) {
		log.info("modifyPOST()");
		log.info("boardDTO = " + boardDTO.toString());
		int result = boardService.updateBoard(boardDTO);
		log.info(result + "행 수정");
		
		// redirect에서 값을 전송하기 위한 방법
		reAttr.addAttribute("number", paginator.getNumber());
		reAttr.addAttribute("size", paginator.getSize());
		reAttr.addAttribute("type", paginator.getType());
		reAttr.addAttribute("keyword", paginator.getKeyword());
		
		return "redirect:/board/list";
	} // end updatePOST()

	// detail.jsp에서 boardId를 전송받아 게시글 데이터 삭제
	// @PreAuthorize("isAuthenticated() and (authentication.name == #memberId)")
	@PostMapping("/delete")
	public String delete(Integer boardId, Paginator paginator, String memberId, RedirectAttributes reAttr) {
		log.info("delete() 호출 : boardId = " + boardId);
		int result = boardService.deleteBoard(boardId);
		log.info(result + "행 삭제");
		
		// redirect에서 값을 전송하기 위한 방법
		reAttr.addAttribute("number", paginator.getNumber());
		reAttr.addAttribute("size", paginator.getSize());
		reAttr.addAttribute("type", paginator.getType());
		reAttr.addAttribute("keyword", paginator.getKeyword());
		
		return "redirect:/board/list";
	} // end delete()

} // end BoardController
