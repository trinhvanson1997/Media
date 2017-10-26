package vn.media.server.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarView extends JMenuBar{
	
	
	public MenuBarView() {
		//setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		JMenu menuFile = new JMenu("Edit"); 
		JMenuItem copyItem = new JMenuItem("Copy Ctrl+C"); 
		JMenuItem pasteItem = new JMenuItem("Paste Ctrl+V");  
		menuFile.add(copyItem);  menuFile.add(pasteItem);     
		
		JMenu menuHelp = new JMenu("Help"); 
		JMenuItem hTopicItem = new JMenuItem("Help Topics"); 
		JMenuItem hAboutItem = new JMenuItem("About Calculator");  
		menuHelp.add(hTopicItem);  menuHelp.addSeparator(); 
		menuHelp.add(hAboutItem);  
		
		add(menuFile);  
		add(menuHelp); 
		 
	}
}
