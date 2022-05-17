<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-05-17
  Time: 오후 4:42
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title></title>
</head>
<body>
<div class="container-fluid">
    <div class="d-flex justify-content-center">
        <div class="d-block">
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/board/list">Board</a>
            <button class="btn btn-secondary" id="AJAXLoadBoardList">게시물 JSON 데이터 가져오기</button>
        </div>
        <div class="">
        </div>
    </div>

    <div id="dataResult">

    </div>
</div>
</body>
</html>
<script>
    document.querySelector("#AJAXLoadBoardList").addEventListener('click', () => {
        const httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                document.querySelector("#dataResult").innerHTML = this.responseText;
                document.querySelector("#dataResult").innerHTML = this.getAllResponseHeaders();
                // document.querySelector("#dataResult").innerHTML = this.getResponseHeader("title");
                console.log(this.responseType);
                console.log(this.responseXML);
            }
        }
        httpRequest.open("GET", "/rest/board/list", true);
        httpRequest.send(null);
    });
</script>
