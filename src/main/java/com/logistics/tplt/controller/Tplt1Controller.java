package com.logistics.tplt.controller;

import com.logistics.tplt.domain.TpltVO;
import com.logistics.tplt.service.Tplt1Service;
import com.logistics.sy.domain.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Tplt1Controller {

	@Autowired
	private Tplt1Service tplt1Service;

	@GetMapping("/tplt/tplt1Init.do")
	public Map<String, Object> getTplt1Init(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userVo) {

		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("tpsbList", tplt1Service.getTpltInitData(param, userVo));

		return returnData;
	}

	@GetMapping("/tplt/tplt1List.do")
	public List<TpltVO> getTplt1GridList(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userVo) {
		return tplt1Service.getTplt1List(param, userVo);
	}

	@GetMapping("/tplt/tplt1Validation.do")
	public List<TpltVO> validationTplt1GridList(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userVo) {
		return tplt1Service.validationTplt1List(param, userVo);
	}

	@PostMapping("/tplt/tplt1Save.do")
	public int saveTplt1Grid(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userVo) {
		return tplt1Service.saveTplt1Data(param, userVo);
	}

	@PostMapping("/tplt/tplt1Update.do")
	public int updateTplt1Grid(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userVo) {
		return tplt1Service.updateTplt1Date(param, userVo);
	}

	@PostMapping("/tplt/tplt1Modify.do")
	public int modifyTplt1Grid(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userVo) {
		return tplt1Service.modifyTplt1Date(param, userVo);
	}

	@DeleteMapping("/tplt/tplt1Delete.do")
	public int deleteTpltGrid(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userVo){
		return tplt1Service.deleteTplt1Date(param, userVo);
	}
}
