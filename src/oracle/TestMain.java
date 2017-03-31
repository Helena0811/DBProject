/*
 * �츮�� ������� �����ͺ��̽� ��ǰ�� ��� DBMS
 * DB(�����)MS(���� �ý���) - ��Ʈ��ũ ����̱� ������ ���� ���� ����
 * 
 * �����ͺ��̽� ���� Ŭ���̾�Ʈ		->		����
 * ex) sqlplus, toad				(Listener+)oracle
 * �̹����� class�� Ŭ���̾�Ʈ��!
 * 
 * ���� ������� ��Ʈ��ũ ���������� TCP/IP ����̹Ƿ�, 
 * �������� ȣ��Ʈ�� �����Ϸ��� �� ȣ��Ʈ�� �ּҸ� �˾ƾ� ��!
 * -> ����� TCP/IP�� ������ IP �ּҸ� �˾ƾ� �Ѵ�!
 * -> user �������� �˾ƾ� ��(ID & PW)
 * */
package oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMain {
	public static void main(String[] args) {
		/*
		 * 1�ܰ� 
		 * ����Ŭ�� java�� ������ �� �ִ� �ڵ尡 ����ִ� jar ������ �޸𸮿� �ε��ؾ� ��
		 * �̷��� �����ͺ��̽� ���� jar ������ java������ ����̹��� ��
		 * ����̹��� DB �����翡�� ������
		 * oracle - oracle��, mysql - oracle��, mssql - MS
		 * 
		 * jar ���� ���
		 * ex) C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6
		 * 
		 * cf) �ý��� ���̺귯��
		 * �����ڰ� �������� �ʾƵ� �̹� default�� ��ΰ� �����Ǿ� ���� ������ ���̺귯��
		 * 
		 * �ܺο� �����ϴ� jar ������ ���� ������Ʈ�� ���̺귯���� ��밡���ϵ��� ����
		 * ������Ʈ Configure Build Path - new - add jar
		 * */
		
		/* 
		 * 2�ܰ� - ����Ŭ�� ���� 
		 * ����̹� Ŭ���� �ʿ� - Ŭ���� ������ ���� ���� ���� �ڵ尡 static/method ������ �ö󰡵���
		 * */
		
		// �ݾƾ� �ϹǷ� ���� �����ϵ��� try�� �ۿ� ����
		Connection con=null;
		PreparedStatement pstmt=null;
		
		// ����̹� Ŭ���� �ε� -> String������ �־��־�� ��
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����");
			
			// �ϱ��ϱ�!(jdbc:DB����:���:@����:@��Ʈ��ȣ)
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521", "batman", "1234");
			
			// try���� �����ߴٰ� �ؼ� ������ ���� �ƴ϶�, 
			// getConnection�� ��ȯ���� connection ��ü�� �޸𸮿� �ö���� �� ������ ������ ����
			if(con!=null){
				System.out.println("���� ����");
				
				// ���� user�� ������ ���̺� insert �õ�
				String sql="insert into company(company_id, brand) values(seq_company.nextval,'����Ű')";
				// ������ ������ ���ؼ��� �������� �����ϴ� ��ü�� �̿��ؾ� �� -> PreparedStatement �������̽�
				
				pstmt=con.prepareStatement(sql);
				int result=pstmt.executeUpdate();	// ������ ���� �޼ҵ�
				// �� ������ ���࿡ ���� �ݿ��� ���ڵ� ���� ��ȯ��
				// ex) insert���� ������ ������ ���� 1, ������ ���� 0
				if(result==1){
					System.out.println("�Է� ����");
				}
				else{
					System.out.println("�Է� ����");
				}
				
			}
			else{	// connection�� null�̸� ��ü�� ���� �޸𸮿� �ö���� ���� ��!
				System.out.println("���� ����");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹��� ã�� �� �����ϴ�.");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			// ��Ʈ���� DB ���� �۾� �Ŀ��� �ݵ�� �ݴ� ó���� �ؾ� �Ѵ�!
			
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
