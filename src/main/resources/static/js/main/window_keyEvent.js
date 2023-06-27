// ============================================================	
// 키보드 제어
// ============================================================
var ggActivPG = 'Home';
function removeDefaultFunction()
{
    window.onhelp = function () { return false; }
}
$(window).bind('keydown', function(e) {
    //This is the F1 key code, but NOT with SHIFT/CTRL/ALT
    var keyCode = e.keyCode || e.which;
	let gTHEMETY = $("#gSessionDatas")[0].getAttribute('gTHEMETY') // 사용자 테마 타입	    
	if (gTHEMETY == 'MAIN1SD') {
		ggActivPG = getParameter('gprogrid');
	}     
		    
    //console.log(' -------------------------------  '  );
    //console.log('(window.keydown) : Active Program : ' + ggActivPG ) ;
    //console.log(' -------------------------------  '  );
    //console.log( 'e.keyCode=' + e.keyCode + ' e.which=' + e.which  + ' event.ctrlKey=' + event.ctrlKey + ' event.shiftKey=' + event.shiftKey);     
    if((keyCode == 112 || e.key == 'F1') && !(e.altKey || e.ctrlKey || e.shiftKey || e.metaKey))
	{ 
        removeDefaultFunction();
        e.cancelable = true;
        e.stopPropagation();
        e.preventDefault();
        e.returnValue = false; 
        //console.log('F1 Help key opened, ' + keyCode);
	}
    // Add other F-keys here:
    else if((keyCode == 113 || e.key == 'F2') && !(e.altKey || e.ctrlKey || e.shiftKey || e.metaKey))
	{ 
        removeDefaultFunction();
        e.cancelable = true;
        e.stopPropagation();
        e.preventDefault();
        e.returnValue = false; 
        //console.log('F2 Help key opened, ' + keyCode );
	}
    else if((keyCode == 114 || e.key == 'F3') && !(e.altKey || e.ctrlKey || e.shiftKey || e.metaKey))
	{ 
        removeDefaultFunction();
        e.cancelable = true;
        e.stopPropagation();
        e.preventDefault();
        e.returnValue = false; 
        //console.log('F3 Help key opened, ' + keyCode );
	} 
	
	// ctrl Key OMS 프로그램
	if (e.ctrlKey) {
		switch ( keyCode)
		{
			case 49 : //Ctrl+1 oms3 주문관리 49
				mainProgramCMD('oms3');
				break;
			case 50 : //Ctrl+2 oms2 주문등록 50
				mainProgramCMD('oms2');
				break;
			case 51 : //Ctrl+3 oms4 샘플관리 51
				mainProgramCMD('oms4');
				break;
			case 52 : //Ctrl+4 oms6 미송관리 52
				mainProgramCMD('oms6');
				break;
			case 53 : //Ctrl+5 oms5 인수완료 53
				mainProgramCMD('oms5');
				break;
			case 54 : //Ctrl+6 oml1 판매내역 54
				mainProgramCMD('oml1');
				break;
			case 55 : //Ctrl+7 없음 55
				break;
			case 56 : //Ctrl+8 없음 56
				break;
			case 57 : //Ctrl+9 mdu2 상품관리 57
				mainProgramCMD('mdu2');
				break;
			case 48 : //Ctrl+0 omw4 재고조회 48	
				mainProgramCMD('omw4');
				break;
			default :
	  			break; 
		}
	} 
	 
	
	// shiftKey WMS 프로그램 
/*	if (e.shiftKey) {
		switch ( keyCode)
		{
			case 49 : //shift+1 wmi3 기타입하등록 49
				mainProgramCMD('wmi3');
				break;
			case 50 : //shift+1 wmi2	입하등록 50
				mainProgramCMD('wmi2');
				break;
			case 51 : //shift+2 wmi5	적치지시 51
				mainProgramCMD('wmi5');
				break;
			case 52 : //shift+3 wmoa1	출고지시 52
				mainProgramCMD('wmoa1');
				break;
			case 53 : //shift+4 wmoa2	할당관리 53
				mainProgramCMD('wmoa2');
				break;
			case 54 : //shift+5 wmop2	수동피킹(피킹완료) 54
				mainProgramCMD('wmop2');
				break;
			case 55 : //shift+6 wmop3 DAS분배 55
				mainProgramCMD('wmop3');
				break;
			case 56 : //shift+7 wmoc2	출고 완료 56
				mainProgramCMD('wmoc2');
				break;
			case 57 : //shift+9 mdu2 상품관리 57
				mainProgramCMD('mdu2');
				break;
			case 48 : //shift+0 wmvs1 재고조회 48
				mainProgramCMD('wmvs1');
				break;
			default :
	  			break; 
		}
	}*/ 	
  						
	// 프로그램 ID별 단축키 기능 정의		
	switch ( ggActivPG)
	{
		case "oms2" :
		
			//console.log(e.altKey  + "/" + e.keyCode);
			//저장 alt + S
	    	if(e.altKey && e.keyCode == 83 ) { 
	    		$("#oms2_save").click();
	    		
	    	//alt + Insert key
	    	}else if(e.altKey && e.keyCode == 45) {
	    		oms2_window_key_addRow();
	    	
	    	//alt + Delete key
	    	} else if(e.altKey && e.keyCode == 46) {
	    		deleteRow();
	    		
	    	// DocType : 판매 (alt + 1)
	        }else if(e.altKey && e.keyCode == 49) {
	        	oms2GridUpdate("210", oms2FilterRow("210"));
	        	
	        // DocType : 미송 (alt + 2)
	        } else if(e.altKey && e.keyCode == 50) {
	        	oms2GridUpdate("250", oms2FilterRow("250"));
	        	
	        // DocType : 반품 (alt + 3)
	        } else if(e.altKey && e.keyCode == 51) {
	        	oms2GridUpdate("240", oms2FilterRow("240"));
	        	
	        // DocType : 무상 (alt + 4)
	        } else if(e.altKey && e.keyCode == 52) {
	        	oms2GridUpdate("220", oms2FilterRow("220"));
	        	
	        // DocType : 매장판매 (alt + 5)
	        } else if(e.altKey && e.keyCode == 53) {
	        	oms2GridUpdate("280", oms2FilterRow("280"));
	        	
	        // DocType : 샘플 (alt + 6)
	        } else if(e.altKey && e.keyCode == 54) {
	        	oms2GridUpdate("230", oms2FilterRow("230"));
	        } 
  			break;
		case "oms3" :
  			console.log('oms3');
  			break;
		case "oms4" :
  			console.log('oms4');
  			break;
		case "oms5" :
  			console.log('oms5');
  			break;
  		
  		case "wmi3" :
  			
  			//alt + Insert key
	    	if(e.altKey && e.keyCode == 45) {
	    		wmi3_window_key_addRow();
	    		
	    	//alt + Delete key
	    	} else if(e.altKey && e.keyCode == 46) {
	    		wmi3_window_key_deleteRow();
	    	}	
  			break;
  				
		default :
  			break;
	}
  		
  	
	
	
});

// ============================================================	
// main search 입력
// gTHEMETY : 메뉴테마방식
// 			MAIN1SD  = Left Single Doc
// 			MAIN2TAB = Left TAB Doc
// 			MAIN3TAB = Top TAB Doc
// ============================================================
function mainSearchKeyevent(tprogcmd) {
	try {
		//var tprogcmd = o.value.toLowerCase();
		var tproleng = tprogcmd.length;
		var gprocode = tprogcmd.substr(0,1); //키워드 @상품명  #오더번호
		var gprodata = tprogcmd.substr(1, tproleng-1); //키워드 검색어
		let gTHEMETY = $("#gSessionDatas")[0].getAttribute('gTHEMETY') // 사용자 테마 타입

		let targetIDs;
		let targetObj; //프로그램 ID 검색
		let gnatitle;		   
		

		switch ( gprocode ) 
		{
			case "@": 
				// 상품정보 조회
				targetIDs = $("#sideMenuBar").find("a[mid=mdu2]");
				targetObj = targetIDs[0]; //프로그램 ID 검색
				gnatitle = targetObj.getAttribute("title");				
				if (gTHEMETY == 'MAIN1SD') {
					location.href = '/main/singlePageCMD.do?gprogcmd=/md/mdu2&gprogrid=mdu2&gnatitle='+gnatitle+'&gprodata='+gprodata;
				} else if (gTHEMETY == 'MAIN2TAB' ) {
					addTab('/md/mdu2', 'mdu2', gnatitle, ' loding..' , gprodata);
					$("#mdu2_search").trigger("click");					
				} else if (gTHEMETY == 'MAIN3TAB' ) {
					addTab('/md/mdu2', 'mdu2', gnatitle, ' loding..' , gprodata);
					$("#mdu2_search").trigger("click");
				} else if (gTHEMETY == 'TEST' ) {
					addTab('/md/mdu2', 'mdu2', gnatitle, ' loding..' , gprodata);
					$("#mdu2_search").trigger("click");
				} else {
					console.log('window_keyEvent.js  not found method!!');
				}
		    	break;
		  	case "#":
		  		// 수주오더 관리
				targetIDs = $("#sideMenuBar").find("a[mid=oms3]");
				targetObj = targetIDs[0]; //프로그램 ID 검색
				gnatitle = targetObj.getAttribute("title");		  		
				if (gTHEMETY == 'MAIN1SD') {
					location.href = '/main/singlePageCMD.do?gprogcmd=/md/oms3&gprogrid=oms3&gnatitle='+gnatitle+'&gprodata='+gprodata;
				} else if (gTHEMETY == 'MAIN2TAB' ) {
					addTab('/md/mdu2', 'oms3', gnatitle, '...' , gprodata);
					$("#mdu2_search").trigger("click");					
				} else if (gTHEMETY == 'MAIN3TAB' ) {
					addTab('/md/mdu2', 'oms3', gnatitle, '...' , gprodata);
					$("#mdu2_search").trigger("click");					
				} else {
					console.log('window_keyEvent.js  not found method!!');
				}
		    	break;
		  	case "!":
		  		// 재고 조회
				targetIDs = $("#sideMenuBar").find("a[mid=wmvs1]");
				targetObj = targetIDs[0]; //프로그램 ID 검색
				gnatitle = targetObj.getAttribute("title");				  		
				if (gTHEMETY == 'MAIN1SD') {
					location.href = '/main/singlePageCMD.do?gprogcmd=/wm/wmvs1&gprogrid=wmvs1&gnatitle='+gnatitle+'&gprodata='+gprodata;
				} else if (gTHEMETY == 'MAIN2TAB' ) {
					addTab('/wm/wmvs1', 'wmvs1', gnatitle, '...' , gprodata);
					$("#wmvs1_SKUbtnSearch").trigger("click");					
				} else if (gTHEMETY == 'MAIN3TAB' ) {
					addTab('/wm/wmvs1', 'wmvs1', gnatitle, '...' , gprodata);
					$("#wmvs1_SKUbtnSearch").trigger("click");					
				} else {
					console.log('window_keyEvent.js  not found method!!');
				}
		  		break;
		  	default:
		  		mainProgramCMD(tprogcmd); 
		  		break;					  		
		} 
		
	} catch (error) {
		alert(error.message);
		console.log(error.message);
	}		
}

// ============================================================	
// 프로그램 바로가기
// ============================================================
function mainProgramCMD(tprogcmd){
	let targetObj   = null;
	let targetIDs = $("#sideMenuBar").find("a[mid='"+tprogcmd+"']");
	let targetNames = $("#sideMenuBar").find("a[title*='"+tprogcmd+"']");
	let gTHEMETY = $("#gSessionDatas")[0].getAttribute('gTHEMETY') // 사용자 테마 타입
	
	if(targetIDs.length > 0 )
		targetObj = targetIDs[0]; //프로그램 ID 검색
	else if (targetNames.length > 0)
		targetObj = targetNames[0]; //프로그램 Title  검색

	if (targetObj!=null) {
		let gprogcmd = targetObj.getAttribute("funurl");
		let gprogrid = targetObj.getAttribute("mid");
		let gnatitle = targetObj.getAttribute("title");
		let gcontent = '...';
		let gprodata = '';
		
		if (gTHEMETY == 'MAIN1SD' || gTHEMETY == 'TEST' ) {
			location.href = '/main/singlePageCMD.do?gprogcmd=' + gprogcmd + '&gprogrid=' + gprogrid + '&gnatitle=' + gnatitle+ '&gprodata='+gprodata;
		} else if (gTHEMETY == 'MAIN2TAB' ) {
			addTab(gprogcmd, gprogrid, gnatitle, gcontent, gprodata);					
		} else if (gTHEMETY == 'MAIN3TAB' ) {
			addTab(gprogcmd, gprogrid, gnatitle, gcontent, gprodata);					
		} else {
			location.href = '/main/singlePageCMD.do?gprogcmd=' + gprogcmd + '&gprogrid=' + gprogrid + '&gnatitle=' + gnatitle+ '&gprodata='+gprodata;
			console.log('window_keyEvent.js  not found method!!');
		}
		//location.href = '/main/singlePageCMD.do?gprogcmd=' + gprogcmd + '&gprogrid=' + gprogrid + '&gnatitle=' + gnatitle+ '&gprodata='+gprodata;
		//addTab(gprogcmd, gprogrid, gnatitle, gcontent, gprodata);
	} else {
		alert('프로그램이 없습니다. \n The program does not exist.');
		//alert( '[[#{sy.main.notProgram}]]' ); //프로그램이 없습니다.
	}	
}

// ============================================================	
// 프로그램 바로가기 (테마, 이동할 페이지, 넘길 값, 메서드)
// ============================================================
function moveProgram(gTHEMETY, tprogcmd, tprodata, tmethod){
	let targetObj   = null;
	let targetIDs = $("#sideMenuBar").find("a[mid='"+tprogcmd+"']");
	let targetNames = $("#sideMenuBar").find("a[title*='"+tprogcmd+"']");
	
	if(targetIDs.length > 0 )
		targetObj = targetIDs[0]; //프로그램 ID 검색
	else if (targetNames.length > 0)
		targetObj = targetNames[0]; //프로그램 Title  검색

	if (targetObj != null) {
		let gprogcmd = targetObj.getAttribute("funurl");
		let gprogrid = targetObj.getAttribute("mid");
		let gnatitle = targetObj.getAttribute("title");
		let gcontent = '...';
		let gprodata = '&';
		
		if(tprodata && (!tmethod || tmethod === 'GET')){
			gprodata += Object.entries(tprodata).map(e => e.join('=')).join('&');
		}
		
		if (gTHEMETY == 'MAIN1SD' || gTHEMETY == 'TEST' ) {
			if(!tmethod || tmethod === 'GET'){
				location.href = encodeURI('/main/singlePageCMD.do?gprogcmd=' + gprogcmd + '&gprogrid=' + gprogrid + '&gnatitle=' + gnatitle + gprodata);
			}else{
				tprodata.gprogcmd = gprogcmd;
				tprodata.gprogrid = gprogrid;
				tprodata.gnatitle = gnatitle;
				
				$.post( "/main/singlePagePostCMD.do", tprodata );
				
//				tprodata.push({0 : 'gprogcmd', 1 : gprogcmd})
//				tprodata.push({0 : 'gprogrid', 1 : gprogrid})
//				tprodata.push({0 : 'gnatitle', 1 : gnatitle})
//				
//				pageGoPost({
//					url: "/main/singlePagePostCMD.do",	//이동할 페이지
//				    vals: tprodata		//전달할 인수들 	
//				});
			}
		} else if (gTHEMETY == 'MAIN2TAB' ) {
			addTab(gprogcmd, gprogrid, gnatitle, gcontent, gprodata);					
		} else if (gTHEMETY == 'MAIN3TAB' ) {
			addTab(gprogcmd, gprogrid, gnatitle, gcontent, gprodata);					
		} else {
			location.href = '/main/singlePageCMD.do?gprogcmd=' + gprogcmd + '&gprogrid=' + gprogrid + '&gnatitle=' + gnatitle + gprodata;
			console.log('window_keyEvent.js  not found method!!');
		}
	} else {
		alert('프로그램이 없습니다. \n The program does not exist.');
	}	
}

function pageGoPost(d){
	var insdoc = "";
    
    for (var i = 0; i < d.vals.length; i++) {
	  insdoc+= "<input type='hidden' name='"+ d.vals[i][0] +"' value='"+ d.vals[i][1] +"'>";
	}
    
    console.log(d.vals);
	debugger;
    
	var goform = $("<form>", {
	  method: "post",
	  action: d.url,
	  html: insdoc
	}).appendTo("body");
    
	goform.submit();
}
 