package com.test.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/delete.do")
public class Delete extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String seq = req.getParameter("seq");
		String page = req.getParameter("page");
		String search = req.getParameter("search");
		BoardDAO dao = new BoardDAO();
		int result = dao.delete(seq);
		
		if(result == 1) {
			resp.sendRedirect(String.format("/Board/board/list.do?page=%s&search=%s",page,search));
		} else {
			PrintWriter writer = resp.getWriter();
			writer.write("<html>");
			writer.write("<body>");
			writer.print("<script>");
			writer.print("alert('failed');history.back();");
			writer.print("</script>");
			writer.write("</body>");
			writer.write("</html>");
		}
	}
}
