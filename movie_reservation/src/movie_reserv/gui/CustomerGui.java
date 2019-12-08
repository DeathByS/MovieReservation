package movie_reserv.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import movie_reserv.dao.CustomerDAO;
import movie_reserv.vo.MovieCustomerVO;

public class CustomerGui {
	ArrayList< MovieCustomerVO > clist = new ArrayList< MovieCustomerVO >();
	private JPanel customerPanel;
	String customerIdCol;
	
	public JPanel getCustomerPanel() {
		return customerPanel;
	}
	public void setCustomerPanel(JPanel customerPanel) {
		this.customerPanel = customerPanel;
	}
	public CustomerGui() {
		customerPanel = new JPanel();
		CustomerPage();
	}
	public void CustomerPage() {
		JTable customerTable 		= new JTable();
		JPanel tablePanel			= new JPanel();
		JLabel custIdLabel			= new JLabel( "아이디" );
		JTextField custIdText 		= new JTextField(30);	
		JLabel custNameLabel		= new JLabel( "이름" );
		JTextField custNameText 	= new JTextField(30);		
		JLabel custAddrLabel		= new JLabel( "주소" );
		JTextField custAddrText		= new JTextField(30);
		JLabel custPhoneLabel		= new JLabel( "핸드폰번호" );
		JTextField custPhoneText 	= new JTextField(30);
		JLabel custPwdLabel			= new JLabel( "패스워드" );
		JPasswordField custPwdText	= new JPasswordField(30);		
		JButton insertBt			= new JButton( "회원등록" );
		JButton updateBt			= new JButton( "정보수정" );
		JButton deleteBt			= new JButton( "회원삭제" );
		JButton logoutBt			= new JButton( "로그아웃" );
		CustomerDAO cdao 			= new CustomerDAO();
		
		Vector< String > header = new Vector<String>();
		header.addElement( "아이디" );
		header.addElement( "이름" );
		header.addElement( "주소" );
		header.addElement( "핸드폰번호" );
		header.addElement( "패스워드" );
		DefaultTableModel model	= new DefaultTableModel( header, 0 );
		
		customerTable.setModel( model );
		setUpdateTableData( customerTable, cdao );		
		tablePanel.add( new JScrollPane( customerTable ));
		
	
		customerPanel.add( tablePanel );
		tablePanel.add( new JScrollPane( customerTable ));
		customerPanel.add( tablePanel );
		
		JPanel custIdPanel = new JPanel();
		custIdPanel.add( custIdLabel);
		custIdPanel.add( custIdText);
		customerPanel.add( custIdPanel );
		
		JPanel custNamePanel = new JPanel();
		custNamePanel.add( custNameLabel);
		custNamePanel.add( custNameText);
		customerPanel.add( custNamePanel );
		
		JPanel custAddrPanel = new JPanel();
		custAddrPanel.add( custAddrLabel);
		custAddrPanel.add( custAddrText);
		customerPanel.add( custAddrPanel );
		
		JPanel custPhonePanel = new JPanel();
		custPhonePanel.add( custPhoneLabel);
		custPhonePanel.add( custPhoneText);
		customerPanel.add( custPhonePanel );
		
		JPanel custPwdPanel = new JPanel();
		custPwdPanel.add( custPwdLabel);
		custPwdPanel.add( custPwdText);
		customerPanel.add( custPwdPanel );
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add( insertBt);
		buttonPanel.add( updateBt);
		buttonPanel.add( deleteBt);	
		buttonPanel.add( logoutBt);	
		customerPanel.add( buttonPanel );
		
		clist = cdao.selectAll();		
		setUpdateTableData( customerTable, cdao );
		
		insertBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String idString 			= null;
				String nameString 			= ( String )custNameText.getText();
				String addrString 			= ( String )custAddrText.getText();
				String phoneString 			= ( String )custPhoneText.getText();
				String pwdString 			= ( String )custPwdText.getText();
				idString					= ( String )custIdText.getText();
				
				if( idString.equals("") || nameString.equals("") || addrString.equals("") || phoneString.equals("") || pwdString.equals("")) {
					JOptionPane.showMessageDialog( null, "빈칸을 채워주세요", "error", JOptionPane.ERROR_MESSAGE );
				}else {
					int result = 0;
					if( cdao.selectOne( idString ).getId() == null ){
						MovieCustomerVO mvo = new MovieCustomerVO();
						mvo.setAll(idString, nameString, addrString, phoneString, pwdString);
						result = cdao.registNew( mvo );
						JOptionPane.showMessageDialog( null, "회원등록 성공!!", "success", JOptionPane.INFORMATION_MESSAGE );
						if (!LoginGui.currentUserId.equals("admin"))
							LoginGui.currentUserId = idString;
					}else {
						JOptionPane.showMessageDialog( null, "회원등록 실패", "error", JOptionPane.ERROR_MESSAGE );
					}
				}
				clist = cdao.selectAll();
				setUpdateTableData( customerTable, cdao );
			}
		});
		
		deleteBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( customerIdCol.equals( "admin" )) {
					JOptionPane.showMessageDialog( null, "관리자계정 삭제 금지", "error", JOptionPane.ERROR_MESSAGE );
				}else {
					int result = cdao.delete( customerIdCol );
					if( result != 0 ) {
						JOptionPane.showMessageDialog( null, "삭제성공!!", "success", JOptionPane.INFORMATION_MESSAGE );
					}else {
						JOptionPane.showMessageDialog( null, "삭제실패", "error", JOptionPane.ERROR_MESSAGE );
					}
					
					if( customerIdCol.equals( LoginGui.currentUserId )) {
						MainGui.mainGui.setVisible( false );
						LoginGui loginGui = new LoginGui();
						LoginGui.currentUserId = "";
					}
					clist = cdao.selectAll();
					setUpdateTableData( customerTable, cdao );  
				}
			}
		});
		
		customerTable.addMouseListener( new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int updateRow = customerTable.getSelectedRow();
				customerIdCol = ( String )customerTable.getValueAt( updateRow, 0 );
				String customerNameCol = ( String )customerTable.getValueAt(updateRow, 1 );
				String customerAddrCol = ( String )customerTable.getValueAt(updateRow, 2 );
				String customerPhoneCol = ( String )customerTable.getValueAt(updateRow, 3 );
				String customerPwdCol = ( String )customerTable.getValueAt(updateRow, 4 );
				
				custIdText.setText( customerIdCol );	
				custNameText.setText( customerNameCol );
				custAddrText.setText( customerAddrCol );
				custPhoneText.setText( customerPhoneCol );
				custPwdText.setText( customerPwdCol );
			}
		});
		
		updateBt.addActionListener( new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( customerIdCol.equals( "admin" )) {
					JOptionPane.showMessageDialog( null, "관리자계정 업데이트 금지", "error", JOptionPane.ERROR_MESSAGE );
				}else {
					MovieCustomerVO cvo = new MovieCustomerVO();
					cvo.setId( customerIdCol );
					cvo.setName( custNameText.getText());
					cvo.setAddr( custAddrText.getText());
					cvo.setPhone( custPhoneText.getText());
					cvo.setPwd( custPwdText.getText());
					int result = cdao.update( cvo );
					if( result != 0 ) {
						JOptionPane.showMessageDialog( null, "수정성공!!", "success", JOptionPane.INFORMATION_MESSAGE );
					}else {
						JOptionPane.showMessageDialog( null, "수정실패", "error", JOptionPane.ERROR_MESSAGE );
					}
					clist = cdao.selectAll();
					setUpdateTableData( customerTable, cdao );
				}
			}
		});
		
		logoutBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainGui.mainGui.setVisible( false );
				LoginGui loginGui = new LoginGui();
				LoginGui.currentUserId = "";
			}
		});
	}
	
	public void setUpdateTableData( JTable customerTable, CustomerDAO cdao ) {
		DefaultTableModel model = ( DefaultTableModel )customerTable.getModel();
		model.setRowCount( 0 );
		
		if( LoginGui.currentUserId.equals( "admin" )) {
			for( MovieCustomerVO cvo : clist ) {
				Vector body = new Vector();
				body.addElement( cvo.getId());
				body.addElement( cvo.getName());
				body.addElement( cvo.getAddr());
				body.addElement( cvo.getPhone());
				body.addElement( hidePassword(cvo.getPwd()));
				//body.addElement( cvo.getPwd());
				model.addRow( body );
			}
		}else {		
			if( LoginGui.currentUserId.equals("")) {
				
			}else {
				MovieCustomerVO cvo = cdao.selectOne( LoginGui.currentUserId );
				Vector body = new Vector();
				body.addElement( cvo.getId());
				body.addElement( cvo.getName());
				body.addElement( cvo.getAddr());
				body.addElement( cvo.getPhone());
				body.addElement( hidePassword(cvo.getPwd()));
				//body.addElement( cvo.getPwd());
				model.addRow( body );
			}
		}
		customerTable.setModel( model );
	}
	
	public String hidePassword( String password ) {
		int i;
		String result = "";
		for( i = 0; i < password.length(); i++ )
			result = result + "*";
		return result;
	}
}
