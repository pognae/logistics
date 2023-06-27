// ---------------------------------
// 원형 차트 1: 매출현황
// ---------------------------------
function setChartWC1(chartid, chartData){	
		var chartDom 	= document.getElementById(chartid);
		var myChart 	= echarts.init(chartDom);
		var option;
		let vData = [{ value: 0, name: '입고 현황' }];
		vData = chartData != null ? chartData : vData;
		option = { 
			tooltip:{trigger: 'item' },
		  	legend:{top: '5%', left: 'center' },	 
		  	series:[
		    			{
							// name: 'Access From',
							type: 'pie',
							radius: ['40%', '70%'],
							avoidLabelOverlap: false,
							label: {show: false, position: 'center'},
	      					emphasis: {label: {show: true,fontSize: '40',fontWeight: 'bold'}},
		      				labelLine: {show: false},
		      				data: vData
		    			}
		  			]
		};
		
		option && myChart.setOption(option);
		window.addEventListener('resize', myChart.resize);
} 
// ---------------------------------
// 원형 차트 2: 출고현황
// ---------------------------------
function setChartWC2(chartid, chartData){	
 
		
		var chartDom 	= document.getElementById(chartid);
		var myChart 	= echarts.init(chartDom);
		var option;
		let vData = [{ value: 0, name: '출고 현황' }];
		vData = chartData != null ? chartData : vData;
				
		option = {
  			//title: {text: 'Referer of a Website',subtext: 'Fake Data',left: 'center'},
			tooltip:{trigger: 'item' },
			visualMap: {show: false, min: 10, max: 1800,inRange: {colorLightness: [0.3, 1]}},
		  	//legend:{top: '5%', left: 'center' },
	  		legend:{orient: 'vertical',left: 'left' },
  			series: [
    					{
      						//name: 'Access From',
      						type: 'pie',
      						radius: '80%',
      						data: vData,
      						emphasis: {itemStyle: {shadowBlur: 10,shadowOffsetX: 0,shadowColor: 'rgba(0, 0, 0.1, 0.5)'}}
    					}
  					]
		};

		option && myChart.setOption(option);
		window.addEventListener('resize', myChart.resize);
} 
// ---------------------------------
// 원형 차트 3 : 도매 - 주간Worst
// ---------------------------------
function setChartWC3(chartid){	 
	
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;
	//var yData = ['.기모)네명MTM_백메란F','.기모)독수리MTM','.기모)레몬MTM','.기모)스페셜카라MTM_수박F','.기모)스피크MTM_검정F'];
	//var sData1 = [100, 249, 290, 4970, 744, 630];
	//var sData2 = [120, 289, 234, 970, 114, 630];
	var chtData = [{ value: 40, name: '.기모)네명MTM_백메란F' },{ value: 38, name: '기모)독수리MTM' },{ value: 32, name: '기모)레몬MTM' },
				   { value: 30, name: '기모)스페셜카라MTM_수박' },{ value: 28, name: '기모)스피크MTM_검정F' },{ value: 26, name: '스피크MTM_검정 6' },
					{ value: 22, name: '스피크MTM_검정 7' },{ value: 18, name: '스피크MTM_검정 8' },{ value: 18, name: '스피크MTM_검정 9' },
					{ value: 18, name: '스피크MTM_검정 10' }];	  
		option = { 
  			tooltip: {trigger: 'item'},
  			legend: {orient: 'vertical',left: 'left'},
  			series: [
    			{
      			name: 'Best',
      			type: 'pie',
      			radius: [30, 150],
      			center: ['40%', '50%'],
      			roseType: 'area',
      			left:200,
      			itemStyle: {borderRadius: 8},
				data: chtData ,
      			emphasis: {itemStyle: {shadowBlur: 10,shadowOffsetX: 0,shadowColor: 'rgba(0, 0, 0, 0.5)'}}
    			}
  			]
		};
 
	
		option && myChart.setOption(option);
		window.addEventListener('resize', myChart.resize);
}
// ---------------------------------
// 세로bar 차트 : binblur=입고수량, 플고수량, 출고처 입고처=출고현황
// ---------------------------------
function setCharthb1(chartid, chartDataX, chartDataS){	
	var dom = document.getElementById(chartid);
	var myChart = echarts.init(dom, null, {renderer: 'canvas',useDirtyRect: false});
	var app = {};
	var option;
	let xData = chartDataX != null ? chartDataX : ['23-01'];
	let sData = chartDataS != null ? chartDataS : [0];
	
	option = {
		tooltip: { trigger: 'axis',  axisPointer: {type: 'shadow'}},
		grid: { left: '20%', right: '0%', top:'10%', bottom: '10%'},
		xAxis: {type: 'category',data: xData },
		yAxis: {type: 'value'},
		dataZoom: [ { type: 'inside' }],
		series: [
					{
						data: sData,
		      			type: 'bar',
		      			showBackground: true,
		      			backgroundStyle: {color: 'rgba(180, 180, 180, 0.2)'},
      					itemStyle: {color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
							{ offset: 0, color: '#83bff6' },
							{ offset: 0.5, color: '#188df0' },
							{ offset: 1, color: '#188df0' }])
						}      			
		    		}
		  		]
	};

	// Enable data zoom when user click bar.
	const zoomSize = 6;
	myChart.on('click', function (params) {
  		console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
  		myChart.dispatchAction({
    		type: 'dataZoom',
    		startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
    		endValue:dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
  		});
	})

	if (option && typeof option === 'object') {
		myChart.setOption(option);
	}

	window.addEventListener('resize', myChart.resize);
}		

// ---------------------------------
// 세로bar-series6개 차트 : binblur=재고
// ---------------------------------
function setCharthb2(chartid, chartData){
	var app = {}; 
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;
	var vData = [ 	['재고일', 'D-2', 'D-1', 'Day']	];
	
	vData = chartData != null ? chartData : vData;
	
	option = {
		// legend: { },
		// legend:{top: '0%', left: 'center' },
		//legend: {orient: 'vertical',left: 'left'},
		legend:{top: '0%', left: 'center' },	
		tooltip: {trigger: 'axis', axisPointer: {type: 'shadow'}},
		grid: { left: '20%', right: '0%', top:'20%', bottom: '10%'},
	  	dataset: {
			source: vData
	  	},
	  	xAxis: { type: 'category' },
	  	yAxis: {},
	    dataZoom: [ { type: 'inside' }],
	  	series: [{ stack: 'one', type: 'bar' },
	  	         { stack: 'one', type: 'bar' },
	  	         { stack: 'one', type: 'bar' },
	  	         { stack: 'one', type: 'bar' },
	  	         { stack: 'one', type: 'bar' },
	  	         { stack: 'one', type: 'bar' }]
	};
	
	// Enable data zoom when user click bar.
	const zoomSize = 6;
	myChart.on('click', function (params) {
  		console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
  		myChart.dispatchAction({
    		type: 'dataZoom',
    		startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
    		endValue:dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
  		});
	})
		
	option && myChart.setOption(option);
	window.addEventListener('resize', myChart.resize);	
}	

// ---------------------------------
// 세로bar-sort 차트 : 출고SKU (사용안함)
// ---------------------------------
function setCharthb3(chartid, chartData){	
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;
	var vData = [
		['Hannah Krause', 41, 'Engineer', 314, '2011-02-12'],
		['Zhao Qian', 20, 'Teacher', 351, '2011-03-01'],
		['Jasmin Krause ', 52, 'Musician', 287, '2011-02-14'],
		['Li Lei', 37, 'Teacher', 219, '2011-02-18'],
		['Karle Neumann', 25, 'Engineer', 253, '2011-04-02'],
		['Adrian Groß', 19, 'Teacher', '-', '2011-01-16'],
		['Mia Neumann', 71, 'Engineer', 165, '2011-03-19'],
		['Böhm Fuchs', 36, 'Musician', 318, '2011-02-24'],
		['Han Meimei', 67, 'Engineer', 366, '2011-03-12']
	]
	
	vData = chartData != null ? chartData : vData;
	
	option = {
		dataset: [
	    	{
	    	dimensions: ['name', 'age', 'profession', 'score', 'date'],
	      	source: vData
			},
	    	{
			transform: {type: 'sort',config: { dimension: 'score', order: 'desc' }}
	    	}
		],
		xAxis: {type: 'category',axisLabel: { interval: 0, rotate: 30 }},
	  	yAxis: {},
	  	series: {type: 'bar',encode: { x: 'name', y: 'score' },datasetIndex: 1}
	};
	
	option && myChart.setOption(option);
	window.addEventListener('resize', myChart.resize);	
}	

// ---------------------------------
// 세로bar 차트 : 출고처(사용안함)
// ---------------------------------
function setCharthb4(chartid, chartDataX, chartDataS){	
	var dom = document.getElementById(chartid);
	var myChart = echarts.init(dom, null, {renderer: 'canvas',useDirtyRect: false});
	var app = {};
	var option;
	let xData = ['D-6', 'D-5', 'D-4', 'D-3', 'D-2', 'D-1', 'D'];
	let sData = [120, 200, 150, 80, 70, 110, 130];
	
	xData = chartDataX != null ? chartDataX : xData;
	sData = chartDataS != null ? chartDataS : sData;
	
		option = {xAxis: {type: 'category',data: xData},yAxis: {type: 'value'},
		series: [
		    		{
		      			data: sData,
		      			type: 'bar',
		      			showBackground: true,
		      			backgroundStyle: {color: 'rgba(180, 180, 180, 0.2)'}
		    		}
		  		]
		};


		if (option && typeof option === 'object') {
			myChart.setOption(option);
		}

		window.addEventListener('resize', myChart.resize);
}	

// ---------------------------------
// 세로bar 차트 : 미정(재고수량)
// ---------------------------------
function setCharthb5(chartid, chartData){	
	var app = {}; 
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;
			
	const posList = [
	  'left',
	  'right',
	  'top',
	  'bottom',
	  'inside',
	  'insideTop',
	  'insideLeft',
	  'insideRight',
	  'insideBottom',
	  'insideTopLeft',
	  'insideTopRight',
	  'insideBottomLeft',
	  'insideBottomRight'
	];
	
	app.configParameters = {
		rotate: {min: -90, max: 90},
		align: {options: {left: 'left',center: 'center',right: 'right'}},
		verticalAlign: {options: {top: 'top',middle: 'middle',bottom: 'bottom'}},
		position: {options: posList.reduce(function (map, pos) {map[pos] = pos;return map;}, {})},
	  	distance: {min: 0, max: 100}
	};
	
	app.config = {
		rotate: 90,
		align: 'left',
		verticalAlign: 'middle',
		position: 'insideBottom',
		distance: 15,
		onChange: function () {
	    	const labelOption = {
	      		rotate: app.config.rotate,
	      		align: app.config.align,
	      		verticalAlign: app.config.verticalAlign,
				position: app.config.position,
				distance: app.config.distance
			};
	    	myChart.setOption({series: [{ label: labelOption},{label: labelOption},{label: labelOption},{label: labelOption}]});
		}
	};
	const labelOption = {
		show: true,
		position: app.config.position,
		distance: app.config.distance,
		align: app.config.align,
		verticalAlign: app.config.verticalAlign,
		rotate: app.config.rotate,
		formatter: '{c}  {name|{a}}',
		fontSize: 16,
		rich: {name: {}}
	};
	
	option = {
		tooltip: {trigger: 'axis',axisPointer: {type: 'shadow'}},
		legend: {data: ['MOND', 'ELAND', 'SAMSUNG', 'LG']
	},
	toolbox: {
	    show: true,
	    orient: 'vertical',
	    left: 'right',
	    top: 'center',
	    feature: {
	    	mark: { show: true },
	      	dataView: { show: true, readOnly: false },
	      	magicType: { show: true, type: ['line', 'bar', 'stack'] },
	      	restore: { show: true },
	      	saveAsImage: { show: true }
		}
	},
	xAxis: [{
	    	type: 'category',
	      	axisTick: { show: false },
	      	data: ['11/02', '11/03', '11/04', '11/05', '11/06']
	    	}],
	yAxis: [{type: 'value'}],
	series: [
			{
			name: 'MOND',
			type: 'bar',
		    barGap: 0,
		    label: labelOption,
		    emphasis: {focus: 'series'},
		    data: [320, 332, 301, 334, 390]
		    },
		    {
		    name: 'ELAND',
		    type: 'bar',
		    label: labelOption,
		    emphasis: {focus: 'series'},
		    data: [220, 182, 191, 234, 290]
		    },
		    {
		    name: 'SAMSUNG',
		    type: 'bar',
		    label: labelOption,
		    emphasis: {focus: 'series'},
		    data: [150, 232, 201, 154, 190]
		    },
		    {
		    name: 'LG',
		    type: 'bar',
		    label: labelOption,
		    emphasis: {focus: 'series'},
			data: [98, 77, 101, 99, 40]
		    }
			]
	};
 	
	if (option && typeof option === 'object') {
		myChart.setOption(option);
	} 
	window.addEventListener('resize', myChart.resize);
}


// ---------------------------------
// 가로bar 차트 : 주간Best / worst
// ---------------------------------
function setChartWb1(chartid, chartDataX, chartDataS){	 
	  
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;

	//chartDataS = ['Brazil', 'Indonesia', 'USA', 'India', 'China', 'World'];
	
	chartDataX = chartDataX != null ? chartDataX : ["Default.\uBE14\uB799","Default.\uD654\uC774\uD2B8"];
	chartDataS = chartDataS != null ? chartDataS : [{"name": "이번주", "type": "bar", "data": [0,0,0,0,0]}, {"name": "지난주", "type": "bar", "data": [0,0,0,0,0]}];

	option = {
  		// title: { text: 'World Population'  },
  		tooltip: {    trigger: 'axis',    axisPointer: {type: 'shadow'}  },
  		legend: {},
  		grid: { left: '0%', right: '10%', top:'10%', bottom: '3%', containLabel: true  },
  		xAxis: { type: 'value', boundaryGap: [0, 0.01]  },
  		yAxis: { type: 'category',
    		data: chartDataX
		},
		series: chartDataS
		};

		option && myChart.setOption(option);
		window.addEventListener('resize', myChart.resize);
}

 
	
// ---------------------------------
// 가로bar 차트 : 주간Best / worst(사용안함)
// ---------------------------------
function setChartWb2(chartid, chartDataS, chartDataX){	 
	  
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;
	
	option = {
  		dataset: {
    		source: [
				['score', 'thsweek','lstweek', 'product'],
				[1, 5822, 1000,'Matcha Latte'],
				[2, 7824, 2000,'Milk Tea']
				[3, 4102, 3000,'Cheese Cocoa'],
				[4, 1275, 40000,'Cheese Brownie'],
				[5, 2015, 5000,'Matcha Cocoa'],
				[6, 7916, 60000,'Tea'],
				[7, 9852, 2,'Orange Juice'],
				[8, 11852, 2,'Lemon Juice'],
				[9, 30112, 2,'Walnut Brownie']
		    ]
		}, 
		legend:{top: '0%', left: 'center' },	
		tooltip: {trigger: 'axis', axisPointer: {type: 'shadow'}}, 
  		grid: { left: '0%', right: '10%', top:'10%', bottom: '3%', containLabel: true  },
  		xAxis: { name: 'thsweek' },
  		yAxis: { type: 'category'}, 
  		visualMap: {
    		//orient: 'horizontal',
    		//left: 'center',
    		min: 1,
    		max: 10,
    		text: ['Best', 'worst'],
    		// Map the score column to color
    		dimension: 0,
    		inRange: {color: ['#65B581', '#FFCE34', '#FD665F']}
  	},
  	series: [
    		{type: 'bar',encode: {x: 'thsweek',y: 'product'}}, 
    		{type: 'bar',encode: {x: 'lstweek',y: 'product'}}
  			]
	};	

	option && myChart.setOption(option);
	window.addEventListener('resize', myChart.resize);	
}

// ---------------------------------
// 가로Line 삼각형꼭지 차트 : owner=샘플현황,미송/미출현황 : 입고처-수주 현활
// ---------------------------------
function setChartl11(chartid, chartDataX, chartDataS){	 

	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;
	
	chartDataX = chartDataX != null ? chartDataX : ['D-6', 'D-5', 'D-4', 'D-3', 'D-2', 'D-1', 'D'];
	chartDataS = chartDataS != null ? chartDataS : [0, 0, 0, 0, 0, 0, 0];

	option = {
			grid: { left: '10%', right: '10%', top:'10%', bottom: '3%', containLabel: true  },
	  		xAxis: {type: 'category', data: chartDataX},
	  		yAxis: {type: 'value'},
	  		series: [
	    	{
	      		data: chartDataS,
	      		type: 'line',
	      		symbol: 'triangle',
	      		symbolSize: 20,
	      		lineStyle: {
	        	color: '#5470C6',
	        	width: 4,
	        	type: 'dashed'
	      		},
	      		itemStyle: {
	        		borderWidth: 1,
	        		borderColor: '#EE6666',
	        		color: 'yellow'
	      			}
	    		}
	  		]
	};
	
	option && myChart.setOption(option);
	window.addEventListener('resize', myChart.resize);
}
// ---------------------------------
// 가로Line 차트 : owner= 샘플현황(월), 미송/미출(월)현황 입고처=결재현황
// ---------------------------------
function setChartl12(chartid, chartDataX, chartDataS){ 
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;

	chartDataX = chartDataX != null ? chartDataX : ['2023-01'];
	chartDataS = chartDataS != null ? chartDataS : [{name: 'Default',type: 'line',stack: 'Total',data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]}];

	//let chartDataX;
	option = {
	  	tooltip: {trigger: 'axis'},
	  	legend: {left: 'center' },
	  	grid: {left: '3%',right: '4%',bottom: '3%',containLabel: true},
	  	//toolbox: {feature: {saveAsImage: {}}},
	  	xAxis: {type: 'category',boundaryGap: false,data: chartDataX},
	  	yAxis: {type: 'value'},
	  	series: chartDataS
	};

	option && myChart.setOption(option);
	window.addEventListener('resize', myChart.resize);
}
// ---------------------------------
// bar + line 차트 
//	var chtcate = [];
//	var chtdata = [];
//	chtcate[0] = "주문수량"; 막대차트
//	chtcate[1] = "주문횟수"; 라인차트
// Json_Arrayagg(POSTDAT) as XAXISDT -> chtdata[0] 범례
// Json_Arrayagg(SOITQTY) as SERIES1 -> chtdata[1] 막대데이터
// Json_Arrayagg(ORDRCNT) as SERIES2 -> chtdata[2] 라인데이터
// ---------------------------------
function setChartbl1(chartid, chtcate, chtdata)  {
	var dom = document.getElementById(chartid);
	var myChart = echarts.init(dom, null, {renderer: 'canvas',useDirtyRect: false});
	
	const colors = ['#5470C6', '#91CC75' ];
	option = {
		color: colors,
		tooltip: { trigger: 'axis', axisPointer: { type: 'cross' }  },
		grid: {left:'5%',right: '5%', bottom: '10%'  },
		// toolbox: {feature: {dataView: { show: true, readOnly: false },restore: { show: true },saveAsImage: { show: true }}},
		legend: { data: [chtcate[0], chtcate[1]]},
		xAxis: [
			{
  				type: 'category',
  				axisTick: {alignWithLabel: true},
  				// prettier-ignore
  				data: chtdata[0]
			}
		],
		yAxis: [
			{
  				type: 'value',
  				name: chtcate[0],
  				position: 'right',
  				alignTicks: true,
  				axisLine: {show: true,lineStyle: {color: colors[0]}},
  				axisLabel: {formatter: '{value}'}
			}, 
			{
  				type: 'value',
  				name: chtcate[1],
  				position: 'left',
  				alignTicks: true,
  				axisLine: {show: true,lineStyle: {color: colors[1]}},
  				axisLabel: {formatter: '{value}'}
			}
		],
		series: [
			{
  				name: chtcate[0],
  				type: 'bar',
  				data: chtdata[1]
			}, 
			{
  				name: chtcate[1],
  				type: 'line',
  				yAxisIndex: 1,
  				data: chtdata[2]
			}
		]
	};
	
	if (option && typeof option === 'object') {
		myChart.setOption(option);
	} 
	window.addEventListener('resize', myChart.resize);
	
}





// ---------------------------------
// WHOLESALE CHART
// ---------------------------------



// ---------------------------------
// bar 차트 : 매출(일)
// ---------------------------------
function setChartb1(chartid){	
	var dom = document.getElementById(chartid);
	var myChart = echarts.init(dom, null, {renderer: 'canvas', useDirtyRect: false});
	var app = {};
	var option;
	let xData = ['D-6', 'D-5', 'D-4', 'D-3', 'D-2', 'D-1', 'D'];
	let sData = [112000, 200000, 105000, 80000, 70000, 101000, 130000];
		option = {
			//title: { text: '총매출액(일)', subtext: '단위:원'},
			grid: { left: '10%', right: '0%', top:'10%', bottom: '10%'},
		  	xAxis: {
		    	type: 'category',
		    	data: xData
		  	},
		  	yAxis: {
		    	type: 'value'
		  	},
		  	series: [
		    {
		    	data: sData ,
		      	type: 'bar',
		      	showBackground: true,
		      	backgroundStyle: { color: 'rgba(180, 180, 180, 0.2)' }
		    }
		  	]
		};


		if (option && typeof option === 'object') {
			myChart.setOption(option);
		}

		window.addEventListener('resize', myChart.resize);
}		

// ---------------------------------
// bar 차트 : 매출(월)
// ---------------------------------
function setChartb2(chartid){
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;
	let dataAxis = ['2021-12',	'2022-01',	'2022-02',	'2022-03',	'2022-04',	'2022-05',	'2022-06',	'2022-07',	'2022-08',	'2022-09',	'2022-10',	'2022-11'];
	let data = [46500,	37800,	79000,	38200,	84200,	75400,	98100,	78300,	22100,	13300,	81300,	81800];
	let yMax = 999999999;
	let dataShadow = [];
		for (let i = 0; i < data.length; i++) {
  			dataShadow.push(yMax);
		}
	option = {
		//title: { text: '총매출액(월)', subtext: '월별 총 매출액'},
		grid: { left: '10%', right: '0%', top:'10%', bottom: '10%'},
		xAxis: {
			data: dataAxis,
			axisLabel: {inside: true, color: '#fff'},
			axisTick: {show: false},
			axisLine: {show: false },
			axisLabel: { interval: 0, rotate: 30 },
			z: 10
			
		},
		yAxis: {
			axisLine: {show: false},
		    axisTick: {show: false},
		    axisLabel: {color: '#999'}
	  	},
  		dataZoom: [ { type: 'inside' }],
  		series: [
    				{
      					type: 'bar',
      					showBackground: true,
      					itemStyle: {color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{ offset: 0, color: '#83bff6' },{ offset: 0.5, color: '#188df0' },{ offset: 1, color: '#188df0' }])},
      					emphasis: {itemStyle: {color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{ offset: 0, color: '#2378f7' },{ offset: 0.7, color: '#2378f7' },{ offset: 1, color: '#83bff6' }])}},
      					data: data
    				}
  				]
		};

	// Enable data zoom when user click bar.
	const zoomSize = 6;
	myChart.on('click', function (params) {
  		console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
  		myChart.dispatchAction({
    		type: 'dataZoom',
    		startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
    		endValue:dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
  		});
	})
	
	option && myChart.setOption(option);
	window.addEventListener('resize', myChart.resize);
}	

// ---------------------------------
// 원 차트 : 주간Worst
// ---------------------------------
function setChartWC4(chartid, x, chartDataS){	 
	
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;
	//var yData = ['.기모)네명MTM_백메란F','.기모)독수리MTM','.기모)레몬MTM','.기모)스페셜카라MTM_수박F','.기모)스피크MTM_검정F'];
	//var sData1 = [100, 249, 290, 4970, 744, 630];
	//var sData2 = [120, 289, 234, 970, 114, 630];
	
	chartDataS = chartDataS != null ? chartDataS : [
					{ value: 10, name: 'Default Sku1' },
					{ value: 20, name: 'Default Sku2' },
					{ value: 30, name: 'Default Sku3' },
					{ value: 40, name: 'Default Sku4' }
				];
				
		option = { 
  			tooltip: {trigger: 'item'},
  			legend: {orient: 'vertical',left: 'left'},
  			series: [
    			{
      			name: 'Best',
      			type: 'pie',
      			radius: [40, 100],
      			center: ['30%', '40%'],
      			roseType: 'area',
      			left:200,
      			itemStyle: {borderRadius: 8},
				data: chartDataS,
      			emphasis: {itemStyle: {shadowBlur: 10,shadowOffsetX: 0,shadowColor: 'rgba(0, 0, 0, 0.5)'}}
    			}
  			]
		};
 
	
		option && myChart.setOption(option);
		window.addEventListener('resize', myChart.resize);
}

// ---------------------------------
// 가로bar 차트 : 주간Worst
// ---------------------------------
function setChartWb3(chartid, chartDataX, chartDataS){	 
	
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;
	var yData = ['Default Sku1','Default Sku2','Default Sku3','Default Sku4','Default Sku5'];
	var sData1 = [18203, 23489, 29034, 104970, 131744];
	var sData2 = [18203, 23489, 29034, 104970, 131744];
	
	chartDataX = chartDataX != null ? chartDataX : yData;
	chartDataS = chartDataS != null ? chartDataS : [
					{
      				name: '지난주',
      				type: 'bar',
      				data: sData1
    				},
					{
      				name: '이번주',
      				type: 'bar',
      				data: sData2
    				}    				
  				];
	
	option = {
  		// title: { text: 'World Population'  },
  		tooltip: {    trigger: 'axis',    axisPointer: {      type: 'shadow'    }  },
  		legend: {},
  		grid: { left: '0%', right: '10%', top:'10%', bottom: '3%', containLabel: true  },
  		xAxis: { type: 'value', boundaryGap: [0, 0.01]  },
  		yAxis: { type: 'category',
    		data: yData
		},
		series: [
					{
      				name: '지난주',
      				type: 'bar',
      				data: sData1
    				},
					{
      				name: '이번주',
      				type: 'bar',
      				data: sData2
    				}    				
  				]
		};

		option && myChart.setOption(option);
		window.addEventListener('resize', myChart.resize);
}





// ---------------------------------
// 가로Line 차트 : 미송현황
// ---------------------------------
function setChartl13(chartid, chartDataX, chartDataS){	 

	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;
	chartDataX = chartDataX != null ? chartDataX : ['D-6', 'D-5', 'D-4', 'D-3', 'D-2', 'D-1', 'D'];
	chartDataS = chartDataS != null ? chartDataS : [0, 0, 0, 0, 0, 0, 0];
	
		option = {
	  		xAxis: {
	    		type: 'category',
	    		data: chartDataX
	  		},
	  		yAxis: {
	    		type: 'value'
	  		},
	  		series: [
	    	{
	      		data: chartDataS,
	      		type: 'line',
	      		symbol: 'triangle',
	      		symbolSize: 20,
	      		lineStyle: {
	        	color: '#5470C6',
	        	width: 4,
	        	type: 'dashed'
	      		},
	      		itemStyle: {
	        		borderWidth: 3,
	        		borderColor: '#EE6666',
	        		color: 'yellow'
	      			}
	    		}
	  		]
	};
	
	option && myChart.setOption(option);
	window.addEventListener('resize', myChart.resize);
};


// ---------------------------------
// 가로Line 차트 : 샘플현황(월)
// ---------------------------------
function setChartl14(chartid){
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;

	option = {
  		title: {
    	//text: 'Stacked Line'
  	},
  	tooltip: {
    	trigger: 'axis'
  	},
  	legend: {
    	data: ['Email', 'Union Ads', 'Video Ads', 'Direct', 'Search Engine']
  	},
  	grid: {
    	left: '3%',
    	right: '4%',
    	bottom: '3%',
    	containLabel: true
  	},
  	toolbox: {
    	feature: {
      		saveAsImage: {}
    	}
  	},
  	xAxis: {
    	type: 'category',
    	boundaryGap: false,
    	data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
  	},
  	yAxis: {
    	type: 'value'
  	},
  	series: [
	    {
			name: 'Email',
			type: 'line',
			stack: 'Total',
			data: [120, 132, 101, 134, 90, 230, 210]
	    },
	    {
			name: 'Union Ads',
			type: 'line',
			stack: 'Total',
			data: [220, 182, 191, 234, 290, 330, 310]
	    },
	    {
			name: 'Video Ads',
			type: 'line',
			stack: 'Total',
			data: [150, 232, 201, 154, 190, 330, 410]
	    },
	    {
			name: 'Direct',
			type: 'line',
			stack: 'Total',
			data: [320, 332, 301, 334, 390, 330, 320]
	    },
	    {
			name: 'Search Engine',
			type: 'line',
			stack: 'Total',
			data: [820, 932, 901, 934, 1290, 1330, 1320]
	    }
  	]
	};

	option && myChart.setOption(option);
	window.addEventListener('resize', myChart.resize);
}