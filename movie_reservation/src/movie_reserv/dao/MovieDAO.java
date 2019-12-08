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
		Statement stmt = null; //SQL���, ���� java.sql
		ResultSet rs = null; //DB ����� ���� ����
		ArrayList<MovieVO> mvlist = null;
	
		try {
			conn = DBConn.getConnection(); //connection ��ü ����
			stmt = conn.createStatement();  //stmt ����غ� �� sql���� �غ� ��, sql�غ� ����
			String sql = "select * from movie order by movieid"; //���� ���̺��� movieid�� �����´�
			rs = stmt.executeQuery(sql); //sql�� ������ ���� rs�� �����
			
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
			pstmt.setString(1, "%"+search+"%"); // 1�� ? �̰� search�Ű����ڸ� "%"+search+"%"�� �� ã���� �˱� ���� ��
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
	
	
	
	//�μ�Ʈ��
	public int insert(MovieVO mvo) {
		//insert into book values('', '', '', '');�̷� ������bvo�� �Ƿ��� ��
		Connection conn = null;
		PreparedStatement pstmt = null; //���� �ޱ� ���ϰ� �ϴ� �� where ���ǰ����� �ؼ� ������ �������� �Ǽ��� �������� ����
		int result = 0;
		try {
			conn = DBConn.getConnection();
			String sql = "insert into movie" + "(movieid, moviename, publisher, price, quantity, showdate)"
											+ "values(movieid_seq.nextval, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql); 
			//prepareStatement�� ���� �ִ� �� �ε����� 1���� ������ ���� (0�ƴ�)
			//pstmt.setInt(1, bvo.getBookid());
			pstmt.setString(1, mvo.getMoviename());
			pstmt.setString(2, mvo.getPublisher());
			pstmt.setInt(3, mvo.getPrice());
			pstmt.setInt(4, mvo.getQuantity());
			pstmt.setString(5, mvo.getShowdate());
			
			
			result = pstmt.executeUpdate(); //��� ������Ʈ �ߴ��� ������ ��ȯ��
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, null, null);
		}
		return result;
		
	}
	
	
	//������Ʈ��
		public int update(MovieVO mvo) {
			//insert into book values('', '', '', '');�̷� ������bvo�� �Ƿ��� ��
			Connection conn = null;
			PreparedStatement pstmt = null; //���� �ޱ� ���ϰ� �ϴ� �� where ���ǰ����� �ؼ� ������ �������� �Ǽ��� �������� ����
			int result = 0;
			
			//%%�󿵳��� �߰�
			try {
				conn = DBConn.getConnection();
				//���ͷ� �� ���� �� ���� ����� update book set���� �ν��� ������ϸ� bookset���� db�� �ν��ϱ� ������ ������
				String sql = "update movie " 
						+ "set moviename = ?, publisher = ?, price = ?, "
						+ "quantity = ?, showdate = ? "
						+ "where movieid = ?";
				pstmt = conn.prepareStatement(sql); //�� sql���� �󿵳��� �߰��������
				//prepareStatement�� ���� �ִ� �� �ε����� 1���� ������ ���� (0�ƴ�)
				
				pstmt.setString(1, mvo.getMoviename());
				pstmt.setString(2, mvo.getPublisher());
				pstmt.setInt(3, mvo.getPrice());
				pstmt.setInt(4, mvo.getQuantity());
				pstmt.setString(5, mvo.getShowdate());
				pstmt.setInt(6, mvo.getMovieid()); //������Ʈ���̶� �����ٲ�
				
				result = pstmt.executeUpdate(); //��� ������Ʈ �ߴ��� ������ ��ȯ��
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt, null, null);
			}
			return result;
			
		}
		
	
		//����Ʈ��
				public int delete(int movieid) {
					//insert into book values('', '', '', '');�̷� ������bvo�� �Ƿ��� ��
					Connection conn = null;
					Statement stmt = null; //���� �ޱ� ���ϰ� �ϴ� �� where ���ǰ����� �ؼ� ������ �������� �Ǽ��� �������� ����
					int result = 0;
					try {
						conn = DBConn.getConnection();
						
						//���ͷ� �� ���� �� ���� ����� update book set���� �ν��� ������ϸ� bookset���� db�� �ν��ϱ� ������ ������
						String sql = "delete from movie " 
								+ "where movieid = "+movieid;
						stmt = conn.createStatement();
						//prepareStatement�� ���� �ִ� �� �ε����� 1���� ������ ���� (0�ƴ�)
						
						
						result = stmt.executeUpdate(sql); //��� ������Ʈ �ߴ��� ������ ��ȯ��
						
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
