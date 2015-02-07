
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title th:text="${main.title}"></title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/lib/jquery-1.10.2.min.js"></script>
    <!--[if lt IE 7]>
    <script type="text/javascript" src="js/DD_belatedPNG_0.0.8a.js"></script>
    <script type="text/javascript">
    DD_belatedPNG.fix('*');
    </script>
    <![endif]-->
    <!--[if lte IE 6]>
    <style type="text/css">
        body {
            behavior: url("css/csshover.htc");
        }
    </style>
    <![endif]-->
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<jsp:include page="adv.jsp" flush="true"/>
<!--中间内容区 -->
<div class="main" th:fragment="main">
    <div class="mainLeft">
        <div class="login">
            <h5>
                <span id="welcome" style="margin-left:40px;margin-top:7px;font-size:13px;font-family:'微软雅黑';text-align: left; float: left;"></span>
                <a href="javascript:void(0);" onclick="closeWindow();" style="margin-right:5px;margin-top:7px;text-align: right; float: right;">
                    <img src="images/logout.png" alt="" style="width: 90px;height: 24px"/>
                </a>
            </h5>
            <div class="loginTx">
            </div>
        </div>

        <div th:each="o:${main.tabs_left}" class="tongzhi" th:style="'height:'+${o.height}+'px;background: url('+${imagepath}+'/'+${o.logo}+') no-repeat;color: #ffffff;cursor:auto;'">
            <h5 th:title="${o.title}" title="" ></h5>
            <div th:id="${o.type}" th:style="'height:'+${o.height-40}+'px;'" class="tzTx" ></div>
        </div>
    </div>
    <div class="mainRight">
        <div  th:each="o,oStat:${main.tabs_right}" th:if="${oStat.even}" class="newsBox" >
            <div class="over">
                <div  class="news">
                    <div class="news-title">
                        <div><label th:text="${o.title}" class="green large"></label><a  th:href="'news.jsp?newsType='+${o.type}" class="green more">更多>></a></div>
                        <div class="hr"><span class="hr-hightlight"></span><span class="hr-gray"></span></div>
                    </div>
                    <div th:id="${o.type}" class="zonghezixunTx" id="">
                    </div>
                </div>
                <div th:if="!${oStat.last}" th:object="${main.tabs_right[oStat.index+1]}" class="news">
                    <div class="news-title">
                        <div><label th:text="*{title}" class="green large"></label><a  th:href="'news.jsp?newsType='+*{type}" class="green more">更多>></a></div>
                        <div class="hr"><span class="hr-hightlight"></span><span class="hr-gray"></span></div>
                    </div>
                    <div th:id="*{type}" class="zonghezixunTx" id="">
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<jsp:include page="buttom.jsp" flush="true"/>
<script type="text/javascript">
    window.rootpath = '${pageContext.request.contextPath}';
    window.accessid = "${sessionScope.sysAccessID}";
</script>
<script type="text/javascript" src="js/pagejs/index.js"></script>
<script type="text/javascript" src="js/pagejs/v6box.js"></script>
<script type="text/javascript" src="js/pagejs/common.js"></script>
</body>
</html>
