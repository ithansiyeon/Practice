package com.test.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/editok.do")
public class EditOk extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String seq = req.getParameter("seq");
		String search = req.getParameter("search");
		String page = req.getParameter("page");
		String title = req.getParameter("title");
		String name = req.getParameter("name");
		String content = req.getParameter("content");
		BoardDTO dto = new BoardDTO();
		
		dto.setSeq(seq);
		dto.setContent(content);
		dto.setTitle(title);
		dto.setName(name);
		
		BoardDAO dao = new BoardDAO();
		int result = dao.edit(dto);
		
		if(result == 1) {
			resp.sendRedirect(String.format("/Board/board/view.do?page=%s&search=%s&seq=%s",page,search,seq));
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
