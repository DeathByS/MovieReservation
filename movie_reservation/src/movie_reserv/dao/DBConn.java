package movie_reserv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

	private static Connection conn = null;
	private DBConn () {};

	public static Connection getConnection() {
		
		if(conn == null) {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@10.10.16.68:1521:xe"; //접속하고자하는 ip주소/1521(포트)/xe(sid값 내가 설치한 db에 따라 다름)
			String id = "java";
			String pwd = "w1r1g1w1";
			
			/* 원래는 이랬고 길호형쪽 db에 접속하기 위해서 ip와 비번 바꿔줌
			 String url = "jdbc:oracle:thin:@localhost:1521:xe"; //접속하고자하는 ip주소/1521(포트)/xe(sid값 내가 설치한 db에 따라 다름)
			String id = "java";
			String pwd = "1234";
			 */
			
			try {
				Class.forName(driver); //동적할당, jvm이 알아서 메모리할당해줘서 new로 새로 인스턴스 생성하지 않아도 되서 편리
				conn = DriverManager.getConnection(url, id, pwd);
				System.out.println("Movie 쪽 DB 연결 확인 ");
				}
				catch(ClassNotFoundException e){
					System.out.println("driver load fail");
				}	
				catch (SQLException e) {
				System.out.println("db connection fail");
				}		
			
		}
		return conn;
		
	}
	
}
