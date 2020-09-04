package com.test.board;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/view.do")
public class View extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		String seq = req.getParameter("seq");
		String search = req.getParameter("search");
		String page = req.getParameter("page");
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.getView(seq);
		Boolean read = (boolean)session.getAttribute("read");
		if(read == null || read ==  false) {
			dao.read(seq);
			session.setAttribute("read", true);
		}
		
		String content = dto.getContent();
		content = content.replace("\r\n", "<br>");
		dto.setContent(content);
		
		req.setAttribute("seq", seq);
		req.setAttribute("search", search);
		req.setAttribute("page", page);
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		dispatcher.forward(req, resp);
	}
}
