/*
 * JTable 사용해보기
 * 
 * JTable : swing의 컴포넌트 중 데이터베이스의 결과 집합을 시각화하기에 최적화된 컴포넌트
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
	JScrollPane scroll;		// JTable도 스크롤 가능
	
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String password="";
	
	Connection con;	// 접속 정보
	PreparedStatement pstmt;
	ResultSet rs;	// select문인 경우만 필요 -> 결과를 담기 위해서
	
	String[][] data;
	String[] column={
		"member_id","name","age"
	};
	
	public TableTest() {
		// DB연동이 되어 있어야 table에 값을 넣을 수 있음
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
	
	// 레코드 채워넣기
	// 테이블을 생성하기 전에, mariaDB를 연동해 member 테이블의 레코드를 2차원 배열에 담기
	// -> JTable의 생성자 인수로 2차원 배열이 사용되기 때문!
	public void loadData(){
		/*
		 * 1. 드라이버 로드
		 * 2. 접속
		 * 3. 쿼리문 수행
		 * 4. DB 닫기
		 * */
		try {
			Class.forName(driver);	// 드라이버 로드
			con=DriverManager.getConnection(url, user, password);
			
			// 접속이 제대로 되었다면
			if(con!=null){
				System.out.println("접속 완료");
				String sql="select * from member";
				pstmt=con.prepareStatement(sql);
				
				rs=pstmt.executeQuery();
				
				// 2차원 배열 data에 넣기
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
				System.out.println("접속 실패");
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
