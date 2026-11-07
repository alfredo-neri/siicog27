/* hack for cell selection in Datatable*/

	$(document).on('click',  "td[role='gridcell']", function(event) { 
		 if (event.target.tagName.toLowerCase() != 'td' && event.target.tagName.toLowerCase() != 'input')
	      {
			 	$(event.target).parents('td:first').click(); 
	      }
	});
	


/* End hack */

// Ventana de espera de las descargas (PrimeFaces.monitorDownload). Si la ventana no existe, la
// descarga sigue: un error aqui cancelaba el envio del formulario y no se descargaba nada.
function start() {
	var w = PrimeFaces.widgets['blockUIWidget'];
	if (w) { w.block(); }
}
function stop() {
	var w = PrimeFaces.widgets['blockUIWidget'];
	if (w) { w.unblock(); }
}
		function executeClickHideButton() {		
			document.getElementById("form1:hideButton").click();
		}
		
		// SIICOG: se retiraron el affix de la barra de Bootstrap (la barra ya no existe), las notas
		// adhesivas (postitall) y el recorrido guiado: sus botones no existian desde difem26 y con
		// jQuery 4 (PrimeFaces 16) esas librerias fallan y detenian el resto de este archivo.

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
	