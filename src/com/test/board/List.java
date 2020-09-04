package com.test.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		BoardDAO dao = new BoardDAO();
		int begin = 0;
		int end = 0;
		int nowPage = 0;
		int pageSize = 10;
		int totalCount = 0;
		int blockSize = 10;
		int totalPage = 0;
		int n = 0;
		int loop = 0;
		
		HttpSession session = req.getSession();
		
		String search = req.getParameter("search");
		String page = req.getParameter("page");
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("search", search);
		if(page == null || page == "") {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		
		begin = (nowPage-1)*pageSize + 1;
		end = begin + pageSize - 1;
		
		map.put("begin", begin+"");
		map.put("end",end+"");
		
		ArrayList<BoardDTO> blist = dao.getList(map);
		
		totalCount = dao.getTotalCount(search);
		totalPage = (int) Math.ceil((double)totalCount/pageSize);
		Calendar now = Calendar.getInstance();
		for(BoardDTO dto : blist) {
			if(dto.getRegdate().startsWith(String.format("%tF", now))) {
				dto.setRegdate(dto.getRegdate().substring(11));
			} else {
				dto.setRegdate(dto.getRegdate().substring(0,10));
			}
			if(search != null && search != "") {
				String title = dto.getTitle();
				title = title.replace(search, "<span style = 'font-weight:bold;color:tomato;'>"+search+"</span>");
				dto.setTitle(title);
				
				String name = dto.getName();
				name = name.replace(search, "<span style = 'font-weight:bold;color:tomato;'>"+search+"</span>");
			}
		}
		
		
		session.setAttribute("read", false);
		loop = 1;
		n = ((nowPage-1)/blockSize)*blockSize + 1;
		String pagebar = "";
		if(search == null) {
			search = "";
		}
//		<nav class = "pagebar">
//        <ul>
//           <li>
//               <a href="#" aria-label="Previous">
//                   <span area-hidden = "true">&laquo;</span>
//               </a>
//           </li>
//           <li><a href = "#">1</a></li>
//           <li><a href = "#">2</a></li>
//           <li><a href = "#">3</a></li>
//           <li><a href = "#">4</a></li>
//           <li><a href = "#">5</a></li>
//           <li><a href = "#">6</a></li>
//           <ul>
//               <li>
//                   <a href = "#" aria-label="Next">
//                       <span area-hidden = "true">&raquo;</span>
//                   </a>
//               </li>
//           </ul>
//        </ul>   
//   </nav>
		
		
		pagebar+="<nav class = \"pagebar\">";
		pagebar+="<ul>";
		
		if(n==1) {
			pagebar+="<li>";
			pagebar+="<a class = 'disabled' href=\"#!\" aria-label=\"Previous\">";
			pagebar+="<span area-hidden = \"true\">&laquo;</span>";
			pagebar+="</a>";
			pagebar+="</li>";
		} else {
			pagebar+="<li>";
			pagebar+=String.format("<a href=\"/Board/board/list.do?page=%d&search=%s\"aria-label=\"Previous\">",n-1,search);
			pagebar+="<span area-hidden = \"true\">&laquo;</span>";
			pagebar+="</a>";
			pagebar+="</li>";
		}
		
		while(!(loop > blockSize || n >totalPage)) {
			if(n == nowPage) {
				pagebar+="<li>";
				pagebar+=String.format("<a  class = 'active' href = \"#!\">%d</a>",n);
				pagebar+="</li>";
			} else {
				pagebar+="<li>";
				pagebar+=String.format("<a href = \"/Board/board/list.do?page=%d&search=%s\">%d</a>",n,search,n);
				pagebar+="</li>";
			}
			loop++;
			n++;
		}
		
		if(n>totalPage) {
			pagebar+="<li>";
			pagebar+="<a class = 'disabled' href=\"#!\" aria-label=\"Next\">";
			pagebar+="<span area-hidden = \"true\">&raquo;</span>";
			pagebar+="</a>";
			pagebar+="</li>";	
		} else {
			pagebar+="<li>";
			pagebar+=String.format("<a href=\"/Board/board/list.do?page=%d&search=%s\" aria-label=\"Next\">",n,search);
			pagebar+="<span area-hidden = \"true\">&raquo;</span>";
			pagebar+="</a>";
			pagebar+="</li>";	
		}
		
		pagebar+="</ul>";
		pagebar+="</nav>";
		
		req.setAttribute("page", page);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("search", search);
		req.setAttribute("blist", blist);
		req.setAttribute("pagebar", pagebar);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		dispatcher.forward(req, resp);
		
	}
}
