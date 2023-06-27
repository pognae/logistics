package com.logistics.md.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.logistics.configuration.util.Criteria;
import com.logistics.md.domain.MboardVO;
import com.logistics.md.service.MDB1Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDB1Controller {

	private final MDB1Service mdb1Service;
	
	@GetMapping("/md/boardSelect.do")
	public ModelAndView getBoardList(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
  		PageInfo<MboardVO> list = new PageInfo<>(mdb1Service.getBoardList(param , userInfo), 15);
		Criteria cri = new Criteria((int)list.getTotal() , (int)list.getNavigateFirstPage(), (int)list.getPageSize() , "", null);
		mv.addObject("boardList" , list);
		mv.addObject("page" , cri.getPageData());
		return mv;
	}
	
	@PostMapping("/md/mdb1Save.do")
	public ModelAndView saveBoardWrite(@RequestBody Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("insertResult" ,mdb1Service.saveBoard(param , userInfo));
		return mv;
	}
	

	@GetMapping("/md/boardDetail.do")
	public ModelAndView getBoardDetail(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo , HttpSession ses){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("dataList", mdb1Service.getBoardDetail(param, userInfo));
		mv.addObject("pageMaker", "");
		return mv;
	}
	
	@PostMapping("/md/updateBoardDetail.do")
	public ModelAndView updateBoardDetail(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("updateResult", mdb1Service.saveBoardDetail(param, userInfo));
		return mv;
	}
	
	@PostMapping("/md/setRefcont.do")
	public ModelAndView addRefcont(@RequestBody Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo)throws JsonProcessingException{
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("result", mdb1Service.insertMbodrf(param ,userInfo));
		return mv;
	}
	
	@PostMapping("/md/boardDelete.do")
	public ModelAndView deleteBoardDetail(@RequestBody Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv= new ModelAndView("jsonView");
		mv.addObject("dataList", mdb1Service.deleteBoardDetail(param, userInfo));
		return mv;
	}
	
	
	@PostMapping("/md/refcontDelete.do")
	public ModelAndView deleteBodrfcont(@RequestBody Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mdb1Service.deleteBodrfcont(param , userInfo);
		return mv;
	}
}
