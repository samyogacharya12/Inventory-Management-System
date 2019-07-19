<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
window.onload = function() {
 
var dps = ["" ,""];
var chart = new CanvasJS.Chart("chartContainer", {
	exportEnabled: true,
	animationEnabled: true,
	title: {
		text: "Quarterly Sales Comparison"
	},
	subtitles: [{
		text: "Click Legend to Hide or Unhide Data Series"
	}],
	axisX: {
		title: "Quarter"
	},
	axisY: {
		title: "Sales",
		prefix: "$",
		includeZero: false
	},
	toolTip: {
		shared: true
	},
	legend: {
		cursor: "pointer",
		verticalAlign: "top",
		itemclick: toggleDataSeries
	},
	data: [{
		type: "column",
		name: "2016",
		showInLegend: true,
		yValueFormatString: "$#,##0",
		dataPoints: dps[0]
	}]
});
 
var yValue;
var label;
 
<c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">	
		yValue = "${dataPoints.buy_date}";
		label = "${dataPoints.customer_id}";
		dps[parseInt("${loop.index}")].push({
			label : label,
			yValue : yValue,
		});		
</c:forEach> 
 
chart.render();
 
function toggleDataSeries(e) {
	if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
		e.dataSeries.visible = false;
	} else {
		e.dataSeries.visible = true;
	}
	e.chart.render();
}
}
</script>
</head>
<body>
          <button class="btn btn-primary btn-sm" onclick="search(this)">Filter</button>
	<div id="chartContainer" style="height: 370px; width: 100%;"></div>
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
	         <script>
    
  var html ='<div class="table">\
  <div class="text-primary">\
  <form action="/getData" method="get">\
      <span class="icon"><i class="fa fa-search"></i></span>\
      <input type="Date" name="sell_date[]" id="search" placeholder="Date..." />\
       <input type="Date" name="sell_date[]" id="search1" placeholder="Date..." />\
       <button type="submit" class="btn btn-fill btn-primary">Find</button>\
       </form>\
  </div>\
</div>';

function search(elem) {
  elem.outerHTML = html;
}
  </script>
</body>

</html>             