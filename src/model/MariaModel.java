/*
 * �� ��ü�� JTable�� �����ڿ��� �䱸�ϴ� ��Ʈ�ѷ� ��ü
 * ���� : �����ΰ� ������ �и������ִ� �߰���!!!
 * */
package model;

import java.sql.Connection;

import javax.swing.table.AbstractTableModel;

// JTable�� ���������� �����ο� �ش��ϱ� ������, ������ ���� ������ �ش� ���� ���� ���� ���̺��� �ȴ�!
// ����, AbstractTableModel���� ũ�⸦ ��� �����ֵ��� ����(���� ���̺�)
// ���� Ÿ�ֿ̹� �� �ڵ带 �����ϴ� ��ü : JTable, JTable�� TableModel�� ������!!!
public class MariaModel extends AbstractTableModel{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	String[][] data=new String[3][3];
	
	Connection con;
	
	public MariaModel() {
		data[0][0]="û����";
		data[0][1]="�����ٳ�";
		data[0][2]="2000";
		
		data[1][0]="�����";
		data[1][1]="���";
		data[1][2]="5000";
		
		data[2][0]="ġ��";
		data[2][1]="����Ű";
		data[2][2]="90000";
	}
	
	// ���ڵ��� ���� ��ȯ
	public int getRowCount() {
		
		return data.length;
	}
	
	// �÷��� ���� ��ȯ
	public int getColumnCount() {
		
		return data[0].length;
	}

	// Ư�� ��ġ�� �� ��ȯ
	// JTable�� ĭ ������ getValueAt���� ĭ�� �ش��ϴ� ���� �޾ư�
	// DB���� 2�����迭�� �ҷ��� �����غ���
	public Object getValueAt(int rowIndex, int columnIndex) {
		System.out.println("row="+rowIndex+",col"+columnIndex+"�� ������ �־�� �ؿ�?");
		return data[rowIndex][columnIndex];
	}
	
}
