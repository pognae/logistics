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
// 컬럼모델, 명령ID, 그리드페이지ID
	var gridWidth	= "auto";
	
	if(!colModel || !progrId || !gridId){
		alert("그리드 정보가 잘못되었습니다.");
		return;
	}else{
		// 사용자 그리드 셋팅 & 컬럼 레이아웃 & layoutSetting 툴 Name 가져오기.
		var data = callAjax("data", "GET", "/sy/syu10Select.do", { progrid : progrId, pgridid : gridId });
		// DB(SGRIDH, SGRIDL)에 저장되어있는 세팅값과 messages.properties에 있는 location에 따른 레이아웃 이름을 가져온다.
		// UserController -> UserService(Impl) -> UserMapper
		
		if(data.gridSettingLayout.length > 0){
			var t = data.gridSettingLayout;
			for(i=0; i<t.length; i++){
				this.numberCell = t[i].nubrcel; //번호표시
				this.hoverMode = t[i].hovermd; // hover모드
				this.freezeCols = t[i].frezcol; // 컬럼고정번호
				this.freezeRows = t[i].frezrow; // 열 고정번호
				this.columnBorders = t[i].colbodr; // 컬럼테두리표시
				this.rowBorders = t[i].rowbodr; // 열 테두리 표시
				this.stripeRows = t[i].strprow; // 열 반전 표시
				this.height = t[i].gheight; // 그리드 높이
			}
		} // SGRIDH 컬럼값들
				
		this.colModel = data.gridColumnLayout.length > 0 ? setColModel(colModel, data.gridColumnLayout) : colModel;
		// colModel 값 = SGRIDL에 레이아웃이 있으면 적용 아니면 입력받은 기본값으로
		this.progrId = progrId ? progrId : null;
		this.gridId = gridId ? gridId : null;
		
		layoutSettingName(data.layoutSettingName);
		// properties 이름 적용(locale)
	}
	
	// 그리드 타이틀 정의/재정의
	this.title;
	this.setTitle = function(title){
		this.title = title;
	} 
	
	// create 이벤트 정의/재정의
	this.create = function() {
		this.widget().pqTooltip();
	} // pqGrid 객체 중 pqTooltip을 가져옴
	this.setCreate = function(fun) {
		this.create = fun;
	}
	
	// change 이벤트 정의/재정의
	this.change = function(evt, ui) {
	}
	this.setChange = function(fun){
		this.change = fun;
	}
	
	// selectChange 이벤트 정의/재정의
	this.selectChange = function(evt, ui) {
	}
	this.setSelectChange = function(fun){
		this.selectChange = fun;
	}
	
	// cellBeforeSave 이벤트 정의/재정의
	this.cellBeforeSave = function(evt, ui){
	}
	this.setCellBeforeSave = function(fun){
		this.cellBeforeSave = fun;
	}
	
	// cellClick 이벤트 정의/재정의
	this.cellClick = function(evt, ui){
	}
	this.setCellClick = function(fun){
		this.cellClick = fun;
	}
	
	this.cellKeyDown = function(evt, ui){
	}
	this.setCellKeyDown = function(fun){
		this.cellKeyDown = fun;
	}
	
	// beforeValidate 이벤트 정의/재정의
	this.beforeValidate = function(evt, ui){
	}
	this.setBeforeValidate = function(fun){
		this.beforeValidate = fun;
	}
	
	// check 이벤트 정의/재정의
	this.check = function(evt, ui){
	}
	this.setCheck = function(fun){
		this.check = fun;
	}
	
	this.beforeCheck = function(evt, ui){
	}
	this.setBeforeCheck = function(fun){
		this.beforeCheck = fun;
	}
	
	// rowDblClick 이벤트 정의/재정의
	this.rowDblClick = function(evt, ui){
	}
	this.setRowDblClick = function(fun){
		this.rowDblClick = fun;
	}
	
	// editorKeyUp 이벤트 정의/재정의
	this.editorKeyUp = function(evt, ui){
	}
	this.setEditorKeyUp = function(fun){
		this.editorKeyUp = fun;
	}
	
	// beforeCheck 이벤트 정의/재정의
	this.beforeCheck = function(evt, ui){
	}
	this.setBeforeCheck = function(fun){
		this.beforeCheck = fun;
	}
	
	// rowInit 이벤트 정의/재정의
	this.rowInit = function(ui){
	}
	this.setRowInit = function(fun){
		this.rowInit = fun;
	}
	
	// cellDblClick 이벤트 정의/재정의
	this.cellDblClick = function(evt, ui){
	}
	this.setCellDblClick = function(fun){
		this.cellDblClick = fun;
	}
	
	// toolbar : find 이벤트 정의/재정의
	var findTb = false;
	this.setFind = function(fun){
		findTb = true;
	}
	
	// toolbar : add 이벤트 정의/재정의
	var addTb = false;
	var addFun = function(evt, ui){
	}
	this.setAdd = function(fun){
		addTb = true;
		addFun = fun;
	}
	
	// toolbar : reset 이벤트 정의/재정의
	var resetTb = false;
	this.setReset = function(fun){
		resetTb  = true;
	}
	
	// toolbar : delete 이벤트 정의/재정의
	var deleteTb = false;
	var deleteFun = function(evt, ui){
	}
	this.setDelete = function(fun){
		deleteTb = true;
		deleteFun = fun;
	}
	
	// toolbar : refresh 이벤트 정의/재정의
	var refreshTb = false;
	var refreshFun = function(evt, ui){
	}
	this.setRefresh = function(fun){
		refreshTb = true;
		refreshFun = fun;
	}

	function toolbarItems(){
		var items = new Array();
		
		items.push(
			// find 활성화 여부
			{ 	type: 'select',
				style: findTb === true ? '' : 'display: none;',
				cls: 'findColumn', //클래스명
				listener: findHandler, // 이벤트리스너
				options: function (ui) { 
					var CM = ui.colModel;
                    var opts = [{ '': TBallFields ? TBallFields : '[ All Fields ]' }];
                    for (var i = 0; i < CM.length; i++) {
                        var column = CM[i];
                        var obj = {};
                        if(column.dataIndx != 'state' && column.hidden != true && column.dataType != 'integer'){
    						obj[column.dataIndx] = column.title;
                        	opts.push(obj); // 콤보박스 채움
    					}
                    }
                    return opts;
				}
			},
			{ 	type: 'textbox', 
				style: findTb === true ? '' : 'display: none;',
				attr: 'placeholder="' + (TBenterYourKeyword ? TBenterYourKeyword : 'enter your keyword') + '"', //속성값
				cls: 'findValue',
				listener: { timeout: findHandler }
			},
			{ 	type: 'button',
            	style: findTb === true ? '' : 'display: none;', 
            	label: TBfind ? TBfind : 'Find', // 레이블
            	cls : 'pq-search-btn',
            	listener: focusHandler 
            },
            // reset 활성화 여부
			{ 	type: 'button', 
				style: resetTb === true ? 'float: right;' : 'display: none;',
				cls : 'pq-action-btn',
				label: "<i class='ui-button-icon-primary ui-icon ui-icon-refresh-f'></i>" + " " + (TBreset ? TBreset : 'Reset') + " ", 
				listener: function () {
					this.rollback();
					this.history({ method: 'resetUndo' });
				} 
			},
			// delete 활성화 여부
			{ 	type: 'button', 
				style: deleteTb === true ? 'float: right;' : 'display: none;',
				cls : 'pq-action-btn',
				label: "<i class='fa-regular fa-trash-can ui-icon-trash-f'></i>" + " " + (TBdelete ? TBdelete : 'Delete') + " ", 
				listener: deleteFun 
			},
            // add 활성화 여부
            { 	type: 'button', 
				style: addTb === true ? 'float: right;' : 'display: none;',
				cls: 'pq-tool-btns pq-action-btn', 
				label: "<i class='ui-button-icon-primary ui-icon ui-icon-add-f'></i>" + " " + (TBadd ? TBadd : 'Add') + " ", 
				listener: addFun 
			},
			// refresh 활성화 여부
			{ 	type: 'button', 
				style: refreshTb === true ? 'float: right;' : 'display: none;',
				cls : 'pq-action-btn',
				label: "<i class='ui-button-icon-primary ui-icon ui-icon-refresh-f'></i>" + " " + (TBrefresh ? TBrefresh : 'Refresh') + " ", 
				listener: refreshFun 
			}
		); // setter 값에 따라서 활성화(True)
		
		return items;
	}
	
	this.open = function(){
		var obj = {
			title: this.title,
			toolbar: { cls: "pq-toolbar-search", items: toolbarItems() },
			warning: { icon: '', style: '', cls: '' },
			bootstrap: { on: true },
			width: gridWidth,
			height: height = this.height === 0 ? 600 : this.height,
			colModel: this.colModel,	//각 개체가 열 속성에 해당하는 개체의 배열입니다. 
			contextMenu: {
	            on: true ,
	            headItems: layoutSetting ,   //header context menu items.
	            cellItems: layoutSetting     //body context menu items
	        },
	        numberCell: { show: numberCell = this.numberCell ? this.numberCell : false }, 		//row번호
	        hoverMode: hoverMode = this.hoverMode ? this.hoverMode : 'row',             //Mouse Over Mode(selection)
	        selectionModel: { type: 'cell', mode: 'block'},
	        freezeCols: freezeCols = this.freezeCols ? this.freezeCols : 0,
	        freezeRows: freezeRows = this.freezeRows ? this.freezeRows : 0,
	        columnBorders: columnBorders = this.columnBorders ? this.columnBorders : false,
	        rowBorders: rowBorders = this.rowBorders ? this.rowBorders : false,
	        stripeRows: stripeRows = this.stripeRows ? this.stripeRows : false,
	        groupModel: {
				on: false, 
				collapsed: [false, true],
				summaryInTitleRow: 'all',
			    merge: true,
				showSummary: [true, true],
				grandSummary: true
			},
	        gprogrid: this.progrId, 	//프로그램ID
	        resizable: true,             //그리드 사이즈 변경  
	        menuIcon: false,             //컬럼아이콘
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
	        create: this.create,	//그리드가 생성될 때 트리거됩니다.
	        change: this.change,	//인라인 셀 편집, 메소드 호출을 통한 행 추가/업데이트/삭제, 행/셀 붙여넣기, 실행 취소, 다시 실행으로 인해 그리드 데이터가 변경된 후 트리거됩니다.
	        selectChange: this.selectChange,
	        cellBeforeSave: this.cellBeforeSave,	//인라인 편집 중에 셀이 pqGrid에 저장되기 전에 트리거됩니다. false를 반환하여 데이터 저장을 취소할 수 있습니다.
	        cellClick : this.cellClick,	//
	        cellKeyDown : this.cellKeyDown,
	        beforeValidate : this.beforeValidate,	//
	        check : this.check,	//
	        beforeCheck : this.beforeCheck,
	        rowDblClick : this.rowDblClick,	//
	        editorKeyUp : this.editorKeyUp,	//
	        beforeCheck : this.beforeCheck,	//
	        rowInit : this.rowInit,	//
	        cellDblClick : this.cellDblClick,	//
		};
		
		this.gridDom = pq.grid('#' + this.gridId, obj);
		// 위의 obj를 가지고 grid 생성
    	
//    	this.gridDom = pq.grid('#' + this.gridId, obj).on("refresh refreshCell", function (evt, ui) {
//	        if (ui.source != 'flex') {
//	            this.flex();
//	        }
//    	});
	};
	
	//======== 2022.08.30 SWH 추가 ==========
	//======== grid Destroy =======
	this.destroy = function(){
		this.gridDom.destroy();
	} // gridDom 제거
	
	//======== grid dataModel =======
	this.searchList = function(data){
		this.gridDom.showLoading(); // 로딩창 보여줌
		this.gridDom.option( "dataModel", { data: data, recIndx: "rowkey" } ); // option
		this.gridDom.hideLoading(); // 로딩창 숨김
		this.gridDom.refreshDataAndView(); // 새로고침
	}
	
	this.getGrid = function(){
		return this.gridDom; // getter
	}
	
	this.getChanges = function(){
		return this.gridDom.getChanges({all:true}); // all:true의 의미를 모르겠음
	}
	
	//===========================================
	// Common Grid Preview (mdu2 참조) 
	//===========================================
	this.preViewShowPage = function(url, param){

		var urlParameters = "";
		var tURL = "";
		
		$("#"+gridId).closest(".grid-container").addClass("fl-block");
		$("#"+gridId).closest(".grid-container").find(".grid-preview").addClass("active");
		
		if(url != null){
			if(param != null) urlParameters = Object.entries(param).map(e => e.join('=')).join('&');
			tURL = url+"&"+urlParameters; // url자체에 ?가 포함 되어있나?

			$("#"+gridId).closest(".grid-container").find(".grid-preview").load(tURL);
		}
	} // param을 받고 url에 조합해서 grid 미리보기 페이지에 load시킴
	
	this.preViewShow = function(){
		var gridColDom 		= $("#"+gridId).closest(".row").find(".grid-col");
		var preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");

		preViewColDom.show(); // display:none 없앰
		
		gridColDom.removeClass(); // "grid-col col-md-12"
		gridColDom.addClass("grid-col col-md-9");
		
		preViewColDom.removeClass(); // "preview-col"
		preViewColDom.addClass("preview-col col-md-3");
		preViewColDom.find(".grid-preview").children().remove();
	}
	
	this.preViewAppendDom = function(dom){
		var preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");
		preViewColDom.find(".grid-preview").children().remove(); // 남아있는 경우가 있으면 지우기?
		preViewColDom.find(".grid-preview").append(dom);
	} // dom 추가
	
	this.preViewRemoveDom = function(dom){
		var preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");
		preViewColDom.find(".grid-preview").children().remove();
	} // dom 지우기
	
	this.preViewHide = function(){
		var gridColDom 		= $("#"+gridId).closest(".row").find(".grid-col");
		var preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");
		
		gridColDom.removeClass();
		gridColDom.addClass("grid-col col-md-12");
		
		preViewColDom.removeClass();
		preViewColDom.addClass("preview-col");
		preViewColDom.hide();
		preViewColDom.find(".grid-preview").children().remove();
	} // 미리보기창 숨기기
	
	this.preViewRemove = function(){
		var preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");
		preViewColDom.find(".grid-preview").children().remove();
	} // 미리보기창 제거
}

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
function exportData(format){
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

function findHandler(evt, ui) {
	var $toolbar = this.$top.find('.pq-toolbar-search'); // jquery 객체가 들어있다는 표시로 $를 넣음
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

function findRender(ui) {
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

