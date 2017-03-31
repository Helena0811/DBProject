/*
 * 우리가 사용중인 데이터베이스 제품은 모두 DBMS
 * DB(저장소)MS(관리 시스템) - 네트워크 기반이기 때문에 원격 접속 가능
 * 
 * 데이터베이스 접속 클라이언트		->		서버
 * ex) sqlplus, toad				(Listener+)oracle
 * 이번에는 class가 클라이언트로!
 * 
 * 현재 사용중인 네트워크 프로토콜은 TCP/IP 기반이므로, 
 * 원격지의 호스트를 접속하려면 그 호스트의 주소를 알아야 함!
 * -> 기반이 TCP/IP기 때문에 IP 주소를 알아야 한다!
 * -> user 정보까지 알아야 함(ID & PW)
 * */
package oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMain {
	public static void main(String[] args) {
		/*
		 * 1단계 
		 * 오라클을 java가 제어할 수 있는 코드가 들어있는 jar 파일을 메모리에 로드해야 함
		 * 이러한 데이터베이스 제어 jar 파일을 java에서는 드라이버라 함
		 * 드라이버는 DB 제조사에서 제공함
		 * oracle - oracle사, mysql - oracle사, mssql - MS
		 * 
		 * jar 파일 경로
		 * ex) C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6
		 * 
		 * cf) 시스템 라이브러리
		 * 개발자가 설정하지 않아도 이미 default로 경로가 설정되어 접근 가능한 라이브러리
		 * 
		 * 외부에 존재하는 jar 파일을 현재 프로젝트의 라이브러리로 사용가능하도록 설정
		 * 프로젝트 Configure Build Path - new - add jar
		 * */
		
		/* 
		 * 2단계 - 오라클에 접속 
		 * 드라이버 클래스 필요 - 클래스 정보가 없을 때는 원본 코드가 static/method 영역에 올라가듯이
		 * */
		
		// 닫아야 하므로 접근 가능하도록 try문 밖에 선언
		Connection con=null;
		PreparedStatement pstmt=null;
		
		// 드라이버 클래스 로드 -> String형으로 넣어주어야 함
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
			
			// 암기하기!(jdbc:DB종류:방식:@계정:@포트번호)
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521", "batman", "1234");
			
			// try문이 성공했다고 해서 성공한 것이 아니라, 
			// getConnection의 반환형인 connection 객체가 메모리에 올라왔을 때 성공한 것으로 간주
			if(con!=null){
				System.out.println("접속 성공");
				
				// 현재 user가 보유한 테이블에 insert 시도
				String sql="insert into company(company_id, brand) values(seq_company.nextval,'나이키')";
				// 쿼리문 수행을 위해서는 쿼리문을 전담하는 객체를 이용해야 함 -> PreparedStatement 인터페이스
				
				pstmt=con.prepareStatement(sql);
				int result=pstmt.executeUpdate();	// 쿼리문 실행 메소드
				// 이 쿼리문 수행에 의해 반영된 레코드 수를 반환함
				// ex) insert문은 언제나 성공한 경우는 1, 실패한 경우는 0
				if(result==1){
					System.out.println("입력 성공");
				}
				else{
					System.out.println("입력 실패");
				}
				
			}
			else{	// connection이 null이면 객체가 아직 메모리에 올라오지 못한 것!
				System.out.println("접속 실패");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			// 스트림과 DB 연동 작업 후에는 반드시 닫는 처리를 해야 한다!
			
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
