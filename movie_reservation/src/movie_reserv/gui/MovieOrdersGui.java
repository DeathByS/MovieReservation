package movie_reserv.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import movie_reserv.dao.MovieOrdersDAO;
import movie_reserv.vo.MovieOrdersVO;

public class MovieOrdersGui {

	private ArrayList<MovieOrdersVO> mlist;
	private int orderidCol;
	private int priceCol;
	private JPanel orderPanel;
	
	
	public JPanel getorderPanel() {
		return orderPanel;
	}

	public void setorderPanel(JPanel orderPanel) {
		this.orderPanel = orderPanel;
	}

	public MovieOrdersGui(JFrame j) {
		orderPanel = new JPanel();
		bookPage(orderPanel);
		j.add(orderPanel);
//		JTabbedPane bookTPanel = new JTabbedPane();
//		
//		JPanel panel1 = new JPanel();
//		JPanel panel2 = new JPanel();
//		JPanel panel3 = new JPanel();
//		
		orderPanel.setSize(550,700);
//		
//		
//		bookTPanel.addTab("1",panel1);
//		bookTPanel.addTab("2",panel2);
//		bookTPanel.addTab("3",panel3);
		
		
	}
	
	public void bookPage(JPanel orderPanel) {
		//�˻� 
//		orderPanel.setLayout(null);
		
		JLabel searchLa = new JLabel("�˻�");
		JTextField searchText = new JTextField(30);
		JButton searchBt = new JButton("�˻�");
		JPanel searchPane = new JPanel();
		searchPane.add(searchLa);
		searchPane.add(searchText);
		searchPane.add(searchBt);
		//searchPane.setBounds(0, 5, 500, 50);
		
		//���, ����, ����
		JLabel orderIdLa = new JLabel("�����ȣ");
		JTextField orderIdText = new JTextField(30);
		
		JLabel custIdLa = new JLabel("�����ڸ�");
		JTextField custIdText = new JTextField(30);
		
		JLabel movieIdLa = new JLabel("��ȭID");
		JTextField movieIdText = new JTextField(30);
		
		JLabel priceLa = new JLabel("����");
		JTextField priceText = new JTextField(30);
		
		JLabel dateLa = new JLabel("������");
		JTextField dateText = new JTextField(30);
		
		
		JButton insertBt = new JButton("���");
		JButton updateBt = new JButton("����");
		JButton deleteBt = new JButton("����");
		JButton refreshBt = new JButton("���ΰ�ħ");
		//JButton orderBt = new JButton("�ֹ�");
		
		
		
		
		//���̺� - �˻����� ����
		JTable orderTable = new JTable();
		JPanel tablePanel = new JPanel();
//		tablePanel.setBounds(0, 50, 500, 500);
		//���̺� ����� (�÷�)
		Vector<String> header = new Vector<String>();
		header.addElement("�����ȣ");
		header.addElement("������ID");
		header.addElement("��ȭ��ȣ");
		header.addElement("����");
		header.addElement("������");
		
		//���̺� �� 
		
		DefaultTableModel model = new DefaultTableModel(header, 0);
		orderTable.setModel(model);
		
		//���̺� ������ �ֱ�
		
		//BookDAO bdao = new BookDAO();
		MovieOrdersDAO odao = new MovieOrdersDAO();
		//dao���� selectAll �Լ� ȣ���ؼ� book ���̺� �ִ� 
		//��ü ������ ������
		mlist = odao.selectAll();
		//Jtable���� vector ������ �����͸� �־�� �ϱ� ������
		//list ���� vector�� �ٽ� �־���
		setUpdateTableData(orderTable);
		
		orderTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablePanel.add(new JScrollPane(orderTable));
		
		
		orderPanel.add(searchLa);
		orderPanel.add(searchText);
		orderPanel.add(searchBt);
		orderPanel.add(searchPane);
		orderPanel.add(tablePanel);
		
//		custIdLa.setBounds(300, 10, 50, 100);
		JPanel orderIdPanel = new JPanel();
		orderIdPanel.add(orderIdLa);
		orderIdPanel.add(orderIdText);
		orderPanel.add(orderIdPanel);
		
		JPanel custIdPanel = new JPanel();
		custIdPanel.add(custIdLa);
		custIdPanel.add(custIdText);
		orderPanel.add(custIdPanel);
		
		JPanel movieIdPanel = new JPanel();
		movieIdPanel.add(movieIdLa);
		movieIdPanel.add(movieIdText);
		orderPanel.add(movieIdPanel);
		
		JPanel pricePanel = new JPanel();
		pricePanel.add(priceLa);
		pricePanel.add(priceText);
		orderPanel.add(pricePanel);
		
		JPanel datePanel = new JPanel();
		datePanel.add(dateLa);
		datePanel.add(dateText);
		orderPanel.add(datePanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(insertBt);
		buttonPanel.add(updateBt);
		buttonPanel.add(deleteBt);
		buttonPanel.add(refreshBt);
//		buttonPanel.add(orderBt);
		
		orderPanel.add(buttonPanel);
		
//		JLabel custIdLa = new JLabel("å�̸�");
//		JTextField custIdText = new JTextField(20);
//		
//		JLabel movieIdLa = new JLabel("���ǻ�");
//		JTextField movieIdText = new JTextField(20);
//		
//		JLabel priceLa = new JLabel("����");
//		JTextField priceText = new JTextField(20);
//		
//		JButton insertBt = new JButton("���");
//		JButton updateBt = new JButton("����");
//		JButton deleteBt = new JButton("����");
		
		
		searchBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mlist = odao.selectSearch(searchText.getText());
				setUpdateTableData(orderTable);
			}
		});
		
		insertBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//MovieVO bvo = new MovieVO();
				//bvo.setBookname(custIdText.getText());
				//bvo.setPublisher(movieIdText.getText());
				//bvo.setPrice(Integer.parseInt(priceText.getText()));
				//int result = bdao.insert(bvo);
				MovieOrdersVO mvo = new MovieOrdersVO();
				mvo.setCustid(custIdText.getText());
				mvo.setMovieid(Integer.parseInt(movieIdText.getText()));
				mvo.setSaleprice(Integer.parseInt(priceText.getText()));
				mvo.setDate(dateText.getText());
				int result = odao.insert(mvo); 
				
				if(result != 0) {
					JOptionPane.showMessageDialog(null,  "��� �Ϸ�!!","success", 
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "��� ����!!","error", 							JOptionPane.ERROR_MESSAGE);
				}
				mlist = odao.selectAll();
				setUpdateTableData(orderTable);
				
			}
		});
		
		orderTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int updateRow = orderTable.getSelectedRow();
				
				orderidCol = (int) orderTable.getValueAt(updateRow, 0);
				String custIdCol = (String)orderTable.getValueAt(updateRow, 1);
				int movieIdCol =(int) orderTable.getValueAt(updateRow, 2);
				priceCol = (int) orderTable.getValueAt(updateRow, 3);
				String orderDateCol = (String)orderTable.getValueAt(updateRow, 4);
				
				orderIdText.setText(String.valueOf(orderidCol)); 
				custIdText.setText(custIdCol);
				movieIdText.setText(String.valueOf(movieIdCol));
				priceText.setText(String.valueOf(priceCol));
				dateText.setText(orderDateCol);
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
			
				
				
				
			}
		});
		
		updateBt.addActionListener(new ActionListener() {
			
		//	@Override
			
			public void actionPerformed(ActionEvent e) {
//				MovieVO bvo = new MovieVO();
//				bvo.setBookid(bookidCol);
//				bvo.setBookname(custIdText.getText());
//				bvo.setPublisher(movieIdText.getText());
//				bvo.setPrice(Integer.parseInt(priceText.getText()));
//				int result = bdao.update(bvo);
				
			
				MovieOrdersVO mvo = new MovieOrdersVO();
				mvo.setOrderid(Integer.parseInt(orderIdText.getText()));
				
				mvo.setCustid(custIdText.getText());
				mvo.setMovieid(Integer.parseInt(movieIdText.getText()));
				mvo.setSaleprice(Integer.parseInt(priceText.getText()));
				mvo.setDate(dateText.getText());
				int result = odao.update(mvo); 
				
				
				if(result != 0) {
					JOptionPane.showMessageDialog(null,  "���� �Ϸ�!!","success", 
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "���� ����!!","error", 							JOptionPane.ERROR_MESSAGE);
				}
				mlist = odao.selectAll();
				setUpdateTableData(orderTable);
			
			
				
			}
		});
		
		deleteBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = odao.delete(Integer.parseInt(orderIdText.getText()));
				if(result != 0) {
					JOptionPane.showMessageDialog(null,  "���� �Ϸ�!!","success", 
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "���� ����!!","error", 							JOptionPane.ERROR_MESSAGE);
				}
				mlist = odao.selectAll();
				setUpdateTableData(orderTable);
				
				
				}
		});
		
		refreshBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mlist = odao.selectAll();
				setUpdateTableData(orderTable);
			}
		});
//		orderBt.addActionListener(new ActionListener() {
			
		//	@Override
//			public void actionPerformed(ActionEvent e) {
//				OrdersVO ovo = new OrdersVO();
//				ovo.setBookid(bookidCol);
//				ovo.setSaleprice(priceCol);
//				ovo.setCustid(1);
//				int result = odao.insert(ovo);
//				if(result != 0) {
//					JOptionPane.showMessageDialog(null,  "�ֹ� �Ϸ�!!","success", 
//							JOptionPane.INFORMATION_MESSAGE);
//				} else {
//					JOptionPane.showMessageDialog(null, "�ֹ� ����!!","error", 							JOptionPane.ERROR_MESSAGE);
//				}
//				
//			}
//		});
		
	}
	
	
	public void setUpdateTableData(JTable orderTable) {
		DefaultTableModel model = (DefaultTableModel)orderTable.getModel();
		model.setRowCount(0);
		for(MovieOrdersVO vo : mlist) {
			Vector body = new Vector();
			body.addElement(vo.getOrderid());
			body.addElement(vo.getCustid());
			body.addElement(vo.getMovieid());
			body.addElement(vo.getSaleprice());
			body.addElement(vo.getDate());
			//vector ������ �־�� �� ���� Jtable�� �߰�
			model.addRow(body);
		}
		//���������� Jtable ���� �ٽ� ����
		orderTable.setModel(model);
	}
	
	
	
}
