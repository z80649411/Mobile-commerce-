<%@ include file="/ecps/console/common/taglibs.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>添加品牌_品牌管理_商品管理</title>
    <meta name="heading" content="品牌管理"/>
    <meta name="tag" content="tagName"/>

    <%--ajax提交表单--%>
    <script type="text/javascript" src="<c:url value='/${system}/res/js/jquery.form.js'/>"></script>

    <script type="text/javascript">
        $(function () {
            //表单提交事件
            $("#form111").submit(function () {
                /*设置标识量为true，如果不校验不通过设置为false*/
                var isSubmit = true;
                /*得到每个必填的input输入框数据*/
                $("[reg2]").each(function () {
                    var tip = $(this).attr("tip");
                    var regStr = $(this).attr("reg2");
                    /*拿到js校验的对象*/
                    var reg = new RegExp(regStr);
                    var value = $.trim($(this).val());

                    /*校验输入的值与校验规则是否匹配*/
                    if (!reg.test(value)) {
                        /*把错误的信息填充到span中*/
                        $(this).next("span").html("<font color='red'>" + tip + "</font>");
                        isSubmit = false;
                        //中断each使用return false,不能使用return;和break;
                        return false;
                    } else {
                        //必填的数据规则都通过了，那么判断品牌名字是否有重复的【后台校验】
                        var inputName = $(this).attr("name");
                        if (inputName == "brandName") {
                            $.ajax({
                                url: "${path}/brand/validateBrandName.do",
                                type: "post",
                                async: false,
                                data: {
                                    brandName: value
                                },
                                dataType: "text",
                                success: function (responseTest) {
                                    if (responseTest == "no") {
                                        $("#brandNameSpan").html("品牌的名称不能相同！!");
                                        isSubmit = false;
                                        return false;
                                    } else {
                                        //如果改正了，那么就把对应的错误提示清空了。
                                        $(this).next("span").html("");
                                    }
                                },
                                error: function () {
                                    alert("系统错误");
                                }
                            });
                        }
                    }
                });
                /*非必填的数据，如果填了就必须按照规则*/
                $("[reg1]").each(function () {
                    var tip = $(this).attr("tip");
                    var regStr = $(this).attr("reg1");
                    /*拿到js校验的对象*/
                    var reg = new RegExp(regStr);
                    var value = $.trim($(this).val());

                    /*如果用户填了数据，那么就需要按照规则*/
                    if (value != null && value != "") {
                        if (!reg.test(value)) {
                            /*把错误的信息填充到span中*/
                            $(this).next("span").html("<font color='red'>" + tip + "</font>");
                            isSubmit = false;
                            //中断each使用return false,不能使用return;和break;
                            return false;
                        } else {
                            //如果改正了，那么就把对应的错误提示清空了。
                            $(this).next("span").html("");
                        }
                    }
                });

                if(isSubmit) {
                    showTip("#refundLoadDiv");
                }

                return isSubmit;
            });

            //光标失去焦点的校验
            $("#form111").find("[reg2]").blur(function () {
                var tip = $(this).attr("tip");
                var regStr = $(this).attr("reg2");
                /*拿到js校验的对象*/
                var reg = new RegExp(regStr);
                var value = $.trim($(this).val());

                /*校验输入的值与校验规则是否匹配*/
                if (!reg.test(value)) {
                    /*把错误的信息填充到span中*/
                    $(this).next("span").html("<font color='red'>" + tip + "</font>");
                } else {
                    //必填的数据规则都通过了，那么判断品牌名字是否有重复的【后台校验】
                    var inputName = $(this).attr("name");
                    if (inputName == "brandName") {
                        $.ajax({
                            url: "${path}/brand/validateBrandName.do",
                            type: "post",
                            async: false,
                            data: {
                                brandName: value
                            },
                            dataType: "text",
                            success: function (responseTest) {
                                if (responseTest == "no") {
                                    $("#brandNameSpan").html("品牌的名称不能相同！!");
                                } else {
                                    //如果改正了，那么就把对应的错误提示清空了。
                                    $(this).next("span").html("");
                                }
                            },
                            error: function () {
                                alert("系统错误");
                            }
                        });
                    }
                }
            });
            //失去焦点校验
            $("#form111").find("[reg1]").blur(function () {
                var tip = $(this).attr("tip");
                var regStr = $(this).attr("reg1");
                /*拿到js校验的对象*/
                var reg = new RegExp(regStr);
                var value = $.trim($(this).val());
                /*如果用户填了数据，那么就需要按照规则*/
                if (value != null && value != "") {
                    if (!reg.test(value)) {
                        /*把错误的信息填充到span中*/
                        $(this).next("span").html("<font color='red'>" + tip + "</font>");
                    } else {
                        //如果改正了，那么就把对应的错误提示清空了。
                        $(this).next("span").html("");
                    }
                }
            });
        });
        function submitUpload() {
            var opt = {
                //重新指定form的action的值
                url: "${path}/upload/uploadPic.do",
                type: "post",
                dateType: "json",
                success: function (responseText) {
                    var jsonObj = $.parseJSON(responseText.replace(/<.*?>/ig, ""));
                    $("#imgsImgSrc").attr("src", jsonObj.realPath);
                    $("#imgs").val(jsonObj.relativePath);
                },
                error: function () {
                    alert("系统错误");
                }
            };
            $("#form111").ajaxSubmit(opt);
        }
    </script>
</head>
<body id="main">
<div class="frameL">
    <div class="menu icon">
        <jsp:include page="/${system}/common/itemmenu.jsp"/>
    </div>
</div>

<div class="frameR">
    <div class="content">

        <c:url value="/${system}/item/brand/listBrand.do" var="backurl"/>
        <div class="loc icon"><samp class="t12"></samp>当前位置：商品管理&nbsp;&raquo;&nbsp;<a
                href="<c:url value="/${system }/item/brand/listBrand.do"/>"
                title="品牌管理">品牌管理</a>&nbsp;&raquo;&nbsp;<span class="gray">添加品牌</span>
            <a href="<c:url value="/brand/listBrand.do"/>" title="返回品牌管理"
                class="inb btn80x20">返回品牌管理</a>
            </div>
            <form id="form111" name="form111" action="${path}/brand/addBrand.do" method="post"
                  enctype="multipart/form-data">
                <div class="edit set">
                <p><label><samp>*</samp>品牌名称：</label><input type="text" id="brandName" name="brandName"
                                                            class="text state" reg2="^[a-zA-Z0-9\u4e00-\u9fa5]{1,20}$"
                                                            tip="必须是中英文或数字字符，长度1-20"/>
                    <span id="brandNameSpan"></span>
                </p>
                <p><label class="alg_t"><samp>*</samp>品牌LOGO：</label><img id='imgsImgSrc' src="" height="100"
                                                                          width="100"/>
                </p>


                <p><label></label><input type='file' size='27' id='imgsFile' name='imgsFile' class="file"
                                         onchange='submitUpload()'/><span id="submitImgTip" class="pos">请上传图片宽为120px，高为50px，大小不超过100K。</span>


                    <input type='hidden' id='imgs' name='imgs' value='' reg2="^.+$" tip="亲！您忘记上传图片了。"/>
                    <span></span>
                </p>

                <p><label>品牌网址：</label><input type="text" name="website" class="text state" maxlength="100"
                                              tip="请以http://开头" reg1="http:///*"/>
                    <span class="pos">&nbsp;</span>
                </p>
                <p><label class="alg_t">品牌描述：</label><textarea rows="4" cols="45" name="brandDesc" class="are"
                                                               reg1="^(.|\n){0,300}$" tip="任意字符，长度0-300"></textarea>
                    <span class="pos">&nbsp;</span>
                </p>
                <p><label>排序：</label><input type="text" id="brandSort" reg1="^[1-9][0-9]{0,2}$" tip="排序只能输入1-3位数的正整数"
                                            name="brandSort" class="text small"/>
                    <span class="pos">&nbsp;</span>
                </p>
                <p><label>&nbsp;</label><input type="submit" name="button1" class="hand btn83x23" value="完成"/><input
                        type="button" class="hand btn83x23b" id="reset1" value='取消' onclick="backList('${backurl}')"/>
                </p>
            </div>
        </form>
        <div class="loc">&nbsp;</div>
    </div>
</div>
</body>

