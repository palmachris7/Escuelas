$('document').ready(function(){
    $('.table #delete').on('click',function(event){
        event.preventDefault();
        var href =$(this).attr('href');
        $('#modalEliminar #delref').attr('href',href);
        $('#modalEliminar').modal();
    });
    $('.table #deletec').on('click',function(event){
        event.preventDefault();
        var href =$(this).attr('href');
        $('#modalEliminarC #delref').attr('href',href);
        $('#modalEliminarC').modal();
    });
    $("#navbar_salir").on("click",function(event){
        event.preventDefault();
        $('#modalSalir').modal();
    })
});