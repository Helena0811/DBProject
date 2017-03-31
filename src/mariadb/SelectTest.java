/*
 * mariaDB를 연동하여 레코드를 콘솔에 출력해보자!
 * 주의) DBMS 제조사가 제공하는 드라이버를 미리 준비해야 함
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
	
	Connection con;	// 접속 정보를 가진 인터페이스
	PreparedStatement pstmt;	// 쿼리 수행 객체
	// 인터페이스므로 connection으로부터 받아와야 함(접속에 의존적)
	ResultSet rs;	// 쿼리문이 select문일 경우, 원격지의 데이터베이스의 테이블과 동일한 결과 집합을 담아놓는 객체(=표)
	/*
	 * ResultSet의 커서는 첫번째 레코드 위(맨 위)에 존재하기 때문에, 값에 접근하려면 next()를 이용해야 함!
	 * ex)
	 *  +-----------+--------+------+
		|         1 | 장현아 	 |   26 |
		|         2 | 이지혜 	 |   27 |
		|         3 | 이아영 	 |   25 |
		+-----------+--------+------+
	 * 
	 * 이동과 동시에 값의 여부를 판단(boolean 반환)
	 * next()
	   Moves the cursor forward one row from its current position.
	 * */
	
	
	public SelectTest() {
		/*
		 * DBMS 연동 원칙
		 * 1. 드라이버를 로드
		 * 2. 접속
		 * 3. 원하는 쿼리문 실행
		 * 4. DB 관련된 자원 닫기
		 * */
		try {
			// 드라이버 로드
			Class.forName(driver);
			System.out.println("로드 성공");
			
			// 접속
			con=DriverManager.getConnection(url, user, password);
			if(con!=null){
				// control창이 아니므로 뒤의 세미콜론은 생략!
				String sql="select * from member";
				System.out.println("접속 성공");
				
				pstmt=con.prepareStatement(sql);
				// insert문은 java->oracle, select문은 oracle->java
				// 메모리 상(서버)에 띄워져 있는 select문에 의한 표를 가져와야 함!
				// pstmt.executeQuery();의 결과 -> resultSet(메모리상의 표와 같은 형태의 객체)
				
				// 쿼리 수행 후 반환되는 결과 집합을 받기
				rs=pstmt.executeQuery();	// 쿼리문이 수행됨
				
			/*	
				// 커서 한 칸 전진
				rs.next();
				// 이름 접근 -> 컬럼에 해당하는 값 반환
				String name=rs.getString("name");
				// 나이 반환
				int age=rs.getInt("age");
				// 멤버 id 반환
				int member_id=rs.getInt("member_id");
				System.out.println(member_id+","+name+","+age);
				
				// 커서 한 칸 더 전진
				rs.next();
			*/
				// 전체 출력
				while(rs.next()){
					String name=rs.getString("name");
					int age=rs.getInt("age");
					int member_id=rs.getInt("member_id");
					System.out.println(member_id+","+name+","+age);
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
		new SelectTest();
	}

}
