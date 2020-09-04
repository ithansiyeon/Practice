<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    #tbl1 tr:nth-child(1){
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

    #a:active {
        color: #fff;
        cursor: default;
        background-color: #6C757D;
        border-color: #6C757D;
    }

    .search {
        float: right;
        margin-right: 50px;
        margin-bottom: 30px;
    }

    #btnsearch {
        cursor: pointer;
        float:right;
    }

    tr:nth-child(1) td{
        padding: 8px;
        line-height: 1.42857143;
        vertical-align: top;
        border-top: 1px solid #ddd;
        border-bottom: 1px solid #ddd;
        background-color: #FAFAFA;
    }

    tr:nth-child(2) {
        border-top: 2px solid #ddd;
        border-bottom: 1px solid #ddd;
        width: 900px;
        height: 100px;
        vertical-align: top;
    }
    
    tr:nth-child(2) td {
        padding-left: 15px;
        padding-right: 15px;
        line-height: 1.42857143;
        padding-top: 10px;
    }

    table {
        margin: 30px auto;
        border-collapse: collapse;
    }

    tr:nth-child(1) td:nth-child(2){
        width: 500px;
    }

</style>
    <%@include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
   
            <div id = "main">
                <div id = "title"><h1>자유 게시판 <small>Board</small></h1></div>
               <table>
                    <tbody>
                        <tr>
                            <td>${dto.seq}.</td>
                            <td>${dto.title}</td>
                            <td>${dto.name}</td>
                            <td>${dto.regdate}</td>
                            <td>읽음(${dto.readcount})</td>
                        </tr>
                        <tr>
                            <td colspan="5">${dto.content}</td>
                        </tr>
                    </tbody>
               </table>

                <button style="float: right;width: 60px;margin-right: 70px;" onclick="location.href='/Board/board/list.do?page=${page}&search=${search}'">목록</button>
                <button style="float: right;width: 60px;" onclick="location.href='/Board/board/edit.do?page=${page}&search=${search}&seq=${dto.seq}'">수정</button>
                <button style="float: right;width: 60px;" onclick="location.href='/Board/board/delete.do?page=${page}&search=${search}&seq=${dto.seq}'">삭제</button>
            </div>
            
</body>
</html>