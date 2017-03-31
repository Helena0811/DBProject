/*
 * 이 객체는 JTable의 생성자에서 요구하는 컨트롤러 객체
 * 역할 : 디자인과 로직을 분리시켜주는 중간자!!!
 * */
package model;

import java.sql.Connection;

import javax.swing.table.AbstractTableModel;

// JTable은 실질적으로 디자인에 해당하기 때문에, 고정된 값을 가지면 해당 값에 대한 전용 테이블이 된다!
// 따라서, AbstractTableModel에서 크기를 대신 정해주도록 구현(대응 테이블)
// 실행 타이밍에 이 코드를 수행하는 주체 : JTable, JTable은 TableModel에 의존적!!!
public class OracleModel extends AbstractTableModel{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	String[][] data=new String[3][2];
	
	Connection con;
	
	public OracleModel() {
		data[0][0]="짜장면";
		data[0][1]="북경반점";
		data[1][0]="샌드위치";
		data[1][1]="서브웨이";
		data[2][0]="핫크리스피 치킨";
		data[2][1]="KFC";
	}
	
	// 레코드의 갯수 반환
	public int getRowCount() {
		
		return 3;
	}
	
	// 컬럼의 갯수 반환
	public int getColumnCount() {
		
		return 2;
	}

	// 특정 위치의 값 반환
	// JTable은 칸 수마다 getValueAt으로 칸에 해당하는 값을 받아감
	// DB에서 2차원배열을 불러와 저장해보기
	public Object getValueAt(int rowIndex, int columnIndex) {
		System.out.println("row="+rowIndex+",col"+columnIndex+"에 무엇을 넣어야 해요?");
		return data[rowIndex][columnIndex];
	}
	
}
