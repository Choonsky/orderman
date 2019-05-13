$(document).ready(function() {
    $(".delrowbtn").click(function() {
        if ($(this).closest(".modal-body").children(".container").length > 1)
            $(this).closest(".container").remove();
        else $('#error').modal('show');
    });
});

$(".addrowbtn").click(function(){
    $("#templateContainer").clone(true).appendTo($(this).closest(".modal-body"));
    $(this).closest(".modal-body").append(this);
});