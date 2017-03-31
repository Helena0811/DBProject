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
public class OracleModel extends AbstractTableModel{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	String[][] data=new String[3][2];
	
	Connection con;
	
	public OracleModel() {
		data[0][0]="¥���";
		data[0][1]="�ϰ����";
		data[1][0]="������ġ";
		data[1][1]="�������";
		data[2][0]="��ũ������ ġŲ";
		data[2][1]="KFC";
	}
	
	// ���ڵ��� ���� ��ȯ
	public int getRowCount() {
		
		return 3;
	}
	
	// �÷��� ���� ��ȯ
	public int getColumnCount() {
		
		return 2;
	}

	// Ư�� ��ġ�� �� ��ȯ
	// JTable�� ĭ ������ getValueAt���� ĭ�� �ش��ϴ� ���� �޾ư�
	// DB���� 2�����迭�� �ҷ��� �����غ���
	public Object getValueAt(int rowIndex, int columnIndex) {
		System.out.println("row="+rowIndex+",col"+columnIndex+"�� ������ �־�� �ؿ�?");
		return data[rowIndex][columnIndex];
	}
	
}
