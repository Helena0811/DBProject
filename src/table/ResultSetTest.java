/*
 * 레코드 결과를 배열로 받을 때의 단점
 * 레코드의 총 갯수를 알 수 없음
 * 
 * jdbc(=Java Data Base Connectivity)
 * java의 데이터베이스 연동 기술
 * 
 * java에는 데이터베이스의 종류에 상관없이 연결 가능한 추상화된 인터페이스 제공
 * 연결 후 작동할 기능은 개발자가 재정의(driver)
 * driver : 데이터베이스의 종류가 달라도 코드 변경이 되지 않음
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
	
	// 인터페이스
	Connection con;	// (MS계열)접속 시도X, 접속한 이후 그 결과를 담는 객체
	PreparedStatement pstmt;
	ResultSet rs;
	
	// RecordSet 객체를 이용하여 총 레코드 수 알아맞춰 보기
	public ResultSetTest() {
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			if(con!=null){
				String sql="select * from company";
				// 접속 된 이후에 쿼리문을 날릴 수 있으므로 connection으로부터 받아옴
				// rs의 커서를 전방향, 후방향 등으로 자유롭게 움직이거나 한꺼번에 건너뛰게 하려면
				// 스크롤 가능한 상수 옵션을 부여해야 함
				// select문의 결과집합을 대상으로 단지 보기만 한다면 READ_ONLY,
				// 수정을 가하고 싶다면 UPDATABLE
				// 경험 상, select문에 의한 레코드는 대부분이 읽기 위함!
				pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs=pstmt.executeQuery();
				
				// ResultSet의 길이 구하기
				/*
				 * 1. 커서를 맨 밑으로 위치시키기
				 *  boolean last() 
					Moves the cursor to the last row in this ResultSet object. 
				 * 2. 다음 값이 있는지 물어보기
				 *  int getRow() 
					Retrieves the current row number.
				 * */
				
				// 제일 마지막 레코드로 보내기
				rs.last();
				// 에러 발생 - 전방향 전용 결과 집합에 부적합한 작업이 수행되었습니다
				// 현재는 커서의 기능에 한꺼번에 건너뛰는 기능이 없어서 나는 에러
				// PreparedStatement API 참고
				int num=rs.getRow();	// 현재 커서가 가리키는 레코드 번호(=레코드 위치)
				
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
