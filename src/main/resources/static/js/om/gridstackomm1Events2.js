//----------------------------------
// 위젯 그리드 이벤트 설정
//----------------------------------
function addEvents(grid, id) {
	let g = (id !== undefined ? 'grid' + id + ' ' : '');

	grid.on('added removed change', function(event, items) {
    	let str = '';
    	let gwzid = '';
    	items.forEach(function(item) {
			gwzid = item.el.getAttribute('gs-wzid'); 
			str += ' (' + item.x + ',' + item.y + ' ' + item.w + 'x' + item.h + ')';
			//console.log(g + event.type + ' ' + items.length + ' items (x,y w h):' + str );
			
			// console.log ('event.type=' + event.type + ' id:=' + item.id + ' id=' +sourceID );
		    if (event.type == 'removed' ) {
				console.log(event.type);
				$('#' + gwzid).show();
			} else if (event.type == 'added') {
				//item.el.setAttribute('ProgID', item.el.getAttribute('gs-progid') );
				grid_AddWz(grid, id, item);
			}		 
		}); 
	});

  	grid.on('enable', function(event) {
    	let grid = event.target;
    	//console.log(g + 'enable');
  	});

  	grid.on('disable', function(event) {
    	let grid = event.target;
    	//console.log(g + 'disable');
  	});

  	grid.on('dragstart', function(event, el) {
	    let node = el.gridstackNode;
	    let x = el.getAttribute('gs-x'); // verify node (easiest) and attr are the same
	    let y = el.getAttribute('gs-y');
	    //console.log(g + 'dragstart ' + el.textContent + ' pos: (' + node.x + ',' + node.y + ') = (' + x + ',' + y + ')');
  	});

	grid.on('drag', function(event, el) {
	    let node = el.gridstackNode;
	    let x = el.getAttribute('gs-x'); // verify node (easiest) and attr are the same
	    let y = el.getAttribute('gs-y');
    // console.log(g + 'drag ' + el.textContent + ' pos: (' + node.x + ',' + node.y + ') = (' + x + ',' + y + ')');
  	});

  	grid.on('dragstop', function(event, el) {
	    let node = el.gridstackNode;
	    let x = el.getAttribute('gs-x'); // verify node (easiest) and attr are the same
	    let y = el.getAttribute('gs-y');
	    //console.log(g + 'dragstop ' + el.textContent + ' pos: (' + node.x + ',' + node.y + ') = (' + x + ',' + y + ')');
  	});

  	grid.on('dropped', function(event, previousWidget, newWidget) {		
	    if (previousWidget) {
	      	//console.log(g + 'dropped - Removed widget from grid:', previousWidget);
	      	gProgID = newWidget.el.getAttribute('gs-progid')
			gGridID = newWidget.el.getAttribute('gs-id'); 
	    }
	    
	    if (newWidget) {
			console.log(g + 'dropped - Added widget in grid:', newWidget);
			grid_AddWz(grid, id, newWidget);  
	    }
	});

  	grid.on('resizestart', function(event, el) {
	    let node = el.gridstackNode;
	    let w = el.getAttribute('gs-w');  // verify node (easiest) and attr are the same
	    let h = el.getAttribute('gs-h');
	    //console.log(g + 'resizestart ' + el.textContent + ' size: (' + node.w + 'x' + node.h + ') = (' + w + 'x' + h + ')');
	});

	grid.on('resize', function(event, el) {
    	let node = el.gridstackNode;
    	let w = el.getAttribute('gs-w');  // verify node (easiest) and attr are the same
    	let h = el.getAttribute('gs-h');
    	// console.log(g + 'resize ' + el.textContent + ' size: (' + node.w + 'x' + node.h + ') = (' + w + 'x' + h + ')');
	});

	grid.on('resizestop', function(event, el) {
	    let node = el.gridstackNode;
	    let w = el.getAttribute('gs-w'); // verify node (easiest) and attr are the same
	    let h = el.getAttribute('gs-h');
	    //console.log(g + 'resizestop ' + el.textContent + ' size: (' + node.w + 'x' + node.h + ') = (' + w + 'x' + h + ')');
		grid_refresh(grid, id, el);
	});
   
	
}

//그리드 refresh
function grid_refresh( grid, id, el){
	try {
		let gGridID = el.getAttribute('gs-id');
		let gHeight = el.clientHeight-25;
		     
		pq.grid('#' + gGridID).option ("height", gHeight);
		pq.grid('#' + gGridID).refresh();
		pq.grid('#' + gGridID).refreshDataAndView();
	} catch (error) {
		console.log(error);
	}
}

//위젯을 그리드로 표현
function grid_AddWz( grid, id, t){
		let gHtmlTx = "";
		let gProgID = "";
		let gGridID = "";
		let gwzid = "";
		let gHeight = 200;

		try {			
				gProgID = t.el.getAttribute('gs-progid');
				gGridID = t.el.getAttribute('gs-id');
				gwzid = t.el.getAttribute('gs-wzid');
				gHeight = t.el.clientHeight-25;
				gHtmlTx += '<div class="grid-stack-item-content"><div id="'+gGridID +'" style="height:'+gHeight+'px;"></div></div>';
				t.el.innerHTML = gHtmlTx;
				//gColumnCM = gColumn; //배열변수를 가져와야 한다.
				if (gGridID=="omm1_grid001"){ wz_gridobj = new GridUtil( omm1_grid001CM, gProgID, gGridID); }
				else if (gGridID=="omm1_grid002") {wz_gridobj = new GridUtil( omm1_grid002CM, gProgID, gGridID);}
				else if (gGridID=="omm1_grid003") {wz_gridobj = new GridUtil( omm1_grid003CM, gProgID, gGridID);}
				else if (gGridID=="omm1_grid004") {wz_gridobj = new GridUtil( omm1_grid004CM, gProgID, gGridID);}
				else if (gGridID=="omm1_grid005") {wz_gridobj = new GridUtil( omm1_grid005CM, gProgID, gGridID);}
				else {
					wz_gridobj = null;
					console.log("그리드 정보 오류");
				}	 
				wz_gridobj.open(); 
				wz_gridobj.getGrid().refresh();
				wz_gridobj.getGrid().refreshDataAndView();
				
				//위젯리스트에서 숨김
				$('#' + gwzid).hide();
			}  catch (error) {
	 			 console.log(error);
			}
	}