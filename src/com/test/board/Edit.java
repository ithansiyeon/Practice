package com.test.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/edit.do")
public class Edit extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String seq = req.getParameter("seq");
		String page = req.getParameter("page");
		String search = req.getParameter("search");
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.getView(seq);
		
		req.setAttribute("dto",dto);
		req.setAttribute("seq", seq);
		req.setAttribute("page", page);
		req.setAttribute("search", search);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/edit.jsp");
		dispatcher.forward(req, resp);
	}
}
