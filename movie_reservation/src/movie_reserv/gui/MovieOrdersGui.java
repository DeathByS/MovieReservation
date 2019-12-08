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
		//검색 
//		orderPanel.setLayout(null);
		
		JLabel searchLa = new JLabel("검색");
		JTextField searchText = new JTextField(30);
		JButton searchBt = new JButton("검색");
		JPanel searchPane = new JPanel();
		searchPane.add(searchLa);
		searchPane.add(searchText);
		searchPane.add(searchBt);
		//searchPane.setBounds(0, 5, 500, 50);
		
		//등록, 수정, 삭제
		JLabel orderIdLa = new JLabel("예약번호");
		JTextField orderIdText = new JTextField(30);
		
		JLabel custIdLa = new JLabel("예약자명");
		JTextField custIdText = new JTextField(30);
		
		JLabel movieIdLa = new JLabel("영화ID");
		JTextField movieIdText = new JTextField(30);
		
		JLabel priceLa = new JLabel("가격");
		JTextField priceText = new JTextField(30);
		
		JLabel dateLa = new JLabel("예약일");
		JTextField dateText = new JTextField(30);
		
		
		JButton insertBt = new JButton("등록");
		JButton updateBt = new JButton("수정");
		JButton deleteBt = new JButton("삭제");
		JButton refreshBt = new JButton("새로고침");
		//JButton orderBt = new JButton("주문");
		
		
		
		
		//테이블 - 검색값을 넣을
		JTable orderTable = new JTable();
		JPanel tablePanel = new JPanel();
//		tablePanel.setBounds(0, 50, 500, 500);
		//테이블 헤더값 (컬럼)
		Vector<String> header = new Vector<String>();
		header.addElement("예약번호");
		header.addElement("예약자ID");
		header.addElement("영화번호");
		header.addElement("가격");
		header.addElement("예약일");
		
		//테이블 모델 
		
		DefaultTableModel model = new DefaultTableModel(header, 0);
		orderTable.setModel(model);
		
		//테이블에 데이터 넣기
		
		//BookDAO bdao = new BookDAO();
		MovieOrdersDAO odao = new MovieOrdersDAO();
		//dao에서 selectAll 함수 호출해서 book 테이블에 있는 
		//전체 데이터 가져옴
		mlist = odao.selectAll();
		//Jtable에는 vector 값으로 데이터를 넣어야 하기 때문에
		//list 값을 vector로 다시 넣어줌
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
		
//		JLabel custIdLa = new JLabel("책이름");
//		JTextField custIdText = new JTextField(20);
//		
//		JLabel movieIdLa = new JLabel("출판사");
//		JTextField movieIdText = new JTextField(20);
//		
//		JLabel priceLa = new JLabel("가격");
//		JTextField priceText = new JTextField(20);
//		
//		JButton insertBt = new JButton("등록");
//		JButton updateBt = new JButton("수정");
//		JButton deleteBt = new JButton("삭제");
		
		
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
					JOptionPane.showMessageDialog(null,  "등록 완료!!","success", 
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "등록 실패!!","error", 							JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null,  "수정 완료!!","success", 
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "수정 실패!!","error", 							JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null,  "삭제 완료!!","success", 
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "삭제 실패!!","error", 							JOptionPane.ERROR_MESSAGE);
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
//					JOptionPane.showMessageDialog(null,  "주문 완료!!","success", 
//							JOptionPane.INFORMATION_MESSAGE);
//				} else {
//					JOptionPane.showMessageDialog(null, "주문 실패!!","error", 							JOptionPane.ERROR_MESSAGE);
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
			//vector 값으로 넣어둔 행 값을 Jtable에 추가
			model.addRow(body);
		}
		//마지막으로 Jtable 값을 다시 셋팅
		orderTable.setModel(model);
	}
	
	
	
}
