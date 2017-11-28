package vn.media.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import vn.media.models.SanPham;
import vn.media.models.Store;

public class IOFile {
	private static String path="D:/EclispeOxygen/Media/src/data.txt";
	private static File f = new File(path);
	public SanPham sp;
	public Store store;
	
	public IOFile() {
		
	}


	public  void writeFile(){
		
		
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(f));
			pw.println(sp.indexOfStaff);
			pw.println(sp.indexOfCus);
			pw.println(sp.indexOfBook);
			pw.println(sp.indexOfMovies);
			pw.println(sp.indexOfMusic);
			pw.println(sp.indexOfBill);
			pw.println(sp.indexOfPaid);
			pw.println(store.totalMoney);
			
			pw.close();
		} catch (IOException e) {
			System.out.println("Failed to open file to write");
			e.printStackTrace();
		}
		
	}
	
	public  void readFile(){
		
		try {
			Scanner inp=new Scanner(new FileReader(f));
			sp.indexOfStaff = Integer.parseInt(inp.nextLine());
			sp.indexOfCus = Integer.parseInt(inp.nextLine());
			sp.indexOfBook = Integer.parseInt(inp.nextLine());
			sp.indexOfMovies = Integer.parseInt(inp.nextLine());
			sp.indexOfMusic = Integer.parseInt(inp.nextLine());
			sp.indexOfBill = Integer.parseInt(inp.nextLine());
			sp.indexOfPaid = Integer.parseInt(inp.nextLine());
			store.totalMoney = Long.parseLong(inp.nextLine());
			
			inp.close();
		} catch (FileNotFoundException e) {
			System.out.println("Failed to open file to read");
			e.printStackTrace();
		}
	}
	
}
