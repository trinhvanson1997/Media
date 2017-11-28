package vn.media.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import vn.media.controller.DBConnector;

public class FuncStatisPanel extends JPanel implements ActionListener{
	private JButton btnXem;
	private JLabel lbFrom,lbTo,lbViewBy;
	private JTextField tfFrom,tfTo;
	private JComboBox<String> cbType;
	String type[] = {"Ngày","Tháng","Năm"};
	private MainFrame mainFrame;
	private DBConnector db;
	public FuncStatisPanel(MainFrame mainFrame,DBConnector db) {
		this.mainFrame = mainFrame;
		this.db = db;
		
		setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		lbFrom = new JLabel("From   ");
		lbTo   = new JLabel("To  ");
		tfFrom = new JTextField(15);
		tfTo   = new JTextField(15);
		lbViewBy = new JLabel("Thống kê theo ");
		cbType = new JComboBox<>(type);
		
		btnXem = new JButton("XEM");
		btnXem.addActionListener(this);
		
		add(lbFrom);
		add(tfFrom);
		add(lbTo);
		add(tfTo);
		add(lbViewBy);
		add(cbType);
		add(btnXem);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnXem) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			int choice = cbType.getSelectedIndex();
			
		
			Date time1 = null;
			Date time2 = null;
			Date sau = null;
			if(choice == 0) {
			try {
				 time1 = db.getMinDateBill(format.parse(tfFrom.getText()));
				 time2 = db.getMaxDateBill(format.parse(tfTo.getText()));
			} catch (ParseException e) {
			
				e.printStackTrace();
			}
			}
			else if(choice == 1) {
				try {
					 time1 = db.getMinDateBillByMonth(format.parse(tfFrom.getText()));
					 time2 = db.getMaxDateBillByMonth(format.parse(tfTo.getText()));
				} catch (ParseException e) {
				
					e.printStackTrace();
				}
			}
			else if(choice == 2) {
				try {
					 time1 = db.getMinDateBillByYear(format.parse(tfFrom.getText()));
					 time2 = db.getMaxDateBillByYear(format.parse(tfTo.getText()));
				} catch (ParseException e) {
				
					e.printStackTrace();
				}
			}
			Calendar calendar = Calendar.getInstance();
			
			calendar.setTime(time2);
			if(choice == 0) {
			calendar.add(Calendar.DATE, 1);
			}
			
			time2 = calendar.getTime();
			calendar.setTime(time1);
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			while (time1.compareTo(time2) <= 0) {
				calendar.setTime(time1);
				
				if(choice == 0) {
					calendar.add(Calendar.DATE, 1);
					}
					else if(choice == 1) {
						calendar.add(Calendar.MONTH, 1);
					}
					else if(choice == 2) {
						calendar.add(Calendar.YEAR, 1);
					}
			
			
				 sau = calendar.getTime();
				dataset.addValue(db.getDoanhThu(time1, sau), "Doanh thu", format.format(time1));
				dataset.addValue(db.getLoiNhuan(time1, sau), "Lợi nhuận", format.format(time1));

				time1 = sau;
			}
			JFreeChart lineChart = ChartFactory.createLineChart("Thống kê doanh thu (đỏ) và lợi nhuận (xanh)".toUpperCase(), "Ngày",
					"Số Tiền (đ)", dataset, PlotOrientation.VERTICAL, false, false, false);
		
			ChartPanel panelChart = new ChartPanel(lineChart);
			
			JPanel tablePanel = mainFrame.getTablePanel();
			JPanel funcPanel = mainFrame.getFuncPanel();
			
			tablePanel.remove(tablePanel.getComponent(0));
			tablePanel.add(panelChart, BorderLayout.CENTER);
			tablePanel.revalidate();
			tablePanel.repaint();
			
			
		}
		
	}

	public JButton getBtnXem() {
		return btnXem;
	}

	public void setBtnXem(JButton btnXem) {
		this.btnXem = btnXem;
	}

	public JLabel getLbFrom() {
		return lbFrom;
	}

	public void setLbFrom(JLabel lbFrom) {
		this.lbFrom = lbFrom;
	}

	public JLabel getLbTo() {
		return lbTo;
	}

	public void setLbTo(JLabel lbTo) {
		this.lbTo = lbTo;
	}

	public JTextField getTfFrom() {
		return tfFrom;
	}

	public void setTfFrom(JTextField tfFrom) {
		this.tfFrom = tfFrom;
	}

	public JTextField getTfTo() {
		return tfTo;
	}

	public void setTfTo(JTextField tfTo) {
		this.tfTo = tfTo;
	}
}
