 
// ---------------------------------
// bar 차트 : 매출(일)
// ---------------------------------
function setChartb1(chartid){	
	var dom = document.getElementById(chartid);
	var myChart = echarts.init(dom, null, {
		renderer: 'canvas',
		useDirtyRect: false
		});
	var app = {};
	var option;
		option = {
			//title: { text: '총매출액(일)', subtext: '단위:원'},
			grid: { left: '10%', right: '0%', top:'10%', bottom: '10%'},
		  	xAxis: {
		    	type: 'category',
		    	data: ['D-6', 'D-5', 'D-4', 'D-3', 'D-2', 'D-1', 'D']
		  	},
		  	yAxis: {
		    	type: 'value'
		  	},
		  	series: [
		    {
		    	data: [112000, 200000, 105000, 80000, 70000, 101000, 130000],
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
// 가로bar 차트 : 주간Best
// ---------------------------------
function setChartWb1(chartid){	 
	
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;

	option = {
  		// title: { text: 'World Population'  },
  		tooltip: {    trigger: 'axis',    axisPointer: {      type: 'shadow'    }  },
  		legend: {},
  		grid: { left: '0%', right: '10%', top:'10%', bottom: '3%', containLabel: true  },
  		xAxis: { type: 'value', boundaryGap: [0, 0.01]  },
  		yAxis: { type: 'category',
    		data: ['Brazil', 'Indonesia', 'USA', 'India', 'China', 'World']
		},
		series: [
					{
      				name: '지난주',
      				type: 'bar',
      				data: [100, 249, 290, 4970, 744, 630]
    				},
					{
      				name: '이번주',
      				type: 'bar',
      				data: [120, 289, 234, 970, 114, 630]
    				}    				
  				] 
		};

		option && myChart.setOption(option);
		window.addEventListener('resize', myChart.resize);
}

// ---------------------------------
// 가로bar 차트 : 주간Worst
// ---------------------------------
function setChartWb2(chartid){	 
	
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;

	option = {
  		// title: { text: 'World Population'  },
  		tooltip: {    trigger: 'axis',    axisPointer: {      type: 'shadow'    }  },
  		legend: {},
  		grid: { left: '0%', right: '10%', top:'10%', bottom: '3%', containLabel: true  },
  		xAxis: { type: 'value', boundaryGap: [0, 0.01]  },
  		yAxis: { type: 'category',
    		data: ['Brazil', 'Indonesia', 'USA', 'India', 'China', 'World']
		},
		series: [
					{
      				name: '지난주',
      				type: 'bar',
      				data: [18203, 23489, 29034, 104970, 131744, 630230]
    				},
					{
      				name: '이번주',
      				type: 'bar',
      				data: [18203, 23489, 29034, 104970, 131744, 630230]
    				}    				
  				]
		};

		option && myChart.setOption(option);
		window.addEventListener('resize', myChart.resize);
}	

// ---------------------------------
// 가로Line 차트 : 샘플현황
// ---------------------------------
function setChartl11(chartid){	 

	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;
	
		option = {
	  		xAxis: {
	    		type: 'category',
	    		data: ['D-6', 'D-5', 'D-4', 'D-3', 'D-2', 'D-1', 'D']
	  		},
	  		yAxis: {
	    		type: 'value',
	    		axisLabel: { formatter: '{value} 원' }
	  		},
	  		series: [
	    	{
	      		data: [120, 200, 150, 80, 70, 110, 130],
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
}
// ---------------------------------
// 가로Line 차트 : 샘플현황(월)
// ---------------------------------
function setChartl12(chartid){
	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;

	let dataAxis = ['2021-12',	'2022-01',	'2022-02',	'2022-03',	'2022-04',	'2022-05',	'2022-06',	'2022-07',	'2022-08',	'2022-09',	'2022-10',	'2022-11'];
	option = {
  		//title: {text: 'Stacked Line'},
  		tooltip: {trigger: 'axis'},
  		legend: {data: ['안느', '프릴몬드', '어텀', '시엔트', '합계']},
  	grid: {left: '3%',right: '4%',bottom: '3%',containLabel: true},
  	toolbox: {feature: {saveAsImage: {}}},
  	xAxis: {
    	type: 'category',
    	boundaryGap: false,
    	data: dataAxis
  	},
  	yAxis: {
    	type: 'value',
    	axisLabel: { formatter: '{value} 원' }
  	},
  	series: [
	    {
			name: '안느',
			type: 'line',
			stack: 'Total',
			data: [120, 132, 101, 134, 90, 230, 210]
	    },
	    {
			name: '프릴몬드',
			type: 'line',
			stack: 'Total',
			data: [220, 182, 191, 234, 290, 330, 310]
	    },
	    {
			name: '어텀',
			type: 'line',
			stack: 'Total',
			data: [150, 232, 201, 154, 190, 330, 410]
	    },
	    {
			name: '시엔트',
			type: 'line',
			stack: 'Total',
			data: [320, 332, 301, 334, 390, 330, 320]
	    },
	    {
			name: 'aa',
			type: 'line',
			stack: 'Total',
			data: [820, 932, 901, 934, 1290, 1330, 1320]
	    }
  	]
	};

	option && myChart.setOption(option);
	window.addEventListener('resize', myChart.resize);
}

// ---------------------------------
// 가로Line 차트 : 미송현황
// ---------------------------------
function setChartl13(chartid){	 

	var chartDom = document.getElementById(chartid);
	var myChart = echarts.init(chartDom);
	var option;
	
		option = {
	  		xAxis: {
	    		type: 'category',
	    		data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
	  		},
	  		yAxis: {
	    		type: 'value'
	  		},
	  		series: [
	    	{
	      		data: [120, 200, 150, 80, 70, 110, 130],
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

