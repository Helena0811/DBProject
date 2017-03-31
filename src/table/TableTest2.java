/*
 * JTable 사용해보기
 * 
 * JTable : swing의 컴포넌트 중 데이터베이스의 결과 집합을 시각화하기에 최적화된 컴포넌트
 * 
 * 레코드의 갯수에 따라 배열의 크기를 지정해서 개발해보자!
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
	JScrollPane scroll;		// JTable도 스크롤 가능
	
	String driver="oracle.jdbc.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	Connection con;	// 접속 정보
	PreparedStatement pstmt;
	ResultSet rs;	// select문인 경우만 필요 -> 결과를 담기 위해서
	
	String[][] data;
	String[] column={
		"empno","ename","job","mgr","hiredate","sal","comm","deptno"
	};
	
	public TableTest2() {
		// DB연동이 되어 있어야 table에 값을 넣을 수 있음
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
				String sql="select * from emp";
				// ResultSet의 크기를 반환하기 위해 업그레이드
				pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
				rs=pstmt.executeQuery();
				
				// 2차원 배열 data에 넣기
				// 마지막 위치에 보내고 위치를 받기
				rs.last();
				int total=rs.getRow();
				int index=0;
				
				data=new String[total][column.length];
				
				// 마지막 위치에서 다시 원상복구
				rs.beforeFirst();		// first()는 첫번째, beforeFirst()는 최초의 커서 위치(아무것도 가리키지 않는 상태)

				while(rs.next()){
					int empno=rs.getInt("empno");
					String ename=rs.getString("ename");
					String job=rs.getString("job");
					int mgr=rs.getInt("mgr");
					String hiredate=rs.getString("hiredate");
					int sal=rs.getInt("sal");
					String comm=rs.getString("comm");	// comm이 null인 경우도 있는데 어떻게 될까?
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
		new TableTest2();
	}

}
