package com.logistics.configuration.util.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

@Service
@Slf4j
public class FileUploadUtilService { 

	private final Map<String, String> path;
	private final Map<String, String> fileConfig;
	private final Map<String, String> imgConfig;
	private final Map<String, String> typeConfig;
	

	@Autowired
	public FileUploadUtilService(FileUploadUtilProperties properties) {
		this.path 		= properties.getPath();
		this.imgConfig 	= properties.getImgConfig();
		this.typeConfig 	= properties.getTypeConfig();
		this.fileConfig 	= properties.getFileConfig();
	}
	
	public Map<String, Object> fileUploadProccess(Map<String, Object> dataMap, Map<String, MultipartFile> params) throws IOException{
		
		//리턴변수
		Map<String, Object> rData = new HashMap<>();
		
		//파일리스트 담는 변수
		List<Object> returnListParam = new ArrayList<>();
		
		//파일 경로 변수
		Date date = new Date();
		SimpleDateFormat formatYear 	= new SimpleDateFormat ("yyyy");
		SimpleDateFormat formatMonth 	= new SimpleDateFormat ("MM");
		String uploadPathDirYear 	= formatYear.format(date);
		String uploadPathDirMonth 	= formatMonth.format(date);
		
		String uploadPathRoot 	= path.get("rootpath");
		String uploadPathUrl 	= path.get("urlpath");
		
		String uploadPathDir	= (String) dataMap.get("uploadPath");
		String uploadRealPath 	= uploadPathRoot+uploadPathDir+"/"+uploadPathDirYear+"/"+uploadPathDirMonth+"/"; // => 루트/디렉토리/년도/
		String uploadRealUrlPath	= uploadPathUrl+uploadPathDir+"/"+uploadPathDirYear+"/"+uploadPathDirMonth+"/";
		
		File Folder = new File(uploadRealPath);
		if(!Folder.exists()) {
			try{
			    Folder.mkdirs(); //폴더 생성합니다.
			}
			catch(Exception e){
				e.getStackTrace();
			}        
		}
		
		//파일 확장자
		String fileExt = "."+imgConfig.get("convertedext");
		String fileNameType = (String) dataMap.get("fileNameType");
		String fileNameKey = "UUID".equals(fileNameType) ? UUID.randomUUID().toString() : (String) dataMap.get("fileNameKey");
		
		//파일 다중 처리
		Iterator<String> paramKey = params.keySet().iterator();
		
		while(paramKey.hasNext()) {
			String key = paramKey.next();
			
			Map<String, Object> returnParam = new HashMap<>();
			
			if(params.get(key) instanceof MultipartFile) {
				
				long fileSize = params.get(key).getSize();
				long fileSizeLimit = Long.valueOf(fileConfig.get("maxfilesize"));
				
				if(fileSize > fileSizeLimit) {
					rData.put("code", "300");
					rData.put("message", "fileSizeOverflow");
					return rData;
				}
				//파일 타입 mime 체크
				String mime = params.get(key).getContentType();
				
				if(imgConfig.get("mimetype").indexOf(mime) != -1) {

					//이미지 처리
					//String fileNameEncoding = URLEncoder.encode(fileNameKey+"_"+params.get(key).getName()+fileExt, "UTF-8");
					String fileName = URLEncoder.encode(fileNameKey+"_"+params.get(key).getName()+fileExt,"UTF-8");
					//boolean result = imagefileProccssing(params.get(key).getInputStream(), uploadRealPath, fileName);
					boolean result = imagefileDirectProccssing(params.get(key).getInputStream(), uploadRealPath, fileName);
					if(result) {
						returnParam.put(key, URLDecoder.decode(uploadRealUrlPath+fileName, "UTF-8"));
					}else {
						rData.put("code", "999");
						rData.put("message", "SystemError");
						return rData;
					}
				}
				else {
					//이미지 외 파일 처리 필요시 생성 ( exe 파일 제외 )
					if(typeConfig.get("excludetype").indexOf(mime) != -1) {
						String fileType = params.get(key).getOriginalFilename().substring(params.get(key).getOriginalFilename().lastIndexOf("."));
						String fileName = URLEncoder.encode(fileNameKey+"_"+ params.get(key).getName() + fileType ,"UTF-8");
						boolean result = textfileProccssing(params.get(key), uploadRealPath, fileName);
						if(result) {
							returnParam.put(key, URLDecoder.decode(uploadRealUrlPath+fileName, "UTF-8"));
						}else {
							rData.put("code", "999");
							rData.put("message", "SystemError");
							return rData;
						}
					}
					else {
						rData.put("code", "400");
						rData.put("message", "fileTypeError");
						return rData;
					}
				}
			}
			else {
				returnParam.put(key, params.get(key));
			}

			if(!returnParam.isEmpty()) {
				returnListParam.add(returnParam);
			}
		}
		
		rData.put("code", "200");
		rData.put("fileResult", returnListParam);
		
		return rData;
	}
	
	

	private boolean textfileProccssing(MultipartFile multipartFile, String uploadRealPath, String fileName) {
		String saveFilePath = uploadRealPath + fileName;
		boolean result = false;
		try {
			multipartFile.transferTo(new File(saveFilePath));
			result = true;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean imagefileProccssing(InputStream inputStream, String filePath, String fileName) throws IOException {
		
		boolean result = true;
		double ratio;													//비율
		int originWidth;												//원본파일 가로 (넓이)
		int originHeight;												//원본파일 세로 (높이)
		int maxWidth 	= Integer.parseInt(imgConfig.get("maxwidth"));	//리사이징 최대 가로값 (넓이)
		int maxHeight 	= Integer.parseInt(imgConfig.get("maxheight")); //리사이징 최대 세로값 (높이)
		int outWidth	= 0;
		int outHeight 	= 0;
		String imageVHType;
		
		BufferedImage inputImage = ImageIO.read(inputStream);
		originWidth 	= inputImage.getWidth();
		originHeight 	= inputImage.getHeight();
		
		//가로형, 세로형 이미지 구분
		if(originWidth > originHeight) {
			imageVHType = "W";
		}
		else if(originWidth == originHeight) {
			imageVHType = "C";
		}
		else {
			imageVHType = "H";
		}

		//가로형 이미지 비율 처리
		if("W".equals(imageVHType)) {     
			if(originWidth > maxWidth) {
				//가로 사이즈가 maxWidth보다 클경우
				ratio 		= (double)maxWidth/(double)originWidth;
				outWidth 	= (int)(originWidth * ratio);
				outHeight 	= (int)(originHeight * ratio);
			}
			else {
				outWidth	= originWidth;
				outHeight	= originHeight;
			}
		}
		else if("H".equals(imageVHType)) {
			if(originHeight > maxHeight)
			{
				//세로 사이즈가 maxHeight보다 클경우
				ratio 		= (double)maxHeight/(double)originHeight;
				outWidth 	= (int)(originWidth * ratio);
				outHeight 	= (int)(originHeight * ratio);
			}
			else {
				outWidth	= originWidth;
				outHeight	= originHeight;
			}
		}else { //C인 조건이 없습니다용!
				outWidth	= originWidth;
				outHeight	= originHeight;
		}

		if(outWidth == 0 || outHeight == 0) {
			result = false;
		}
		else {
			String FilePath = filePath+fileName;
			BufferedImage outputImage = new BufferedImage(outWidth, outHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = outputImage.createGraphics();
	        graphics2D.drawImage(inputImage, 0, 0, outWidth, outHeight, null);
	        graphics2D.dispose();

	        result = ImageIO.write(outputImage, "jpg", new File(FilePath));
		}

		return result;
	}
	
	
	public boolean imagefileDirectProccssing(InputStream inputStream, String filePath, String fileName) throws IOException {
		
		boolean result = true;

		OutputStream outputStream = null;
		
		String FilePath = filePath+fileName;

		outputStream = new FileOutputStream(FilePath);
		 
        int readByte = 0;
        byte[] buffer = new byte[8192];

        while ((readByte = inputStream.read(buffer, 0, 8120)) != -1) {
            outputStream.write(buffer, 0, readByte); //파일 생성 ! 
        }
        
        outputStream.close();
        inputStream.close();

		return result;
	}
	
}
