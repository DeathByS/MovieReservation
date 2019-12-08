package movie_reserv.dao;

import java.sql.*;
import java.util.ArrayList;

import movie_reserv.vo.MovieOrdersVO;

public class MovieOrdersDAO {

	public ArrayList<MovieOrdersVO> selectAll(){
		Connection conn = null; //connection 객체 생성
		Statement stmt = null;  //SQL 등록, 실행
		ResultSet rs = null;    //DB 결과값 받을 공간
		
	
		ArrayList<MovieOrdersVO> mlist = null;
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement(); //stmt 사용준비 
					//sql 사용준비
			String sql = "select * from movieorders "
					+ "order by orderid";
			rs = stmt.executeQuery(sql); //sql을 실행한 값을 
										//rs에 담아줌
			mlist = new ArrayList<MovieOrdersVO>();
			while(rs.next()) {
				MovieOrdersVO mvo = new MovieOrdersVO();
				
				mvo.setOrderid(rs.getInt("orderid"));
				mvo.setCustid(rs.getString("custid"));
				mvo.setMovieid(rs.getInt("movieid"));
				mvo.setSaleprice(rs.getInt("saleprice"));
				mvo.setDate(rs.getString("orderdate"));
				
				mlist.add(mvo);
				
//				System.out.println(rs.getInt("orderid")
//						+ " | "+rs.getString("custid")
//						+ " | "+rs.getInt("movieid")
//						+ " | "+rs.getInt("saleprice")
//						+ " | "+rs.getString("orderdate"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		return mlist;
	}
	
	public ArrayList<MovieOrdersVO> selectSearch(String search){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MovieOrdersVO> mlist = null;
		try {
			conn = DBConn.getConnection();
			String sql = "select * from MovieOrders "
					+ "where custid like ? "
					+ "order by orderid";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();
			mlist = new ArrayList<MovieOrdersVO>();
			while(rs.next()) {
				MovieOrdersVO mvo = new MovieOrdersVO();
				mvo.setOrderid(rs.getInt("orderid"));
				mvo.setCustid(rs.getString("custid"));
				mvo.setMovieid(rs.getInt("movieid"));
				mvo.setSaleprice(rs.getInt("saleprice"));
				mvo.setDate(rs.getString("orderdate"));
				
				mlist.add(mvo);
				
//				System.out.println(rs.getInt("orderid")
//				+ " | "+rs.getString("custid")
//				+ " | "+rs.getInt("movieid")
//				+ " | "+rs.getInt("saleprice")
//				+ " | "+rs.getString("orderdate"));
				}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
		return mlist;
	}
	
	
	public int insert(MovieOrdersVO mvo) {
		// insert into book values(mvo);
		Connection conn = null; // DB 연결
		PreparedStatement pstmt = null; // sql문 실행

		int result = 0; // 결과 값 -> 실행된 행의 수
		try {
			conn = DBConn.getConnection();
			String sql = "insert into movieorders "
				+ "(orderid, custid, movieid, saleprice, orderdate) "
				+ "values (orderid_seq.nextval, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'))";

			pstmt = conn.prepareStatement(sql);

//			pstmt.setInt(1, mvo.getBookid());
			pstmt.setString(1, mvo.getCustid());
			pstmt.setInt(2, mvo.getMovieid());
			pstmt.setInt(3, mvo.getSaleprice());
			pstmt.setString(4, mvo.getDate());

			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt, null);
		}

		return result;

	}
	
	public int update(MovieOrdersVO mvo) {
		//insert into book values(mvo);
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			conn = DBConn.getConnection();
			String sql = "update MovieOrders "
					+ "set custid = ?, movieid = ?, saleprice = ?, orderdate = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') "
					+ "where orderid = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mvo.getCustid());
			pstmt.setInt(2, mvo.getMovieid());
			pstmt.setInt(3, mvo.getSaleprice());
			pstmt.setString(4, mvo.getDate());
			pstmt.setInt(5,  mvo.getOrderid());
			
			result =  pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt, null);
		}
		
		return result;
	}	
	
	public int delete(int orderid) {
		//insert into book values(mvo);
		Connection conn = null;
		Statement stmt = null;
		int result=0;
		try {
			conn = DBConn.getConnection();
			
			String sql = "delete from MovieOrders "
					+ "where orderid = "+orderid; 
			stmt = conn.createStatement();
			
			
			result =  stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt, null);
		}
		
		return result;
	}	
	
	
	public void close(PreparedStatement pstmt, ResultSet rs) {
		try {
			if(pstmt!=null) {
				pstmt.close();
			}
			if(rs !=null) {
				rs.close();
			}
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}
	
	public void close(Statement stmt, ResultSet rs) {
		try {
			if(stmt!=null) {
				stmt.close();
			}
			if(rs !=null) {
				rs.close();
			}
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}
	
}
