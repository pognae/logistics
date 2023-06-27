/* 
사용자 권한이 있는 프로그램 목록 가져오기
 */
function getUserMenuList(){  
  	var data = { };
	var aul = new AjaxUtil_Order("/main/userMenu.do", data, "GET");
		aul.setCallbackSuccess(function (data){
			jsonToMenu(data);
		});
		aul.send();	  
}


/* 
메뉴권한을 이용한 side 동적 메뉴 생성
 */
function jsonToMenu(json){ 
	let parent = document.querySelector('#sideMenuBar');  
	let menugbn = "";
	let menunam = "";
	let progrid = "";
	let progcmd = "";
	let menuico = "";
	let itemExs = " "; //Sub Item Show 처리 구분
	let itemAtv = " "; //Sub Item active 처리 구분		
	let newMenulvl = 0;
	let oldMenulvl = 0;
	let valMenulvl = 0; 
	let menuclass ="";
	let menuHtml = "";
	let addDHtml = ""; 
	let x = 0;
	let gfavoryn = $("#gSessionDatas")[0].getAttribute('gfavoryn') // 즐겨찾기 Open 여부
	
	//스크롤바 디자인 변경
	$("#sidebardiv").mCustomScrollbar(); 
 
	// 노드 추가 순환
	json.forEach((item)=>{
		x=x+1;
		newMenulvl = item.menulvl; 
		valMenulvl = newMenulvl - oldMenulvl;
		menugbn = item.menugbn; //FLD , PGM
	    progrid = item.progrid; //PG ID
	    progcmd = item.progcmd;
	    menunam = item.menunam;
	    menuico = item.menuico; 
	    // console.log(item );
	    if(valMenulvl < 0 ) {
	    	for (var i = valMenulvl; i<=0; i++ ) { 
	    		if (valMenulvl==i) {
	    			//addDHtml += "</ul>";
	    		}		    			
	    		else {
	    			addDHtml += "</ul></li>";
	    		}
	    	}
	    } else if (newMenulvl == 1 && newMenulvl == oldMenulvl ){//lv=1 의 서브 프로그램이 없을경우
			addDHtml += "</ul></li>";
		}
		
		if(progrid=='SYB' && gfavoryn=='Y') {
			itemExs = "show";
			itemAtv = "active";
		}
		else {
			itemExs = "";
			itemAtv = "";
		}		

		if(newMenulvl== 1) menuclass = "main-menu depth2";
		if(newMenulvl== 2) menuclass = "main-menu depth3";
		if(newMenulvl== 3) menuclass = "main-menu depth4";
		
	    if(valMenulvl != 0  ) {	 
	    	if(menugbn =='FLD' ){ 		
	    		addDHtml += "<li class='"+itemAtv+"'> ";
	    		addDHtml += "<span>"+menunam+"<i class='fa-solid fa-angle-down'></i></span>";
	    		addDHtml += "<ul class='"+ menuclass +"' id='" + progrid + "'>  ";
	    		
	    		//addDHtml += "<li class='"+itemAtv+"'> ";
	    		//addDHtml += "<a href='#"+progrid+"' data-toggle='collapse' aria-expanded='false' class='dropdown-toggle'><p"+newMenulvl+"><img src='"+menuico+"' width='20px' >" + menunam + "</p"+newMenulvl+"></a> ";
	    		//addDHtml += "<UL class='collapse list-unstyled "+ itemExs +"' id='" + progrid + "'>  ";
	    	} else {
				addDHtml += "<li>";
				addDHtml += "<a mid='"+progrid+"' funurl='"+progcmd+"' onclick='addTabMenu(this);' title='"+menunam+"' href='#' >"+ menunam + "</a>";
				addDHtml += "</li>";
	    		//addDHtml += " <LI><a mid='"+progrid+"' funurl='"+progcmd+"' onclick='addTabMenu(this);' title='"+menunam +'('+ progrid+ ')' + "' href='#' ><p"+newMenulvl+">"+ menunam + "</p"+newMenulvl+"></a></LI> ";
	    	}
	    } else {
	    	if(menugbn =='FLD' ){
	    		// 프로그램이 없는 폴더일 경우
	    		addDHtml += "<li class='"+itemAtv+"'> ";
	    		addDHtml += "<span>"+menunam+"<i class='fa-solid fa-angle-down'></i></span>";
	    		addDHtml += "<ul class='"+ menuclass +"' id='" + progrid + "'>  ";
	    		
	    		//addDHtml += "<LI class='"+itemAtv+"'> ";
	    		//addDHtml += "<a href='#"+progrid+"' data-toggle='collapse' aria-expanded='false' class='dropdown-toggle'><p"+newMenulvl+">" + menunam + "</p"+newMenulvl+"></a> ";
	    		//addDHtml += "  <UL class='collapse list-unstyled' id='" + progrid + "'> ";
	    	} else {
				addDHtml += "<li>";
				addDHtml += "<a mid='"+progrid+"' funurl='"+progcmd+"' onclick='addTabMenu(this);' title='"+menunam+"' href='#' >"+ menunam + "</a>";
				addDHtml += "</li>";		
	    		//addDHtml += " <LI><a mid='"+progrid+"' funurl='"+progcmd+"' onclick='addTabMenu(this);' title='"+menunam+'('+progrid+ ')'+ "'  href='#'><p"+newMenulvl+">"+ menunam + "</p"+newMenulvl+"></a></LI>";
	    	}
	    } 

	    oldMenulvl = newMenulvl; 
	    
	    //console.log('[' + x + '] ' + addDHtml );
	    menuHtml += addDHtml;
	    addDHtml = "";
	}
	);
	//end loop
		
	
	// ul 마루리로 닫기
    if(newMenulvl > 0 ) {
    	for (var i = 1; i<newMenulvl; i++ ) {
	    	if (i==newMenulvl ) {
	    		//addDHtml += "</UL>  ";
	    	}
	    	else { 
	    		addDHtml += "</ul></li>  \r"
	    	} 
    	}
    }
    //console.log('[end]' + addDHtml );
    menuHtml += addDHtml;
    addDHtml = "";
    
	document.getElementById('sideMenuBar').innerHTML = menuHtml ;
	
}

//북마크 추가
function BookmarkAdd(progrid){
	var targetObj = $("#sideMenuBar").find("a[mid='"+progrid+"']");
	var progcmd = targetObj[0].getAttribute("funurl");
	var menunam = targetObj[0].getAttribute("title");
	var newMenulvl = 2;
							 
	let addDHtml = "<LI><a mid='"+progrid+"' funurl='"+progcmd+"' title='"+menunam+' '+progrid+"'  href='/main/singlePageCMD.do?gprogcmd="+progcmd+"&gprogrid="+progrid+"&gnatitle="+menunam+"'><p"+newMenulvl+">"+ ' '+menunam + "</p"+newMenulvl+"></a></LI>";
	$("#SYB").append(addDHtml);	
}

//북마크 삭제
function BookmarkDelete(progrid){	
	$("#SYB").find("a[mid='"+progrid+"']").remove() ;		
}