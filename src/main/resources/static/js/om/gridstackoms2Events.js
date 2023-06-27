//============================================================	
// 위젯 셋팅 이벤트
//============================================================
var wzGridData, wzGridCM, wzGridTitle, wzGridLay, wzGridRowDblClick
function setwzGridData(gridData, gridCM, gridTitle, gridLay, gridRowDblClick){
	wzGridData = gridData;
	wzGridCM = gridCM;
	wzGridTitle = gridTitle;
	wzGridLay = JSON.parse(gridLay);
	wzGridRowDblClick = gridRowDblClick;
}

function setGridStack(gprogrid){
	
	var grid_stsck = GridStack.init({
	  	float: true,
	  	cellHeight: 30, //블럭 height 설정
	  	column: 10, 	// 그리드 컬럼 갯수 설정
	  	minRow: 20,		//최소 행 수. 기본값은 0
	  	acceptWidgets: true,	//다른 그리드 또는 외부에서 드래그한 위젯을 허용.(기본값: false) 
	  	removable: true,		//그리드 외부로 드래그하여 위젯을 제거
	  	disableOneColumnMode: true, //그리드 너비가 minW보다 작으면 oneColumnMode를 비활성화합니다(기본값: 'false').
	  	staticGrid: true	//드래그|드롭|크기 조정을 제거합니다(기본값 false). 
  	});
  	
  	GridStack.setupDragIn('.widget-container .grid-stack-item', { appendTo: 'body', helper: 'clone' });
  	
  	if(wzGridLay){
		grid_stsck.load(wzGridLay,true);
		
		for(var i=0; i<wzGridLay.length; i++){
			var	oms2_wzgrid = new GridUtil(wzGridCM[0][wzGridLay[i].id], gprogrid, wzGridLay[i].id);
   			if(wzGridTitle){
				oms2_wzgrid.setTitle(wzGridTitle[0][wzGridLay[i].id]);
			}
			if(wzGridRowDblClick){
				oms2_wzgrid.setRowDblClick(wzGridRowDblClick[0][wzGridLay[i].id]);
			}
   			oms2_wzgrid.open();
 	  			
  	  		var wzgrid = pq.grid('#'+wzGridLay[i].id);
 	  		wzgrid.option( "dataModel", { data: wzGridData[0][wzGridLay[i].id] } );
 	  		wzgrid.refreshDataAndView();
  	  	}

		wzGridLay.forEach(function(item) {
			$('#' + item.wzid).hide();
		});
	}
  	
  	$('#' + gprogrid + '_toggle_switch').change(function() {
		let b = $(this).prop('checked'); 
 		grid_stsck.setStatic(b);
	});
	
	$('#' + gprogrid + '_toggle_save').click(function () {
		delete serializedFull;
  		serializedData = grid_stsck.save(true,false);
  		data = {
			progrid : gprogrid,
			laydata : JSON.stringify(serializedData)
		};
		var aul = new AjaxUtil_Order("/om/wzGridSave.do", data);
		aul.setCallbackSuccess(function (data){
			alert("저장 성공하였습니다.");
		});
		aul.send();
	});
	
	$('#' + gprogrid + '_toggle_clear').click(function () {
		grid_stsck.removeAll();
	});
  	
  	grid_stsck.on('added', function(event, items) {
		items.forEach(function(item) {
			grid_AddWz(item);
			$('#' + item.el.getAttribute('gs-wzid')).hide();
		});
	});
	
	grid_stsck.on('removed', function(event, items) {
		items.forEach(function(item) {
			$('#' + item.el.getAttribute('gs-wzid')).show();
		});
	});
	
	grid_stsck.on('change', function(event, items) {
    	items.forEach(function(item) {
		});
	});

  	grid_stsck.on('enable', function(event) {
  	});

  	grid_stsck.on('disable', function(event) {
  	});

  	grid_stsck.on('dragstart', function(event, el) {
  	});

	grid_stsck.on('drag', function(event, el) {
  	});

  	grid_stsck.on('dragstop', function(event, el) {
  	});

  	grid_stsck.on('dropped', function(event, previousWidget, newWidget) {
	    if (previousWidget) {
	    }
	    if (newWidget) {	
	    }
	});

  	grid_stsck.on('resizestart', function(event, el) {
	});

	grid_stsck.on('resize', function(event, el) {
	});

	grid_stsck.on('resizestop', function(event, el) {
		let gGridID = el.getAttribute('gs-id');
		let gHeight = el.clientHeight-25;
		
		pq.grid('#' + gGridID).option ("height", gHeight);
		pq.grid('#' + gGridID).refresh();
		pq.grid('#' + gGridID).refreshDataAndView();
	});
	
	return grid_stsck;
}

//위젯을 그리드로 표현
function grid_AddWz(t){
	
	var gProgID = t.el.getAttribute('gs-progid');
	var gGridID = t.el.getAttribute('gs-id');
	var gHeight = t.el.clientHeight-25;
	
	t.el.innerHTML = '<div class="grid-stack-item-content"><div id="'+gGridID +'" style="height:'+gHeight+'px;"></div></div>';
	
	
		var wz_gridobj = new GridUtil(wzGridCM[0][gGridID], gProgID, gGridID);
		if(wzGridTitle){
			wz_gridobj.setTitle(wzGridTitle[0][wz_gridobj.gridId]);
		}
		if(wzGridRowDblClick){
			wz_gridobj.setRowDblClick(wzGridRowDblClick[0][wz_gridobj.gridId]);
		}
		wz_gridobj.open();
		
		if(wzGridData){
			var grid = pq.grid('#'+wz_gridobj.gridId);
	   		grid.option( "dataModel", { data: wzGridData[0][wz_gridobj.gridId] } );
	   		grid.refreshDataAndView();
		}
}

//기존 위젯 그리드 데이터 change
function changewzGridData(tgGridId, tgGridData){
	if($("#"+tgGridId).length > 0){
		var tgGrid = pq.grid("#"+tgGridId);
		tgGrid.option( "dataModel", { data: tgGridData } );
		tgGrid.refreshDataAndView();
		
		wzGridData[0][tgGridId] = tgGridData;
	}
}
