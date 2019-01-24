/*
	ClockJS - analog clock developed by Jiri Caga 4.November 2017
*/
const logger = createLogger();
logger.info("Startng get canvas.")
const canvas = document.getElementById("canvasId");
const ctx = canvas.getContext("2d");
var radius = 0;
logger.info("Get canvas is  complete.")

function onLoad(){
	onResize();
	setInterval(drawClock,1000);    // Redraw clock each 1000 miliseconds
}

function onResize(){
	logger.info("Resize clock canvas.")
	const size = Math.min(window.innerHeight, window.innerWidth)*0.95;
	canvas.width = size;
	canvas.height = size;			
	radius = computeRadius(canvas);
}

function computeRadius(canvas){
	logger.info("Starting compute radius.")
	let radius = canvas.height / 2; // Radius of clock
	ctx.translate(radius,radius);   // Remap the (0,0) position to the center of canvas
	radius = radius * 0.90;         // Reduce clock radius to 90% for better place into canvas
	return radius;
}

function drawClock(){
	drawFace(ctx,radius);
	drawNumbers(ctx,radius);
	drawTime(ctx,radius);
}

function drawFace(ctx,radius){
	logger.info("Draw white background circle in clock.");
	ctx.beginPath();
	ctx.arc(0,0,radius,0,2*Math.PI);
	ctx.fillStyle = "#B2FF66";
	ctx.fill();

	logger.info("Draw gradient frame about clock.");
	let grad = ctx.createRadialGradient(0,0,radius*0.95,0,0,radius*1.05);
	grad.addColorStop(0,'#333');
	grad.addColorStop(0.5,'white');
	grad.addColorStop(1,'#333');
	ctx.strokeStyle = grad;
	ctx.lineWidth = radius*0.1
	ctx.stroke();
	
	logger.info("Draw clock center.");
	ctx.beginPath();
	ctx.arc(0,0,radius*0.1,0,2*Math.PI);
	ctx.fillStyle = "#333";
	ctx.fill();	
}

function drawNumbers(ctx, radius){
	logger.info("Draw one to twelve numbers.");
  	ctx.font = radius*0.15 + "px arial";
  	ctx.textBaseline="middle";
  	ctx.textAlign="center";
	  
	/* 	Calculate the print position (for 12 numbers) to 85% of the radius, 
		rotated (PI/6) for each number. */
	for(let num = 1; num < 13; num++){
    	ang = num * Math.PI / 6;
    	ctx.rotate(ang);
    	ctx.translate(0, -radius*0.85);
    	ctx.rotate(-ang);
    	ctx.fillText(num.toString(), 0, 0);
    	ctx.rotate(ang);
    	ctx.translate(0, radius*0.85);
    	ctx.rotate(-ang);
  	}
}

function drawTime(ctx,radius){
	logger.info("Update clock hands")
	const now = new Date();
	let hour = now.getHours();
	let minute = now.getMinutes();
	let second = now.getSeconds();
	
	// Hour hand
	hour = hour%12; // Calculate an angle of the hour hand
    hour = (hour*Math.PI/6)+ (minute*Math.PI/(6*60)) + (second*Math.PI/(360*60));
	drawHand(ctx, hour, radius*0.5, radius*0.07);
	
	// Minute hand
	 minute=(minute*Math.PI/30)+(second*Math.PI/(30*60));
    drawHand(ctx, minute, radius*0.8, radius*0.07);
    
	// Second hand
    second=(second*Math.PI/30);
    drawHand(ctx, second, radius*0.9, radius*0.02);
}

function drawHand(ctx, pos, length, width){
	ctx.beginPath();
	ctx.lineWidth = width;
	ctx.lineCap = "round";
	ctx.moveTo(0,0);
	ctx.rotate(pos)
	ctx.lineTo(0,-length);
	ctx.stroke();
	ctx.rotate(-pos);
}

function createLogger(){
	let logger =  {
		INFO_LOG: false,
		ERROR_LOG: true,
		info: function(message){
			if(this.INFO_LOG){
				console.log(Date() + " INFO: " + message);
			}
		},
		error: function(message){
			if(this.ERROR_LOG){
				console.log(Date() + " ERROR: " + message);
			}
		}
	};
	return logger;
}
