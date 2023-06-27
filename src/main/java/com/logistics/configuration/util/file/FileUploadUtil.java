package com.logistics.configuration.util.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadUtil {

	@Autowired
	private FileUploadUtilService fileUploadUtilService;
	
	@PostMapping(value = "/com/upload/fileUpload")
    @ResponseBody
	public ModelAndView listUploadedFiles(MultipartHttpServletRequest multiRequest) throws IOException {
		
		//Enumeration<String> paramKey 		= multiRequest.getParameterNames();	//파라미터 동적으로 받을 경우 사용
		
		ModelAndView mv = new ModelAndView("jsonView");

		try {
			Map<String, Object> dataMap 		= new HashMap<>();
			Map<String, MultipartFile> fileMap 	= multiRequest.getFileMap();

			String uploadPath = multiRequest.getParameter("uploadPath");
			
			if(uploadPath == null || uploadPath.trim().equals("") ) {
				mv.addObject("code", 400);
				mv.addObject("message", "file upload fail");
			}
			else {
				dataMap.put("fileNameType", multiRequest.getParameter("fileNameType"));
				dataMap.put("fileNameKey", multiRequest.getParameter("fileNameKey"));
				dataMap.put("uploadPath", multiRequest.getParameter("uploadPath"));
				
				Map<String, Object> result = fileUploadUtilService.fileUploadProccess(dataMap, fileMap);
				
				if("200".equals(result.get("code"))){
					int listSize = ((ArrayList<Object>)result.get("fileResult")).size();
					
					if(listSize > 0) {
						mv.addObject("data", result.get("fileResult"));
					}
					else {
						mv.addObject("data", null);
						mv.addObject("message", "file upload fail");
					}
					
					mv.addObject("code", result.get("code"));
				}
				else {
					mv.addObject("code", result.get("code"));
					mv.addObject("message", result.get("message"));
				}
			}
		}
		catch(Exception e) {
			mv.addObject("code", 999);
		}

		return mv;
		
	}
}