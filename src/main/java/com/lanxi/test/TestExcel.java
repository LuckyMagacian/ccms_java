package com.lanxi.test;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.lanxi.common.ExcelUtil;

public class TestExcel {

	
	@Test
	public void testSheet(){
		System.out.println(ExcelUtil.getSheet(0));
		System.out.println(ExcelUtil.getSheet(0));
		System.out.println(ExcelUtil.getSheet(1));
		System.out.println(ExcelUtil.getSheet(1));
		System.out.println(ExcelUtil.getSheet(3));
		System.out.println(ExcelUtil.getSheet(3));
		System.out.println(ExcelUtil.getSheet(0));
		System.out.println(ExcelUtil.getSheet(0));
		System.out.println(ExcelUtil.getSheet(1));
		System.out.println(ExcelUtil.getSheet(1));
	}
	
	@Test
	public void testRow(){
		System.out.println(ExcelUtil.getRow(0, 0));
		System.out.println(ExcelUtil.getRow(0, 0));
		System.out.println(ExcelUtil.getRow(0, 3));
		System.out.println(ExcelUtil.getRow(0, 3));
		System.out.println(ExcelUtil.getRow(0, 4));
		System.out.println(ExcelUtil.getRow(0, 4));
		System.out.println(ExcelUtil.getRow(0, 0));
		System.out.println(ExcelUtil.getRow(0, 0));
		System.out.println(ExcelUtil.getRow(0, 3));
		System.out.println(ExcelUtil.getRow(0, 3));
	}
	@Test
	public void testCell(){
		System.out.println(ExcelUtil.getRow(0, 0));
		System.out.println(ExcelUtil.getCell(0, 0, 0).hashCode());
		System.out.println(ExcelUtil.getCell(0, 0, 0).hashCode());
		System.out.println(ExcelUtil.getCell(0, 0, 3).hashCode());
		System.out.println(ExcelUtil.getCell(0, 0, 3).hashCode());
		System.out.println(ExcelUtil.getCell(0, 2, 0).hashCode());
		System.out.println(ExcelUtil.getRow(0, 2));
		System.out.println(ExcelUtil.getCell(0, 2, 0).hashCode());
		System.out.println(ExcelUtil.getRow(0, 0));
		System.out.println(ExcelUtil.getCell(0, 0, 0).hashCode());
		System.out.println(ExcelUtil.getCell(0, 0, 0).hashCode());
		System.out.println(ExcelUtil.getCell(0, 0, 3).hashCode());
		System.out.println(ExcelUtil.getCell(0, 0, 3).hashCode());
	}
	
	@Test
	public void testExcel() throws IOException{
		
		//number=sheet count & number >sheet count
		//没有sheet number=0  1个sheet number=1 两个sheet number=2
		HSSFWorkbook workbook=new HSSFWorkbook();
//		System.out.println(workbook.getNumberOfSheets());
		workbook.createSheet();
//		System.out.println(workbook.getNumberOfSheets());
		workbook.createSheet();
//		System.out.println(workbook.getNumberOfSheets());
		//可以隔行创建行,未创建的行依然是null,
		//最终行号为创建的行号最大值 没有创建时 最大行号为0
		HSSFSheet sheet=workbook.getSheetAt(0);
		System.out.println(sheet.getLastRowNum());
		sheet.createRow(0);
//		System.out.println(sheet.getRow(5));
		System.out.println(sheet.getLastRowNum());
		sheet.createRow(5);
//		System.out.println(sheet.getRow(5));
//		System.out.println(sheet.getLastRowNum());
		sheet.createRow(2);
		System.out.println(sheet.getRow(5));
//		System.out.println(sheet.getLastRowNum());
		
		HSSFRow row=sheet.getRow(5);
		System.out.println(row.getLastCellNum());
		//必须创建cell
		//没有创建时最大行号为-1 创建1个为1 2个为2
		row.createCell(100);
		HSSFCell cell=row.getCell(0);
		row.createCell(1);
		System.out.println(row.getLastCellNum());
		System.out.println(cell.hashCode());
		workbook.close();
	}
}
