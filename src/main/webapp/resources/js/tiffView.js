var lastIndex = 0
//silence console log
//console.log = function() {}


function initializedSlide(){
	$('#imageView').empty()
	$('#imageView').css('background-color','#f7f7f9')	
	$('#imageView').css('borderr','#e1e1e8')
	let divDots = document.createElement('div')
	divDots.id = 'divDots'
	$('#imageView').append(divDots)
	let largeSlick = document.createElement('div')
	largeSlick.setAttribute('class', 'large-slick')
	$('#imageView').append(largeSlick)
}

function buildSlide () {
	$('.large-slick').slick({
        infinite: false,
        speed: 300,
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: true,
        dots: true,
        appendDots:$('#divDots')
    });
	addCustomArrows()
};

function addCustomArrows(){
	var prevArrow = '<a class="btn-prev left carousel-control" style="z-index: 1000;position:absolute; top:50%; float:left; transform: translate(0, -50%);" role="button"><span class="glyphicon glyphicon-chevron-left" style="color: #fff;font-size: 45px;"></span></a>'
		,nextArrow= '<a class="btn-next right carousel-control" style="z-index: 1000;right: -25px; position:absolute; top:50%; float:right; transform: translate(0, -50%);" role="button"><span class="glyphicon glyphicon-chevron-right" style="color: #fff;font-size: 45px;"></span></a>'

		$('.slick-prev').before(prevArrow).hide()
		$('.slick-next').before(nextArrow).hide()
		$('.btn-next').on('click',()=>{$('.slick-next').click()})
		$('.btn-prev').on('click',()=>{$('.slick-prev').click()})
}

function viewTiff(imageTiff){
	PF('blockUIWidget').block();
	$('#imageView').empty();
	Tiff.initialize({TOTAL_MEMORY: 16777216 * 12});
	var loc = window.location
	,baseUrl = loc.protocol + "//" + loc.hostname + (loc.port? ":"+loc.port : "") + '//' + imageTiff;
   
	var xhr = new XMLHttpRequest();
    xhr.responseType = 'arraybuffer';
    xhr.open('GET', baseUrl );
    xhr.onload = function (e) {
    	currentTiff = new Tiff({buffer:xhr.response})
    	length = currentTiff.countDirectory()
    	buildTiff(0)
    };
    xhr.send();
	   
}


buildTiff = function(firstIndex){
	if(firstIndex < 0 || firstIndex >= length){
		alert('El Rango de imagenes solicitada no existe')
		return
	}
	PF('blockUIWidget').block();
	setTimeout(()=>{
		
		let imagesBuffer = []
		try{
			if(!currentTiff){return}
			
			for(var index = firstIndex; index<firstIndex+5 && index <= length;index++){
				currentTiff.setDirectory(index);
				let dataURL = currentTiff.toCanvas().toDataURL();
				imagesBuffer.push(dataURL)			
				// lastIndex = index
			}
			lastIndex = firstIndex+ 4
			imagesBuffer.length > 0 ? initializedSlide() : null
			imagesBuffer.forEach((item, i)=>{					
				
				let $slide = $('.large-slick')
				,$div = document.createElement('div')
				,$canvas = resizeCanvas(item)
				
				$div.setAttribute('class', 'image')				
				$div.append($canvas)
				$slide.append($div)
				
				if(i==imagesBuffer.length-1){
					buildSlide()
					var $dialog = $('#img_title')
					, oldTitle = $dialog.text().substr(0, 20); 
					$dialog.text(oldTitle + ' Imagen ' + (firstIndex+1) +' hasta ' + (firstIndex + 5 > length ? length : firstIndex + 5 ) + ', total ' + length);
					PF('blockUIWidget').unblock();
				}
			})	
		}catch(e){
			console.log(e.message)
		}
	}, 250)
}

resizeCanvas = function(dataURL, width = '800', height = '900'){
	var canvas = document.createElement('canvas')
    var context = canvas.getContext('2d')
	var img = new Image()
	canvas.width = width
	canvas.height = height
	canvas.setAttribute('style','margin-left: 10em;')
	img.onload = function() {
	  context.drawImage(this, 0, 0, width, height)
	}

	img.src = dataURL
	
	return canvas
}
