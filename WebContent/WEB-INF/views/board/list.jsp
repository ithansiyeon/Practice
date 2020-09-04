<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<style>
	body{
		background-color:#EEEEEE;
	}

    #main {
        margin: 50px auto;
        background: #FDFDFE;
        width: 1000px;
        height: 700px;
        border-radius: 3px;
        border-color: 1px solid rgb(221, 221, 221);;
    }

    h1 {
        font-size: 1.5em;
        width:900px; 
        margin: 0px auto;
        padding-top: 10px;
        border-bottom: 1px solid #E6E6E6;
        padding-left: 10px;
        margin-bottom: 20px;
    }

    #tbl1 {
        width: 900px;
        margin: 10px auto;
        text-align: center;
        border-collapse: collapse;
        border-spacing: 0;
    }

    #tbl1 th{
        background-color: #FAFAFA;
        border: 0px;
        border-bottom: 2px solid #ddd;
        font-size: 14px;
        color: #444;
    }

    #tbl1 tr{
        border-top: 1px solid #ddd;
        vertical-align: bottom;
  
    }

    #tbl1 td{
        border: 0px;
    }

   thead{
        background-color: #DDDDDD;
    }

   #tbl1 td:nth-child(2){
        text-align: left;
    }

    .pagebar {
        display: inline;
    }

    ul{
        list-style:none;
    }

    a {
        position: relative;
        float: left;
        padding: 6px 12px;
        margin-left: -1px;
        line-height: 1.42857143;
        text-decoration: none;
        border: 1px solid #ddd;
        color: #444;
        
    }

    a:hover{  
        color: tomato;
        background-color: #eeeeee;
        border-color: #ddd;
    }

    #pbox {
        width: 350px;
        margin: 30px auto;
    }

    .active {
        color: #fff;
        cursor: default;
        background-color: #6C757D;
        border-color: #6C757D;
    }
    
    
    .active:hover {
        color: #fff;
        cursor: default;
        background-color: #6C757D;
        border-color: #6C757D;
    }

    .searchbox {
        float: right;
        margin-right: 50px;
        margin-bottom: 30px;
    }

    #btnsearch {
        cursor: pointer;
        float:right;
    }
    
    tr:hover{
    	background-color:#F5F5F5;
    }
    
    tr td:nth-child(2):hover{
       cursor:pointer;
       color:red;
    }
 	
 	.disabled{
 		cursor: not-allowed;
 		color: #444;
 		background-color:#FDFDFE;
 	}
    
    .disabled:hover{
 		cursor: not-allowed;
 		color: #444;
 		background-color:#FDFDFE;
 	}
 	
 	#searchbox {
 		margin-left:50px;
 		position:relative;
 		top:30px;
 		background-color:#F5F5F5;
 		width:300px;
 		vertical-align:middle;
 		padding: 9px;
    	border-radius: 3px;
    	border: 1px solid #e3e3e3;
 	}

</style>
    <%@include file = "/WEB-INF/views/inc/asset.jsp" %>
</head>
<body>
            <div id = "main">
                <div id = "title"><h1>자유 게시판 <small>Board</small></h1></div>    
                <form method = "GET" action = "/Board/board/list.do" id = "searchForm">
	                <c:if test = "${not empty search}">
	                	<div id = "searchbox">${search}(으)로 ${totalCount}건의 게시물을 검색했습니다.</div>
	                </c:if>
                <div class ="searchbox">
                    <div>
                        <div>
                            <input name = "search" type = "text" id = "search" placeholder = "검색어를 입력하세요." required value = "${search}">
                            <button id = "btnsearch" onclick="$('searchForm').submit()">검색</button>
                        </div>
                    </div>
                </div>
                </form>
                <table id = "tbl1">
                    <thead>
                        <th>번호</th>
                        <th>제목</th>
                        <th>이름</th>
                        <th>날짜</th>
                        <th>읽음</th>
                    </thead>
                    <tbody>
                    <c:if test="${not empty search && blist.size()==0 }">
                    	<td colspan="5">검색 결과가 없습니다.</td>
                    </c:if>
                     <c:if test="${empty search && blist.size()==0 }">
                    	<td colspan="5">게시물이 없습니다.</td>
                    </c:if>
                    <c:if test = "${blist.size()!=0}">
                    <c:forEach items = "${blist}" var = "dto">
                    <tr>  
                        <td>${dto.seq}</td>
                        <td onclick="location.href='/Board/board/view.do?page=${page}&search=${search}&seq=${dto.seq}'">${dto.title}
                        <c:if test = "${dto.gap<1}">
                        <span style="color:red;">new</span>
                        </c:if>
                  		</td>
                        <td>${dto.name}</td>
                        <td>${dto.regdate}</td>
                        <td>${dto.readcount}</td>
                    </tr>
                    </c:forEach>
                    </c:if>
                    </tbody>
                </table>
                
                <div id = "pbox">
                
                ${pagebar}
                </div>
                <button style="float:right;width:60px; margin-right: 50px;" onclick="location.href='/Board/board/write.do'">글쓰기</button>
                <button style="float:right;width:60px;" onclick="location.href='/Board/board/list.do'">목록</button>
            </div>
<script>

	$("#pagebar").val(${page});
		
	 $("a").mouseover(function(){
		if($("a").css("background-color")=="#6C757D"){
			$("a").css("background-color","#6C757D");
		}
	 });
	
</script>
</body>
</html>