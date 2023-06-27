/**
 * JQuery mainnavigationjtab
 */
var ggActivPG = 'Home';
$(document).ready(function() {
 
	$(document).on( "click", "#tabs a.tab", function( ) {  
		// Get the tab name
        let gprogrid = $(this).attr("id"); //a테크 아이디
        //console.log('[js/main/1/jqueryTab.js] event : tab a.tab click id='+mid);
        var contentname = gprogrid + "_content";

        // hide all other tabs
        allHiddenTab();

        // show current tab
        $("#" + contentname).show();
        //$("#tabli_" + mid ).removeClass("default"); //ksw
        $("#tabli_" + gprogrid ).addClass("current");
        $('#content px').removeAttr('disabled');
      
	    console.log(' -------------------------------  '  );
	    console.log('(click) : Active Program : ' + gprogrid );
	    ggActivPG = gprogrid;
	    console.log(' -------------------------------  '  );
	    
	    // Tab bookmark ReSize 
	    settabs();  
	});

	$(document).on( "click", "#tabs a.remove", function( ) {
    	// Get the tab name
        var gprormid = $(this).parent().find(".tab").attr("id");

        // remove tab and related content
        var contentname = gprormid + "_content";
        $("#" + contentname).remove();
        $(this).parent().remove();

        // if there is no current tab and if there are still tabs left, show the first one
        if ($("#tabs li.current").length == 0 && $("#tabs li").length > 0) {

        	// find the first tab    
            var firsttab = $("#tabs li:first-child");
            //firsttab.removeClass("default");//ksw
            firsttab.addClass("current");

            // get its link name and show related content
            var gprogrid = $(firsttab).find("a.tab").attr("id");
            $("#" + gprogrid + "_content").show();
            
		    console.log(' -------------------------------  '  );
		    console.log('(Remove) : Active Program : ' + gprogrid );
		    ggActivPG = gprogrid;
		    console.log(' -------------------------------  '  );            
		}
	    
	    // Tab bookmark ReSize 
	    settabs();	
	});
});
        
// ------------------------------------
// 프로그램 메뉴클릭으로 시랭
// mid = gprogrid
// funurl = gprogcmd
// ------------------------------------     
function addTabMenu(link) {
    let gprogrid= $(link).attr("mid");   
    let gprogcmd = $(link).attr("funurl");
    let gnatitle = $(link).attr("title");
    let gcontent = "loading... ";
    let gprodata = $(link).attr("gprodata"); // 메뉴에서 데이터를 프로그램에 전송 할 경우 사용할 예정
 	
	addTab(gprogcmd, gprogrid, gnatitle, gcontent, gprodata);
}  
    
// ------------------------------------
// 프로그램 바로 가기
// ------------------------------------
function addTab(gprogcmd, gprogrid, gnatitle, gcontent, gprodata) {
	let gshtitle = gnatitle;
	
    console.log(' -------------------------------  '  );
    console.log('(addTab) : Active Program : ' + gprogrid );
    console.log('           addTabMenu=' + gprogrid + ' ' + gprogcmd + ' ' + gnatitle);
    ggActivPG = gprogrid;
    console.log(' -------------------------------  '  );
    	
	//활성화 되어있는 프로그램일 경우
    if ($("#" + gprogrid).length != 0) { 
        allHiddenTab();  
        $("#" + gprogrid + "_content").show();
        //$("#tabli_" + gprogrid ).removeClass("default");  //ksw
		$("#tabli_" + gprogrid ).addClass("current");
		$("#mdu2_skudesc").val(gprodata);
		$("#" + gprogrid + "_content").load('/main/tabPageCMD.do'+'?gprogrid='+gprogrid+'&gprogcmd='+gprogcmd+'&gnatitle='+encodeURI(gnatitle)+'&gprodata='+encodeURI(gprodata) );
        return;
    }
    
    allHiddenTab();
    let titlelen = getStringLength(gnatitle); //문자열 길이
    if(titlelen>16)
    	gshtitle = getStrCut(gnatitle,20);
    else 
    	gshtitle = gnatitle; //setleadingSpaces(gnatitle, 16);
    	
    	
    
    //append -> prepend 2022.11.15 ksw
    $("#tabs").prepend("<li id='tabli_"+gprogrid+"' style='width:200px;' class='current' ><a class='tab' id='" + gprogrid + "'  href='#' title='"+ gnatitle + "' >" + gshtitle + "</a><a href='#' class='remove'>x</a></li>");
	$("#content").append("<px id='" + gprogrid + "_content'>" + gcontent + "</px>");
    $("#" + gprogrid + "_content").load('/main/tabPageCMD.do'+'?gprogrid='+gprogrid+'&gprogcmd='+gprogcmd+'&gnatitle='+encodeURI(gnatitle)+'&gprodata='+encodeURI(gprodata) );
    
    // Tab bookmark ReSize 
    settabs();
}
        
function allHiddenTab() {
    $("#content px").hide();
    $('#content px').attr('disabled', true);
    $("#tabs li").removeClass("current");
   // $("#tabs li").addClass("ddd");//ksw
} 
  
  
var tabContainerWidth = 0;  
var tabListWidth = 0;
var tabCount = 0; 
var tabCount2 = 0; 
function settabs(){  			        		
	//var tabs = $('.tabs');	
	if($('.tabs').length === 0) return;
	//var tabLen = $('.tabs').find('li').length;
	 tabContainerWidth = $('.tabs').parent().width();
	 tabListWidth = 0;
	//var prevBtn = $('.tab-prev');
	//var nextBtn = $('.tab-next');

	$('.tabs').find('li').each(function(idx){		
		if( idx === 0) return; 
		tabListWidth = tabListWidth + $(this).outerWidth();
	})
	//console.log('settabs :  tabListWidth=' + tabListWidth);
	if( tabContainerWidth > tabListWidth ) {
		$('.tab-prev').addClass('disabled');
		$('.tab-next').addClass('disabled');
	} else{
		$('.tab-prev').on('click', tabToLeft);
		$('.tab-next').on('click', tabToRight);
		
		$('.tab-prev').removeClass('disabled');
		$('.tab-next').removeClass('disabled');
	}  
	$('.tab-close').on('click', tabAllClose);
}	


function tabToLeft(){ 
	//var tabs = $('.tabs'); 
	if($('.tabs').length === 0) return;  
		 
	//var tabLen = $('.tabs').find('li').length;
	tabContainerWidth = $('.tabs').parent().width();  
		
	$('.tab-next').removeClass('disabled');
	var marginRight = 0;
	++tabCount2;
	marginRight += $('.tabs').find('li').eq(tabCount).outerWidth();
	
	//console.log('tabToLeft  : margin-left='+parseInt($(tabs).css('margin-left')) + ' marginRight=' + marginRight);
	if( parseInt($(tabs).css('margin-left'))+ marginRight > 0 ){
		return; 
		
	} else if( parseInt($(tabs).css('margin-left'))+ marginRight + 50 === 0 ){
		 $('.tab-prev').addClass('disabled');
	}

	$('.tabs').css('margin-left', parseInt($(tabs).css('margin-left'))+ marginRight);

	--tabCount;

}

function tabAllClose(){
	let tabs = $(this).parent().find(".tab");
	let tabid;
	let contentname;
	if($('.tabs').length === 0) return;
	for (var i = 0; i < tabs.length-1; i++) {
		tabid = tabs[i].id;
        contentname = tabid + "_content";
        $("#" + contentname).remove();
        //$("#" + contentname).parent().remove();
        $("#tabli_" + tabid).remove();
        //$("#tabli_" + tabid).parent().remove();
        		
	}	
	
    if ($("#tabs li.current").length == 0 && $("#tabs li").length > 0) {
    	// find the first tab    
        var firsttab = $("#tabs li:first-child");
        
        //firsttab.removeClass("default"); //ksw
        firsttab.addClass("current");
        // get its link name and show related content
        var gprogrid = $(firsttab).find("a.tab").attr("id");
        $("#" + gprogrid + "_content").show();
        
	    console.log(' -------------------------------  '  );
	    console.log('(allClose) : Active Program : ' + gprogrid );
	    ggActivPG = gprogrid;
	    console.log(' -------------------------------  '  );        
	}
			 
}

function tabToRight(){ 
	 
	//var tabs = $('.tabs'); 
	if($('.tabs').length === 0) return;  
		 
	//var tabLen = $('.tabs').find('li').length;
	tabContainerWidth = $('.tabs').parent().width();
			
	$('.tab-prev').removeClass('disabled');
	var marginLeft = 0;
	
	++tabCount;
	//console.log('tabToRight : count='+tabCount + ' tabListWidth=' + tabListWidth);
	for (var i = 1; i < tabCount+1; i++) {
		marginLeft +=  $('.tabs').find('li').eq(i).outerWidth();
		if( tabListWidth - marginLeft < tabContainerWidth ){
			$('.tab-next').addClass('disabled');
		}
		$('.tabs').css('margin-left', - marginLeft);
	}  
}	