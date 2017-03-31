/*
 * 어떤 어플리케이션을 개발하던, 유지보수성이 좋아야 비용이 절감되고 어플리케이션의 품질이 향상됨!
 * 특히, 디자인과 로직이 섞여 있는 GUI가 있는 어플리케이션의 경우, 
 * 사용자에게 보이지 않는 순수 로직과 컴포넌트와 같은 디자인 로직이 마구 섞여 있을 경우
 * 추후 업무 내용이 변경되었을 때 대응하기 힘들어짐. 즉, 유지보수성이 떨어진다.
 * 이러한 문제는 전산의 고질적 문제였고, 이전 개발자들이 이미 경험했던 문제!
 * [MVC 패턴]
 * - 로직과 디자인(뷰)은 분리시켜 개발하자!!!
 * -> 유지보수성 향상
 * 
 * JTable은 swing 컴포넌트 중 MVC 패턴이 탑재되어 있는 컴포넌트이며,
 * 디자인에 해당하는 JTable과 로직에 해당하는 DB 데이터를 분리시키기 위해 TableModel이라는 중간 컨트롤러를 제공
 * */
package model;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ModelTest extends JFrame{
	JTable table;
	JScrollPane scroll;
	
	// 디자인만 분리
	public ModelTest() {
		// JTable(TableModel dm), JTable은 Interface이기 때문에 자식 클래스를 인수로 넣어야 함
		// OracleModel()이 JTable을 결정, JTable은 껍데기에 불과함!
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
