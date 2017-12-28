$(function(){
    $.ajax({
        url:path+"/user/getUser.do",
        type:"post",
        dataType:"text",
        success:function(responseText){
            var userObj  = $.parseJSON(responseText);
            if(userObj.user != null){
                $("#loginAlertIs").html(userObj.user.username)
            }
        },
        error:function(){
            alert("系统错误");
        }
    })
})
