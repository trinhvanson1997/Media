package vn.media.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.swing.Timer;

import vn.media.common.IOFile;
import vn.media.controller.DBConnector;

public class Store {
	private String storeName;
	public static long totalMoney;
	private List<Fee> costList;
	private DBConnector db;
	SanPham sp;
	IOFile io = new IOFile();
	
	public Store(String storeName, long totalMoney, DBConnector db) {
		this.storeName = storeName;
		this.totalMoney = totalMoney;
		this.db = db;
		this.costList = db.getAllFee();
		
		
		Date time = new Date();
		@SuppressWarnings("deprecation")
		Timestamp now = new Timestamp(time.getYear(), time.getMonth(), time.getDate(), time.getHours(), time.getMinutes(), time.getSeconds(), 0);
		for(Fee fe:costList) {
			Timestamp last = fe.getLastRequest();
			@SuppressWarnings("deprecation")
			int day=(now.getYear()-last.getYear())*360+(now.getMonth()-last.getMonth())*30+now.getDate()-last.getDate();
			int count = day/fe.getFeeCycle();
			for(int i=0;i<count;i++) {
				String id = "PA"+sp.indexOfPaid;
				db.addPaid(new Paid(id, fe.getFeeName(), 0, fe.getFeeValue(), now, null));
				sp.indexOfPaid++;
				
				fe.setLastRequest(now);
				db.updateFee(fe);
			}
		}
		Timer timer = new Timer(86400 ,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("WE ARE IN TIMER");
				Date time = new Date();
				@SuppressWarnings("deprecation")
				Timestamp now = new Timestamp(time.getYear(), time.getMonth(), time.getDate(), time.getHours(), time.getMinutes(), time.getSeconds(), 0);
				for(Fee fe:costList) {
					Timestamp last = fe.getLastRequest();
					@SuppressWarnings("deprecation")
					int day=(now.getYear()-last.getYear())*360+(now.getMonth()-last.getMonth()*30+now.getDate()-last.getDate());
					int count = day/fe.getFeeCycle();
					for(int i=0;i<count;i++) {
						String id = "PA"+sp.indexOfPaid;
						db.addPaid(new Paid(id, fe.getFeeName(), 0, fe.getFeeValue(), now, null));
						sp.indexOfPaid++;
						
						fe.setLastRequest(now);
						db.updateFee(fe);
					}
				}
				
			}
		});
		timer.start();
		io.writeFile();
	}
	/**
	 * add cost to costlist
	 * @param cost
	 * @return true if success else false
	 */
	public boolean addCost(Fee cost) {
		if(cost!=null) {
			costList.add(cost);
			return true;
		}
		return false;
	}
	/**
	 * remove cost by reference
	 * @param cost
	 * @return true true if success else false 
	 */

	public boolean removeCost(Fee cost) {
		return this.costList.remove(cost);
	}
	/**
	 * remove cost by index 
	 * @param index index of cost in list
	 * @return true if success else false
	 */
	public boolean removeCost(int index) {
		if(this.costList.remove(index)==null) return false;
		return true;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public long getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(long totalMoney) {
		this.totalMoney = totalMoney;
	}
	public List<Fee> getCostList() {
		return costList;
	}
	public void setCostList(List<Fee> costList) {
		this.costList = costList;
	}
	public DBConnector getDbconnector() {
		return db;
	}
	
	
}
