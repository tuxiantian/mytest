package com.tuxt.mytest.excle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InitWeekDate {

	private static String[] developers = {"郭雪超","李攀","李朝勇","赵亮","张国东","张刚强","桑坤","王海洋","周源","高攀","焦腾达","欧阳春光","涂贤田"};
	private static String templatePath = "E:/template.xlsx";
	private static String workHourPath = "E:/工时/";
	private static int year = 2016;
	
	/**
	 * 获取当前时间所在年的周数
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取当前时间所在年的最大周数
	 * @param year
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

		return getWeekOfYear(c.getTime());
	}

	/**
	 * 获取某年的第几周的开始日期
	 * @param year
	 * @param week 第几周
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getFirstDayOfWeek(cal.getTime());
	}

	/**
	 * 获取某年的第几周的结束日期
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getLastDayOfWeek(cal.getTime());
	}

	/**
	 * 获取当前时间所在周的开始日期
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 获取当前时间所在周的结束日期
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}
	/**
	 * 获取一个时间上周的日期
	 * @param date
	 * @return
	 */
	public static Date getDayOfLastWeek(Date date){
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR)-7);
		return c.getTime();
	}
	
	/**
	 * 获取一年52个周每周的第一天日期和最后一天日期存入二维数组中
	 * @return
	 */
	public static String [][] getWeekDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
		Date today = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(today);

		/*System.out.println("当前时间:current date = " + sdf.format(today));
		System.out.println("当前第几周:getWeekOfYear = " + getWeekOfYear(today));
		System.out.println("年度总周数:getMaxWeekNumOfYear = " + getMaxWeekNumOfYear(year));
		System.out.println("本周第一天:getFirstDayOfWeek = " + sdf.format(getFirstDayOfWeek(year, week)));
		System.out.println("本周最后一天:getLastDayOfWeek = " + sdf.format(getLastDayOfWeek(year, week)));
		System.out.println("本周第一天:getFirstDayOfWeek = " + sdf.format(getFirstDayOfWeek(today)));
		System.out.println("本周最后一天:getLastDayOfWeek = " + sdf.format(getLastDayOfWeek(today)));*/
		String [][] weekDate=new String[52][2];
		//System.out.println(weekDate.length);
		int weekCount=getMaxWeekNumOfYear(year);
		for (int i = 1; i <=weekCount ; i++) {
			weekDate[i-1][0]=sdf.format(getFirstDayOfWeek(year, i));
			weekDate[i-1][1]=sdf.format(getLastDayOfWeek(year, i));
			//System.out.println("第"+i+"周"+"第一天："+sdf.format(getFirstDayOfWeek(year, i))+"最后一天："+sdf.format(getLastDayOfWeek(year, i)));
		}
		/*for (int i = 0; i < weekDate.length; i++) {
			System.out.println("第"+(i+1)+"周"+"第一天："+weekDate[i][0]+"最后一天："+weekDate[i][1]);
		}*/
		return weekDate;
	}
	/**
	 * 将文件从一个地方拷贝到另一个地方
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFile(String oldPath, String newPath) { 
		try { 
			int byteread = 0; 
			File oldfile = new File(oldPath); 
			if (oldfile.exists()) { //文件存在时 
				InputStream inStream = new FileInputStream(oldPath); //读入原文件 
				FileOutputStream fs = new FileOutputStream(newPath); 
				byte[] buffer = new byte[1444]; 
				while ( (byteread = inStream.read(buffer)) != -1) { 
					fs.write(buffer, 0, byteread); 
				} 
				inStream.close(); 
				fs.close();
			} 
		} 
		catch (Exception e) { 
			System.out.println("复制单个文件操作出错"); 
			e.printStackTrace(); 
		} 

	} 
	/**
	 * 根据工时模板生成所有开发人员的工作簿
	 */
	public static void copyExcleFile(){
		for (int i = 0; i < developers.length; i++) {
			copyFile(templatePath, workHourPath+developers[i]+".xlsx");
		}
	}
	/**
	 * 初始化每周的起始和结束日期到工时工作簿
	 */
	public static void createTemplate(){
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream=new FileInputStream(new File(templatePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		XSSFWorkbook hwb = null;
		try {
			hwb = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet sheet1 = hwb.getSheetAt(0);
		String [][] weekDate=getWeekDate();
		int count=0;
		try {
			for (int i = 1; i <= weekDate.length; i++) {
				count=i;
				sheet1.getRow(i).getCell(0).setCellValue(weekDate[i - 1][0]);
				sheet1.getRow(i).getCell(1).setCellValue(weekDate[i - 1][1]);
			}
		} catch (Exception e) {
			System.err.println(count);
		}
		try {
			outputStream = new FileOutputStream(templatePath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			hwb.write(outputStream);
			outputStream.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//copyExcleFile();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(sdf.format(getDayOfLastWeek(date)));
		System.out.println(sdf.format(getFirstDayOfWeek(getDayOfLastWeek(date))));
		System.out.println(getWeekOfYear(getDayOfLastWeek(date)));
	}

}
