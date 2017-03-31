/*
 * ���ڵ� ����� �迭�� ���� ���� ����
 * ���ڵ��� �� ������ �� �� ����
 * 
 * jdbc(=Java Data Base Connectivity)
 * java�� �����ͺ��̽� ���� ���
 * 
 * java���� �����ͺ��̽��� ������ ������� ���� ������ �߻�ȭ�� �������̽� ����
 * ���� �� �۵��� ����� �����ڰ� ������(driver)
 * driver : �����ͺ��̽��� ������ �޶� �ڵ� ������ ���� ����
 * */
package table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetTest {
	String driver="oracle.jdbc.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	// �������̽�
	Connection con;	// (MS�迭)���� �õ�X, ������ ���� �� ����� ��� ��ü
	PreparedStatement pstmt;
	ResultSet rs;
	
	// RecordSet ��ü�� �̿��Ͽ� �� ���ڵ� �� �˾Ƹ��� ����
	public ResultSetTest() {
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			if(con!=null){
				String sql="select * from company";
				// ���� �� ���Ŀ� �������� ���� �� �����Ƿ� connection���κ��� �޾ƿ�
				// rs�� Ŀ���� ������, �Ĺ��� ������ �����Ӱ� �����̰ų� �Ѳ����� �ǳʶٰ� �Ϸ���
				// ��ũ�� ������ ��� �ɼ��� �ο��ؾ� ��
				// select���� ��������� ������� ���� ���⸸ �Ѵٸ� READ_ONLY,
				// ������ ���ϰ� �ʹٸ� UPDATABLE
				// ���� ��, select���� ���� ���ڵ�� ��κ��� �б� ����!
				pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs=pstmt.executeQuery();
				
				// ResultSet�� ���� ���ϱ�
				/*
				 * 1. Ŀ���� �� ������ ��ġ��Ű��
				 *  boolean last() 
					Moves the cursor to the last row in this ResultSet object. 
				 * 2. ���� ���� �ִ��� �����
				 *  int getRow() 
					Retrieves the current row number.
				 * */
				
				// ���� ������ ���ڵ�� ������
				rs.last();
				// ���� �߻� - ������ ���� ��� ���տ� �������� �۾��� ����Ǿ����ϴ�
				// ����� Ŀ���� ��ɿ� �Ѳ����� �ǳʶٴ� ����� ��� ���� ����
				// PreparedStatement API ����
				int num=rs.getRow();	// ���� Ŀ���� ����Ű�� ���ڵ� ��ȣ(=���ڵ� ��ġ)
				
				for(int i=0; i<num; i++){
					
				}
								
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ResultSetTest();

	}

}
