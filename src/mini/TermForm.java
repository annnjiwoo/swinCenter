package mini;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TermForm extends JFrame implements ActionListener{
	
	public DataBase db;
	

	private JTable table;
	private DefaultTableModel tableModel;
	
	
	private JTextField idField;
	private JTextField monthField;
	private int memberMonth = 0;
	

	//생성자
	public TermForm(DataBase db) {
		
		this.db = db;
		
		
		idField = new JTextField(10);
		monthField = new JTextField(10);
		
		JLabel idJLabel = new JLabel("회원번호 : ");
		JLabel monthJLabel = new JLabel("추가개월수int : ");

		JButton addButton = new JButton("추가");
		
		//액션리스너
		addButton.addActionListener(this);
		
		
		//패널위에 올리기
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		//패널에 넣기
		JPanel inputJPanel = new JPanel();
		inputJPanel.setLayout(new GridLayout(2,2));
		
		inputJPanel.add(idJLabel);
		inputJPanel.add(idField);
		inputJPanel.add(monthJLabel);
		inputJPanel.add(monthField);
		
		mainPanel.add(inputJPanel, BorderLayout.SOUTH);
		mainPanel.add(addButton, BorderLayout.EAST);
		
		//타이틀
		setTitle("기간연장");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mainPanel);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	
	@Override
	public void actionPerformed(ActionEvent e) {

		// 회원코드 id로 저장
		String id = String.valueOf(idField.getText());
		System.out.println("회원코드" + id);
		
		//추가기간
		int month = Integer.parseInt(monthField.getText());
		System.out.println("추가한 기간" + month);
		
		
		

		Term term = new Term(id,month);
		
		//db에넣기
		this.db.addTerm(term);
		
		//회원번호 memberList의 회원번호와 입력한 id필드와 일치하면
		// 회원등록 시 입력한 등록개월 + 기간연장에 입력한 추가개월을 더하여 회원조회의 잔여개월 업데이트
		for (int i = 0; i < db.memberList.size(); i++) {
			
			if (db.memberList.get(i).getId().equals(id)) {
				memberMonth = db.memberList.get(i).getMonth();
				int sum = month + memberMonth;
				System.out.println("최종출력" + sum);
				db.getMemberList().get(i).setMonth(sum);
				JOptionPane.showMessageDialog(null, "기간이 연장되었습니다.");
				this.dispose();
				MainMenu mainMenu = new MainMenu(db);
				mainMenu.setVisible(true);

			}else {// 회원번호가 일치하지 않으면 회원번호 재입력 알림창
				JOptionPane.showMessageDialog(null, "회원번호를 다시 입력해주세요.");
				this.dispose();
				MainMenu mainMenu = new MainMenu(db);
				mainMenu.setVisible(true);
			}
		}



	}
	public static void main(String[] args) {
		DataBase db = new DataBase();
		new TermForm(db);
	}
}
