(function($){

  window.triggerDownload = function(){
    setTimeout(function(){
      PF('downbtn').getJQ().click();
    },3000);
  };

})(jQuery);
