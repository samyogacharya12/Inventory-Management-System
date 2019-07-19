var c = document.getElementById("SimpleBarGraph");
var ctx = c.getContext("2d");
var graphHeight = c.height - 50;

var lightColor = "#5b5b5b";
var darkColor = "#222222";
var lineColor = "#cccccc";
var valueColor = "#FFFFFF";
var labelColor = "#DDDDDD";
var bgColor = "#000000";

var drawGraph = function()
{
  var width =  c.width/data.length;
  for(var i = 0; i<data.length;i++)
  {
    var height =  graphHeight*data[i].value;
    drawBar(i,data[i].label,data[i].value,width,height);
  }
    drawLines();
}

var drawLines = function()
{
  ctx.strokeStyle = lineColor;
  ctx.beginPath();
  ctx.moveTo(3, graphHeight);
  ctx.lineTo(c.width, graphHeight);
  ctx.stroke();
  ctx.beginPath();
  ctx.moveTo(2, graphHeight+1);
  ctx.lineTo(2, 0);
  ctx.stroke();
}

var drawBar = function(index, label, value, width, height)
{
    // Create gradient
  var grd = ctx.createLinearGradient(0,graphHeight,0,graphHeight-height);
  grd.addColorStop(0,darkColor);
  grd.addColorStop(1,lightColor);

  // Fill with gradient
  ctx.fillStyle = grd;
  ctx.fillRect(2+(index*width),graphHeight-height,width,height);
  drawShadow(index, width, height);
  drawLabel(index, label,value, width);
}

var drawShadow = function(index,  width, height)
{
    // Create gradient
  var grd = ctx.createLinearGradient(0,graphHeight,0,graphHeight+50);
  grd.addColorStop(0,darkColor);
  grd.addColorStop(1,bgColor);

  // Fill with gradient
  ctx.fillStyle = grd;
  ctx.fillRect(2+(index*width),graphHeight,width,height);
}

var drawLabel = function(index,label,value,width)
{
  ctx.fillStyle = labelColor;
  ctx.font = "bold 12px Courier";
  ctx.textAlign="center"; s
  ctx.fillText(label, width/2 + index*width, graphHeight+20);
    ctx.fillStyle = valueColor;
  ctx.font = "bold 12px Courier";
  ctx.textAlign="center"; 
  ctx.fillText(value, width/2 + index*width, graphHeight-8);
}

var date='$date';
console.log(date);
var sales='$sales';
console.log(sales);
	var data = [
		for(let salesValue of sales)
		{
		for(let dateValue of date)
		{
	  {label:date,value:salesValue}
		}
		}}];


drawGraph();