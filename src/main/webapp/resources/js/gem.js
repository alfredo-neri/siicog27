/* hack for cell selection in Datatable*/

	$(document).on('click',  "td[role='gridcell']", function(event) { 
		 if (event.target.tagName.toLowerCase() != 'td' && event.target.tagName.toLowerCase() != 'input')
	      {
			 	$(event.target).parents('td:first').click(); 
	      }
	});
	


/* End hack */

function start() {
    		PF('statusDialog').show();
		}
		function stop() {
    		PF('statusDialog').hide();    		
		}							
		function executeClickHideButton() {		
			document.getElementById("form1:hideButton").click();
		}
		
		$('#navv').affix({
		      offset: {
		        top: $('div#headGem').height()
		      }
		});	


	    // Set savable global
	    $.fn.postitall.globals.savable = true;
	    // Disables some features
	    $.fn.postitall.globals.askOnDelete = false;
	    $.fn.postitall.globals.fixed = false;
	    $.fn.postitall.globals.randomColor = false;
	    $.fn.postitall.globals.showInfo = false;
	    // Modify note style
// $.fn.postitall.defaults.style.fontfamily = "'Shadows Into Light',
// sans-serif";
	    $.fn.postitall.defaults.style.fontsize = "larger";
	   
	    $(document).ready(function() {
	        // Load previous saved notes
	        $.PostItAll.load();
	    });
	    $(document).ready(function() {
	        $('#idAddNote').click(function(e) {
	            if($(this).hasClass('disabled')) return;
	            $.PostItAll.new({
	            	attachedTo : {
	            		 element: '#divToAttach',
	            	        position: 'top right',
	            	        arrow: true,
	            	        fixed: true},
	            	 style:{	            		 
	            		 backgroundcolor : '#99c000'            		 
	            		 
	            	 }
	               
	            });
	            e.preventDefault();
	        });
	        $('#idHideAll2').click(function(e) {
	            if($(this).hasClass('disabled')) return;
	            $.PostItAll.hide();
	            e.preventDefault();
	        });
	        $('#idShowAll2').click(function(e) {
	            if($(this).hasClass('disabled')) return;
	            $.PostItAll.show();
	            e.preventDefault();
	        });
	        $('#idDeleteAll2').click(function(e) {
	            if($(this).hasClass('disabled')) return;
	            $.PostItAll.remove();
	            e.preventDefault();
	        });
	    });

	    var idNoteBottom, objNoteBottom;
	    var doNotPress = function() {
	       
	    };
	    
	    

	    $(document).ready(function() {

	        var steps = [
	            [{
	                content: "En la barra de menu encontrará todas aquellas opciones que le permitiran realizar su trabajo de manera eficiente",
	                element         : '#navbar',
	                position        : 'bottom',
	            }],[{
	                content: "El icono de Home siempre lo llevará al inicio de la aplicación, al dar click aqui podria perder su trabajo realizado en algun módulo",
	                element         : '#navbar',
	                position        : 'bottom left',
	            }],[{
	                content: "Aqui encontrará accesos directos a sitios de interes para el Gobierno del Estado de México",
	                element         : '#divToAttach',
	                position        : 'top left',
	            }],[{
	                content: "Si lo require puede escribir Notas para recordar alguna información de nteres o útil entre los módulos, si borra los archivos temporales de su nabegador perderá esta información",
	                element         : '#divToAttach',
	                position        : 'top right',
	            }]
	            ,[{
	                content: "Cuanto termine sus actividades puede dar click en el boton Salir para terminar correctamente su sesión en la aplicación",
	                element         : '#navbar',
	                position        : 'bottom right',
	            }]
	        ];

	        var running = false;
	        var tutorial = function(steps) {
	            if(running) return;

	            var currentStep = 0;
	            var idStep = "";

	            $(document).delegate('.PIA-next', 'click', function(e) {
	                $(idStep).postitall('destroy');
	                e.preventDefault();
	            });
	            $(document).delegate('.PIA-previous', 'click', function(e) {
	                currentStep -= 2;
	                $(idStep).postitall('destroy');
	                e.preventDefault();
	            });

	            var newNote = function(step) {
	                currentStep++;
	                var content = step.content;
	                var element = step.element;
	                var position = step.position;
	                var previousStepLiteral = (currentStep > 1 ? "Anterior" : "");
	                var nextStepLiteral = (steps.length > currentStep ? "Siguiente" : "Terminar");
	                $.PostItAll.new({
	                    content: content + "<p>"
	                        + "<p style='text-align:center;color:#000'>Paso " + currentStep.toString() + " de " + steps.length + "<br>"
	                        + (previousStepLiteral != "" ? "<a href='#' class='PIA-previous' style='color:#000'>" + previousStepLiteral + "</a> | " : "")
	                        + "<a href='#' class='PIA-next' style='color:#000'>" + nextStepLiteral + "</a>"
	                        + "</p></p>",
	                    attachedTo : {
	                        element : element,
	                        position : position,
	                    },
	                    flags: {
	                        highlight: true,
	                        blocked: true
	                    },
	                    style:{	            		 
		            		 backgroundcolor : '#99c000' ,
		            	     textcolor       : '#000',
		            	     tresd :false,
		                	 textshadow:false,
		            	 },
	                    features: {
	                        editable : false,
	                        draggable: false,
	                        resizable: false,
	                        htmlEditor: false,
	                        toolbar : false,
	                    },
	                    onCreated: function(id) {
	                        running = true;
	                        idStep = id;
	                    },
	                    onDelete : function() {
	                        if(steps.length > currentStep) {
	                            // Next step
	                            newNote(steps[currentStep][0]);
	                        } else {
	                            // Finished
	                            running = false;
	                            $('#the_lights_close').click();
	                        }
	                    }
	                });
	            };
	            // Trigger note for the first step
	            if(steps.length > 0)
	                newNote(steps[0][0]);
	        };

	        $('#idStart').click(function() {
	            tutorial(steps);
	        });
	    });
	    
	    function leftpad(num, string, char) {
	        for (var i = 0; i < num; i++) {
	            string += char;
	        }
	        return string;
	    }
	    
	    function rigthpad(num, string, char) {
	        for (var i = 0; i < num; i++) {
	            string += char;
	        }
	        return string;
	    }
	    
	    function decimalMask(event, decimals) {
            if (event.shiftKey == true) {
                event.preventDefault();
            }
            if ((event.keyCode >= 48 && event.keyCode <= 57) || 
            		(event.keyCode >= 96 && event.keyCode <= 105) || 
            		event.keyCode == 8 || event.keyCode == 9 || 
            		event.keyCode == 37 || event.keyCode == 39 || 
            		event.keyCode == 46 || event.keyCode == 190) {
						
            } else {
                event.preventDefault();
            }
            alert($(this).val().substring($(this).val().indexOf('.'), $(this).val().length) );
            if($(this).val().indexOf('.') !== -1 && event.keyCode == 190 ){
                event.preventDefault();
						} 
            if($(this).val().indexOf('.') !== -1 &&   ($(this).val().substring($(this).val().indexOf('.'), $(this).val().length).length >decimals) ){
            event.preventDefault();
            }
        }
	