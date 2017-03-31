/*
 * JTable ����غ���
 * 
 * JTable : swing�� ������Ʈ �� �����ͺ��̽��� ��� ������ �ð�ȭ�ϱ⿡ ����ȭ�� ������Ʈ
 * 
 * ���ڵ��� ������ ���� �迭�� ũ�⸦ �����ؼ� �����غ���!
 * */
package table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableTest2 extends JFrame{
	JTable table;
	JScrollPane scroll;		// JTable�� ��ũ�� ����
	
	String driver="oracle.jdbc.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	Connection con;	// ���� ����
	PreparedStatement pstmt;
	ResultSet rs;	// select���� ��츸 �ʿ� -> ����� ��� ���ؼ�
	
	String[][] data;
	String[] column={
		"empno","ename","job","mgr","hiredate","sal","comm","deptno"
	};
	
	public TableTest2() {
		// DB������ �Ǿ� �־�� table�� ���� ���� �� ����
		loadData();
		
		//setLayout(new FlowLayout());
		//JTable(int numRows, int numColumns)
		//table=new JTable(3,3);
		//JTable(Object[][] rowData, Object[] columnNames)
		table=new JTable(data, column);
		scroll=new JScrollPane(table);
		
		add(scroll);
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// ���ڵ� ä���ֱ�
	// ���̺��� �����ϱ� ����, mariaDB�� ������ member ���̺��� ���ڵ带 2���� �迭�� ���
	// -> JTable�� ������ �μ��� 2���� �迭�� ���Ǳ� ����!
	public void loadData(){
		/*
		 * 1. ����̹� �ε�
		 * 2. ����
		 * 3. ������ ����
		 * 4. DB �ݱ�
		 * */
		try {
			Class.forName(driver);	// ����̹� �ε�
			con=DriverManager.getConnection(url, user, password);
			
			// ������ ����� �Ǿ��ٸ�
			if(con!=null){
				System.out.println("���� �Ϸ�");
				String sql="select * from emp";
				// ResultSet�� ũ�⸦ ��ȯ�ϱ� ���� ���׷��̵�
				pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
				rs=pstmt.executeQuery();
				
				// 2���� �迭 data�� �ֱ�
				// ������ ��ġ�� ������ ��ġ�� �ޱ�
				rs.last();
				int total=rs.getRow();
				int index=0;
				
				data=new String[total][column.length];
				
				// ������ ��ġ���� �ٽ� ���󺹱�
				rs.beforeFirst();		// first()�� ù��°, beforeFirst()�� ������ Ŀ�� ��ġ(�ƹ��͵� ����Ű�� �ʴ� ����)

				while(rs.next()){
					int empno=rs.getInt("empno");
					String ename=rs.getString("ename");
					String job=rs.getString("job");
					int mgr=rs.getInt("mgr");
					String hiredate=rs.getString("hiredate");
					int sal=rs.getInt("sal");
					String comm=rs.getString("comm");	// comm�� null�� ��쵵 �ִµ� ��� �ɱ�?
					int deptno=rs.getInt("deptno");
					
					data[index][0]=Integer.toString(empno);
					data[index][1]=ename;
					data[index][2]=job;
					data[index][3]=Integer.toString(mgr);
					data[index][4]=hiredate;
					data[index][5]=Integer.toString(sal);
					data[index][6]=comm;
					data[index][7]=Integer.toString(deptno);
					
					index++;
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
		new TableTest2();
	}

}
