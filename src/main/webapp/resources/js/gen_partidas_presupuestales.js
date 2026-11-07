(function($){

  window.rcCB = function(title){
    PF('statusDialog').hide();
    PF('resGrid').show();
    PF('resGrid').getJQ().find(".ui-dialog-title").text(title);
  };

  window.endModal = function(){
    PF('resGrid').hide();
    window.enableKPListener();
  };

  window.enableKPListener = function(){
    $(".helpable").keydown(function(e){
      if(e.which === 112){
        var node = $(e.currentTarget).data("node");
        $(".currentCatalog").val(node);
        PF('statusDialog').show();
        switch (node) {
          case 'dep':
            searchDeps();
            break;
          case 'prog':
            getProgs();
            break;
          case 'part':
            getParts();
            break;
        }
      }
    });
  };

  var init = function(){
    window.enableKPListener();
  };

  $(document).ready(init);
})(jQuery);
