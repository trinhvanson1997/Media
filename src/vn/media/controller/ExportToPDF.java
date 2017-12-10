package vn.media.controller;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Locale;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import vn.media.models.HoaDon;
import vn.media.models.KhachHang;
import vn.media.models.MuaHang;

public class ExportToPDF {
	public static final String FONT
    = "c:/windows/fonts/Arial.ttf";
	
	// private static String FILE = "D:/Bill.pdf";
	
	    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 24,
	            Font.BOLD);
	    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.NORMAL, BaseColor.RED);
	    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 19,
	            Font.BOLD);
	    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.BOLD);
	    private static Font italic = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.BOLDITALIC);
	    private static Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.BOLD);
	    private static Font normal2 = new Font(Font.FontFamily.TIMES_ROMAN, 15,
	            Font.BOLD);
	    
	    DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
	    public ExportToPDF(HoaDon hoadon,DBConnector db) {
	        try {
	            Document document = new Document(new Rectangle(380, 700));
	            PdfWriter.getInstance(document, new FileOutputStream("D:/Bill" + hoadon.getId()+ ".pdf"));
	            document.open();
	            BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	            // create title of bill
	            Paragraph titleParagraph = new Paragraph();
	            Paragraph nameStore = 		new Paragraph("MediaOne Store",catFont); 				nameStore.setAlignment(Paragraph.ALIGN_CENTER);
	            Paragraph addressStore = 	new Paragraph("No.1, Dai Co Viet Street",italic);	 	addressStore.setAlignment(Paragraph.ALIGN_CENTER);
	            Paragraph phoneStore = 		new Paragraph("LH: 024.123.456",italic);				phoneStore.setAlignment(Paragraph.ALIGN_CENTER);
	            Paragraph stars = 			new Paragraph("**********************",smallBold);		stars.setAlignment(Paragraph.ALIGN_CENTER);
	            
	            titleParagraph.add(nameStore);
	            titleParagraph.add(addressStore);
	            //titleParagraph.add(phoneStore);
	            titleParagraph.add(stars);
	            addEmptyLine(titleParagraph, 1);
	            
	            document.add(titleParagraph);
	            
	            //create information of customer
	            Paragraph infoParagraph = 	new Paragraph();
	           KhachHang kh = db.getCus(db.getUsernameCus(hoadon.getIdKhachHang()));
	            Paragraph BILL			=   new Paragraph("BILL",subFont); BILL.setAlignment(Paragraph.ALIGN_CENTER);
	            
	            Paragraph billID 	= 		new Paragraph("Bill ID    :  "+ "#"+hoadon.getId(),new Font(bf, 12));
	            Paragraph nameCustomer = 	new Paragraph("Name    :  "+ kh.getHoTen(),new Font(bf, 12));
	            Paragraph phoneCustomer = 	new Paragraph("Phone   :  "+ kh.getsDT(),new Font(bf, 12));
	            Paragraph addressCustomer = new Paragraph("Address:  "+ kh.getDiaChi(),new Font(bf, 12));
	           
	            
	            infoParagraph.add(BILL);
	         
	            infoParagraph.add(billID);
	            infoParagraph.add(nameCustomer);
	            infoParagraph.add(phoneCustomer);
	            infoParagraph.add(addressCustomer);
	            addEmptyLine(infoParagraph, 1);
	            
	            document.add(infoParagraph);
	            
	           // create bill detail table
	            PdfPTable header = new PdfPTable(4); // column of table = 4
	            
	            //create header of table
	            PdfPCell header1 = new PdfPCell(new Phrase("Product Name",normal));
	            PdfPCell header2 = new PdfPCell(new Phrase("Quantity",normal));
	            PdfPCell header3 = new PdfPCell(new Phrase("Rate",normal));
	            PdfPCell header4 = new PdfPCell(new Phrase("Amount",normal));
	            

	            
	            header1.setBorderColor(BaseColor.WHITE);
	            header2.setBorderColor(BaseColor.WHITE);
	            header3.setBorderColor(BaseColor.WHITE);
	            header4.setBorderColor(BaseColor.WHITE);
	            
	            header.addCell(header1);
	            header.addCell(header2);
	            header.addCell(header3);
	            header.addCell(header4);
	            
	            Paragraph dash = new Paragraph("--------------------------------------------------------------------");
	            dash.setAlignment(Paragraph.ALIGN_CENTER);
	            PdfPTable table = new PdfPTable(4); 
	          
	            
	            for(int i=0;i<hoadon.getMuaHang().size();i++) {
	            	MuaHang m = hoadon.getMuaHang().get(i);
	            	
	            	PdfPCell cell1 = new PdfPCell(new Phrase(db.getNameProduct(m.getIdSanPham()),new Font(bf, 12)));
	            	PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(m.getSoLuong())));
	            	PdfPCell cell3 = new PdfPCell(new Phrase(format.format(m.getDonGia()).toString()));
	            	PdfPCell cell4 = new PdfPCell(new Phrase(format.format(m.getDonGia() * m.getSoLuong()).toString()));
	            	
	            	cell1.setBorderColor(BaseColor.WHITE);
	            	cell2.setBorderColor(BaseColor.WHITE);
	            	cell3.setBorderColor(BaseColor.WHITE);
	            	cell4.setBorderColor(BaseColor.WHITE);
	            	
	            	cell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
	            	table.addCell(cell1);
	            	table.addCell(cell2);
	            	table.addCell(cell3);
	            	table.addCell(cell4);
	            }
	            
	            long totalMoney = 0;
	            for(int i=0;i<hoadon.getMuaHang().size();i++) {
	            	totalMoney += hoadon.getMuaHang().get(i).getSoLuong() * hoadon.getMuaHang().get(i).getDonGia();
	            }
	            
	            Paragraph totalPara = new Paragraph("TOTAL : " + format.format(totalMoney).toString() + "VND",normal2);
	            totalPara.setAlignment(Paragraph.ALIGN_RIGHT);
	            
	            document.add(header);
	            document.add(dash);
	            document.add(table);
	            document.add(dash);
	            document.add(totalPara);
	            
	            
	            document.newPage();
	            document.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	    }

}

