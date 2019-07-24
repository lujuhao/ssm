$(function(){
	//加载图表
	 $('#demo-pie-1').pieChart({
        barColor: '#2dde98',
        trackColor: '#eee',
        lineCap: 'round',
        lineWidth: 8,
        onStep: function (from, to, percent) {
            $(this.element).find('.pie-value').text(Math.round(percent) + '%');
        }
    });

    $('#demo-pie-2').pieChart({
        barColor: '#8e43e7',
        trackColor: '#eee',
        lineCap: 'butt',
        lineWidth: 8,
        onStep: function (from, to, percent) {
            $(this.element).find('.pie-value').text(Math.round(percent) + '%');
        }
    });

    $('#demo-pie-3').pieChart({
        barColor: '#ffc168',
        trackColor: '#eee',
        lineCap: 'square',
        lineWidth: 8,
        onStep: function (from, to, percent) {
            $(this.element).find('.pie-value').text(Math.round(percent) + '%');
        }
    });
    
    
    $("#owl-demo").owlCarousel({
			items : 3,
			lazyLoad : true,
			autoPlay : true,
			pagination : true,
			nav:true,
		});
    
    $("#Bargraph").SimpleChart({
        ChartType: "Bar",
        toolwidth: "50",
        toolheight: "25",
        axiscolor: "#E6E6E6",
        textcolor: "#6E6E6E",
        showlegends: true,
        data: [graphdata4, graphdata3, graphdata2, graphdata1],
        legendsize: "140",
        legendposition: 'bottom',
        xaxislabel: 'Hours',
        title: 'Weekly Profit',
        yaxislabel: 'Profit in $'
    });
    $("#sltchartype").on('change', function () {
        $("#Bargraph").SimpleChart('ChartType', $(this).val());
        $("#Bargraph").SimpleChart('reload', 'true');
    });
    $("#Hybridgraph").SimpleChart({
        ChartType: "Hybrid",
        toolwidth: "50",
        toolheight: "25",
        axiscolor: "#E6E6E6",
        textcolor: "#6E6E6E",
        showlegends: true,
        data: [graphdata4],
        legendsize: "140",
        legendposition: 'bottom',
        xaxislabel: 'Hours',
        title: 'Weekly Profit',
        yaxislabel: 'Profit in $'
    });
    $("#Linegraph").SimpleChart({
        ChartType: "Line",
        toolwidth: "50",
        toolheight: "25",
        axiscolor: "#E6E6E6",
        textcolor: "#6E6E6E",
        showlegends: false,
        data: [graphdata4, graphdata3, graphdata2, graphdata1],
        legendsize: "140",
        legendposition: 'bottom',
        xaxislabel: 'Hours',
        title: 'Weekly Profit',
        yaxislabel: 'Profit in $'
    });
    $("#Areagraph").SimpleChart({
        ChartType: "Area",
        toolwidth: "50",
        toolheight: "25",
        axiscolor: "#E6E6E6",
        textcolor: "#6E6E6E",
        showlegends: true,
        data: [graphdata4, graphdata3, graphdata2, graphdata1],
        legendsize: "140",
        legendposition: 'bottom',
        xaxislabel: 'Hours',
        title: 'Weekly Profit',
        yaxislabel: 'Profit in $'
    });
    $("#Scatterredgraph").SimpleChart({
        ChartType: "Scattered",
        toolwidth: "50",
        toolheight: "25",
        axiscolor: "#E6E6E6",
        textcolor: "#6E6E6E",
        showlegends: true,
        data: [graphdata4, graphdata3, graphdata2, graphdata1],
        legendsize: "140",
        legendposition: 'bottom',
        xaxislabel: 'Hours',
        title: 'Weekly Profit',
        yaxislabel: 'Profit in $'
    });
    $("#Piegraph").SimpleChart({
        ChartType: "Pie",
        toolwidth: "50",
        toolheight: "25",
        axiscolor: "#E6E6E6",
        textcolor: "#6E6E6E",
        showlegends: true,
        showpielables: true,
        data: [Piedata],
        legendsize: "250",
        legendposition: 'right',
        xaxislabel: 'Hours',
        title: 'Weekly Profit',
        yaxislabel: 'Profit in $'
    });

    $("#Stackedbargraph").SimpleChart({
        ChartType: "Stacked",
        toolwidth: "50",
        toolheight: "25",
        axiscolor: "#E6E6E6",
        textcolor: "#6E6E6E",
        showlegends: true,
        data: [graphdata3, graphdata2, graphdata1],
        legendsize: "140",
        legendposition: 'bottom',
        xaxislabel: 'Hours',
        title: 'Weekly Profit',
        yaxislabel: 'Profit in $'
    });

    $("#StackedHybridbargraph").SimpleChart({
        ChartType: "StackedHybrid",
        toolwidth: "50",
        toolheight: "25",
        axiscolor: "#E6E6E6",
        textcolor: "#6E6E6E",
        showlegends: true,
        data: [graphdata3, graphdata2, graphdata1],
        legendsize: "140",
        legendposition: 'bottom',
        xaxislabel: 'Hours',
        title: 'Weekly Profit',
        yaxislabel: 'Profit in $'
    });
});