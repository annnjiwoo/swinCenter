package mini;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LessonList extends JFrame implements ActionListener{
	
	public DataBase db;
	
	private JTable table;
	private DefaultTableModel tableModel;
	
	public LessonList(DataBase db) {
		this.db = db;
		
		String[] column = {"과목명"};
		List<Lesson> lessonList = db.getLessonList();
		Object[][] data = new Object[lessonList.size()][1];
		
		for (int i = 0; i < lessonList.size(); i++) {
			Lesson lesson = lessonList.get(i);
			data[i][0] = lesson.getSubject();
		}
		
		

		
        tableModel = new DefaultTableModel(data, column);
        table = new JTable(tableModel);		
		
        
		//버튼
		JButton closeButton = new JButton("닫기");
		
		//액션리스너
		closeButton.addActionListener(this);
		
		JPanel mainJPanel = new JPanel();
		mainJPanel.setLayout(new BorderLayout());
		mainJPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		
        // 입력 필드들이 놓일 input 패널 생성
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 1));
        
        mainJPanel.add(inputPanel, BorderLayout.SOUTH);
		mainJPanel.add(closeButton, BorderLayout.SOUTH);
		

		setTitle("강좌조회");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mainJPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 창닫기
		this.dispose();

		// 이동해갈 메인 메뉴 생성
		MainMenu mainMenu = new MainMenu(db);

		// 메인 메뉴 보이기
		mainMenu.setVisible(true);
		
	}
public static void main(String[] args) {
	DataBase db = new DataBase();
	new LessonList(db);
}
}
