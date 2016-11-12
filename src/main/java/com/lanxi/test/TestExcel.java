package com.lanxi.test;

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
	}
	
	@Test
	public void testRow(){
		System.out.println(ExcelUtil.getRow(0, 0));
		System.out.println(ExcelUtil.getRow(0, 0));
		System.out.println(ExcelUtil.getRow(0, 3));
		System.out.println(ExcelUtil.getRow(0, 3));
		System.out.println(ExcelUtil.getRow(0, 4));
		System.out.println(ExcelUtil.getRow(0, 4));
	}
	@Test
	public void testCell(){
		System.out.println(ExcelUtil.getCell(0, 0, 0).hashCode());
		System.out.println(ExcelUtil.getCell(0, 0, 0).hashCode());
		System.out.println(ExcelUtil.getCell(0, 0, 3).hashCode());
		System.out.println(ExcelUtil.getCell(0, 0, 3).hashCode());
		System.out.println(ExcelUtil.getCell(0, 2, 0).hashCode());
		System.out.println(ExcelUtil.getCell(0, 2, 0).hashCode());
		System.out.println(ExcelUtil.getCell(3, 4, 2).hashCode());
		System.out.println(ExcelUtil.getCell(3, 4, 2).hashCode());
	}
	
}
