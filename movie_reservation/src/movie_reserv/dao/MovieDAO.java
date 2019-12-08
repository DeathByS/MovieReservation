package movie_reserv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import movie_reserv.vo.MovieVO;



public class MovieDAO {

	public ArrayList<MovieVO> selectAll() {
		Connection conn = null;
		Statement stmt = null; //SQL등록, 실행 java.sql
		ResultSet rs = null; //DB 결과값 받을 공간
		ArrayList<MovieVO> mvlist = null;
	
		try {
			conn = DBConn.getConnection(); //connection 객체 생성
			stmt = conn.createStatement();  //stmt 사용준비 즉 sql날릴 준비가 됨, sql준비 실행
			String sql = "select * from movie order by movieid"; //무비 테이블에서 movieid를 가져온다
			rs = stmt.executeQuery(sql); //sql을 실행한 값을 rs에 담아줌
			
			mvlist = new ArrayList<MovieVO>();
				
			while (rs.next()) {
				MovieVO mvo = new MovieVO();
				mvo.setMovieid(rs.getInt("movieid"));
				mvo.setMoviename(rs.getString("moviename"));
				mvo.setPublisher(rs.getString("publisher"));
				mvo.setPrice(rs.getInt("price"));
				mvo.setQuantity(rs.getInt("quantity"));
				mvo.setShowdate(rs.getString("showdate"));
			
				
			
				mvlist.add(mvo);

			}
			
		} catch (Exception e) {
			
		} finally {
			
			close(stmt, rs, null);	
			
		}	
		return mvlist;
		
	}
	
	
	public ArrayList<MovieVO> selectSearch(String search) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MovieVO> mvlist = null;
		try {
			conn = DBConn.getConnection();
			String sql = "select * from movie "
					+ "where moviename like ? "
					+ "order by movieid";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%"); // 1은 ? 이고 search매개인자를 "%"+search+"%"로 뭘 찾는지 알기 위한 것
			rs = pstmt.executeQuery();
			mvlist = new ArrayList<MovieVO>();
			while(rs.next()) {
				MovieVO mvo = new MovieVO(); 
				mvo.setMovieid(rs.getInt("movieid"));
				mvo.setMoviename(rs.getString("moviename"));
				mvo.setPublisher(rs.getString("publisher"));
				mvo.setPrice(rs.getInt("price"));
				mvo.setQuantity(rs.getInt("quantity"));
				mvo.setShowdate(rs.getString("showdate"));				
				
				mvlist.add(mvo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs, null);
		}
		
		return mvlist;
	}
	
	
	
	//인서트문
	public int insert(MovieVO mvo) {
		//insert into book values('', '', '', '');이런 값들이bvo에 실려서 옴
		Connection conn = null;
		PreparedStatement pstmt = null; //값을 받기 편하게 하는 것 where 조건같은거 해서 조건이 많아지면 실수가 많아지기 때문
		int result = 0;
		try {
			conn = DBConn.getConnection();
			String sql = "insert into movie" + "(movieid, moviename, publisher, price, quantity, showdate)"
											+ "values(movieid_seq.nextval, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql); 
			//prepareStatement로 값을 넣는 건 인덱스가 1부터 시작함 주의 (0아님)
			//pstmt.setInt(1, bvo.getBookid());
			pstmt.setString(1, mvo.getMoviename());
			pstmt.setString(2, mvo.getPublisher());
			pstmt.setInt(3, mvo.getPrice());
			pstmt.setInt(4, mvo.getQuantity());
			pstmt.setString(5, mvo.getShowdate());
			
			
			result = pstmt.executeUpdate(); //몇개를 업데이트 했는지 갯수를 반환함
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, null, null);
		}
		return result;
		
	}
	
	
	//업데이트문
		public int update(MovieVO mvo) {
			//insert into book values('', '', '', '');이런 값들이bvo에 실려서 옴
			Connection conn = null;
			PreparedStatement pstmt = null; //값을 받기 편하게 하는 것 where 조건같은거 해서 조건이 많아지면 실수가 많아지기 때문
			int result = 0;
			
			//%%상영날자 추가
			try {
				conn = DBConn.getConnection();
				//엔터로 줄 내릴 땐 띄어쓰기 해줘야 update book set으로 인식함 띄어쓰기안하면 bookset으로 db가 인식하기 때문에 오류남
				String sql = "update movie " 
						+ "set moviename = ?, publisher = ?, price = ?, "
						+ "quantity = ?, showdate = ? "
						+ "where movieid = ?";
				pstmt = conn.prepareStatement(sql); //위 sql문에 상영날자 추가해줘야함
				//prepareStatement로 값을 넣는 건 인덱스가 1부터 시작함 주의 (0아님)
				
				pstmt.setString(1, mvo.getMoviename());
				pstmt.setString(2, mvo.getPublisher());
				pstmt.setInt(3, mvo.getPrice());
				pstmt.setInt(4, mvo.getQuantity());
				pstmt.setString(5, mvo.getShowdate());
				pstmt.setInt(6, mvo.getMovieid()); //업데이트문이라 순서바뀜
				
				result = pstmt.executeUpdate(); //몇개를 업데이트 했는지 갯수를 반환함
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt, null, null);
			}
			return result;
			
		}
		
	
		//딜리트문
				public int delete(int movieid) {
					//insert into book values('', '', '', '');이런 값들이bvo에 실려서 옴
					Connection conn = null;
					Statement stmt = null; //값을 받기 편하게 하는 것 where 조건같은거 해서 조건이 많아지면 실수가 많아지기 때문
					int result = 0;
					try {
						conn = DBConn.getConnection();
						
						//엔터로 줄 내릴 땐 띄어쓰기 해줘야 update book set으로 인식함 띄어쓰기안하면 bookset으로 db가 인식하기 때문에 오류남
						String sql = "delete from movie " 
								+ "where movieid = "+movieid;
						stmt = conn.createStatement();
						//prepareStatement로 값을 넣는 건 인덱스가 1부터 시작함 주의 (0아님)
						
						
						result = stmt.executeUpdate(sql); //몇개를 업데이트 했는지 갯수를 반환함
						
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						close(stmt, null, null);
					}
					return result;
					
				}
		
		
	
	public void close(Statement stmt, ResultSet rs, Connection conn) {
		try {
			if(stmt != null) {
				stmt.close();
			}
			if(rs != null) {
				rs.close();
			}
			if(conn != null) {
				conn.close();
			}
			
		} catch (Exception e2) {
			
		}
	}
	
	public void close(PreparedStatement stmt, ResultSet rs, Connection conn) {
		try {
			if(stmt != null) {
				stmt.close();
			}
			if(rs != null) {
				rs.close();
			}
			if(conn != null) {
				conn.close();
			}
			
		} catch (Exception e2) {
			
		}
	}
	
}
