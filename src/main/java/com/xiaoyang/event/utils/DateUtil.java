package com.xiaoyang.event.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	
	public static String getDatePath() {
		Calendar cd = Calendar.getInstance();
		int year = cd.get(Calendar.YEAR);
		String month = "";
		if(cd.get(Calendar.MONTH) + 1<10) {
			month = "0"+(cd.get(Calendar.MONTH) + 1);
		}else {
			month = ""+(cd.get(Calendar.MONTH) + 1);
		}
		String day = "";
		if(cd.get(Calendar.DATE)<10) {
			day= "0"+cd.get(Calendar.DATE);
		}else {
			day = ""+cd.get(Calendar.DATE);
		}
	    String dayPath = month+day;
		String dirPath=year+"/"+dayPath+"/";
		return dirPath;
	}
	
	public static String getTimeByMin(String m) {
    	int index = m.lastIndexOf(".");
    	if(index!=-1) {
    		m = m.substring(0,index);
    	}
    	Integer times = Integer.parseInt(m);
    	if(times>60) {
    		int second = times % 60;  
    		int min = times / 60;
    		m = min + "分" + second + "秒";  
    		if( min > 60 ){  
    			min = times / 60 % 60;  
 		        int hour = times / 60 / 60 ;
 		        m = hour + "小时" + min + "分" + second + "秒";  
 		        if( hour > 24 ){  
 		        	hour = times / 60 /60  % 24;  
		            int day = times / 60 /60  / 24 ;  
		            m = day + "天" + hour + "小时" + min + "分" + second + "秒";  
 		       }
    		}
    	} 
    	return m;
	}
	
	public static String getServerTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	/**
	 * 把服务器时间转字符串，做图片路径使用
	 * 
	 * @param template 
	 * 				转换格式
	 * 
	 * @return 时间字符串			
	 */
	public static String getServerTime(String template) {
		return new SimpleDateFormat(template).format(new Date());
	}

	public static boolean isDateTime(String arg, String template) {
		DateFormat df = new SimpleDateFormat(template);
		try {
			Date date = df.parse(arg);
			return df.format(date).equals(arg);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int diffDate(Date d1, Date d2) {
		if (null == d1 || null == d2) {
			return -1;
		}
		return (int) (d1.getTime() - d2.getTime()) / 86400000;
	}

	public static String getDisplayInfo(int day) {
		String info = "";
		if (day > 30) {
			int result = day / 30;
			if (result < 2) {
				info = "一个月后";
			} else if (result < 3) {
				info = "二个月后";
			} else if (result < 6) {
				info = "三个月后";
			} else if (result < 9) {
				info = "六个月后";
			} else {
				info = "九个月后";
			}
		} else {
			info = day + "天后";
		}
		return info;
	}

	public static int daysOfTwo(Date fDate, Date oDate) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(fDate);
		int year1 = aCalendar.get(Calendar.YEAR);
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(oDate);
		int year2 = aCalendar.get(Calendar.YEAR);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		int addDay = 0;
		if (year1 != year2) {
			addDay = (year2 - year1) * 365;
		}
		return day2 - day1 + addDay;
	}

	public String getHexString(String str) {
		String newName = new String();
		for (int i = 0; i < str.length(); ++i) {
			if (Character.toString(str.charAt(i)).matches("[0-9a-zA-Z\\._\\-]"))
				newName = newName + str.charAt(i);
			else {
				newName = newName + Integer.toHexString(str.charAt(i));
			}
		}
		return newName;
	}

	public static Date stringToDate(String str, String pattner)
			throws ParseException {
		return new SimpleDateFormat(pattner).parse(str);
	}

	public static String formatDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	public  String formatDate1(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 注意会获得服务器系统小时
	 * 
	 * @return
	 */
	public static Calendar getFirstDayOfMonth() {

		Calendar curCal = Calendar.getInstance();
		curCal.set(Calendar.HOUR_OF_DAY, 0);
		curCal.set(Calendar.MINUTE, 0);
		curCal.set(Calendar.DAY_OF_MONTH, 1);
		return curCal;
	}

	/**
	 * 注意会获得服务器系统小时
	 * 
	 * @return
	 */
	public static Calendar getLastDayOfMonth() {
		Calendar curCal = Calendar.getInstance();
		curCal.set(Calendar.HOUR_OF_DAY, 23);
		curCal.set(Calendar.MINUTE, 59);
		curCal.set(Calendar.DATE, 1);
		curCal.roll(Calendar.DATE, -1);
		return curCal;
	}

	private static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 两时间对比, 相差是否超过3秒
	 * 
	 * @param dateA
	 * @param dateB
	 * @return
	 * @throws ParseException
	 */
	public static boolean compare(String dateA, String dateB)
			throws ParseException {
		DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
		return Math.abs(df.parse(dateA).getTime() - df.parse(dateB).getTime()) > 3000;
	}

	public static java.sql.Date toSqlDate(Calendar c) {
		return new java.sql.Date(c.getTimeInMillis());
	}

	public static Calendar getFirstDayOfWeek() {
		Calendar monday = Calendar.getInstance();
		monday.set(Calendar.HOUR_OF_DAY, 0);
		monday.set(Calendar.MINUTE, 0);
		return getADayOfWeek(monday, Calendar.MONDAY);
	}	
	public static Calendar getAfterDay(int day) {
		Calendar monday = Calendar.getInstance();
		monday.set(Calendar.HOUR_OF_DAY, 0);
		monday.set(Calendar.MINUTE, 0);
		monday.add(Calendar.DATE, day);
		return monday;
	}

	public static Calendar getLastDayOfWeek() {
		Calendar sunday = Calendar.getInstance();
		sunday.set(Calendar.HOUR_OF_DAY, 23);
		sunday.set(Calendar.MINUTE, 59);

		return getADayOfWeek(sunday, Calendar.SUNDAY);
	}

	private static Calendar getADayOfWeek(Calendar day, int dayOfWeek) {
		int week = day.get(Calendar.DAY_OF_WEEK);
		if (week == dayOfWeek)
			return day;
		int diffDay = dayOfWeek - week;
		if (week == Calendar.SUNDAY) {
			diffDay -= 7;
		} else if (dayOfWeek == Calendar.SUNDAY) {
			diffDay += 7;
		}
		day.add(Calendar.DATE, diffDay);
		return day;
	}
	  public static int daysBetween(Date smdate,Date bdate) throws ParseException    
	    {    
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        smdate=sdf.parse(sdf.format(smdate));  
	        bdate=sdf.parse(sdf.format(bdate));  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(smdate);    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(bdate);    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days));           
	    }    
	  
	  /** 
	   * 金额格式化 
	   * @param s 金额 
 	   * @param len 小数位数 
	   * @return 格式后的金额 
	   */
	  public static String insertComma(String s, int len) { 
	      if (s == null || s.length() < 1) { 
	          return ""; 
	      } 
	      NumberFormat formater = null; 
	      double num = Double.parseDouble(s); 
	      if (len == 0) { 
	          formater = new DecimalFormat("###,###"); 
	   
	      } else { 
	          StringBuffer buff = new StringBuffer(); 
	          buff.append("###,###."); 
	          for (int i = 0; i < len; i++) { 
	              buff.append("#"); 
	          } 
	          formater = new DecimalFormat(buff.toString()); 
	      } 
	      return formater.format(num); 
	  } 
	  
	  /** 
	   * 金额去掉“,” 
	   * @param s 金额 
	   * @return 去掉“,”后的金额 
	   */
	  public static String delComma(String s) { 
	      String formatString = ""; 
	      if (s != null && s.length() >= 1) { 
	          formatString = s.replaceAll(",", ""); 
	      } 
	   
	      return formatString; 
	  } 
	  
	  /**
	   * 获取当前月的所有日期
	   * @return
	   * @throws ParseException 
	   */
	  @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<List> getAllMouthDay() throws ParseException {
		  SimpleDateFormat format = new SimpleDateFormat("dd"); 
		  SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Calendar c = Calendar.getInstance();
		  List<List> listAll = new ArrayList<List>();
		  String months = "";
	      String days = "";
		  for(int i=0;i<=30;i++) {
	    	  c.setTime(new Date());
		      c.set(Calendar.DATE, c.get(Calendar.DATE)-30+i);
		      int year = c.get(Calendar.YEAR); 
			  int month = c.get(Calendar.MONTH)+1; 
		      int day = Integer.parseInt(format.format(c.getTime()));
		      List list = new ArrayList();
		      if(day<10) {
	    		  days = "0"+day;
	    	  }else {
	    		  days = day+"";
	    	  }
	    	  if(month<10) {
	    		  months = "0"+month;
	    	  }else {
	    		  months = month+"";
	    	  }
	    	  Date dates = format1.parse(year+"-"+months+"-"+days+" 08:00:00");
	    	  list.add(dates.getTime());
	    	  list.add(days);
	    	  listAll.add(list);
	      }
		  return listAll;
	  }
	  
	  @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<List> getAllDayTime() throws ParseException {
		  Calendar c = Calendar.getInstance();
		  c.setTime(new Date());
		  int year = c.get(Calendar.YEAR); 
		  int month = c.get(Calendar.MONTH)+1; 
		  int day = c.get(Calendar.DATE); 
		  SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  List<List> listAll = new ArrayList<List>();
		  String months = "";
	      String days = "";
	      String hours = "";
		  for(int i=8;i<32;i++) {
			  List list = new ArrayList();
			  if(day<10) {
	    		  days = "0"+day;
	    	  }else {
	    		  days = day+"";
	    	  }
	    	  if(month<10) {
	    		  months = "0"+month;
	    	  }else {
	    		  months = month+"";
	    	  }
			  if(i<10) {
				  hours = "0"+i;
			  }else {
				  hours = i+"";
			  }
			  if(i>=24) {
				  if(i==24) {
					  c.setTime(new Date());
					  c.set(Calendar.DATE, day+1);
					  day = c.get(Calendar.DATE);
					  month = c.get(Calendar.MONTH)+1; 
				  }
				  if(day<10) {
		    		  days = "0"+day;
		    	  }else {
		    		  days = day+"";
		    	  }
				  hours = "0"+(i-24);
				  Date dates = format1.parse(year+"-"+months+"-"+days+" "+hours+":00:00");
				  list.add(dates.getTime());
				  list.add(i-8);
			  }else {
				  Date dates = format1.parse(year+"-"+months+"-"+days+" "+hours+":00:00");
				  list.add(dates.getTime());
				  list.add(i-8);
			  }
			  listAll.add(list);
		  }
		  return listAll;
	  }
	  
	  public static String getFirstDay() {
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		  Calendar c = Calendar.getInstance();    
		  c.add(Calendar.MONTH, 0);
		  c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		  String firstDay = format.format(c.getTime());
		  return firstDay;
	  }
	  
	  public static String getLastDay() {
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		  Calendar c = Calendar.getInstance();    
		  c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));  
		  String lastDay = format.format(c.getTime());
		  return lastDay;
	  }
	  
	  /**
	   * 计算时间
	   * @param date 需要计算的时间
	   * @param minute 计算的秒数
	   * @param sign 计算符号 0:减 1:加
	   * @return
	   */
	  public static Date calculationTime(Date date, Integer second, Integer sign) {
		  if(sign==0) {
			  date = new Date(date.getTime() - second*1000);
		  }else {
			  date = new Date(date.getTime() + second*1000);
		  }
		  return date;
	  }
	  
	/* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(long time){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        res = simpleDateFormat.format(date);
        return res;
    }
    
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate1(long time){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(time);
        res = simpleDateFormat.format(date);
        return res;
    }
}
