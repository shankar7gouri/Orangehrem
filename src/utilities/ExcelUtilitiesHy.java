package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilitiesHy {
    Workbook wb;
    
    public ExcelUtilitiesHy() throws Exception{
    	FileInputStream fi=new FileInputStream("D:\\DEC_seleniumOJT82\\StackAccounting_Hybrid\\TestInput\\InputSheet.xlsx");
    	wb=new XSSFWorkbook(fi);
    			
    }
    public int  rowCount(String sheetname){
    	
		return wb.getSheet(sheetname).getLastRowNum();
    	
    }
    public int colCount(String sheetname){
    	return wb.getSheet(sheetname).getRow(0).getLastCellNum();
    }
	public String getData(String sheetname,int row,int col){
		String data="";
		if(wb.getSheet(sheetname).getRow(row).getCell(col).getCellType()==Cell.CELL_TYPE_NUMERIC){
			         int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(col).getNumericCellValue();
			         data=String.valueOf(celldata);
			         
		}else{
			data=wb.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
		}
		return data;
	}
	
	public void setData(String sheetname,int row, int col,String status) throws Throwable{
		Cell cell=wb.getSheet(sheetname).getRow(row).createCell(col);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass")){
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
		}else if(status.equalsIgnoreCase("Fail")){
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			
		}else if(status.equalsIgnoreCase("Not Executed")){
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			
		}
		FileOutputStream foi=new FileOutputStream("D:\\DEC_seleniumOJT82\\StackAccounting_Hybrid\\TestOutput\\HybridOutput.xlsx");
		wb.write(foi);
		foi.close();
		
	}

}
