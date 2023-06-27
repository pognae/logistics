
//============================================================	
// 페이지 이동
//============================================================	   
function openWindowPop(url){
    var options = 'top=10, left=10, width=800, height=800, status=no, menubar=no, toolbar=no, resizable=yes';
    window.open(url, "test", options);
}

function logout(strMsg){
	//var msg = "[[#{sy.main.auth.userinfoerr}]]";
	alert(strMsg); 
	window.location.replace('/login'); 
}
function sessionTimeOut(strMsg){
	//var msg = "[[#{sy.main.auth.sessionout}]]";
	alert(strMsg);
	window.location.replace('/login'); 
}

//============================================================	
// 스피너 ON / OFF
//============================================================	
function spinnerOpen(){
	$(".spinner-wrapper").show();
}

function spinnerClose(){
	$(".spinner-wrapper").hide();
}

//============================================================
// 공통 콤보박스 (GridID, 컬럼키 & ID , Param, URL)
//============================================================
function Combo (gridnm, colnm, param, url, mapob, type){
	
	if(!colnm){
		return false;
	}
	
	this.url = url ? url : "/md/mdc7code.do";
	
	if(!gridnm){
		$.getJSON(this.url, param, function (response) {
			$("#" + colnm + " option").not("[value='']").remove();	//초기화
			if(mapob != null && mapob.combovl != undefined && mapob.combonm != undefined){
				$.each(response.item, function(key, value) { $('#' + colnm) .append($("<option></option>").attr("value",value[mapob.combovl]).text(value[mapob.combonm])); });
			}else{
				$.each(response.item, function(key, value) { $('#' + colnm) .append($("<option></option>").attr("value",value.combovl).text(value.combonm)); });
			}
		});
	}else{
		$.getJSON(this.url, param, function (response) {
	        var grid = pq.grid("#" + gridnm)
	          , column = grid.getColumn({ dataIndx: colnm });
	          
	        var options = response.item.map(function(obj){
	        	var rObj = {};
	        	if(mapob != null && mapob.combovl != undefined && mapob.combonm != undefined){
	        		rObj[obj[mapob.combovl]] = obj[mapob.combonm];
	        	}else{
					rObj[obj.combovl] = obj.combonm;
	        	}
				return rObj;
			});
			
	        column.editor.options = type === true ? response.item : options;
	    });
	}
}

function comboCreateData(gridnm, colnm, obj, mapob, type){

	if(!gridnm){
		$("#" + colnm + " option").not("[value='']").remove();	//초기화
		if(mapob != null && mapob.combovl != undefined && mapob.combonm != undefined){
			$.each(obj, function(key, value) {
				$('#' + colnm) .append($("<option></option>").attr("value",value[mapob.combovl]).text(value[mapob.combonm]));
			});
		}else{
			$.each(obj, function(key, value) {
				$('#' + colnm) .append($("<option></option>").attr("value",value.combovl).text(value.combonm));
			});
		}
	}
	else{

		var grid 	= pq.grid("#" + gridnm);
		var column 	= grid.getColumn({ dataIndx: colnm });

		var options = obj.map(function(obj){
			var rObj = {};
			if(mapob != null && mapob.combovl != undefined && mapob.combonm != undefined){
				rObj[obj[mapob.combovl]] = obj[mapob.combonm];
			}else{
				rObj[obj.combovl] = obj.combonm;
			}
			return rObj;
		});

		column.editor.options = options;
	}
}

//============================================================
//공통 그리드 날짜 달력 오픈 이벤트
//============================================================	
function dateEditor(ui) {
	var $inp = ui.$cell.find("input"),
        format = ui.column.format || "yy-mm-dd" 
        val = $inp.val(),
        val = val ? $.datepicker.formatDate(format, new Date(val)) : "";

    $inp.attr('readonly', 'readonly')
        .val(val)
        .datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: format,
            showAnim: '',
            onSelect: function () {
                this.firstOpen = true;
            },
            beforeShow: function (input, inst) {
                setTimeout(function () {
                    $('.ui-datepicker').css('z-index', 999999999999);
                });
            },
            onClose: function () {
                this.focus();
            }
	});
}

//============================================================
//공통 그리드 시간 오픈 이벤트
//============================================================	
function timeEditor(ui) {
	var $inp = ui.$cell.find("input");  
	$inp.datetimepicker({
	    format: "HH:mm:ss",
	    stepping: 1,
	    icons: {
			up: "fa fa-chevron-up",
 	 		down: "fa fa-chevron-down"
//	      up: "fa fa-chevron-up",
//	      down: "fa fa-chevron-down"
	    }
	});
}

//============================================================
//공통 Ajax
//============================================================	
var AjaxUtil_Order = function(url, params, type, dataType ){
	
	if(!url){
		alert("url 정보가 없습니다.");
		return null;
	}
	
	// AjaxUtil_Order 생성자 함수를 사용하여 새로운 객체를 생성 할 때, 매개변수(param) url을 
	// this 객체 내부의 url 멤버 변수에 할당시키기 위해 사용
	// 인스턴스 변수를 생성하여 객체 인스턴스 내부에서 사용 가능
	this.url = url;
	
	var generateJSON = function(params) {
		if(!params){
			return "";
		}
		return JSON.stringify(params);
	}
	
	this.type = type ? type : "POST";
	this.dataType = dataType ? dataType : "json";
	this.param = type === "GET" ? params : generateJSON(params);
	
	this.callbackSuccess = function(json) {
		var data = JSON.stringify(json);
	}
	this.setCallbackSuccess = function(callback) {
		this.callbackSuccess = callback;
	}
	
	this.complete = function() {

	}
	this.setComplete = function(fun){
		this.complete = fun;
	}
	
	this.err = function(xhr, status, e) {
		if(xhr.status=="500"){
			logout("Login Session Expired");
		}else{
			if (xhr.responseJSON) { 
				var obj = xhr.responseJSON;
				alert(obj.code + ":" + obj.message);
			} else {
				alert('AjaxUtil_Order error');
				console.log("AjaxUtil_Order url=" + this.url + " 에러 = " + e);

			}
		}
	}
	this.setErr = function(fun){
		this.err = fun;
	}
	
	this.send = function() {
		$.ajax({
			type : this.type,
			url : this.url,
			dataType : this.dataType,
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type","application/json;charset=utf-8");
				xhr.setRequestHeader("AJAX", true);
			},
			data : this.param,
			success : this.callbackSuccess,
			error : this.err,
			complete : this.complete
		});
	}
}

//============================================================
//공통 callAjax
//============================================================
function callAjax(rType, method, tUrl, param){
	
	switch(rType)
	{
		case "data":
			
			var rData;

			$.ajax({ 
				type: method, 
				url: tUrl, 
				cache: false, 
				data: param = method === "GET" ? param : JSON.stringify(param),
				async: false,
				dataType: "json",
				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type","application/json;charset=utf-8");
					xhr.setRequestHeader("AJAX", true);
					spinnerOpen();
				},
				success: function(data){
					rData = data;
				},
				error: function(xhr, status, error){
					if(xhr.status=="500"){
						logout("Login Session Expired");     
						//location.href = "/";
					}else{
						if (xhr.responseJSON) { 
							var obj = xhr.responseJSON;
							alert(obj.code + ":" + obj.message);
						} else {
							alert('AjaxUtil_Order error');
							console.log("AjaxUtil_Order url=" + this.url + " 에러 = " + e);
	
						}
					}
				},
				complete : function(){
					spinnerClose();
				}
			});
			
			return rData;		
		break;
		
		case "html":
			
			var rData;

			$.ajax({ 
				type: method, 
				url: tUrl, 
				cache: false, 
				data: param,
				async: false,
				dataType: "html",
				beforeSend : function() {

				},
				success: function(data){
					rData = data;
				},
				error: function(xhr, status, error){
					if(xhr.status=="500"){
						logout("Login Session Expired");     
						//location.href = "/";
					}else{
						if (xhr.responseJSON) { 
							var obj = xhr.responseJSON;
							alert(obj.code + ":" + obj.message);
						} else {
							alert('AjaxUtil_Order error');
							console.log("AjaxUtil_Order url=" + this.url + " 에러 = " + e);
	
						}
					}
				},
				complete : function(){

				}
			});
			
			return rData;		
		break;
		
		case "msg":

			$.ajax({ 
				type: method, 
				url: tUrl, 
				cache: false, 
				data: param,
				dataType: "json",
				beforeSend : function() {

				},
				success: function(data){
					alert(data['msg']);
					if(data['rUrl'] != null){
						location.href= data['rUrl'];
					}
				},
				error: function(xhr, status, error){
						if(xhr.status=="500"){
						logout("Login Session Expired");     
						//location.href = "/";
					}else{
						if (xhr.responseJSON) { 
							var obj = xhr.responseJSON;
							alert(obj.code + ":" + obj.message);
						} else {
							alert('AjaxUtil_Order error');
							console.log("AjaxUtil_Order url=" + this.url + " 에러 = " + e);
	
						}
					}
				},
				complete : function(){
				
				}
			});
			
		break;
		
		case "form":
			
			jQuery.ajaxSettings.traditional = true;
			var rData;

			$.ajax({ 
				type: method, 
				url: tUrl, 
				cache: false, 
				data: param,
				async: false,
				dataType: "json",
				enctype: 'multipart/form-data',
				contentType : false,
				processData : false,
				beforeSend : function() {
					spinnerOpen();
				},
				success: function(data){
					rData = data;
				},
				error: function(xhr, status, error){
						if(xhr.status=="500"){
						logout("Login Session Expired");     
						//location.href = "/";
					}else{
						if (xhr.responseJSON) { 
							var obj = xhr.responseJSON;
							alert(obj.code + ":" + obj.message);
						} else {
							alert('AjaxUtil_Order error');
							console.log("AjaxUtil_Order url=" + this.url + " 에러 = " + e);
	
						}
					}
				},
				complete : function(){
					spinnerClose();
				}
			});
			
			return rData;		
		break;
		
		default:
			
			$.ajax({ 
				type: method, 
				url: tUrl, 
				cache: false, 
				data: param,
				dataType: "json",
				success: function(data){
					if(data['rUrl'] != null){
						location.href= data['rUrl'];
					}
				},
				error: function(xhr, status, error){
					if(xhr.status=="500"){
					logout("Login Session Expired");     
					//location.href = "/";
				}else{
					if (xhr.responseJSON) { 
						var obj = xhr.responseJSON;
						alert(obj.code + ":" + obj.message);
					} else {
						alert('AjaxUtil_Order error');
						console.log("AjaxUtil_Order url=" + this.url + " 에러 = " + e);

					}
				}
				}
			});
		
		break;
	}
}

//============================================================
//공통 Preview Load
//============================================================
function callPreviewLoad(previewDomId, paramList){
	var list = new Object();
	list.itemList = paramList;

    var data = {
    	list : list
    };

	var rData = callAjax("data", "POST", "/wm/wmpt6InvoiceCall.do", data);
	rData.previewId = previewDomId;
	
	var url = "/main/modalLoad?gprogurl=/com/modal/wmpt6.html";
	var urlParameters = Object.entries(rData).map(e => e.join('=')).join('&');

	var tURL = url+"&"+urlParameters;

	$("#"+previewDomId).load(tURL);
}

//============================================================
//공통 Preview Load & print
//============================================================
function callPreviewLoadPrint(previewDomId, paramList){
	var list = new Object();
	list.itemList = paramList;

    var data = {
    	list : list
    };

	var rData = callAjax("data", "POST", "/wm/wmpt6InvoiceCall.do", data);
	rData.previewId = previewDomId;
	
	var url = "/main/modalLoad?gprogurl=/com/modal/wmpt6.html";
	var urlParameters = Object.entries(rData).map(e => e.join('=')).join('&');

	var tURL = url+"&"+urlParameters;
	
	$("#"+previewDomId).load(tURL, function(){
		var wrapDom 	= $("#"+previewDomId).find(".wmpt6-wrap");
		var rollDom 	= wrapDom.find(".wmpt6-roll");
		
		for(var i=0; i<rollDom.length; i++){
			rollDom.eq(i).printThis({
				importCSS:false,
				loadCSS: "/css/pt/pt.css",
			});
		}
	});
}

//============================================================
//공통 Preview print
//============================================================
function callPreviewPrint(previewDomId){
	if($("#"+previewDomId).length > 0){
		var wrapDom 	= $("#"+previewDomId).find(".wmpt6-wrap");
		var rollDom 	= wrapDom.find(".wmpt6-roll");
		
		for(var i=0; i<rollDom.length; i++){
			rollDom.eq(i).printThis({
				importCSS:false,
				loadCSS: "/css/pt/pt.css",
			});
		}
	}
}

//============================================================
//공통 Javascript 숫자 3자리(천단위) 마다 콤마 찍기
//============================================================
function comma(num){
	var parts = num.toString().split('.')
   	parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ',')
   	return parts.join('.');
//return val.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
}

//============================================================
//공통 Javascript 숫자 3자리(천단위) 콤마 제거
//============================================================
function uncomma(num){
	str = String(num);
    return Number(str.replace(/[^-+\d]+/g, ''));
	//return val.replace(/,/g, "");
}

//============================================================
//공통 Javascript 숫자(정수)만 가능(양수,음수)
//============================================================
function onlyNumber(e){
   var regexp = /^[-]?[0-9]*([0-9]?)?$/;
   var regexp2 = /\s/g;
   
   var value = $(e).val().replaceAll(",","");
   value = value.replace(/^[-]?0+/, "")
   if(!regexp.test(value)){
      var result = value.replace(value,"");
      $(e).val(comma(result));
      $(e).focus();
	  return false; // 
   }
	  $(e).val(comma(value));
}

//============================================================
//공통 Javascript 숫자(양수)만 가능
//============================================================
function onlyNumberPlus(e){
	var regexp = /[^0-9]/g;
   
	var value = $(e).val().replaceAll(",","");
	value = value.replace(/(^0+)/, ""); 
   	if(regexp.test(value)){
		var result = value.replace(value,"");
      	$(e).val(comma(result));
      	$(e).focus();
	  	return false; 
   	}
	$(e).val(comma(value));
}

//============================================================
//공통 Javascript 숫자(실수)만 가능
//============================================================
function onlyFloat(e){
	var regexp = /[^0-9|.]/g;
	var regexp2 = /\s/g;
	
	var value = $(e).val().replaceAll(",","");
	
	if(regexp.test(value)){
		var result = value.replace(regexp,"");
		$(e).val(comma(result));
		$(e).focus();
		return false;
	}
	$(e).val(comma(value));
}

//============================================================
//공통 params json 형식으로 리턴
//============================================================
function paramsJsonData(d){
	if(d){
		d = d.replace(/ /g, "");
		d = d.replace(/{/g, "");
		d = d.replace(/}/g, "");
		d = d.split(',');
		const prams = d.reduce((newObj, obj) => {
		    dobj = obj.split('=');
		    newObj[dobj[0]] = dobj[1];
		    return newObj;
		}, {});
		return prams;
	}else{
		return null;
	}
}

//============================================================
//검색조건 폼 펼치고 접기 - 2022.09.20 - 퍼블리싱 스크립트 추가
//============================================================
function bookmarkclick(obj ) {
	$(obj).toggleClass('active');

	var pprogrid = obj.getAttribute("pprogrid");
	var bkactive = obj.getAttribute("class").indexOf('active');
	var bookmark = (bkactive >= 0) ? "Y" : "N";
	
	//alert(pprogrid);
	
	
 	var data = { progrid : pprogrid, bookmark : bookmark };
	var aul = new AjaxUtil_Order("/main/userMenuBookmark.do", data, "GET");
		aul.setCallbackSuccess(function (data){
			//jsonToMenu(data);
			var jsonStr = JSON.stringify(data);
			//alert(jsonStr);			
			
			//북마크 추가
			if(bookmark=='Y') {			
				//alert(bookmark);
				//BookmarkAdd(pprogrid, progcmd, menunam, newMenulvl);
				BookmarkAdd(pprogrid);
			}
			
			//북마크 삭제
			if(bookmark=='N') {
				//alert(bookmark);
				BookmarkDelete(pprogrid);
			}	

			
		});
		aul.send();		
	// ajax[/main/userMenuBookmark.do]
}

$(document).ready(function () {
	$('.main-search-wrap').each(function(){
		
		var rowCount = $(this).find('.form-group.row').length;
		
		if( rowCount < 3 ){
			$(this).find('.btn-fold-wrap').hide();
		} 
	})

});
function onSearchExpand(button){
	var searchWrap = $(button).closest('.main-search-wrap');
	var rowHeight = searchWrap.find('.form-group.row').outerHeight();
	var searchForm = searchWrap.find('.search-form');
	var margin = 17; 
	
	
	if($(button).hasClass('expanded')){
		
		searchForm.css('max-height',rowHeight*2 + margin)
		$(button).removeClass('expanded');
		
	}else{
		
		searchForm.css('max-height','none')
		$(button).addClass('expanded');
		
	}
}
 
$(function() {
	//$('.bookmark-btn').on('click',function(){
	//	$(this).toggleClass('active');
	//})
	$("#sidebardiv").mCustomScrollbar({
		theme: "darkbule"
	});
	
})

$(function() {
	$('.file-input').on('change',function(e){
		var fileName = $(this)[0].files[0].name;
		var inputField = $(this).closest('.input-file-group').find('.file-name');
		inputField.html(fileName)
	})
})


//============================================================
//공통 모달 레이어 팝업 호출 - 2022.10.05
//============================================================	
function callModalPopup(progId, param) {

	var url = "/main/modalLoad?gprogurl=/com/modal/"+progId+".html";
	var urlParameters = Object.entries(param).map(e => e.join('=')).join('&');
	var tURL = url+"&"+urlParameters;

	$("#modalCommon").load(tURL);
}

//============================================================
//공통 문자열 길이 (영문,숫자=1 한글=2)
//============================================================
function getStringLength(str) {
    var len = 0;
    for (var i = 0; i < str.length; i++) {
        if (escape(str.charAt(i)).length == 6) {
            len++;
        }
        len++;
    }
    return len;
}
//문자열 자르기
function getStrCut(str, len)
{		
	var s = 0;
	for (var i=0; i<str.length; i++) {
		s += (str.charCodeAt(i) > 128) ? 2 : 1;
		if (s > len) return str.substring(0,i) + "..";
	}
	return str;
}
//문자열에 공백매꾸기 : n=문저열, digits=공백갯수 
function setleadingSpaces(n, digits) {
  var space = '';
  n = n.toString();

  if (n.length < digits) {
    for (var i = 0; i < digits - n.length; i++)
      space += '&nbsp;';
  }
  return space + n;
}
//날자 입력 input에서 del 키누를때 전부 삭제 하기 
function setInputDateDel(e) {
	if(event.keyCode==46) // keyboard del key code
    	e.value='';
}

//============================================================
// url get Parameter 리턴
//============================================================
function getParameter(param){
	//현재 주소를 decoding
	var requestParam;
    var url = unescape(location.href); 
        
	//파라미터만 자르고, 다시 &그분자를 잘라서 배열에 넣는다. 
    var paramArr = (url.substring(url.indexOf("?")+1,url.length)).split("&"); 
        
    for(var i = 0 ; i < paramArr.length ; i++){
    	var temp = paramArr[i].split("="); //파라미터 변수명을 담음
        
        if(temp[0].toUpperCase() == param.toUpperCase()){
        	// 변수명과 일치할 경우 데이터 삽입
        	requestParam = paramArr[i].split("=")[1]; 
            break;
		}
	}
	
	return requestParam;
}
 