package movie_reserv.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import movie_reserv.dao.CustomerDAO;

public class LoginGui extends JFrame{
	public static String currentUserId = "";
	public LoginGui() {
		JPanel loginPanel = new JPanel();
		loginPage( loginPanel );
		
		this.add( loginPanel );
		this.setTitle( "Login" );
		this.setSize( 500, 80 );
		this.setVisible( true );
	}
	
	public void loginPage( JPanel loginPanel ) {
		JLabel idLabel = new JLabel( "id" );
		JTextField idText = new JTextField( 10 );
		JLabel pwdLabel = new JLabel( "password" );
		JPasswordField pwdText = new JPasswordField( 10 );
		JButton loginBt = new JButton( "login" );
		
		//
		JButton registerBt = new JButton( "register" );
		
		idLabel.setBounds( 10, 10, 50, 50 );
		
		loginPanel.add( idLabel );
		loginPanel.add( idText );
		loginPanel.add( pwdLabel );
		loginPanel.add( pwdText );
		loginPanel.add( loginBt );
		//
		loginPanel.add( registerBt );
		CustomerDAO cdao = new CustomerDAO();
		
		loginBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String custId = ( String )(idText.getText());
				String pwd = pwdText.getText();
				String resultCustId = cdao.login( custId, pwd);
				if( resultCustId == null ) {
					JOptionPane.showMessageDialog( null, "로그인 실패", "error", JOptionPane.ERROR_MESSAGE );
				}else {
					JOptionPane.showMessageDialog( null, "로그인 성공!!", "success", JOptionPane.INFORMATION_MESSAGE );
					currentUserId = custId;
					MainGui mainGui = new MainGui();
					setVisible( false );
				}
			}
		});
		registerBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainGui mainGui = new MainGui();
				setVisible( false );
			}
		});
	}
}
