package movie_reserv.gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;



public class MainGui extends JFrame {
	
	static MainGui mainGui = null;

	public MainGui() {
		mainGui = this;
		
		MovieGui movieGui = new MovieGui(this);
		MovieOrdersGui ordersGui = new MovieOrdersGui(this);
		CustomerGui customerGui = new CustomerGui();

		JTabbedPane tPane = new JTabbedPane();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();

		// CustomerGui customerGui = new CustomerGui();

		tPane.addTab("영화 예약", movieGui.getMoviePanel());
		tPane.addTab("예약 정보", ordersGui.getorderPanel());
		tPane.addTab("회원정보", customerGui.getCustomerPanel());
		
		
		this.add(tPane);

		this.setTitle("Movie Box");
		this.setSize(550, 880);
		this.setVisible(true);
	}


}
