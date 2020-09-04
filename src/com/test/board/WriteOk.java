package com.test.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/writeok.do")
public class WriteOk extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String title = req.getParameter("title");
		String name = req.getParameter("name");
		String content = req.getParameter("content");
		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setName(name);
		dto.setContent(content);
		
		BoardDAO dao = new BoardDAO();
	
		
		int result = dao.write(dto);
		dao.close();
		
		if(result == 1) {
			resp.sendRedirect("/Board/board/list.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<html>");
			writer.print("<body>");
			writer.print("<script>");
			writer.print("alert('failed');history.back();");
			writer.print("</script>");
			writer.print("</body>");
			writer.print("</html>");
			writer.close();
		}
		
	}
}
