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
public class MariaModel extends AbstractTableModel{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	String[][] data=new String[3][3];
	
	Connection con;
	
	public MariaModel() {
		data[0][0]="청바지";
		data[0][1]="지오다노";
		data[0][2]="2000";
		
		data[1][0]="가디건";
		data[1][1]="뱅뱅";
		data[1][2]="5000";
		
		data[2][0]="치마";
		data[2][1]="나이키";
		data[2][2]="90000";
	}
	
	// 레코드의 갯수 반환
	public int getRowCount() {
		
		return data.length;
	}
	
	// 컬럼의 갯수 반환
	public int getColumnCount() {
		
		return data[0].length;
	}

	// 특정 위치의 값 반환
	// JTable은 칸 수마다 getValueAt으로 칸에 해당하는 값을 받아감
	// DB에서 2차원배열을 불러와 저장해보기
	public Object getValueAt(int rowIndex, int columnIndex) {
		System.out.println("row="+rowIndex+",col"+columnIndex+"에 무엇을 넣어야 해요?");
		return data[rowIndex][columnIndex];
	}
	
}
