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

    tr td{
        padding: 8px;
        line-height: 1.42857143;
        vertical-align: top;
        border-top: 1px solid #ddd;
    }

    #title {
        display: block;
        width: 900px;
    }

    #name {
        display: block;
        width: 900px;
    }

    table {
        border-collapse: collapse;
    }
    

    #content{
        display: block;
        height: 500px;
        width: 900px;
    }

    #box {
        width: 900px;
        margin: 30px auto;
    }

</style>
     <%@include file = "/WEB-INF/views/inc/asset.jsp" %>
</head>
<body>
   
            <div id = "main">
                <div id = "title"><h1>자유 게시판 <small>Board</small></h1></div>
                    <div id = "box"> 
                    	<form method = "POST" action = "/Board/board/writeok.do">
                         <table>
                                <tr><td><input name ="title" id = "title" type="text" placeholder="title" required autocomplete="off"></td></tr>
                                <tr><td><input name ="name" id = "name" type="text" placeholder="name" required autocomplete="off"></td></tr>
                                <tr><td><textarea name ="content" id = "content" placeholder="content" required autocomplete="off"></textarea></td></tr>
                        </table>
                        <button type = "submit" style="float: right;">쓰기</button>
                        </form>
                        <button type = "button" style="float: right;margin-right: 10px;" onclick="location.href='/Board/board/list.do';">뒤로가기</button>
                    </div>
            </div>

                
                
            
</body>
</html>