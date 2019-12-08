package movie_reserv.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//�̹����߰�
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import movie_reserv.dao.MovieDAO;
import movie_reserv.dao.MovieOrdersDAO;
import movie_reserv.vo.MovieOrdersVO;
import movie_reserv.vo.MovieVO;



public class MovieGui extends JFrame {

	private JPanel moviePanel;

	private ArrayList<MovieVO> mvlist;
	private int movieidCol;
	private int priceCol;
	private int quantityCol;
	private String showDateCol;
	private String movieNameCol;
	private String publisherCol;
	JLabel imgLa = null;
	

	public MovieGui(JFrame j) {
		moviePanel = new JPanel();
		moviePage(moviePanel);
		j.add(moviePanel);
	}

	public JPanel getMoviePanel() {
		return moviePanel;
	}

	public void setmoviePanel(JPanel moviePanel) {
		this.moviePanel = moviePanel;
	}

	public void moviePage(JPanel moviePage) {
		// �˻�
		JLabel searchLa = new JLabel("��ȭ �˻�");
		JTextField searchText = new JTextField(30);
		JButton searchBt = new JButton("�˻�");
		// ���, ����, ����
		JLabel movieNameLa = new JLabel("��ȭ�̸�");
		JTextField movieNameText = new JTextField(30);

		JLabel publisherLa = new JLabel("��޻�");
		JTextField publisherText = new JTextField(30);

		JLabel priceLa = new JLabel("����");
		JTextField priceText = new JTextField(30);

		// �����߰�
		JLabel quantityLa = new JLabel("ǥ����");
		JTextField quantityText = new JTextField(30);
		// �����߰�
		JLabel showdateLa = new JLabel("����");
		JTextField showdateText = new JTextField(38);

		JButton insertBt = new JButton("���");
		JButton updateBt = new JButton("����");
		JButton deleteBt = new JButton("����");
		JButton orderBt = new JButton("����");

		// ���̺� - �˻����� ����
		JTable movieTable = new JTable();
		JPanel tablePanel = new JPanel();

		// ���̺� ����� (�÷�)
		Vector<String> header = new Vector<String>();
		header.addElement("��ȭid"); // �̰� admin ���������� ���̰�????
		header.addElement("��ȭ�̸�");
		header.addElement("��޻�");
		header.addElement("����");
		header.addElement("ǥ����");
		header.addElement("�󿵳���");

		// ���̺� ��

		DefaultTableModel model = new DefaultTableModel(header, 0);
		movieTable.setModel(model);

		// ���̺� ������ �ֱ�

		MovieDAO mdao = new MovieDAO();
		MovieOrdersDAO odao = new MovieOrdersDAO();
		// dao���� selectAll �Լ� ȣ���ؼ� book ���̺� �ִ�
		// ��ü ������ ������

		mvlist = mdao.selectAll();

		// Jtable���� vector ������ �����͸� �־�� �ϱ� ������
		// list ���� vector�� �ٽ� �־���
		setUpdateTableData(movieTable); // ���⼭ �����������ִ� ��

		tablePanel.add(new JScrollPane(movieTable));

		moviePanel.add(searchLa);
		moviePanel.add(searchText);
		moviePanel.add(searchBt);
		moviePanel.add(tablePanel);

//		bookNameLa.setBounds(300, 10, 50, 100);
		JPanel movieNamePanel = new JPanel();
		movieNamePanel.add(movieNameLa);
		movieNamePanel.add(movieNameText);
		moviePanel.add(movieNamePanel);

		JPanel publisherPanel = new JPanel();
		publisherPanel.add(publisherLa);
		publisherPanel.add(publisherText);
		moviePanel.add(publisherPanel);

		JPanel pricePanel = new JPanel();
		pricePanel.add(priceLa);
		pricePanel.add(priceText);
		moviePanel.add(pricePanel);

		// ���� �߰�
		JPanel quantityPanel = new JPanel();
		quantityPanel.add(quantityLa);
		quantityPanel.add(quantityText);
		moviePanel.add(quantityPanel);
		// �����߰�
		JPanel showdatePanel = new JPanel();
		showdatePanel.add(showdateLa);
		showdatePanel.add(showdateText);
		moviePanel.add(showdatePanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(insertBt);
		buttonPanel.add(updateBt);
		buttonPanel.add(deleteBt);
		buttonPanel.add(orderBt);

		moviePanel.add(buttonPanel);
		
		
		
		//�̹������
//		ImageIcon ic = new ImageIcon("\\movie_reservation\\img\\��Ŀ.jpg");
//		Image img = ic.getImage();
//		img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); //�̹��� ũ�� ����
//		ic = new ImageIcon();
//		JPanel imgPanel = new JPanel();
//		// imgPanel.setBounds(600,0,500,500); 
//		//�� �κ��� this.setLayout(null);�� ���� ����� ��ǥ�� �������� �� �ִ�
//		
//		JLabel imgLa = new JLabel(ic);
//		imgPanel.add(imgLa);
		
		//ã�� �̹����ֱ�
//		ImageIcon icon = new ImageIcon("/movie_reservation/img/��Ŀ.jpg"); //�̹��� ������ ��ü ����
//		//�̹����� ������ ���� �ִ� Image��ü �̾ƿ���
//		Image im = icon.getImage(); //�̾ƿ� �̹��� ��ü ����� ���Ӱ� �����!
//		Image im2 = im.getScaledInstance(250, 250, Image.SCALE_DEFAULT);
//		//���� ������ �������� �̹���(im2)�� ������ ImageIcon ��ü�� �ٽ� ����
//		ImageIcon icon2 = new ImageIcon(im2);
//		JLabel img = new JLabel(icon2);
//		moviePanel.add(img);
		
		////������icon ���� �ް��ڽ�
		
		String selectedItem = "";
		String thisLocation = System.getProperty("user.dir");
		String refImg = "\\img\\�ް��ڽ�.jpg";
		//new ImageIcon("image/��Ŀ.jpg");
		System.out.println(thisLocation);
		ImageIcon imgic = new ImageIcon(thisLocation+refImg);
		
		
		Image im = imgic.getImage(); //�̾ƿ� �̹��� ��ü ����� ���Ӱ� �����!
		Image im2 = im.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(im2);
		
		
		System.out.println(thisLocation+refImg);
		//this.setIconImage(imgic.getImage());
		imgLa = new JLabel(icon2); //imgic
		moviePanel.add(imgLa);
		
		//������ ���� ��
		
		

		//���ο� ���� ��ư�� ���̴��� �ƴ���
		if( !(LoginGui.currentUserId.equals("admin")) ) { //������ �ƴ� �� �������� ���ͼ� ��ư �Ⱥ��̰�
		insertBt.setVisible(false);
		updateBt.setVisible(false);
		deleteBt.setVisible(false);
		}
		
		// �˻���ư
		searchBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mvlist = mdao.selectSearch(searchText.getText());
				setUpdateTableData(movieTable);

			}
		});

		// �μ�Ʈ��ư
		insertBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MovieVO mvo = new MovieVO();

				mvo.setMoviename(movieNameText.getText());
				mvo.setPublisher(publisherText.getText());
				mvo.setPrice(Integer.parseInt(priceText.getText()));
				mvo.setQuantity(Integer.parseInt(quantityText.getText()));
				mvo.setShowdate(showdateText.getText());
				

				/*
				 * date to string Date from = new Date(); SimpleDateFormat transFormat = new
				 * SimpleDateFormat("yyyy/MM/dd"); String to = transFormat.format(from);
				 */

				// �����߰�
				// �����߰�

				int result = mdao.insert(mvo);
				if (result != 0) {
					JOptionPane.showMessageDialog(null, "��� �Ϸ�!!", "success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "��� ����!!", "error", JOptionPane.ERROR_MESSAGE);
				}

				mvlist = mdao.selectAll();
				setUpdateTableData(movieTable);
				
				
			}
		});

		movieTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

				int updateRow = movieTable.getSelectedRow();

				movieidCol = (int) movieTable.getValueAt(updateRow, 0);
				movieNameCol = (String) movieTable.getValueAt(updateRow, 1);
				publisherCol = (String) movieTable.getValueAt(updateRow, 2);
				priceCol = (int) movieTable.getValueAt(updateRow, 3);
				quantityCol = (int) movieTable.getValueAt(updateRow, 4);
				// %%�󿵳��� �߰� Date showDateCol = ;
				//���Ʈ��
				showDateCol = (String) movieTable.getValueAt(updateRow, 5);

				movieNameText.setText(movieNameCol);
				publisherText.setText(publisherCol);
				priceText.setText(String.valueOf(priceCol));
				quantityText.setText(String.valueOf(quantityCol));
				showdateText.setText(showDateCol);

				
				
				
				//�߰� ������ ����
				
				
				
				
				
				
				
				
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
				String selectedItem = movieNameCol;
				String thisLocation = System.getProperty("user.dir");
				String refImg = "\\img\\"+selectedItem+".jpg";
				//new ImageIcon("image/��Ŀ.jpg");
				System.out.println(thisLocation);
				ImageIcon imgic = new ImageIcon(thisLocation+refImg);				
				
				Image im = imgic.getImage(); //�̾ƿ� �̹��� ��ü ����� ���Ӱ� �����!
				Image im2 = im.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
				ImageIcon icon2 = new ImageIcon(im2);
				//icon2 = new ImageIcon(im2);				
				
				System.out.println(thisLocation+refImg);				
				//JLabel imgLa = new JLabel(icon2); //imgic
				//imgLa = new JLabel(icon2); //imgic
				moviePanel.remove(imgLa);
				moviePanel.repaint();
				
				imgLa = new JLabel(icon2);
				//imgLa.removeAll();
				//imgLa.setIcon(icon2);
				moviePanel.add(imgLa);
				moviePanel.validate();
				moviePanel.repaint();

			}
		});

		updateBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MovieVO mvo = new MovieVO();
				mvo.setMovieid(movieidCol);
				mvo.setMoviename(movieNameText.getText());
				mvo.setPublisher(publisherText.getText());
				mvo.setPrice(Integer.parseInt(priceText.getText()));
				mvo.setQuantity(Integer.parseInt(quantityText.getText()));
				// %%�󿵳��� �߰�
				mvo.setShowdate(showdateText.getText());

				int result = mdao.update(mvo);

				if (result != 0) {
					JOptionPane.showMessageDialog(null, "���� �Ϸ�!!", "success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "���� ����!!", "error", JOptionPane.ERROR_MESSAGE);
				}
				mvlist = mdao.selectAll();
				setUpdateTableData(movieTable);

			}
		});

		// ���� ��ư
		deleteBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = mdao.delete(movieidCol);
				if (result != 0) {
					JOptionPane.showMessageDialog(null, "���� �Ϸ�!!", "success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "���� ����!!", "error", JOptionPane.ERROR_MESSAGE);
				}
				mvlist = mdao.selectAll();
				setUpdateTableData(movieTable);
			}
		});

		//���� ��ư ���� �������� �ذ� ����
		orderBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(quantityCol == 0) {
					JOptionPane.showMessageDialog(null, "��ȭǥ�� 0�� ������ �� �����ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				MovieOrdersVO ovo = new MovieOrdersVO();
				//ovo.setOrderid( );
				ovo.setCustid(LoginGui.currentUserId); //���� ������ ������ ���� LoginGui.currentUserId
				ovo.setMovieid(movieidCol);
				ovo.setSaleprice(priceCol);
				
				//��ó https://the-illusionist.me/41
				java.util.Date today = new java.util.Date();
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//format1.format(today)
				
				//ovo.setDate("sysdate"); //sysdate �ھƳ����� �ȵ�.
				//ovo.setDate("1990/01/01 11:12:13"); //����ð� �ֹ�?
				ovo.setDate(format1.format(today)); //����ð� �ֹ�?
				
				//%%���⿡ �α����� ȸ�� �� ������ �ٲ���� �� �������� �α����� ��� id�� �ʿ�?
				//ovo.setCustid(1);
				
				
				
				//���⼭ ǥ���� -1�� ����� ��
				MovieVO mvo = new MovieVO();
				mvo.setMovieid(movieidCol);
				mvo.setMoviename(movieNameCol);
				mvo.setPublisher(publisherCol);
				mvo.setPrice(priceCol);
				// = quantityCol-1;
				mvo.setQuantity(quantityCol-1);
				mvo.setShowdate(showDateCol);
				
				
				
				int result = odao.insert(ovo);
				if (result != 0) {
					//JOptionPane.showMessageDialog(null, "result �ֹ� �Ϸ�!!", "success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "result �ֹ� ����!!", "error", JOptionPane.ERROR_MESSAGE);
				
				}
				
				
				int mvoResult = mdao.update(mvo);
				//������ �ֹ��� �����ؾ� ������ ��
				if (mvoResult != 0 && result != 0) {
					JOptionPane.showMessageDialog(null, "���� �Ϸ�!!", "success", JOptionPane.INFORMATION_MESSAGE);
					//����Ʈ���ؼ� ��������Ʈ �� �ް� �ٽ� �����ؼ� �ѷ��ش�
					mvlist = mdao.selectAll();
					setUpdateTableData(movieTable);
				} else {
					JOptionPane.showMessageDialog(null, "���� ����!!", "error", JOptionPane.ERROR_MESSAGE);
					
					MovieVO fmvo = new MovieVO();
					fmvo.setMovieid(movieidCol);
					fmvo.setMoviename(movieNameCol);
					fmvo.setPublisher(publisherCol);
					fmvo.setPrice(priceCol);
					// = quantityCol-1;
					fmvo.setQuantity(quantityCol+1); //�տ��� -1�Ȱ� ����
					fmvo.setShowdate(showDateCol);
					mvoResult = mdao.update(mvo);
					mvlist = mdao.selectAll();
					setUpdateTableData(movieTable);
				}
				
				//%%��ȣ�������� ������ ó���ϴ� �κ�				
				
				
				
				
			}
		});

	} // moviepage ��

	public void setUpdateTableData(JTable movieTable) {
		DefaultTableModel model = (DefaultTableModel) movieTable.getModel();
		model.setRowCount(0);
		for (MovieVO vo : mvlist) {
			Vector body = new Vector();
			body.addElement(vo.getMovieid());
			body.addElement(vo.getMoviename());
			body.addElement(vo.getPublisher());
			body.addElement(vo.getPrice());
			body.addElement(vo.getQuantity());
			body.addElement(vo.getShowdate());
			// vector ������ �־�� �� ���� Jtable�� �߰�
			model.addRow(body);
		}
		// ���������� Jtable ���� �ٽ� ����
		movieTable.setModel(model);
	}
	
	
	
	
	
	
	
	
	
	
	
}
