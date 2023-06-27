------------------------
util
------------------------
//=============================
// 파일 업로드 
// FileUploadUtil.java
// FileUploadUtilProperties.java
// FileUploadUtilService.java
// FileUploadUtilServiceImpl.java
//=============================
URL : /com/upload/fileUpload		

var param = {
	fileNameType" : "UUID" 		//해당 파라미터 값 넣을경우 UUID로 파일명 생성
	fileNameKey : "공통 파일명",
	uploadPath : "디렉토리명",
	inputDom : 파일,
	...
	inputDom10 : 파일10
}

//=============================
//사용예제 (mdu2.html 참고)
//=============================

var formData = new FormData();
formData.append("fileNameKey", $("input[name='modal_skumkey']").val());
formData.append("uploadPath", "sku");	//uploadPath 가 없을경우 에러 발생 (code : 400)
formData.append("file1", "input[name='file1'][0].files[0]");

var rdata = callAjax("form", "POST", "/com/upload/fileUpload", formData);

if(rdata.code == "200"){
	//정상처리
	//rdata.data => 업로드한 파일 경로 리스트
}
else{
	//rdata.message => 에러 메세지
}