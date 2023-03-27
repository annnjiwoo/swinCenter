package mini;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ComeInForm extends JFrame implements ActionListener{
	
	public DataBase db;
	
	public JTable table;
	public DefaultTableModel tableModel;
	
	private JTextField idField;
	
	public ComeInForm() {
	}
	
	public ComeInForm(DataBase db) {
		
		
		this.db = db;
		
		idField = new JTextField(10);

		JLabel idLabel = new JLabel("회원번호 : ");

        JButton closeButton = new JButton("입장");

		
		//액션리스너
		closeButton.addActionListener(this);

		
		JPanel mainJPanel = new JPanel();
		mainJPanel.setLayout(new BorderLayout());
		mainJPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		
        // 입력 필드들이 놓일 input 패널 생성
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 2));
        
        inputPanel.add(idLabel);
        inputPanel.add(idField);

        mainJPanel.add(inputPanel, BorderLayout.SOUTH);
		mainJPanel.add(closeButton, BorderLayout.EAST);

		
        setTitle("회원입장");
        setSize(600,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainJPanel);
        setLocationRelativeTo(null);
        setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String id = idField.getText();
		
		ComeIn comeIn = new ComeIn(id);
		
		this.db.addComeIn(comeIn);

		
		for (int i = 0; i < db.memberList.size(); i++) {
			if (db.getMemberList().get(i).getId().equals(id)) {
				JOptionPane.showMessageDialog(null, "입장을 환영합니다.");
				dispose();
				
				MainMenu mainMenu = new MainMenu(db);
				mainMenu.setVisible(true);
				return;
			}
		}
		//회원번호와 회원입장 시 입력한 회원번호가 다를 시 아래 알림창 푸쉬
		JOptionPane.showMessageDialog(null, "회원번호를 다시 입력해주세요.");
		this.dispose();
		


		// 이동해갈 메인 메뉴 생성
		MainMenu mainMenu = new MainMenu(db);

		// 메인 메뉴 보이기
		mainMenu.setVisible(true);

	}

	public static void main(String[] args) {
		new ComeInForm();
	}

}
