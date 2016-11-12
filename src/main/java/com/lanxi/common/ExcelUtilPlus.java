package com.lanxi.common;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtilPlus {

	private static Map<String, HSSFWorkbook> workbook;
	
	
}
class ExcelWorkBook{
	private Integer			bookIndex;
	private HSSFWorkbook 	book;
	public Integer getBookIndex() {
		return bookIndex;
	}

	public void setBookIndex(Integer bookIndex) {
		this.bookIndex = bookIndex;
	}

	public HSSFWorkbook getBook() {
		return book;
	}

	public void setBook(HSSFWorkbook book) {
		this.book = book;
	}
	
}
class ExcelSheet{
	private Integer		bookIndex;
	private Integer 	sheetIndex;
	private HSSFSheet 	sheet;
	public Integer getBookIndex() {
		return bookIndex;
	}
	public void setBookIndex(Integer bookIndex) {
		this.bookIndex = bookIndex;
	}
	public Integer getSheetIndex() {
		return sheetIndex;
	}
	public void setSheetIndex(Integer sheetIndex) {
		this.sheetIndex = sheetIndex;
	}
	public HSSFSheet getSheet() {
		return sheet;
	}
	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}
	
}
class ExcelRow{
	private Integer	bookIndex;
	private Integer sheetIndex;
	private Integer rowIndex;
	private HSSFRow row;
	public Integer getBookIndex() {
		return bookIndex;
	}
	public void setBookIndex(Integer bookIndex) {
		this.bookIndex = bookIndex;
	}
	public Integer getSheetIndex() {
		return sheetIndex;
	}
	public void setSheetIndex(Integer sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}
	public HSSFRow getRow() {
		return row;
	}
	public void setRow(HSSFRow row) {
		this.row = row;
	}
}
class ExcleColumn{
	private Integer	bookIndex;
	private Integer sheetIndex;
	private Integer columnIndex;
	private List<HSSFCell> column;
	public Integer getBookIndex() {
		return bookIndex;
	}
	public void setBookIndex(Integer bookIndex) {
		this.bookIndex = bookIndex;
	}
	public Integer getSheetIndex() {
		return sheetIndex;
	}
	public void setSheetIndex(Integer sheetIndex) {
		this.sheetIndex = sheetIndex;
	}
	public Integer getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(Integer columnIndex) {
		this.columnIndex = columnIndex;
	}
	public List<HSSFCell> getColumn() {
		return column;
	}
	public void setColumn(List<HSSFCell> column) {
		this.column = column;
	}
	
	
}
class ExcelCell{
	private Integer	bookIndex;
	private Integer sheetIndex;
	private Integer rowIndex;
	private Integer colIndex;
	private HSSFCell cell;
	public HSSFCell getCell() {
		return cell;
	}
	public void setCell(HSSFCell cell) {
		this.cell = cell;
	}
	public Integer getBookIndex() {
		return bookIndex;
	}
	public void setBookIndex(Integer bookIndex) {
		this.bookIndex = bookIndex;
	}
	public Integer getSheetIndex() {
		return sheetIndex;
	}
	public void setSheetIndex(Integer sheetIndex) {
		this.sheetIndex = sheetIndex;
	}
	public Integer getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}
	public Integer getColIndex() {
		return colIndex;
	}
	public void setColIndex(Integer colIndex) {
		this.colIndex = colIndex;
	}
	
}
