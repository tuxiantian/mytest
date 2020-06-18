package com.tuxt.mytest.excle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkHourCaculate {

	private static String resultPath="E:/test/2_result.xlsx";//指定统计结果文件
	private static String staticsPath="E:/test/workhour/";//指定统计工时目录
	
	public static XSSFWorkbook getXSSFWorkbook(String filePath) {
		InputStream tmpIn = null;
		File excleFile=new File(filePath);
		try {
			tmpIn=new FileInputStream(excleFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		XSSFWorkbook xwb = null;
		try {
			xwb = new XSSFWorkbook(tmpIn);
			tmpIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xwb;
	}
	public static void saveXSSFWorkbook(String resultPath,XSSFWorkbook xwb) {
		OutputStream out = null;
		try {
			out = new FileOutputStream(resultPath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			xwb.write(out);
			out.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public static int getRowNum() {
		Date date=new Date();
		int headPosition=1;
		int row=InitWeekDate.getWeekOfYear(InitWeekDate.getDayOfLastWeek(date))+headPosition;
		return row;
	}
	public static String[] getExcleHeadDate() {
		String[] start2end=new String[2];
		Date now=new Date();
		Date last=InitWeekDate.getDayOfLastWeek(now);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		start2end[0]=sdf.format(InitWeekDate.getFirstDayOfWeek(last));
		start2end[1]=sdf.format(InitWeekDate.getLastDayOfWeek(last));
		return start2end;
	}
	public static int getNotWriteWorkHourPerson() {
		List<String> notWritePerson = null;
		try {
			String[] files =getExcle(staticsPath);
			notWritePerson=new ArrayList<String>();
			for (int i = 0; i < files.length; i++) {
				String person=isWriteWorkHour(staticsPath+files[i]);
				if (person!=null) {
					notWritePerson.add(person);
				}
			}
			System.err.println(notWritePerson.toString()+"未填写工时");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notWritePerson.size();
	}
	public static String isWriteWorkHour(String templePath){
		int row=getRowNum();
		
		XSSFSheet sheet1 =getXSSFWorkbook(templePath).getSheetAt(0);
		String personName=null;
		int workContentColumn=9;
		String workContent=sheet1.getRow(row).getCell(workContentColumn).getStringCellValue();
		if ("".equals(workContent.trim())) {
			personName = getPersonName(templePath);
		}
		return personName;
	}
	/**
	 * 从路径中解析出人名
	 * @param templePath
	 * @return
	 */
	public static String getPersonName(String templePath) {
		String personName;
		personName=templePath.substring(templePath.lastIndexOf("/")+1, templePath.lastIndexOf("."));
		return personName;
	}
	public static String[] getExcle(String path) throws Exception{
		File srcDir = new File(path);
		String[] files = null;
		if(!(srcDir.exists() && srcDir.isDirectory()))
			throw new Exception("目录不存在");
		files =srcDir.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				
				return name.endsWith("xlsx");
			}
		});
		return files;
	}
	public static void caculateAllPerson(XSSFWorkbook xwb) {
		try {
			String[] files =getExcle(staticsPath);
			for (int i = 0; i < files.length; i++) {
				caculateOnePerson(xwb,staticsPath+files[i], i+2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void caculateOnePerson(XSSFWorkbook xwb, String tempPath,int resultRow) {		
		XSSFSheet tempSheet=getXSSFWorkbook(tempPath).getSheetAt(0);
		//XSSFWorkbook xwb=getXSSFWorkbook(resultPath);
		XSSFSheet resultSheet=xwb.getSheetAt(0);
		XSSFRow newRow=resultSheet.createRow(resultRow);
		int row=getRowNum();
		int monday=2,sunday=8;
		double sum=0;
		//输入姓名
		String personName=getPersonName(tempPath);
		newRow.createCell(0,Cell.CELL_TYPE_STRING).setCellValue(personName);
		//拷贝工时
		for (int i = monday; i <= sunday; i++) {
			double temp=tempSheet.getRow(row).getCell(i).getNumericCellValue();
			sum+=temp;
			newRow.createCell(i-1,Cell.CELL_TYPE_NUMERIC).setCellValue(temp);
		}
		//输入总计工时
		int sumColumn=10;
		newRow.createCell(sumColumn,Cell.CELL_TYPE_NUMERIC).setCellValue(sum);
		//输入工作内容和备注
		int start=9,end=10;
		for (int i = start; i <=end; i++) {
			newRow.createCell(i-1,Cell.CELL_TYPE_STRING).setCellValue(tempSheet.getRow(row).getCell(i).getStringCellValue());
		}
	}
	public static XSSFWorkbook createExcleHead() {
		XSSFWorkbook xwb= new XSSFWorkbook();
		XSSFSheet sheet=xwb.createSheet();
		XSSFRow firstrRow = sheet.createRow(0);
		XSSFCell firstCell = firstrRow.createCell(0);
		//设置文本水平和垂直方向居中
		XSSFCellStyle style = xwb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		//首行 合并单元格
		int columnCount=10;
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnCount));
		
		//设置字体加粗
		Font sheetFont = xwb.createFont();
		sheetFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(sheetFont);
		String[] start2end=getExcleHeadDate();
		
		firstCell.setCellValue(String.format("亚信%s至%s工时明细", start2end[0],start2end[1]));
		firstCell.setCellStyle(style);
		
				
		XSSFRow secondRow = sheet.createRow(1);

		String[] columnName={"姓名","	星期一","星期二","星期三","星期四","星期五","星期六","星期日","	工作内容","备注","总计"};
		
		int normal=10,workContent=45,remark=25;
		int workContentIndex=8,remarkIndex=9,totalIndex=10,specialColumnCount=3;
		int length=columnName.length-specialColumnCount;
		for (int i = 0; i < length; i++) {
			XSSFCell cell =  secondRow.createCell(i);
			cell.setCellValue(columnName[i]);
			sheet.setColumnWidth(i, normal * 256);
		}
		
		XSSFCell workContentCell =  secondRow.createCell(workContentIndex);
		workContentCell.setCellValue(columnName[workContentIndex]);
		sheet.setColumnWidth(workContentIndex, workContent * 256);
		
		XSSFCell remarkCell =  secondRow.createCell(remarkIndex);
		remarkCell.setCellValue(columnName[remarkIndex]);
		sheet.setColumnWidth(remarkIndex, remark * 256);
		
		XSSFCell totalCell=secondRow.createCell(totalIndex);
		totalCell.setCellValue(columnName[totalIndex]);
		sheet.setColumnWidth(totalIndex, normal * 256);
		return xwb;
	}
	public static void createResultExcle() {
		XSSFWorkbook xwb=createExcleHead();
		caculateAllPerson(xwb);
		//保存工作簿
		saveXSSFWorkbook(resultPath,xwb);
	}
	public static void main(String[] args) {
		//getNotWriteWorkHourPerson();
		//caculateAllPerson();
		//createExcleHead();
		
		if (getNotWriteWorkHourPerson()==0) {
			createResultExcle();
		}
	}
}
