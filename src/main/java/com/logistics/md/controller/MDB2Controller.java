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

import com.github.pagehelper.PageInfo;
import com.logistics.configuration.util.Criteria;
import com.logistics.md.domain.McommtVO;
import com.logistics.md.service.MDB2Service;
import com.logistics.md.service.MDC7Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDB2Controller {

	private final MDB2Service mdb2Service;
	
	private final MDC7Service mdc7Service;
	
	@GetMapping("/md/mdb2SelectBox.do")
	public ModelAndView getMdb2SelectBoxList(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("item", mdc7Service.getMcodemSelect(param, userInfo)); 
		return mv;
	}
	
	@GetMapping("/md/mdb2TABSelect.do")
	public ModelAndView getMdb2QnAList(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo , HttpSession ses) {
		ModelAndView mv = new ModelAndView("jsonView");
		PageInfo<McommtVO> list = new PageInfo<>(mdb2Service.getMdb2List(param,userInfo) , 6);
		Criteria cri = new Criteria((int)list.getTotal() , (int)list.getNavigateFirstPage(), (int)list.getPageSize() , "", null);
		mv.addObject("userType", userInfo.getUsertyp());
		mv.addObject("page", cri.getPageData());
		mv.addObject("boardList", list);
		return mv;
	}
	
	@GetMapping("/md/mdb2DetailList.do")
	public ModelAndView getMdb2DetailList(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo , HttpSession ses) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("userType", userInfo.getUsertyp());
		mv.addObject("dataList", mdb2Service.getMdb2DetailList(param,userInfo));
		return mv;
	}
	
	
	@GetMapping("/md/mdb2QnASortList.do")
	public ModelAndView getMdb2QnASortList(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", mdb2Service.getMdb2QnASortList(param,userInfo));
		return mv;
	}
	
	@PostMapping("/md/mdb2WriteSave.do")
	public ModelAndView getMdb2WriteSave(@RequestBody Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mdb2Service.getMdb2WriteSave(param,userInfo);
		return mv;
	}
	
	@PostMapping("/md/mdb2WriteUpdate.do")
	public ModelAndView getMdb2WriteUpdate(@RequestBody Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mdb2Service.getMdb2WriteUpdate(param,userInfo);
		return mv;
	}
	
	@PostMapping("/md/mdb2WriteDelete.do")
	public ModelAndView getMdb2WriteDelete(@RequestBody Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mdb2Service.getMdb2WriteDelete(param,userInfo);
		return mv;
	}
	
	@PostMapping("/md/mdb2RefContSave.do")
	public ModelAndView getMdb2RefContSave(@RequestBody Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mdb2Service.getMdb2RefContSave(param,userInfo);
		return mv;
	}
}
