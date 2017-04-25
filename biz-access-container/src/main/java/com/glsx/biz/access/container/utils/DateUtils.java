package com.glsx.biz.access.container.utils;

import com.glsx.cloudframework.core.util.DateFormatUtil;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	
	public static final String  DATE_FOMART_TIME = "yyyy-MM-dd HH:mm:ss";  
	/**
	 * 显示日期的格式,yyyy-MM
	 */
	public static final String DATE_YEAE_MONTH = "yyyy-MM";
	
	/**
	 * 获取当前月份的最大天数
	 * @return 返回当前月份的最大天数
	 */
	public static int getMonthMaxDay(){
		Calendar cal = Calendar.getInstance();   
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
	}
	
	/**
	 * 获取当前日期所在的星期属于今年的第几周
	 * @return 返回当前日期所在的星期属于今年的第几周
	 */
	public static int getThisDayWeekNo(){
		GregorianCalendar cal = new GregorianCalendar();
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
	public static Date getNextMinutes(int minutes){
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, minutes);  
		return nowTime.getTime();  
	}
	
	public static String format(String date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2=new SimpleDateFormat(format);
		String newDate="";
		try {
			newDate=sdf2.format(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}
    /**
     * 当前日期增加n个月
     * @param s
     * @param n
     * @return
     */
	public static String addMonth(String simpleDateFormat,String date, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(simpleDateFormat);

			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(date));
			cd.add(Calendar.MONTH, n);//增加一个月

			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return null;
		}
	}

	public static Date string2Date(String dateString) {
		try {
			Format f = new SimpleDateFormat(DATE_FOMART_TIME);
			Date date = (Date) f.parseObject(dateString);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	 //把日期转为字符串  
    public static String date2String(Date date){ 
    	if(null != date){
    		 DateFormat df = new SimpleDateFormat(DATE_FOMART_TIME); 
    	        return df.format(date);
    	}
		return "";
    }  
	public static void main(String[] args){
		System.out.println(addMonth(DATE_YEAE_MONTH,"2014-01-31 09:00:00",1));
	}

	private static final String date_format = "yyyy-MM-dd HH:mm:ss";
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(); 
 
    public static DateFormat getDateFormat()   
    {  
        DateFormat df = threadLocal.get();  
        if(df==null){  
            df = new SimpleDateFormat(date_format);  
            threadLocal.set(df);  
        }  
        return df;  
    }  

    /**
     * 将日期对象转换成 "yyyy-MM-dd HH:mm:ss" 格式
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatDate(Date date) throws ParseException {
        return getDateFormat().format(date);
    }

    /**
     * 将日期串"yyyy-MM-dd HH:mm:ss"转换成 日期对象 格式
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    } 
    
    public static String format(Date date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 日期减少分钟数
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static String minusSeconds(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);

		return DateFormatUtil.toString(calendar.getTime(), DateFormatUtil.pattern19);
	}

	/**
	 * 计算两个时间之间的差值
	 * @param endTime
	 * @param date
	 * @return
	 */
	public static int lastingForDate(Date endTime, Date date) {
		long between = date.getTime() - endTime.getTime();
		return (int) (between/1000);
	}
}
