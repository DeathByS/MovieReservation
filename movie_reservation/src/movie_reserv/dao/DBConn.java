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
			String url = "jdbc:oracle:thin:@10.10.16.68:1521:xe"; //�����ϰ����ϴ� ip�ּ�/1521(��Ʈ)/xe(sid�� ���� ��ġ�� db�� ���� �ٸ�)
			String id = "java";
			String pwd = "w1r1g1w1";
			
			/* ������ �̷��� ��ȣ���� db�� �����ϱ� ���ؼ� ip�� ��� �ٲ���
			 String url = "jdbc:oracle:thin:@localhost:1521:xe"; //�����ϰ����ϴ� ip�ּ�/1521(��Ʈ)/xe(sid�� ���� ��ġ�� db�� ���� �ٸ�)
			String id = "java";
			String pwd = "1234";
			 */
			
			try {
				Class.forName(driver); //�����Ҵ�, jvm�� �˾Ƽ� �޸��Ҵ����༭ new�� ���� �ν��Ͻ� �������� �ʾƵ� �Ǽ� ��
				conn = DriverManager.getConnection(url, id, pwd);
				System.out.println("Movie �� DB ���� Ȯ�� ");
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
