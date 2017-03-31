/*
 * JTable ����غ���
 * 
 * JTable : swing�� ������Ʈ �� �����ͺ��̽��� ��� ������ �ð�ȭ�ϱ⿡ ����ȭ�� ������Ʈ
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

public class TableTest extends JFrame{
	JTable table;
	JScrollPane scroll;		// JTable�� ��ũ�� ����
	
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String password="";
	
	Connection con;	// ���� ����
	PreparedStatement pstmt;
	ResultSet rs;	// select���� ��츸 �ʿ� -> ����� ��� ���ؼ�
	
	String[][] data;
	String[] column={
		"member_id","name","age"
	};
	
	public TableTest() {
		// DB������ �Ǿ� �־�� table�� ���� ���� �� ����
		loadData();
		
		//setLayout(new FlowLayout());
		//JTable(int numRows, int numColumns)
		//table=new JTable(3,3);
		//JTable(Object[][] rowData, Object[] columnNames)
		table=new JTable(data, column);
		scroll=new JScrollPane(table);
		
		add(scroll);
		setSize(500, 150);
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
				String sql="select * from member";
				pstmt=con.prepareStatement(sql);
				
				rs=pstmt.executeQuery();
				
				// 2���� �迭 data�� �ֱ�
				//data=new String[rs.getRow()][column.length];
				data=new String[3][3];
				
				int index=0;
				
				while(rs.next()){
					int member_id=rs.getInt("member_id");
					String name=rs.getString("name");
					int age=rs.getInt("age");
					
					data[index][0]=Integer.toString(member_id);
					data[index][1]=name;
					data[index][2]=Integer.toString(age);
					
					index++;
				/*
					for(int i=0; i<rs.getFetchSize(); i++){
						data[i][0]=Integer.toString(member_id);
						data[i][1]=name;
						data[i][2]=Integer.toString(age);
					}							
				*/
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
		new TableTest();
	}

}
