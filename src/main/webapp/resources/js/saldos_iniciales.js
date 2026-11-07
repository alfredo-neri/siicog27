(function($){

  window.correctPass = function(){
    console.info("correctPass over change");
    $("#salIniForm .header-show").fadeIn("slow");
  };

  window.invalidPass = function(){
    console.warn("invalid pass");
    $("#salIniForm .header-show").fadeOut("slow");
  };

  window.validAccount = function(){
    console.info("validAccount");
  };

  window.showStatus = function(){
    PF('statusDialog').show();
  };

  window.hideStatus = function (){
    PF('statusDialog').hide();
  };

  window.dblClickCallback = function(xhr, status, args){
    console.debug("dblClickCallback", args.details);
    if(args.details){
      console.debug("displaying sal-show");
      $("#salIniForm .sal-show").fadeIn("slow");
    }else{
      console.debug("hiding sal-show");
      $("#salIniForm .sal-show").fadeOut("slow");
    }
  };

  var enableListeners = function(){
    $(".cve_acceso").blur(function(){
      var val = $(".cve_acceso").val();
      if(!_.isEmpty(val)){
				        validatePass();
      }
    });

    $(".cuenta").blur(function(){
      var val = $(".cuenta").val();
      PF('resultTable').unselectAllRows();
      if(!_.isEmpty(val)){
        validateAccount();
      }
    });

    $(".scta").blur(function(){
      var val = $(".scta").val();
      PF('resultTable').unselectAllRows();
      if(!_.isEmpty(val)){
        validateScta();
      }
    });
    $("#nom_cta").change(function(){
        var val = $("#nom_cta").val();
        PF('resultTable').unselectAllRows();
        if(!_.isEmpty(val)){
          validateScta();
        }
      });
  };

  var init = function(){
    console.info("init saldos iniciales");
    enableListeners();
  };

  $(document).ready(init);
})(jQuery);
