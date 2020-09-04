package com.test.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


public class BoardDAO {
	
	Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	private ResultSet rs;
	
	public BoardDAO() {
		DBUtil util = new DBUtil();
		conn = util.open();
	}
	
	public void close() {
		try {
			conn.close();
			
		} catch (Exception e) {
			System.out.println("BoardDAO.close()");
			e.printStackTrace();
		}
	}
	
	public ArrayList<BoardDTO> getList(HashMap<String, String> map) {
		try {
			String where = "";
			if(map.get("search")!=null) {
				where = String.format("where title like  '%%%s%%' or name like  '%%%s%%' or content like '%%%s%%'",map.get("search"),map.get("search"),map.get("search"));
			}

			
			String sql = String.format("select b.* from (select a.*, rownum as rnum from (select * from vwboard %s order by seq desc) a) b where rnum >= %s and rnum <= %s",where,map.get("begin"),map.get("end"));
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			ArrayList<BoardDTO> blist = new ArrayList<BoardDTO>();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setGap(rs.getInt("gap"));
				blist.add(dto);
			}
			return blist;
			
		} catch (Exception e) {
			System.out.println("BoardDAO.getList()");
			e.printStackTrace();
		}
		
		return null;
	}

	public int getTotalCount(String search) {
		try {
			String where = "";
			if(search!=null) {
				where = String.format("where title like  '%%%s%%' or name like  '%%%s%%' or content like '%%%s%%'",search,search,search);
			}
			
			String sql = String.format("select count(*) as cnt from (select a.*, rownum as rnum from (select * from vwboard %s order by seq desc) a) b",where);
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(rs.next()) {
				return rs.getInt("cnt");
			}
		
			
		} catch (Exception e) {
			System.out.println("BoardDAO.getTotalCount()");
			e.printStackTrace();
		}
		return 0;
		
	}

	public int write(BoardDTO dto) {
		try {
			
			String sql = "";
			sql = "insert into tblBoard values(SeqBoard.nextVal,?,?,default,default,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getName());
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("BoardDAO.write()");
			e.printStackTrace();
		}
		return 0;
	}

	public BoardDTO getView(String seq) {
		try {
			String sql = "";
			sql = "select * from tblBoard where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			if(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setTitle(rs.getString("title"));
				dto.setName(rs.getString("name"));
				dto.setContent(rs.getString("content"));
				dto.setSeq(rs.getString("seq"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcount(rs.getString("readcount"));
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("BoardDAO.getView()");
			e.printStackTrace();
		}
		return null;
	}

	public void read(String seq) {
		try {
			
			String sql = "update tblBoard set readcount=readcount+1 where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("BoardDAO.read()");
			e.printStackTrace();
		}
		
	}

	public int edit(BoardDTO dto) {
		try {
			
			String sql = "update tblBoard set name = ?, title = ?, content = ? where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getTitle());
			pstat.setString(3, dto.getContent());
			pstat.setString(4, dto.getSeq());
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("BoardDAO.edit()");
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(String seq) {
		try {
			String sql = "delete tblBoard where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("BoardDAO.delete()");
			e.printStackTrace();
		}
		return 0;
	}
	
	

}
