/**
 * pqgrid 공동 기능 js
 */
//============================================================
// layoutSetting  : Name Default 셋팅
//============================================================	
var LOlayoutSave, LOlayoutSetting, LOlayoutReset, LOexport, LOexportCsv, LOexportHtml, LOexportJson, LOexportXlsx
  , LOnumberCell, LOselectionModel, LOselectionModelCell, LOselectionModelRow, LOfreeze, LOcolsCancel
  , LOrowsCancel, LOfreezeCol, LOfreezeRow, LOcolumnBorders, LOrowBorders, LOstripeRows, LOaddNodes
  , LOundo, LOredo, LOgroupModel, LOcopy, LOpaste, TBallFields, TBenterYourKeyword, TBfind, TBadd, TBreset, TBdelete, TBrefresh;
//============================================================
//공통 그리드
//============================================================	
var GridUtil = function(colModel, progrId, gridId){
	//colModel : 각 열의 속성(title, width, dataIndex..)배열을 정의
	//progrId : gridId를 생성하는데 사용되는 문자열
	//gridId : 그리드의 id를 나타냄
	
	var gridWidth	= "auto";	// gridWidth는 auto로 설정
	
	if(!colModel || !progrId || !gridId){  // colModel, progrId, gridId 중 하나라도 빈 값이면 alert창 띄우기
		alert("그리드 정보가 잘못되었습니다.");
		return;
	}else{
		// 사용자 그리드 셋팅 & 컬럼 레이아웃 & layoutSetting 툴 Name 가져오기.
		var data = callAjax("data", "GET", "/sy/syu10Select.do", { progrid : progrId, pgridid : gridId });
		//common.js에 있는 callAjax 함수 호출
		if(data.gridSettingLayout.length > 0){	//gridSettingLayout : SGRIDL테이블 조회
			var t = data.gridSettingLayout;
			for(i=0; i<t.length; i++){
				this.numberCell = t[i].nubrcel;		//new GridUtil로 생성한 객체의 numberCell(행 번호)의 값은 t[i].nubrcel
				this.hoverMode = t[i].hovermd;		//new GridUtil로 생성한 객체의 hoverMode의 값은 t[i].hovermd
				this.freezeCols = t[i].frezcol;		//new GridUtil로 생성한 객체의 freezeCols(고정시킬 총 열 개수 지정)의 값은 t[i].frezcol
				this.freezeRows = t[i].frezrow;		//new GridUtil로 생성한 객체의 freezeRows(고정시킬 총 행 개수 지정)의 값은 t[i].frezrow
				this.columnBorders = t[i].colbodr;	//new GridUtil로 생성한 객체의 columnBorders(세로 테두리)의 값은 t[i].colbodr
				this.rowBorders = t[i].rowbodr;		//new GridUtil로 생성한 객체의 rowBorders(가로 테두리)의 값은 t[i].rowbodr
				this.stripeRows = t[i].strprow;		//new GridUtil로 생성한 객체의 stripeRows(그리드의 행에 대한 스타일을 교대로 지정하는 기능)의 값은 t[i].strprow
				this.height = t[i].gheight;			//new GridUtil로 생성한 객체의 heigh(높이)t의 값은 t[i].gheight
			}
		}
		
		this.colModel = data.gridColumnLayout.length > 0 ? setColModel(colModel, data.gridColumnLayout) : colModel;
		// data.gridColumnLayout.length > 0 이면 new GridUtil로 생성한 객체의 colModel 값이 setColModel(colModel, data.gridColumnLayout) 이고 아니라면 매개변수로 받은 colModel
		this.progrId = progrId ? progrId : null;
		// new GridUtil로 객체를 생성할 때 매개변수 progrId 값을 입력하면 그 값이 this.progId의 값이고, progId의 값을 입력하지 않았다면 this.progId의 값은 null
		this.gridId = gridId ? gridId : null;
		// new GridUtil로 객체를 생성할 때 매개변수 gridId 값을 입력하면 그 값이 this.gridId의 값이고, gridId의 값을 입력하지 않았다면 this.gridId의 값은 null
		
		layoutSettingName(data.layoutSettingName);	//layoutSetting : 언어 설정?
		// layoutSettingName 함수 호출, data.layoutSettingName을 매개변수로 받음
	}
	
	// 그리드 타이틀 정의/재정의
	this.title;
	this.setTitle = function(title){	// new GridUtil로 생성한 객체의 title의 값은 해당 객체의 setTitle 함수를 호출해서 매개변수로 입력받은 title 값이다.
		this.title = title;
	} 
	
	// create 이벤트 정의/재정의
	this.create = function() {
		this.widget().pqTooltip();	// new GridUtil로 객체 생성 후 create 함수를 호출하면 해당 객체의 widget 함수의 pqTooltip 함수가 호출된다.
	}
	this.setCreate = function(fun) {  // new GridUtil로 객체 생성 후 setCreate 함수를 호출하면 create 함수는 fun 함수로 재정의된다.
		this.create = fun;
	}
	
	// change 이벤트 정의/재정의
	this.change = function(evt, ui) {	// new GridUtil로 객체 생성 후 change 함수를 호출하면 빈 함수이므로 동작하는게 없다.
	}
	this.setChange = function(fun){		// new GridUtil로 객체 생성 후 setChange 함수를 호출하면 change 함수는 fun 함수로 재정의된다.
		this.change = fun;
	}
	
	// selectChange 이벤트 정의/재정의
	this.selectChange = function(evt, ui) {		// new GridUtil로 객체 생성 후 selectChange 함수를 호출하면 빈 함수이므로 동작하는게 없다.
	}
	this.setSelectChange = function(fun){		// new GridUtil로 객체 생성 후 setSelectChange 함수를 호출하면 selectChange 함수는 fun 함수로 재정의된다.
		this.selectChange = fun;
	}
	
	// cellBeforeSave 이벤트 정의/재정의
	this.cellBeforeSave = function(evt, ui){	// new GridUtil로 객체 생성 후 cellBeforeSave 함수를 호출하면 빈 함수이므로 동작하는게 없다.
	}
	this.setCellBeforeSave = function(fun){		// new GridUtil로 객체 생성 후 setCellBeforeSave 함수를 호출하면 cellBeforeSave 함수는 fun 함수로 재정의된다.
		this.cellBeforeSave = fun;
	}
	
	// cellClick 이벤트 정의/재정의
	this.cellClick = function(evt, ui){		// new GridUtil로 객체 생성 후 cellClick 함수를 호출하면 빈 함수이므로 동작하는게 없다
	}
	this.setCellClick = function(fun){		// new GridUtil로 객체 생성 후 setCellClick 함수를 호출하면 cellClick 함수는 fun 함수로 재정의된다.
		this.cellClick = fun;
	}
	
	this.cellKeyDown = function(evt, ui){		// new GridUtil로 객체 생성 후 cellKeyDown 함수를 호출하면 빈 함수이므로 동작하는게 없다
	}
	this.setCellKeyDown = function(fun){		// new GridUtil로 객체 생성 후 setCellKeyDown 함수를 호출하면 cellKeyDown 함수는 fun 함수로 재정의된다.
		this.cellKeyDown = fun;
	}
	
	// beforeValidate 이벤트 정의/재정의
	this.beforeValidate = function(evt, ui){	// new GridUtil로 객체 생성 후 beforeValidate 함수를 호출하면 빈 함수이므로 동작하는게 없다
	}
	this.setBeforeValidate = function(fun){		// new GridUtil로 객체 생성 후 setBeforeValidate 함수를 호출하면 beforeValidate 함수는 fun 함수로 재정의된다.
		this.beforeValidate = fun;
	}
	
	// check 이벤트 정의/재정의
	this.check = function(evt, ui){		// new GridUtil로 객체 생성 후 check 함수를 호출하면 빈 함수이므로 동작하는게 없다
	}
	this.setCheck = function(fun){		// new GridUtil로 객체 생성 후 setCheck 함수를 호출하면 check 함수는 fun 함수로 재정의된다.
		this.check = fun;
	}
	
	this.beforeCheck = function(evt, ui){		// new GridUtil로 객체 생성 후 beforeCheck 함수를 호출하면 빈 함수이므로 동작하는게 없다
	}
	this.setBeforeCheck = function(fun){		// new GridUtil로 객체 생성 후 setBeforeCheck 함수를 호출하면 beforeCheck 함수는 fun 함수로 재정의된다.
		this.beforeCheck = fun;
	}
	
	// rowDblClick 이벤트 정의/재정의
	this.rowDblClick = function(evt, ui){		// new GridUtil로 객체 생성 후 rowDblClick 함수를 호출하면 빈 함수이므로 동작하는게 없다
	}
	this.setRowDblClick = function(fun){		// new GridUtil로 객체 생성 후 setRowDblClick 함수를 호출하면 rowDblClick 함수는 fun 함수로 재정의된다.
		this.rowDblClick = fun;
	}
	
	// editorKeyUp 이벤트 정의/재정의
	this.editorKeyUp = function(evt, ui){		// new GridUtil로 객체 생성 후 editorKeyUp 함수를 호출하면 빈 함수이므로 동작하는게 없다
	}
	this.setEditorKeyUp = function(fun){		// new GridUtil로 객체 생성 후 setEditorKeyUp 함수를 호출하면 editorKeyUp 함수는 fun 함수로 재정의된다.
		this.editorKeyUp = fun;
	}
	
	// beforeCheck 이벤트 정의/재정의
	this.beforeCheck = function(evt, ui){		// new GridUtil로 객체 생성 후 beforeCheck 함수를 호출하면 빈 함수이므로 동작하는게 없다
	}
	this.setBeforeCheck = function(fun){		// new GridUtil로 객체 생성 후 setBeforeCheck 함수를 호출하면 beforeCheck 함수는 fun 함수로 재정의된다.
		this.beforeCheck = fun;
	}
	
	// rowInit 이벤트 정의/재정의
	this.rowInit = function(ui){		// new GridUtil로 객체 생성 후 rowInit 함수를 호출하면 빈 함수이므로 동작하는게 없다
	}
	this.setRowInit = function(fun){	// new GridUtil로 객체 생성 후 setRowInit 함수를 호출하면 rowInit 함수는 fun 함수로 재정의된다.
		this.rowInit = fun;
	}
	
	// cellDblClick 이벤트 정의/재정의
	this.cellDblClick = function(evt, ui){		// new GridUtil로 객체 생성 후 cellDblClick 함수를 호출하면 빈 함수이므로 동작하는게 없다
	}
	this.setCellDblClick = function(fun){		// new GridUtil로 객체 생성 후 setCellDblClick 함수를 호출하면 cellDblClick 함수는 fun 함수로 재정의된다.
		this.cellDblClick = fun;
	}
	
	// toolbar : find 이벤트 정의/재정의
	var findTb = false;
	this.setFind = function(fun){		// findTb가 false로 정의 되어있는데 new GridUtil로 객체 생성 후 setFind 함수를 호출하면 findTb가 true로 재정의된다.
		findTb = true;
	}
	
	// toolbar : add 이벤트 정의/재정의
	var addTb = false;
	var addFun = function(evt, ui){
	}
	this.setAdd = function(fun){		// addTb가 false로, addFun 함수는 evt, ui를 매개변수로 받는 빈 함수로 정의 되어있는데 new GridUtil로 객체 생성 후 setAdd 함수를 호출하면 addTb는 true로, addFun 함수는 fun 함수로 재정의된다.
		addTb = true;
		addFun = fun;
	}
	
	// toolbar : reset 이벤트 정의/재정의
	var resetTb = false;  
	this.setReset = function(fun){		// resetTb가 false로 정의 되어있는데 new GridUtil로 객체 생성 후 setReset 함수를 호출하면 resetTb가 true로 재정의된다.
		resetTb  = true;
	}
	
	// toolbar : delete 이벤트 정의/재정의
	var deleteTb = false;
	var deleteFun = function(evt, ui){
	}
	this.setDelete = function(fun){		// deleteTb가 false로, deleteFun 함수는 evt, ui를 매개변수로 받는 빈 함수로 정의 되어있는데 new GridUtil로 객체 생성 후 setAdd 함수를 호출하면 deleteTb는 true로, deleteFun 함수는 fun 함수로 재정의된다.
		deleteTb = true;
		deleteFun = fun;
	}
	
	// toolbar : refresh 이벤트 정의/재정의
	var refreshTb = false;
	var refreshFun = function(evt, ui){
	}
	this.setRefresh = function(fun){	// refreshTb가 false로, refreshFun 함수는 evt, ui를 매개변수로 받는 빈 함수로 정의 되어있는데 new GridUtil로 객체 생성 후 setAdd 함수를 호출하면 refreshTb는 true로, refreshFun 함수는 fun 함수로 재정의된다.
		refreshTb = true;
		refreshFun = fun;
	}

	function toolbarItems(){
		var items = new Array();	// items 배열 객체 생성
		
		items.push(  // 아래 구조체들을 items에 push
			// find 활성화 여부
			{ 	type: 'select',		// type은 select
				style: findTb === true ? '' : 'display: none;',		// findTb가 true면 보이고 아니면 안보이게
				cls: 'findColumn',			// class이름 findColumn
				listener: findHandler,		// findHandlerd 이벤트 발생
				options: function (ui) { 
					var CM = ui.colModel;
                    var opts = [{ '': TBallFields ? TBallFields : '[ All Fields ]' }];
                    for (var i = 0; i < CM.length; i++) {
                        var column = CM[i];
                        var obj = {};
                        if(column.dataIndx != 'state' && column.hidden != true && column.dataType != 'integer'){
    						obj[column.dataIndx] = column.title;
                        	opts.push(obj);
    					}
                    }
                    return opts;
				}
			},
			{ 	type: 'textbox', 	// type은 textbox
				style: findTb === true ? '' : 'display: none;',
				attr: 'placeholder="' + (TBenterYourKeyword ? TBenterYourKeyword : 'enter your keyword') + '"',
				//	placeholor는 TBenterYourKeyword 값이 있다면 TBenterYourKeyword, 값이 없으면 enter your keyword
				cls: 'findValue',		// class이름 findValue
				listener: { timeout: findHandler }		// timeout:findHandler 이벤트 발생 => 사용자가 검색 기능 사용 시 검색 대상 데이터 찾을 때까지 대기할 시간 지정	
			},
			{ 	type: 'button',		// type은 button
            	style: findTb === true ? '' : 'display: none;',
            	label: TBfind ? TBfind : 'Find',		//label은 TBfind, 없으면 Find
            	cls : 'pq-search-btn',		// class이름 pq-search-btn
            	listener: focusHandler		//focusHandler 이벤트 발생
            },
            // reset 활성화 여부
			{ 	type: 'button',		// type은 button
				style: resetTb === true ? 'float: right;' : 'display: none;',		// resetTb true면 float: right; 아니면 안보이게
				// resetTb가 true면 float:right, 아니면 안보이게
				cls : 'pq-action-btn',		// class이름 pq-action-btn
				label: "<i class='ui-button-icon-primary ui-icon ui-icon-refresh-f'></i>" + " " + (TBreset ? TBreset : 'Reset') + " ", 
				// label은 <i class='ui-button-icon-primary ui-icon ui-icon-refresh-f'></i> + TBreset(없으면 Reset)
				listener: function () {
					this.rollback();	// 이벤트 발생 : rollback 함수 실행 => 이전으로 되돌리기
					this.history({ method: 'resetUndo' });	//이전 상태를 기록하고 이력 관리 수행(사용자가 실수로 데이터를 삭제하거나 변경한 경우, 이전 상태로 되돌릴 수 있다) => 취소/다시실행 기능에서 사용		
				}
			},
			// delete 활성화 여부
			{ 	type: 'button',		// type은 button
				style: deleteTb === true ? 'float: right;' : 'display: none;',		// deleteTb true면 float: right; 아니면 안보이게
				// deleteTb true면 float:right, 아니면 안보이게
				cls : 'pq-action-btn',		// class이름 pq-action-btn
				label: "<i class='fa-regular fa-trash-can ui-icon-trash-f'></i>" + " " + (TBdelete ? TBdelete : 'Delete') + " ", 
				// label은 <i class='fa-regular fa-trash-can ui-icon-trash-f'></i> + TBdelete(없으면 Delete)
				listener: deleteFun		//  deleteFun 이벤트 발생
			},
            // add 활성화 여부
            { 	type: 'button',		// type은 button
				style: addTb === true ? 'float: right;' : 'display: none;',		// addTb true면 float: right; 아니면 안보이게
				// addTb true면 float:right, 아니면 안보이게
				cls: 'pq-tool-btns pq-action-btn',		// class이름 pq-tool-btns pq-action-btn
				label: "<i class='ui-button-icon-primary ui-icon ui-icon-add-f'></i>" + " " + (TBadd ? TBadd : 'Add') + " ", 
				// label은 <i class='ui-button-icon-primary ui-icon ui-icon-add-f'></i> + TBadd(없으면 Add)
				listener: addFun		// addFun 이벤트 발생
			},
			// refresh 활성화 여부
			{ 	type: 'button',		//type은 button
				style: refreshTb === true ? 'float: right;' : 'display: none;',		// refreshTb true면 float: right; 아니면 안보이게
				cls : 'pq-action-btn',		// class이름 pq-action-btn
				label: "<i class='ui-button-icon-primary ui-icon ui-icon-refresh-f'></i>" + " " + (TBrefresh ? TBrefresh : 'Refresh') + " ",
				// label은 <i class='ui-button-icon-primary ui-icon ui-icon-refresh-f'></i> + TBrefresh(없으면 Refresh) 
				listener: refreshFun		// refreshFun 이벤트 발생
			}
		);
		
		return items;		// items 배열을 리턴
	}
	
	this.open = function(){		// new GridUtil로 생성한 객체의 open 함수 ==> 해당 객체의 속성값 설정
		var obj = {
			title: this.title,		//그리드 제목
			toolbar: { cls: "pq-toolbar-search", items: toolbarItems() },	//toolbar 설정
			warning: { icon: '', style: '', cls: '' },	//경고 메세지 설정
			bootstrap: { on: true },	//부트스트랩 CSS 사용 여부
			width: gridWidth,	//width는 gridWidth(=auto)로 설정  ==> 그리드 너비
			height: height = this.height === 0 ? 600 : this.height,	//그리드 높이, 0으로 설정하면 높이는 600으로 지정됨
			colModel: this.colModel,	//각 개체가 열 속성에 해당하는 개체의 배열입니다. 
			contextMenu: {	//그리드 내에서 마우스 우클릭 시 나타나는 메뉴의 속성 설정 
	            on: true ,
	            headItems: layoutSetting ,   //header context menu items.
	            cellItems: layoutSetting     //body context menu items
	        },
	        numberCell: { show: numberCell = this.numberCell ? this.numberCell : false }, 		//row(행)번호
			// numberCell : {show:(numberCell의 값이 있으면 해당 numbercell, 없으면 false)}
	        hoverMode: hoverMode = this.hoverMode ? this.hoverMode : 'row',             //Mouse Over Mode(selection)
			// hoverMode : 마우스 오버 시 선택되는 항목의 모드 설정
	        selectionModel: { type: 'cell', mode: 'block'},
	        // selectionModel : 그리드에서 선택되는 영역의 모드 설정
	        freezeCols: freezeCols = this.freezeCols ? this.freezeCols : 0,
			// freezeCols : freezeCols의 값이 있으면 해당 freezeCols 값 만큼 열 고정, 없으면 0
	        freezeRows: freezeRows = this.freezeRows ? this.freezeRows : 0,
			// freezeRows : freezeRows 값이 있으면 해당 freezeRows 값 만큼 행 고정, 없으면 0
	        columnBorders: columnBorders = this.columnBorders ? this.columnBorders : false,
			// columnBorders : columnBorders 값이 있으면(true) 열(세로) 테두리 그리기, 없으면 false(테두리 X)
	        rowBorders: rowBorders = this.rowBorders ? this.rowBorders : false,
			// rowBorders : rowBorders 값이 있으면(true) (행)가로 테두리 그리기, 없으면 false(테두리 X)
	        stripeRows: stripeRows = this.stripeRows ? this.stripeRows : false,
			// stripeRows : stripeRows 값이 있으면 행에 대한 스타일 교대로 지정, 없으면 false
	        groupModel: {	// 그룹화 설정
				on: false,	// 그룹화 활성화 여부
				collapsed: [false, true],	// 그룹화된 항목 축소 여부
				summaryInTitleRow: 'all',	// 그룹화된 항목의 요약정보를 표시할 위치
			    merge: true,	// 그룹화된 항목의 셀 병합 여부
				showSummary: [true, true],	// 그룹화된 항목의 요약 정보 표시 여부
				grandSummary: true	// 모든 그룹화된 항목의 총 요약 정보 표시 여부
			},
	        gprogrid: this.progrId, 	//그리드가 속한 프로그램 ID
	        resizable: true,             //그리드 크기 조절 가능 여부  
	        menuIcon: false,             //컬럼 아이콘
	        collapsible: {on: false, toggle: false},	//상단 툴바의 오른쪽 아이콘 두개(크게보기, 줄이기)
	        showTitle: this.title ? true : false,	//타이틀 나오는 상단 툴바
	        showToolbar: (findTb || addTb || resetTb || deleteTb || refreshTb) ? true : false,	//버튼 나오는 상단 툴바.
	        hwrap: false,	//줄 바꿈
	        wrap : false,	// true인 경우 셀의 텍스트가 다음 줄로 줄 바꿈되고 그렇지 않으면 넘친 텍스트가 숨겨지고 연속 기호 ...가 끝에 표시됩니다.
			rowHt: 32,	//그리드의 모든 행의 높이를 일정하게 설정합니다.
			trackModel: { on: true },	// 그리드 인라인 추가, 업데이트 및 삭제 작업에 대한 추적 속성을 설정
			postRenderInterval: -1,	//그리드 보기 새로 고침과 column.postRender 에 대한 첫 번째 호출 사이의 시간 간격(밀리초)을 결정합니다 .
	        scrollModel: { autoFit: false },	//Grid는 스크롤 없이 뷰포트에 모두 맞도록 열의 너비를 변경, 그리드 너비의 모든 크기 조정/변경은 열 너비의 비례 변경
	        editor: { select: true }, 	//전체 그리드에 대한 편집기 속성을 제공합니다.
			//flex: { on: false, one: false, all: false },	//이 옵션은 모든 셀 내용이 줄 바꿈 없이 한 줄에 표시되는 방식으로 열 또는 열 수를 설정하는 데 도움이 됩니다.
	        columnTemplate: columnTemplate = findTb ? {render: findRender} : false,	//이 옵션은 DRY 원칙에 따라 모든 열에서 속성이 반복되지 않도록 공통 열 속성을 정의합니다(반복하지 않음).
	        create: this.create,	//그리드가 생성될 때 트리거(이벤트 발생)됩니다.
	        change: this.change,	//인라인 셀 편집, 메소드 호출을 통한 행 추가/업데이트/삭제, 행/셀 붙여넣기, 실행 취소, 다시 실행으로 인해 그리드 데이터가 변경된 후 트리거됩니다.
	        selectChange: this.selectChange,	// 선택된 행이 변경될 때 이벤트 발생
	        cellBeforeSave: this.cellBeforeSave,	//인라인 편집 중에 셀이 pqGrid에 저장되기 전에 트리거됩니다. false를 반환하여 데이터 저장을 취소할 수 있습니다.
	        cellClick : this.cellClick,	// 셀을 클릭할 때 이벤트 발생
	        cellKeyDown : this.cellKeyDown,	// 셀에서 키보드를 누를때 이벤트 발생
	        beforeValidate : this.beforeValidate,	// 셀에 입력한 값의 유효성을 검사하기 전에 이벤트 발생
	        check : this.check,	// 체크박스 셀 관리 기능
	        beforeCheck : this.beforeCheck,	// 체크박스를 체크/언체크 하기 전에 이벤트 발생
	        rowDblClick : this.rowDblClick,	// 행 더블클릭 시 이벤트 발생
	        editorKeyUp : this.editorKeyUp,	// 셀 에디터에서 키보드를 눌렀다 떼었을 때 이벤트 발생
	        beforeCheck : this.beforeCheck,	// 
	        rowInit : this.rowInit,	// 각각의 행이 초기화 될 때 이벤트 발생
	        cellDblClick : this.cellDblClick,	// 셀을 더블클릭할 때 이벤트 발생
		};
		
		this.gridDom = pq.grid('#' + this.gridId, obj);
    	// new GridUtil로 생성한 객체의 gridDom 값은 pq.grid('#' + this.gridId, obj)
		// gridDom : 그리드의 뷰를 생성하고 관리하는 객체, pq.grid('#그리드명') ==> #그리드명 : id가 그리드명인 HTML 요소

//    	this.gridDom = pq.grid('#' + this.gridId, obj).on("refresh refreshCell", function (evt, ui) {
//	        if (ui.source != 'flex') {
//	            this.flex();
//	        }
//    	});
	};
	
	//======== 2022.08.30 SWH 추가 ==========
	//======== grid Destroy =======
	this.destroy = function(){		// new GridUtil로 생성한 객체의 destroy 함수는 해당 객체의 gridDom의 destroy 함수를 실행
		this.gridDom.destroy();		// destroy : 해당 그리드 기능을 제거하고 요소를 초기화 전 상태로 되돌림
	}
	
	//======== grid dataModel =======
	this.searchList = function(data){	// new GridUtil로 생성한 객체의 searchList 함수는 해당 객체의 gridDom의 showLoading, option, hideLoading, refreshDataAndView 함수를 실행  
		this.gridDom.showLoading();		// 로딩 보여주기
		this.gridDom.option( "dataModel", { data: data, recIndx: "rowkey" } );  //recIndx : record index
		this.gridDom.hideLoading();		//로딩 사라짐
		this.gridDom.refreshDataAndView();	// 새로고침
	}
	
	this.getGrid = function(){		// new GridUtil로 생성한 객체의 getGrid 함수는 해당 객체의 gridDom 값을 리턴
		return this.gridDom;
	}
	
	this.getChanges = function(){		// new GridUtil로 생성한 객체의 getChanges 함수는 해당 객체의 gridDom의 getChanges 함수를 리턴
		return this.gridDom.getChanges({all:true});	// 그리드에서 변경된 값 가져오기
	}
	
	//===========================================
	// Common Grid Preview (mdu2 참조) 
	//===========================================
	this.preViewShowPage = function(url, param){	//그리드 뷰의 미리보기 창을 화면에 표시(url : 미리보기 창에 표시할 url)

		var urlParameters = "";
		var tURL = "";
		
		$("#"+gridId).closest(".grid-container").addClass("fl-block");	//선택된 gridId로 지정된 id 속성을 가진 HTML 요소에서 가장 가까운 grid-container 클래스를 갖는 요소에 fl-block 클래스 추가
		$("#"+gridId).closest(".grid-container").find(".grid-preview").addClass("active");	//.find는 선택된 HTML 요소의 하위 요소 중 grid-preview 클래스를 갖는 요소를 선택해서 active 클래스 추가
		
		if(url != null){
			if(param != null) urlParameters = Object.entries(param).map(e => e.join('=')).join('&');
			tURL = url+"&"+urlParameters;

			$("#"+gridId).closest(".grid-container").find(".grid-preview").load(tURL);
		}
	}
	
	this.preViewShow = function(){	// 뷰 페이지 보여주기, 내용 초기화?
		var gridColDom 		= $("#"+gridId).closest(".row").find(".grid-col");
		var preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");

		preViewColDom.show();  // preViewColDom 보여주기?
		
		gridColDom.removeClass();	// gridColDom에 클래스 삭제
		gridColDom.addClass("grid-col col-md-9");	// gridColDom에 클래스 추가
		
		preViewColDom.removeClass();
		preViewColDom.addClass("preview-col col-md-3");
		preViewColDom.find(".grid-preview").children().remove();	// preViewColDom에 grid-preview 클래스를 가진 요소의 자식 요소 삭제
	}
	
	this.preViewAppendDom = function(dom){	// 미리보기 창에 새로운 dom 요소 추가
		var preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");
		preViewColDom.find(".grid-preview").children().remove();
		preViewColDom.find(".grid-preview").append(dom);	// preViewColDom에 grid-preview 클래스를 가진 요소에 dom 요소 추가
	}
	
	this.preViewRemoveDom = function(dom){	// dom 요소 제거
		var preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");
		preViewColDom.find(".grid-preview").children().remove();
	}
	
	this.preViewHide = function(){	// 미리보기 창 숨기기
		var gridColDom 		= $("#"+gridId).closest(".row").find(".grid-col");
		var preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");
		
		gridColDom.removeClass();
		gridColDom.addClass("grid-col col-md-12");
		
		preViewColDom.removeClass();
		preViewColDom.addClass("preview-col");
		preViewColDom.hide();	// preViewColDom 숨기기?
		preViewColDom.find(".grid-preview").children().remove();
	}
	
	this.preViewRemove = function(){	//미리보기 창 내용 삭제
		var preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");
			preViewColDom.find(".grid-preview").children().remove();
	}
}


//>>>>>>>>>>>>>>>>>>>>>>>>>여기까지 GridUtil<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<





//============================================================
//공통 그리드 : grid Column Layout 재정의
//============================================================
function setColModel(colModel, gridColMD){
	for(i=0; i<gridColMD.length; i++){
		for(j=0; j<colModel.length; j++){
			if(colModel[j].dataIndx == gridColMD[i].dataidx){
				//hidden 값 추가 부분
				if(gridColMD[i].phidden != null){
					colModel[j].hidden = gridColMD[i].phidden;
				}
				//Width 값 추가 부분
				if(gridColMD[i].pqwidth != null){
					colModel[j].width = gridColMD[i].pqwidth;
				}
				//순서 바꾸기
				const item = colModel.splice(j, 1);
				colModel.splice(gridColMD[i].sortnum, 0, item[0]);
			} 
		}
	}
	return colModel;
}

//============================================================
// layoutSetting
//============================================================
function layoutSetting(evt, ui){
    return [
        {
        	name: LOlayoutSave = LOlayoutSave ? LOlayoutSave : 'Layout Save',
        	action: function(evt, ui){
        		setLayoutSetting(this);
        	}
        },
        {
        	name: LOlayoutSetting = LOlayoutSetting ? LOlayoutSetting : 'Layout Setting',
        	action: function(evt, ui){
				modalgridlayoutshow(this);
        	}
        },
        {
        	name: LOlayoutReset = LOlayoutReset ? LOlayoutReset : 'Layout Reset',
        	action: function(evt, ui){
				setLayoutReset(this);
        	}
        },
        'separator',
        {
            name: LOexport = LOexport ? LOexport : 'Export',
            subItems: [ 
            	{
					name: LOexportCsv = LOexportCsv ? LOexportCsv : 'csv',
            	    action: function(){ 
						exportData.call(this, 'csv'); 
					}
				},
                {
					name: LOexportHtml = LOexportHtml ? LOexportHtml : 'html', 
					action: function(){ 
						exportData.call(this, 'html');
					}
				},
                {
					name: LOexportJson = LOexportJson ? LOexportJson : 'json', 
					action: function(){ 
						exportData.call(this, 'json');
					}
				},
                {
					name: LOexportXlsx = LOexportXlsx ? LOexportXlsx : 'xlsx', 
					action: function(){ 
						exportData.call(this, 'xlsx');
					}
				}
            ]                        
        },
        'separator',
        {
        	name: LOnumberCell = LOnumberCell ? LOnumberCell : 'Row Number',
        	action: function(evt, ui){
        		var numberCell = this.option( "numberCell" );
        		this.option("numberCell.show", !numberCell.show);
        		this.refresh();
        	}
        },  
        {
        	name: LOselectionModel = LOselectionModel ? LOselectionModel : 'Selection Model',
        	subItems: [
        		{
					name: LOselectionModelCell = LOselectionModelCell ? LOselectionModelCell : 'cell',  
					action: function( ){
						this.option( "hoverMode", "cell");
						this.refresh();
        			}
        		},
        		{
					name: LOselectionModelRow = LOselectionModelRow ? LOselectionModelRow : 'row',
					action: function( ){
						this.option( "hoverMode", "row");
						this.refresh();
        			}
        		}
        	],
        },        
        {
        	name: LOfreeze = LOfreeze ? LOfreeze : 'Freeze',
        	subItems: [
        		(this.option('freezeCols') ? { name: LOcolsCancel = LOcolsCancel ? LOcolsCancel : 'Cols Cancel', action: function(){ this.option('freezeCols', 0); this.refresh(); } } : null),
        		(this.option('freezeRows') ? { name: LOrowsCancel = LOrowsCancel ? LOrowsCancel : 'Rows Cancel', action: function(){ this.option('freezeRows', 0); this.refresh(); } } : null),
        		'separator',
        		{name: LOfreezeCol = LOfreezeCol ? LOfreezeCol : 'Col Freeze',  action: function(evt, ui){ this.option("freezeCols", ui.colIndx+1); this.refresh();}},
        		{name: LOfreezeRow = LOfreezeRow ? LOfreezeRow : 'Row Freeze',  action: function(evt, ui){ this.option("freezeRows", ui.rowIndx+1); this.refresh();}}
        	],
        },	 
        {
        	name: LOcolumnBorders = LOcolumnBorders ? LOcolumnBorders : 'Column Borders',
        	action: function(evt, ui){
        		var b = this.option( "columnBorders" );
        		this.option("columnBorders", !b);
        	}
        },
        {
        	name: LOrowBorders = LOrowBorders ? LOrowBorders : 'Row Borders',
        	action: function(evt, ui){
        		var b = this.option( "rowBorders" );
        		this.option("rowBorders", !b);
        	}
        },	
        {
        	name: LOstripeRows = LOstripeRows ? LOstripeRows : 'Stripe Rows',
        	icon: 'ui-icon ui-icon-shuffle',
        	action: function(evt, ui){
        		var b = this.option( "stripeRows" );
        		this.option("stripeRows", !b);
        		this.refresh();
        	}
        },
        'separator',
        {
            name: LOundo = LOundo ? LOundo : 'Undo',
            icon: 'ui-icon ui-icon-arrowrefresh-1-n',
            disabled: !this.History().canUndo(), 
            action: function(evt, ui){
                this.History().undo();
            }
        },
        {
            name: LOredo = LOredo ? LOredo : 'Redo',
            icon: 'ui-icon ui-icon-arrowrefresh-1-s',
            disabled: !this.History().canRedo(), 
            action: function(evt, ui){
                this.History().redo();
            }
        },
        'separator',
        {
			name: LOgroupModel = LOgroupModel ? LOgroupModel : 'Data Group',			 
			icon: 'ui-icon ui-icon-calculator',
            action: function (evt, ui) {
				var b = this.option( "groupModel.on" );
                this.Group().option({ on: !b });
            }
		},
        'separator',
        {
            name: LOcopy = LOcopy ? LOcopy : 'Copy',
            icon: 'ui-icon ui-icon-copy',
            shortcut: 'Ctrl - C',
            tooltip: "Works only for copy / paste within the same grid",
            action: function(){
                this.copy();
            }
        },
        {
            name: LOpaste = LOpaste ? LOpaste : 'Paste',
            icon: 'ui-icon ui-icon-clipboard',
            shortcut: 'Ctrl - V',
            action: function(){                        
                this.paste();
            }
        }
    ]
}

//============================================================
// layoutSetting : Layout 툴 Name 재정의
//============================================================
function layoutSettingName(t){
	for(i=0; i<t.length; i++){
		LOlayoutSave = t[i].layoutSave;
		LOlayoutSetting = t[i].layoutSetting;
		LOlayoutReset = t[i].layoutReset;
		LOexport = t[i].export;
		LOexportCsv = t[i].exportCsv;
		LOexportHtml = t[i].exportHtml;
		LOexportJson = t[i].exportJson;
		LOexportXlsx = t[i].exportXlsx;
		LOnumberCell = t[i].numberCell;
		LOselectionModel = t[i].selectionModel;
		LOselectionModelCell = t[i].selectionModelCell;
		LOselectionModelRow = t[i].selectionModelRow;
		LOfreeze = t[i].freeze;
		LOcolsCancel = t[i].colsCancel;
		LOrowsCancel = t[i].rowsCancel;
		LOfreezeCol = t[i].freezeCol;
		LOfreezeRow = t[i].freezeRow;
		LOcolumnBorders = t[i].columnBorders;
		LOrowBorders = t[i].rowBorders;
		LOstripeRows = t[i].stripeRows;
		LOaddNodes = t[i].addNodes;
		LOundo = t[i].undo;
		LOredo = t[i].redo;
		LOgroupModel = t[i].groupModel;
		LOcopy = t[i].copy;
		LOpaste = t[i].paste;
		
		TBallFields = t[i].allFields;
		TBenterYourKeyword = t[i].enterYourKeyword;
		TBfind = t[i].find;
		TBadd = t[i].add;
		TBreset = t[i].reset;
		TBdelete = t[i].delete;
		TBrefresh = t[i].refresh;
	}
}

//============================================================
// layoutSetting : export 이벤트 정의
//============================================================
function exportData(format){	//그리드의 데이터를 다양한 형식으로 내보내기 위한 기능 제공
    var blob = this.exportData({
        format : format
    }) 
    if(typeof blob === "string"){                            
        blob = new Blob([blob]);
    }
    saveAs(blob, "binblur_"+ this.origOptions.gprogrid + "." + format );
}

//============================================================	
// Grid Find : Data 찾기 이벤트
//============================================================
var findRules = [];

function findHandler(evt, ui) {	//이벤트 핸들러 등록 시 해당 함수가 이미 등록되어 있는지 확인하고 이미 등록되어 있다면 해당 함수를 반환하여 중복 등록 방지
	var $toolbar = this.$top.find('.pq-toolbar-search');
	var value = $toolbar.find(".findValue").val();
	var dataIndx = $toolbar.find(".findColumn").val();
	
    if (dataIndx == "") {
    	var obj = [];
    	this.getColModel().map(function(column){                                    
        	if(column.dataIndx == 'state' || column.hidden == true || column.dataType == 'integer'){
        		return;
        	}else{
        		obj.push({ dataIndx: column.dataIndx, value: value });
        	}
        })
        findRules = obj;
    }else {
    	findRules = [{ dataIndx: dataIndx, value: value}];
	}
    this.refreshView();
}

function findRender(ui) {	// 특정 텍스트가 발견되면 해당 부분을 하이라이트 표시
	if(ui.cellData != null && ui.cellData != undefined && ui.cellData != '' && ui.dataIndx != 'state' && findRules.length > 0 && ui.dataType != 'integer'){
		var valType = typeof ui.cellData;
		var val = ui.cellData.toString();
		var valUpper = val.toUpperCase();
		var col = ui.dataIndx;
		var indx = -1;
		
		for(i=0; i<findRules.length; i++){
			var txt = findRules[i].value.toUpperCase();
			indx = valUpper.indexOf(txt);
			if(col === findRules[i].dataIndx){
				if (indx >= 0) {
					var txt1 = val.substring(0, indx);
					var txt2 = val.substring(indx, indx + txt.length);
					var txt3 = val.substring(indx + txt.length);
					return txt1 + "<span style='background:yellow;color:#333;'>" + txt2 + "</span>" + txt3;
				}
			}
		}
		return valType === "string" ? val : Number(val);
	}
}

//============================================================	
// Grid Find : Find Data 포커스 이벤트.
//============================================================
function focusHandler(evt, ui) {
	var $toolbar = this.$top.find('.pq-toolbar-search');
	var value = $toolbar.find(".findValue").val();
	
	if(value){
		var data = this.getData();
		var val = value.toString();
		var valUpper = val.toUpperCase();
		var indx = -1;
		var focusRules = [];
		var sel = this.Selection();
		var nowFocRowIndx, nowFocColIndx, nextFocRowIndx, nextFocColIndx;
		
		for(i=0; i<data.length; i++){
			for(j=0; j<findRules.length; j++){
				var dataVal = data[i][findRules[j].dataIndx] == null ? '' : data[i][findRules[j].dataIndx].toString();
				var dataValUpper = dataVal.toUpperCase();
				indx = dataValUpper.indexOf(valUpper);
				if (indx >= 0) {
					focusRules.push({ rowIndx: data[i].pq_ri, colIndx: this.getColIndx( { dataIndx: findRules[j].dataIndx } ) });
				}
			}
		}
		
		if(focusRules.length == 1){
			this.setSelection( {rowIndx: focusRules[0].rowIndx, colIndx: focusRules[0].colIndx} );
		}else if(focusRules.length > 1){
			for(i=0; i<focusRules.length; i++){
				var isselected = sel.isSelected({rowIndx: focusRules[i].rowIndx, colIndx: focusRules[i].colIndx});
				if(isselected == true){
					nowFocRowIndx = focusRules[i].rowIndx;
					nowFocColIndx = focusRules[i].colIndx;
					nextFocRowIndx = i == (focusRules.length - 1) ? focusRules[i-(focusRules.length-1)].rowIndx : focusRules[i+1].rowIndx;
					nextFocColIndx = i == (focusRules.length - 1) ? focusRules[i-(focusRules.length-1)].colIndx : focusRules[i+1].colIndx;
				}
			}
			if(!nextFocRowIndx && !nextFocColIndx){
				this.setSelection( {rowIndx: focusRules[0].rowIndx, colIndx: focusRules[0].colIndx} );
			}else{
				this.setSelection( {rowIndx: nextFocRowIndx, colIndx: nextFocColIndx} );
			}
		}
	}
}

