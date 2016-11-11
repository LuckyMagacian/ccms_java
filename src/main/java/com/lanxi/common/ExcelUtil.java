package com.lanxi.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {
	/**工作本*/
	private static HSSFWorkbook  workbook;		
	/**标题样式*/
	private static HSSFCellStyle titleStyle;	
	/**标题字体*/
	private static HSSFFont		titleFont;		
	/**普通样式*/
	private  static HSSFCellStyle commonStyle;	
	/**普通字体*/
	private static HSSFFont		commonFont;		
	/**数据格式-小数*/
	private static HSSFDataFormat doubleFormat;	
	/**加载初始化*/
	static{
		init();
	}
	/**
	 * 初始化工作簿
	 */
	public static void init(){
		workbook = new HSSFWorkbook();
		
		titleFont	=	workbook.createFont();
		titleFont.setFontHeightInPoints((short) 16);					//标题字体字号
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);	//标题字体加粗
		
		titleStyle	=	workbook.createCellStyle();				
		titleStyle.setFont(titleFont);							
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);				//标题上下居中
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//标题左右居中
		titleStyle.setFillBackgroundColor(HSSFColor.BRIGHT_GREEN.index);;	//设置背景颜色为浅绿

		
		commonFont	=	workbook.createFont();
		commonFont.setFontHeightInPoints((short) 14);				//普通字体字号
		
		commonStyle	=	workbook.createCellStyle();
		commonStyle.setFont(commonFont);									
		commonStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);				//普通上下居中
		commonStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//普通左右居中
	}
	/**
	 * 设置页名
	 * @param name
	 */
	public static void setSheetName(int index,String name){
		workbook.setSheetName(0, name);
	}
	
	public static HSSFCellStyle getCommonStyle(){
		return commonStyle;
	}
	public static HSSFCellStyle getTitleStyle(){
		return titleStyle;
	}
	/**
	 * 根据索引获取页
	 * @param index
	 * @return
	 */
	public static HSSFSheet getSheet(int index){
		if(workbook==null)
			init();
		int count=workbook.getNumberOfSheets();
		if(index>=count)
			for(int i=count;i<=index;i++)
				workbook.createSheet();
		return workbook.getSheetAt(index);
	}
	/**
	 * 根据索引获取行
	 * @param sheetIndex
	 * @param rowIndex
	 * @return
	 */
	public static HSSFRow getRow(int sheetIndex,int rowIndex){
		HSSFSheet sheet=getSheet(sheetIndex);
		int count=sheet.getLastRowNum();
		if(rowIndex>=count)
			for(int i=count;i<=rowIndex;i++)
				sheet.createRow(i);
		return sheet.getRow(rowIndex);
	}
	/**
	 * 根据索引获取 单元格
	 * @param sheetIndex
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 */
	public static HSSFCell getCell(int sheetIndex,int rowIndex,int cellIndex){
		HSSFRow row=getRow(sheetIndex, rowIndex);
		int count =row.getLastCellNum();
		if(count==-1)
			count=0;
		if(cellIndex>=count)
			for(int i=count;i<=cellIndex;i++)
				row.createCell(i);
		return row.getCell(cellIndex);
	}
	/**
	 * 根据参数数量获取内容
	 * 1个 获取sheet
	 * 2个获取row
	 * 3个获取cell
	 * @param sheetIndex
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 */
	public static Object get(Integer sheetIndex,Integer rowIndex,Integer cellIndex){
		if(sheetIndex!=null&&rowIndex==null&&cellIndex==null)
			return getSheet(sheetIndex);
		else if(sheetIndex!=null&&rowIndex!=null&&cellIndex==null)
			return getRow(sheetIndex, rowIndex);
		else if(sheetIndex!=null&&rowIndex!=null&&cellIndex!=null)
			return getCell(sheetIndex, rowIndex, cellIndex);
		else
			return null;
	}
	/**
	 * 将指定表中的旧字符串替换为新字符串
	 * @param sheetIndex
	 * @param oldStr
	 * @param newStr
	 */
	public static void sheetReplace(HSSFSheet sheet,String oldStr,String newStr){
		if(oldStr==null||oldStr.trim().isEmpty()||newStr==null)
			return;
		for(int i=0;i<sheet.getLastRowNum();i++)
			rowReplace(sheet.getRow(i), oldStr, newStr);

	}
	/**
	 * 将行中的旧字符串替换为新字符串
	 * @param row
	 * @param oldStr
	 * @param newStr
	 */
	public static void rowReplace(HSSFRow row,String oldStr,String newStr){
		for(int j=0;j<row.getLastCellNum();j++)
			cellReplace(row.getCell(j), oldStr, newStr);
	}
	/**
	 * 将单元格中的旧字符串替换为新字符串
	 * @param cell
	 * @param oldStr
	 * @param newStr
	 */
	public static void cellReplace(HSSFCell cell,String oldStr,String newStr){
		String str=cell.getStringCellValue();
		cell.setCellValue(str.equals(oldStr)?newStr:str);
	}
	/**
	 * 生成excel文件
	 * 若path=null 	->	生成到classPath下 	年月日时分秒4位随机数.xls
	 * 若path为目录	->	生成到目录下	年月日时分秒4位随机数.xls
	 * 若path为文件	->	生成内容到该文件中
	 * @param path
	 */
	public static void generatorExcel(String path){
		if(path==null)
			path=ExcelUtil.class.getResource("/").getPath();
		try {
			File file=new File(path);
			File excel=null;
			if(file.isDirectory())
				excel=new File(path+"/"+TimeUtil.getDateTime()+RandomUtil.getRandomNumber(4)+".xls");
			else
				excel=file;
			workbook.write(excel);
		} catch (IOException e) {
			throw new AppException("构建excel文件异常",e);
		}
	}
}
