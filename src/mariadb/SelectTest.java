/*
 * mariaDB�� �����Ͽ� ���ڵ带 �ֿܼ� ����غ���!
 * ����) DBMS �����簡 �����ϴ� ����̹��� �̸� �غ��ؾ� ��
 * */
package mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String password="";
	
	Connection con;	// ���� ������ ���� �������̽�
	PreparedStatement pstmt;	// ���� ���� ��ü
	// �������̽��Ƿ� connection���κ��� �޾ƿ;� ��(���ӿ� ������)
	ResultSet rs;	// �������� select���� ���, �������� �����ͺ��̽��� ���̺�� ������ ��� ������ ��Ƴ��� ��ü(=ǥ)
	/*
	 * ResultSet�� Ŀ���� ù��° ���ڵ� ��(�� ��)�� �����ϱ� ������, ���� �����Ϸ��� next()�� �̿��ؾ� ��!
	 * ex)
	 *  +-----------+--------+------+
		|         1 | ������ 	 |   26 |
		|         2 | ������ 	 |   27 |
		|         3 | �̾ƿ� 	 |   25 |
		+-----------+--------+------+
	 * 
	 * �̵��� ���ÿ� ���� ���θ� �Ǵ�(boolean ��ȯ)
	 * next()
	   Moves the cursor forward one row from its current position.
	 * */
	
	
	public SelectTest() {
		/*
		 * DBMS ���� ��Ģ
		 * 1. ����̹��� �ε�
		 * 2. ����
		 * 3. ���ϴ� ������ ����
		 * 4. DB ���õ� �ڿ� �ݱ�
		 * */
		try {
			// ����̹� �ε�
			Class.forName(driver);
			System.out.println("�ε� ����");
			
			// ����
			con=DriverManager.getConnection(url, user, password);
			if(con!=null){
				// controlâ�� �ƴϹǷ� ���� �����ݷ��� ����!
				String sql="select * from member";
				System.out.println("���� ����");
				
				pstmt=con.prepareStatement(sql);
				// insert���� java->oracle, select���� oracle->java
				// �޸� ��(����)�� ����� �ִ� select���� ���� ǥ�� �����;� ��!
				// pstmt.executeQuery();�� ��� -> resultSet(�޸𸮻��� ǥ�� ���� ������ ��ü)
				
				// ���� ���� �� ��ȯ�Ǵ� ��� ������ �ޱ�
				rs=pstmt.executeQuery();	// �������� �����
				
			/*	
				// Ŀ�� �� ĭ ����
				rs.next();
				// �̸� ���� -> �÷��� �ش��ϴ� �� ��ȯ
				String name=rs.getString("name");
				// ���� ��ȯ
				int age=rs.getInt("age");
				// ��� id ��ȯ
				int member_id=rs.getInt("member_id");
				System.out.println(member_id+","+name+","+age);
				
				// Ŀ�� �� ĭ �� ����
				rs.next();
			*/
				// ��ü ���
				while(rs.next()){
					String name=rs.getString("name");
					int age=rs.getInt("age");
					int member_id=rs.getInt("member_id");
					System.out.println(member_id+","+name+","+age);
				}
			}
			else{
				System.out.println("���� ����");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		new SelectTest();
	}

}
