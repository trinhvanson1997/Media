package vn.media.client.controller;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import vn.media.client.view.ClientUI;
import vn.media.client.view.SearchClientPanel;
import vn.media.server.models.DiaNhac;
import vn.media.server.models.DiaPhim;
import vn.media.server.view.TabbedProduct;


public class ChangeTableClient {
	private TabbedProduct tabbedProduct;
	private JPanel tablePanel; 
	private SearchClientPanel searchClientPanel;
	
	public ChangeTableClient(ClientUI clientUI) {
		tabbedProduct = clientUI.getTabbedProduct();
		tablePanel = clientUI.getTablePanel();
		searchClientPanel = clientUI.getSearchClientPanel();
		
		tabbedProduct.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	            if(tabbedProduct.getSelectedIndex() == 0) {
	            	tablePanel.remove(tablePanel.getComponent(1));
					tablePanel.add(searchClientPanel.getSearchBookPanel(), BorderLayout.SOUTH);
					tablePanel.revalidate();
					tablePanel.repaint();
	            }
	            else if(tabbedProduct.getSelectedIndex() == 1) {
	            
	            	tablePanel.remove(tablePanel.getComponent(1));
					tablePanel.add(searchClientPanel.getSearchMoviePanel(), BorderLayout.SOUTH);
					tablePanel.revalidate();
					tablePanel.repaint();
					
					
	            }
	            else if(tabbedProduct.getSelectedIndex() == 2) {
	            	tablePanel.remove(tablePanel.getComponent(1));
					tablePanel.add(searchClientPanel.getSearchMusicPanel(), BorderLayout.SOUTH);
					tablePanel.revalidate();
					tablePanel.repaint();
					
	            }
	        }
	    });
	}
}
