$(document).ready(function(){
    $("#hiding").click(function(){
        $("#hide").hide();
        $("#showing").show();
    });

    $("#hide-input").hide();
    $("input[name$='user']").click(function () {
        var test = $(this).val();
        if(test === "doctor"){
            $("#hide-input").show();
        } else {
            $("#hide-input").hide();
        }
    });
});