<div class="foot" th:fragment="bottom">
    <div class="container" style="width:700px; margin:auto;">
        <div>
            <div class="weixin" style="float: left; margin-right: 5px">
                <img th:src="${imagepath}+'/'+${bottom.weixin_path}" src="images/wkyl_weixin.jpg" alt="微信" style="width: 70px;height: 70px"/>
            </div>
            <div th:each="o:${bottom.txt1}" class="address" style="width:55%;float: left;text-align: left;font-family:'微软雅黑';font-size: 13px;">
                <p th:text="${o.txt}"></p>
            </div>
            <div th:each="o:${bottom.txt2}" class="address" style="width:26%;float: left;text-align: left;font-family:'微软雅黑';font-size: 13px;">
                <p th:text="${o.txt}"></p>
            </div>
            <div style="clear: both;"></div>
        </div>
    </div>
</div>
