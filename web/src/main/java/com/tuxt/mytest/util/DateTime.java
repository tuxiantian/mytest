package com.tuxt.mytest.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.util.GregorianCalendar;


/**
 * <p>Title: Asiainfo Portal System</p>
 * <p>Description: </p>
 * <p>日期时间公共方法类</p>
 * <p>该类提供了日期格式,处理等功能。</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Asiainfo Technologies (China),Inc.HangZhou</p>
 * @author Asiainfo PSO/yuanjq
 * @version 1.0
 */
public class DateTime {
    /**
     * 得到当前日期，格式yyyy-MM-dd。
     * FrameWork使用
     * @return String 格式化的日期字符串
     */
    public static String getCurrDate()throws Exception {
        return getFormattedDate(getDateByString(""));
    }

    /**
     * 对输入的日期字符串进行格式化,
     * 如果输入的是0000/00/00 00:00:00则返回空串.
     * FrameWork使用
     * @param strDate String 需要进行格式化的日期字符串
     * @param strFormatTo String 要转换的日期格式
     * @return String 经过格式化后的字符串
     */
    public static String getFormattedDate(String strDate, String strFormatTo) {
        if ((strDate == null) || strDate.trim().equals("")) {
            return "";
        }
        strDate = strDate.replace('/', '-');
        strFormatTo = strFormatTo.replace('/', '-');
        if (strDate.equals("0000-00-00 00:00:00") ||
            strDate.equals("1800-01-01 00:00:00")) {
            return "";
        }
        String formatStr = strFormatTo; //"yyyyMMdd";
        if ((strDate == null) || strDate.trim().equals("")) {
            return "";
        }
        switch (strDate.trim().length()) {
        case 6:
            if (strDate.substring(0, 1).equals("0")) {
                formatStr = "yyMMdd";
            } else {
                formatStr = "yyyyMM";
            }
            break;
        case 8:
            formatStr = "yyyyMMdd";
            break;
        case 10:
            if (strDate.indexOf("-") == -1) {
                formatStr = "yyyy/MM/dd";
            } else {
                formatStr = "yyyy-MM-dd";
            }
            break;
        case 11:
            if (strDate.getBytes().length == 14) {
                formatStr = "yyyy年MM月dd日";
            } else {
                return "";
            }
            break;
        case 14:
            formatStr = "yyyyMMddHHmmss";
            break;
        case 19:
            if (strDate.indexOf("-") == -1) {
                formatStr = "yyyy/MM/dd HH:mm:ss";
            } else {
                formatStr = "yyyy-MM-dd HH:mm:ss";
            }
            break;
        case 21:
            if (strDate.indexOf("-") == -1) {
                formatStr = "yyyy/MM/dd HH:mm:ss.S";
            } else {
                formatStr = "yyyy-MM-dd HH:mm:ss.S";
            }
            break;
        default:
            return strDate.trim();
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(strDate));
            formatter = new SimpleDateFormat(strFormatTo);
            return formatter.format(calendar.getTime());
        } catch (Exception e) {
            //Common.printLog("转换日期字符串格式时出错;" + e.getMessage());
            return "";
        }
    }

    /**
     * 根据传入的日期字符串转换成相应的日期对象，
     * 如果字符串为空或不符合日期格式，则返回当前时间。
     * FrameWork使用
     * @param strDate String 日期字符串
     * @return java.sql.Timestamp 日期对象
     * */
    public static Timestamp getDateByString(String strDate)throws Exception {
        if (strDate.trim().equals("")) {
            return getCurrentDate();
        }
        try {
            strDate = getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss") +
                      ".000000000";
            return Timestamp.valueOf(strDate);
        } catch (Exception ex) {
            return getCurrentDate();
        }
    }
    public static Timestamp formatDateTimeStr(String strDate)throws Exception {
        if (strDate.trim().equals("")) {
            return getCurrentDate();
        }
        try {
            strDate = getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss");
            return Timestamp.valueOf(strDate);
        } catch (Exception ex) {
            return getCurrentDate();
        }
    }
    //获取当前数据库时间
    public static Timestamp getCurrentDate()throws Exception{
        return new Timestamp(System.currentTimeMillis());
    }
    
    /**
     * 对输入的日期按照默认的格式yyyy-MM-dd转换.
     * FrameWork使用
     * @param dtDate java.sql.Timestamp 需要进行格式化的日期字符串
     * @return String 经过格式化后的字符串
     */
    public static String getFormattedDate(Timestamp dtDate) {
        return getFormattedDate(dtDate, "yyyy-MM-dd");
    }

    /**
     * 对输入的日期进行格式化, 如果输入的日期是null则返回空串.
     * FrameWork使用
     * @param dtDate java.sql.Timestamp 需要进行格式化的日期字符串
     * @param strFormatTo String 要转换的日期格式
     * @return String 经过格式化后的字符串
     */
    public static String getFormattedDate(Timestamp dtDate,
                                          String strFormatTo) {
        if (dtDate == null) {
            return "";
        }
        if (dtDate.equals(new Timestamp(0))) {
            return "";
        }
        strFormatTo = strFormatTo.replace('/', '-');
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
            if (Integer.parseInt(formatter.format(dtDate)) < 1900) {
                return "";
            } else {
                formatter = new SimpleDateFormat(strFormatTo);
                return formatter.format(dtDate);
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 得到当前日期时间,格式为yyyy-MM-dd hh:mm:ss.
     * FrameWork使用
     * @return String
     */
    public static String getCurrDateTime() throws Exception{
        Timestamp date = getCurrentDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    /**add by liufeng 20061111
     * 得到当前日期时间,格式为yyyyMMddhhmmss.
     * FrameWork使用
     * @return String
     */
    public static String getCurrDateTime_yyyymmddhhmmss()throws Exception {
        Timestamp date = getCurrentDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(date);
    }

    /**
     * 返回两个日期之间隔了多少天
     * @param date1
     * @param date2
     */
    public static int DateDiff(Date date1, Date date2) {
        int i = (int) ((date1.getTime() - date2.getTime()) / 3600 / 24 / 1000);
        return i;
    }

    /**
     * 月份相加 add by yuanjq
     * @param timest1
     * @param month
     * @return
     */
    public static Timestamp DateAddMonth(Timestamp timest1, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timest1);
        cal.add(Calendar.MONTH,month);
        return new Timestamp(cal.getTimeInMillis());
    }
    /**
     * 当前日期增加iDays天后日期 add by liufeng 20061208
     * @param strDate String
     * @param iDays int
     * @param strFormatTo String
     * @return String
     */
    public static String getDateAddDay(String strDate, int iDays,String strFormatTo) {
        Timestamp tsDate = Timestamp.valueOf(strDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(tsDate);
        cal.add(Calendar.DAY_OF_MONTH,iDays);
        Timestamp tsEndDateAdd = new Timestamp(cal.getTimeInMillis());
        return DateTime.getFormattedDate(tsEndDateAdd,strFormatTo);
    }
    
    /**
     * 当前日期增加或减去minute分钟后日期 add by zhourh 2009-08-20
     * @param dateTime
     * @param minute
     * @return
     */
    public static Timestamp getDateAddMinute(Timestamp dateTime, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.add(Calendar.MINUTE,minute);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 得到本月最后一天 add by yuanjq
     * @param timest1
     * @return
     */
    public static Timestamp getLastDayOfMonth(Timestamp timest1) {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(timest1);
        cal.add(Calendar.MONTH,1);
        cal.set(Calendar.DAY_OF_MONTH,0);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 得到今天日期，格式yyyy-MM-dd。
     * @return String 格式化的日期字符串
     */
    public static String getToday() {
        Date cDate = new Date();
        SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return cSimpleDateFormat.format(cDate);
    }

    /**
     * 得到昨天日期，格式yyyy-MM-dd。
     * @return String 格式化的日期字符串
     */
    public static String getYesterday() {
        Date cDate = new Date();
        cDate.setTime(cDate.getTime() - 24 * 3600 * 1000);
        SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return cSimpleDateFormat.format(cDate);
    }

    /**
     * 得到明天日期，格式yyyy-MM-dd。
     * @return String 格式化的日期字符串
     */
    public static String getTomorrow() {
        Date cDate = new Date();
        cDate.setTime(cDate.getTime() + 24 * 3600 * 1000);
        SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return cSimpleDateFormat.format(cDate);
    }

    /**
     * 得到指定的日期，如一年三个月零九天后(以yyyy/MM/dd格式显示)参数为("yyyy/MM/dd",1,3,9)
     * @param strFormat
     * @param iYear
     * @param iMonth
     * @param iDate
     * @return
     */
    public static String getSpecTime(String strFormat, int iYear, int iMonth,
                                     int iDate, int iHour, int iMinute,
                                     int iSecond) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.set(Calendar.YEAR, rightNow.get(Calendar.YEAR) + iYear);
        rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH) + iMonth);
        rightNow.set(Calendar.DATE, rightNow.get(Calendar.DATE) + iDate);
        rightNow.set(Calendar.HOUR, rightNow.get(Calendar.HOUR) + iHour);
        rightNow.set(Calendar.MINUTE, rightNow.get(Calendar.MINUTE) + iMinute);
        rightNow.set(Calendar.SECOND, rightNow.get(Calendar.SECOND) + iSecond);
        SimpleDateFormat df = new SimpleDateFormat(strFormat);
        return df.format(rightNow.getTime());
    }

    /**
     * 得到当前日期格式yyyyMM转换。
     * @return String 经过格式化后的字符串
     */
    public static String getCurrentYearMonth() throws Exception{
        return getYearMonth(getCurrentDate());
    }

    /**
     * 对输入的日期进行默认的格式yyyyMM转换。
     * @return String 经过格式化后的字符串
     */
    public static String getYearMonth(Timestamp dtDate) {
        return getFormattedDate(dtDate, "yyyyMM");
    }

    /**
     * 渠道使用
     * 修改时间 chg by liufeng 20050714 修改为年、月、日+传入的时、分、秒  统一去掉以前的时、分、秒
     * @param strDate String
     * @param strFormat String
     * @param iDiffYear int
     * @param iDiffMonth int
     * @param iDiffDay int
     * @param iDiffHour int
     * @param iDiffMinute int
     * @param iDiffSecond int
     * @return String
     */
    public static String changeDate(String strDate, String strFormat,
                                    int iDiffYear, int iDiffMonth, int iDiffDay,
                                    int iDiffHour, int iDiffMinute,
                                    int iDiffSecond) {
        String strChangedDay = "";
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        strChangedDay += strDate.substring(0, 10) + " " + iDiffHour + ":" +
                iDiffMinute + ":" + iDiffSecond;
        return strChangedDay;
    }
    /**
        * add by liufeng 20061031 strFormat值如："yyyy-MM-dd HH:mm:ss"
        * @param strDate String
        * @param strFormat String
        * @return boolean
        */
       public static boolean isValidDataTime(String strDate,String strFormat){
           if(strDate == null || strDate.equals("")){
               return false;
           }
           if(strFormat == null || strFormat.equals("")){
               return false;
           }
           if(strDate.length() != strFormat.length()){
               return false;
           }
           try {
               SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
               formatter.parse(strDate);
           } catch (ParseException ex) {
               return false;
           }

           String strTemp = getFormattedDate(strDate,strFormat);
           if(strTemp.equals(strDate)){
               return true;
           }else{
               return false;
           }
    }
    /**add by liufeng 20061122
       * 得到当前时间（前或后）iDays天的日期
       * @param strFormat String　日期格式比如　yyyy-MM-dd
       * @param iDays int
       * @return String
       */
      public static String getCurenDayAddDay(String strFormat,int iDays) {
          Calendar c = new GregorianCalendar();
          c.add(Calendar.DAY_OF_MONTH,iDays);
          Date cDate = new Date();
          cDate.setTime(c.getTimeInMillis());
          SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat(strFormat);
          return cSimpleDateFormat.format(cDate);
    }
      
    /**
     * add by liufeng 20071126
     * 得到当前时间前(后)几个月的第一天的日期
     * @return
     */  
    public static String getMonthFrtDate(int iMonth,String strFormat){
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.MONTH,iMonth);
    	Date cDate = new Date();
    	cDate.setTime(cal.getTimeInMillis());        
        SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat(strFormat);
        String strNewDate = cSimpleDateFormat.format(cDate); 
        return strNewDate.subSequence(0, 8) + "01";
    }

	public static String getStringTimestamp( Timestamp timestamp, String strFormat ) throws ParseException {
		if(timestamp==null){
			return "";
		}
		String strTimestamp=getStringDate( (Date)timestamp, strFormat );
		return strTimestamp;
	}

	public static String getStringDate( Date date, String strFormat ) throws ParseException {
		if(date==null){
			return "";
		}
		SimpleDateFormat format=new SimpleDateFormat( strFormat );
		String strDate=format.format(date);
		
		return strDate;
	}
	
	
	/**将日期字符串转换为日期
	 * 
	 * @param strDate
	 * @param mask
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp convertStringToTimestamp(String strDate,String mask) throws ParseException{
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(mask);
		try {
			date = df.parse(strDate);
			return new Timestamp(date.getTime());
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
	}
//	public static int monthDiffToNow(String dateStr, String format) throws Exception
//	{
//		 Timestamp currDate = DateTime.getCurrentDate();
//		 Timestamp date = convertStringToTimestamp(dateStr,format);
//		
//		return currDate.getYear()*12-date.getYear()*12+currDate.getMonth()-date.getMonth();
//	}
	
	  /**
     * 当前日期增加或减去minute分钟后日期 add by zhourh 2009-08-20
     * @param dateTime
     * @param minute
     * @return
     */
    public static Timestamp getDateAdd(Timestamp dateTime, int CalendarType,  int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.add(CalendarType,minute);
        return new Timestamp(cal.getTimeInMillis());
    }
    
    
    
    public static String getLastDayOfMonth(Timestamp dateTime, String format) throws ParseException{
       String time =	getYearMonth(dateTime);
       Timestamp temp =   convertStringToTimestamp(time,"yyyyMM");
    	  Calendar cal = Calendar.getInstance();
          cal.setTime(temp);
          cal.add(Calendar.MONTH,1);
          cal.add(Calendar.DAY_OF_YEAR, -1);
          
          SimpleDateFormat formatter = new SimpleDateFormat(format);
          return formatter.format(cal.getTime());
    }
    /**
     * 当前日期增加加上小时候后日期 add by xyf 2014-08-20
     * @param dateTime
     * @param minute
     * @return
     */
    public static String getDateAddHours(int hours) {
    	   Date cDate = new Date();
           cDate.setTime(cDate.getTime() + hours * 3600 * 1000);
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           return formatter.format(cDate);
    }
    
}
