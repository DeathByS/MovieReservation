package movie_reserv.gui;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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


import movie_reserv.dao.MovieDAO;
import movie_reserv.dao.MovieOrdersDAO;

import movie_reserv.vo.MovieOrdersVO;
import movie_reserv.vo.MovieVO;

public class dump_MovieGui {
	
	

	
	private JPanel moviePanel;

	private ArrayList<MovieVO> mvlist;
	private int movieidCol;
	private int priceCol;
	private int quantityCol;
	private String showDateCol;
	private String movieNameCol;
	private String publisherCol;

	public dump_MovieGui(JFrame j) {
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
		// 검색
		JLabel searchLa = new JLabel("영화 검색");
		JTextField searchText = new JTextField(30);
		JButton searchBt = new JButton("검색");
		// 등록, 수정, 삭제
		JLabel movieNameLa = new JLabel("영화이름");
		JTextField movieNameText = new JTextField(30);

		JLabel publisherLa = new JLabel("배급사");
		JTextField publisherText = new JTextField(30);

		JLabel priceLa = new JLabel("가격");
		JTextField priceText = new JTextField(30);

		// 수량추가
		JLabel quantityLa = new JLabel("표수량");
		JTextField quantityText = new JTextField(30);
		// 상영일추가
		JLabel showdateLa = new JLabel("상영일");
		JTextField showdateText = new JTextField(30);

		JButton insertBt = new JButton("등록");
		JButton updateBt = new JButton("수정");
		JButton deleteBt = new JButton("삭제");
		JButton orderBt = new JButton("예매");

		// 테이블 - 검색값을 넣을
		JTable movieTable = new JTable();
		JPanel tablePanel = new JPanel();

		// 테이블 헤더값 (컬럼)
		Vector<String> header = new Vector<String>();
		header.addElement("영화id"); // 이건 admin 계정에서만 보이게????
		header.addElement("영화이름");
		header.addElement("배급사");
		header.addElement("가격");
		header.addElement("표수량");
		header.addElement("상영날자");

		// 테이블 모델

		DefaultTableModel model = new DefaultTableModel(header, 0);
		movieTable.setModel(model);

		// 테이블에 데이터 넣기

		MovieDAO mdao = new MovieDAO();
		MovieOrdersDAO odao = new MovieOrdersDAO();
		// dao에서 selectAll 함수 호출해서 book 테이블에 있는
		// 전체 데이터 가져옴

		mvlist = mdao.selectAll();

		// Jtable에는 vector 값으로 데이터를 넣어야 하기 때문에
		// list 값을 vector로 다시 넣어줌
		setUpdateTableData(movieTable); // 여기서 리프레시해주는 것

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

		// 수량 추가
		JPanel quantityPanel = new JPanel();
		quantityPanel.add(quantityLa);
		quantityPanel.add(quantityText);
		moviePanel.add(quantityPanel);
		// 날자추가
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

		// 검색버튼
		searchBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mvlist = mdao.selectSearch(searchText.getText());
				setUpdateTableData(movieTable);

			}
		});

		// 인서트버튼
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

				// 수량추가
				// 상영일추가

				int result = mdao.insert(mvo);
				if (result != 0) {
					JOptionPane.showMessageDialog(null, "등록 완료!!", "success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "등록 실패!!", "error", JOptionPane.ERROR_MESSAGE);
				}

				mvlist = mdao.selectAll();
				setUpdateTableData(movieTable);
			}
		});

		movieTable.addMouseListener(new MouseListener() {

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
				int updateRow = movieTable.getSelectedRow();

				movieidCol = (int) movieTable.getValueAt(updateRow, 0);
				movieNameCol = (String) movieTable.getValueAt(updateRow, 1);
				publisherCol = (String) movieTable.getValueAt(updateRow, 2);
				priceCol = (int) movieTable.getValueAt(updateRow, 3);
				quantityCol = (int) movieTable.getValueAt(updateRow, 4);
				// %%상영날자 추가 Date showDateCol = ;
				//쇼데이트콜
				showDateCol = (String) movieTable.getValueAt(updateRow, 5);

				movieNameText.setText(movieNameCol);
				publisherText.setText(publisherCol);
				priceText.setText(String.valueOf(priceCol));
				quantityText.setText(String.valueOf(quantityCol));
				showdateText.setText(showDateCol);

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
				// %%상영날자 추가
				mvo.setShowdate(showdateText.getText());

				int result = mdao.update(mvo);

				if (result != 0) {
					JOptionPane.showMessageDialog(null, "수정 완료!!", "success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "수정 실패!!", "error", JOptionPane.ERROR_MESSAGE);
				}
				mvlist = mdao.selectAll();
				setUpdateTableData(movieTable);

			}
		});

		// 삭제 버튼
		deleteBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = mdao.delete(movieidCol);
				if (result != 0) {
					JOptionPane.showMessageDialog(null, "삭제 완료!!", "success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "삭제 실패!!", "error", JOptionPane.ERROR_MESSAGE);
				}
				mvlist = mdao.selectAll();
				setUpdateTableData(movieTable);
			}
		});

		//예매 버튼 여긴 합쳐져야 해결 가능
		orderBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(quantityCol == 0) {
					JOptionPane.showMessageDialog(null, "영화표가 0개 예매할 수 없습니다.", "error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				MovieOrdersVO ovo = new MovieOrdersVO();
				//ovo.setOrderid( );
				ovo.setCustid("yjs");
				ovo.setMovieid(movieidCol);
				ovo.setSaleprice(priceCol);
				
				//출처 https://the-illusionist.me/41
				java.util.Date today = new java.util.Date();
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//format1.format(today)
				
				//ovo.setDate("sysdate"); //sysdate 박아놓으니 안됨.
				//ovo.setDate("1990/01/01 11:12:13"); //현재시간 주문?
				ovo.setDate(format1.format(today)); //현재시간 주문?
				
				//%%여기에 로그인한 회원 값 변수로 바꿔줘야 함 종근이형 로그인한 사람 id가 필요?
				//ovo.setCustid(1);
				
				
				
				//여기서 표수량 -1로 해줘야 함
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
					JOptionPane.showMessageDialog(null, "result 주문 완료!!", "success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "result 주문 실패!!", "error", JOptionPane.ERROR_MESSAGE);
				
					
				}
				
				
				int mvoResult = mdao.update(mvo);
				//위에서 주문이 성공해야 예약이 됨
				if (mvoResult != 0 && result != 0) {
					JOptionPane.showMessageDialog(null, "mvoResult 예약 완료!!", "success", JOptionPane.INFORMATION_MESSAGE);
					//셀렉트올해서 기존리스트 다 받고 다시 갱신해서 뿌려준다
					mvlist = mdao.selectAll();
					setUpdateTableData(movieTable);
				} else {
					JOptionPane.showMessageDialog(null, "mvoResult 예약 실패!!", "error", JOptionPane.ERROR_MESSAGE);
					
					MovieVO fmvo = new MovieVO();
					fmvo.setMovieid(movieidCol);
					fmvo.setMoviename(movieNameCol);
					fmvo.setPublisher(publisherCol);
					fmvo.setPrice(priceCol);
					// = quantityCol-1;
					fmvo.setQuantity(quantityCol+1); //앞에서 -1된거 복구
					fmvo.setShowdate(showDateCol);
					mvoResult = mdao.update(mvo);
					mvlist = mdao.selectAll();
					setUpdateTableData(movieTable);
				}
				
				//%%길호형쪽으로 보내서 처리하는 부분				
				
				
				
				
			}
		});

	} // moviepage 끝

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
			// vector 값으로 넣어둔 행 값을 Jtable에 추가
			model.addRow(body);
		}
		// 마지막으로 Jtable 값을 다시 셋팅
		movieTable.setModel(model);
	}

}
	

