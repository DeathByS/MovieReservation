package movie_reserv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import movie_reserv.vo.MovieCustomerVO;

public class CustomerDAO {
	
	
	public String login( String custid, String pwd ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String resultCustid = null;
		
		try {
			conn = DBConn.getConnection();
			String sql = "select custid from moviecustomer "
							+ "where custid = ? and pwd = ? ";
					
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, custid );
			pstmt.setString( 2, pwd );
			rs = pstmt.executeQuery();
			while( rs.next()) {
				resultCustid = rs.getString( "custid" );
			}
		} catch ( SQLException e) {
			// TODO: handle exception
		}finally {
			close( pstmt, rs, null );
		}
		return resultCustid;	
	}
	
	public void close(Statement stmt, ResultSet rs, Connection conn) {
		try {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}

	public void close(PreparedStatement stmt, ResultSet rs, Connection conn) {
		try {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
	public int registNew( MovieCustomerVO mvo ) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();
			String sql = "insert into moviecustomer "
							+ "(custid, pwd, name, address, phone ) "
							+ "values ( ?,?,?,?,?)";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, mvo.getId());
			pstmt.setString( 2, mvo.getPwd());
			pstmt.setString( 3, mvo.getName());
			pstmt.setString( 4, mvo.getAddr());
			pstmt.setString( 5, mvo.getPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close( pstmt, null, null );
		}
		return result;
	}
	public MovieCustomerVO selectOne( String id ) {
		Connection conn 			= null;
		Statement stmt 				= null;
		ResultSet rs 				= null;
		MovieCustomerVO mvo			= new MovieCustomerVO();
		
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from moviecustomer where custid = '" + id + "'";
			rs = stmt.executeQuery( sql );			
			if( rs.next()) {
				mvo.setId( rs.getString( "custid" ));
				mvo.setName( rs.getString( "name" ));
				mvo.setAddr( rs.getString( "address" ));
				mvo.setPhone( rs.getString( "phone" ));
				mvo.setPwd( rs.getString( "pwd" ));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close( stmt, rs, null );
		}
		return mvo;
	}
	
	public int delete( String custid ) {
		Connection conn = null;
		Statement stmt = null;
		
		if( custid.equals( "admin" )) {
			return -1;
		}
		
		int result = 0;
		try {
			conn = DBConn.getConnection();
			String sql = "delete from moviecustomer "
							+ "where custid = '" + custid + "'";
			stmt = conn.createStatement();			
			result = stmt.executeUpdate( sql );
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close( stmt, null, null );
		}
		
		return result;
	}
	
	public ArrayList< MovieCustomerVO > selectAll(){
		Connection conn 					= null;
		Statement stmt 						= null;
		ResultSet rs 						= null;
		ArrayList< MovieCustomerVO > clist 	= null;
		
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from moviecustomer";
			rs = stmt.executeQuery( sql );
			clist = new ArrayList< MovieCustomerVO >();
			while( rs.next()) {
				MovieCustomerVO cvo = new MovieCustomerVO();
				cvo.setId( rs.getString( "custid" ));
				cvo.setName( rs.getString( "name" ));
				cvo.setAddr( rs.getString( "address" ));
				cvo.setPhone( rs.getString( "phone" ));
				cvo.setPwd( rs.getString( "pwd" ));
				clist.add( cvo );		
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close( stmt, rs, null );
		}
		return clist;
	}
	
	public int update( MovieCustomerVO cvo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DBConn.getConnection();
			String sql = "update moviecustomer "
							+ "set name = ?, pwd = ?, address = ?, phone = ? "
							+ "where custid = ?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, cvo.getName());
			pstmt.setString( 2, cvo.getPwd());
			pstmt.setString( 3, cvo.getAddr());
			pstmt.setString( 4, cvo.getPhone());
			pstmt.setString( 5, cvo.getId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close( pstmt, null, null );
		}
		return result;
	}
}
