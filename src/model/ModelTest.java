/*
 * � ���ø����̼��� �����ϴ�, ������������ ���ƾ� ����� �����ǰ� ���ø����̼��� ǰ���� ����!
 * Ư��, �����ΰ� ������ ���� �ִ� GUI�� �ִ� ���ø����̼��� ���, 
 * ����ڿ��� ������ �ʴ� ���� ������ ������Ʈ�� ���� ������ ������ ���� ���� ���� ���
 * ���� ���� ������ ����Ǿ��� �� �����ϱ� �������. ��, ������������ ��������.
 * �̷��� ������ ������ ������ ��������, ���� �����ڵ��� �̹� �����ߴ� ����!
 * [MVC ����]
 * - ������ ������(��)�� �и����� ��������!!!
 * -> ���������� ���
 * 
 * JTable�� swing ������Ʈ �� MVC ������ ž��Ǿ� �ִ� ������Ʈ�̸�,
 * �����ο� �ش��ϴ� JTable�� ������ �ش��ϴ� DB �����͸� �и���Ű�� ���� TableModel�̶�� �߰� ��Ʈ�ѷ��� ����
 * */
package model;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ModelTest extends JFrame{
	JTable table;
	JScrollPane scroll;
	
	// �����θ� �и�
	public ModelTest() {
		// JTable(TableModel dm), JTable�� Interface�̱� ������ �ڽ� Ŭ������ �μ��� �־�� ��
		// OracleModel()�� JTable�� ����, JTable�� �����⿡ �Ұ���!
		// table=new JTable(new OracleModel());
		table=new JTable(new MariaModel());
		
		
		scroll=new JScrollPane(table);
		
		add(scroll);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ModelTest();

	}

}
