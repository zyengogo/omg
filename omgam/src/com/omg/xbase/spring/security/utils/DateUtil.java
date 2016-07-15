package com.omg.xbase.spring.security.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * ʱ��������
 * @author lz
 * @version 1.0
 */
public class DateUtil {
    private DateUtil() {
    }

    /**
     * ��ʽ���ɣ�2006-08-17
     * @param date Date
     * @return String
     */
    public static String getDateStrYMD(Date date) {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null) {
            str = sdf.format(date);
        }
        return str;
    }
    
    /**
     * ��ʽ���ɣ�20060817
     * @param date Date
     * @return String
     */
    public static String getDateStr(Date date) {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (date != null) {
            str = sdf.format(date);
        }
        return str;
    }

    public static String datesplitbydotTime2String(Date date) {
        SimpleDateFormat datesplitbydotTimeFormat = new SimpleDateFormat(
                "yyyy.MM.dd HH:mm:ss");
        return date != null ? datesplitbydotTimeFormat.format(date) : "";
    }

    /**
     * yyyy-MM-dd hh:mm:ss��ʽ���ַ�ת����Date��ʽ
     * @param str String yyyy-MM-dd hh:mm:ss��ʽ���ַ�
     * @return Date
     */
    public static Date getDateByStrYMDHMS(String str) {
    	if(str==null || "".equals(str.trim())){
    		return null;
    	}
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date t = null;
        try {
            t = formatter.parse(str.trim());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return t;
    }
    
    /**
     * yyyy-MM-dd hh:mm��ʽ���ַ�ת����Date��ʽ
     * @param str String yyyy-MM-dd hh:mm:ss��ʽ���ַ�
     * @return Date
     */
    public static Date getDateByStrYMDHM(String str) {
    	if(str==null || "".equals(str.trim())){
    		return null;
    	}    	
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date t = null;
        try {
            t = formatter.parse(str.trim());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return t;
    }    
    
    /**
     * yyyy-MM-dd hh:mm��ʽ���ַ�ת����Date��ʽ
     * @param str String yyyy-MM-dd hh:mm:ss��ʽ���ַ�
     * @return Date
     */
    public static String getStrByDateYMDHM(Date tDate) {
        String str = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (tDate != null) {
            str = formatter.format(tDate);
        }
        return str;
    }  

    /**
     * yyyy-MM-dd hh:mm��ʽ���ַ�ת����Date��ʽ
     * @param str String yyyy-MM-dd hh:mm:ss��ʽ���ַ�
     * @return Date
     */
    public static String getStrByDateYMDHMS(Date tDate) {
        String str = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (tDate != null) {
            str = formatter.format(tDate);
        }
        return str;
    } 
    /**
     * ��ʽ���ɣ�2006/08/17
     * @param date Date
     * @return String
     */
    public static String getDateStrYMDPath(Date date) {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if (date != null) {
            str = sdf.format(date);
        }
        return str;
    }

    public static String getDateStrYMDNoSplit(Date date) {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (date != null) {
            str = sdf.format(date);
        }
        return str;
    }

    /**
     * ��֤:������ȷ�������ʽ�� [2004-2-29], [2004-02-29 23:29:39], [2004/12/31]
     * @param strToCheck String
     * @return boolean
     */
    public static boolean isDateTimeYMD24HMS(String strToCheck) {
        return isMatchRegex(strToCheck, "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|(1[0-9])|(2[0-3]))\\:([0-5][0-9])((\\s)|(\\:([0-5][0-9])))))?$");
    }

    public static boolean isMatchRegex(String strSrc, String strRegex) {
        Pattern p = Pattern.compile(strRegex);
        return p.matcher(strSrc).matches();
    }

    public static String getDateStrYMDNoSplit(String date) {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (date != null) {
            str = sdf.format(getDateByStrYMDHMS(date));
        }
        return str;
    }

    /**
     * ���������date�������days������
     * @param date Date
     * @param days int
     * @return Date
     */
    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    /**
     * ��ǰʱ��ת���ɸ�ʽ��20061012231211
     * @return String
     */
    public static String getCurrentDateStrYMDHMSNoSplit24() {
        String str = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        if (date != null) {
            str = sdf.format(date);
        }
        return str;
    }
    /**
     * ��ǰʱ��ת���ɸ�ʽ��200912211137263726�����
     * @return String
     */
    public static String getCurrentDateStrYMDHMSNoSplit24AndMS() {
        String str = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssms");
        if (date != null) {
            str = sdf.format(date);
        }
        return str;
    }

    /**
     * �жϵ�һ�������Ƿ��ڵڶ�������֮ǰ
     * */
    public static boolean isDateFstBeforeDateScd(Date d1, Date d2) {
        return d1.before(d2);
    }

    /**
     * ���}����֮����������
     * */
    public static int getDayCountOfTwoDate(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return Integer.parseInt((diff / (1000 * 60 * 60 * 24)) + "");
    }
    
    //����}������֮��ļ��
    //aMode 1��ȥ��ͷ����;2:����һ����һ��
    // aInterval ������"D"Ϊ�죬"H"ΪСʱ
    public static int calInterval(Date aStrartDate, Date aEndDate,int aMode,String aInterval){
    	long diff = aEndDate.getTime() - aStrartDate.getTime();
    	double tIntervel =0;
    	if("D".equals(aInterval)){
    		tIntervel = diff / (1000 * 60 * 60 * 24);
    	}else{
    		tIntervel = diff / (1000 * 60 * 60);
    	}
    		
    	double tResule=0;
    	if(1==aMode){
    		tResule = Math.floor(tIntervel);
    	}else{
    		if(Math.round(tIntervel)<tIntervel){
    			tResule = Math.floor(tIntervel)+1;
    		}else{
    			tResule = Math.floor(tIntervel);
    		}
    	}
    	return (int)tResule;
    }

    /**
     * yyyy-MM-dd��ʽ���ַ�ת����Date��ʽ
     *
     */
    public static Date getDateByStrYMD(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date t = null;
        try {
            t = formatter.parse(str);
        } catch (ParseException ex) {
        	ex.printStackTrace();
        }
        return t;
    }

    public static String getLocaleDateTimeOfNow() {
        Date myDate = new Date();
        String str = "";
        if (myDate != null) {
            str = myDate.toLocaleString();
        }
        return str;

    }

    /**
     * ��ʽ���ɣ�2006-08-17 14:02:12
     */
    public static String getLocaleDateTime(Date myDate) {
        String str = "";
        if (myDate != null) {
            str = myDate.toLocaleString();
        }
        return str;
    }

    /**
     * ��ʽ���ɣ�2006-08-17 14:02:12
     */
    public static String getDateStrYMDHMS24(Date date) {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date != null) {
            str = sdf.format(date);
        }
        return str;
    }

    /**
     * ��ʽ���ɣ�2006-08-17 14:02:12
     * @return String
     */
    public static String getDateStrYMDHMS24Now() {
        Date date = new Date();
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date != null) {
            str = sdf.format(date);
        }
        return str;
    }


    public static String getDateStrYMDHMSCurrentTime() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        str = sdf.format(new Date());
        return str;
    }

    public static String getDateStrYMDOfNow() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        str = sdf.format(new Date());
        return str;
    }

    /**
     * ��õ�ǰ���ڵ���ݡ�
     * @return int ���
     */
    public static int getNowYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * ��ø����ڵ����
     * @param dt ������
     * @return int ���
     * @throws NullPointerException ���������Ϊnull���׳��쳣��
     */
    public static int getYear(Date dt) throws NullPointerException {
        if (dt == null) {
            throw new NullPointerException("���ڲ���Ϊnull");
        } else {
            Calendar cld = Calendar.getInstance();
            cld.setTime(dt);
            return cld.get(Calendar.YEAR);
        }
    }

    /**
     * ��õ�ǰ���ڵ��·ݡ�
     * @return int �·�
     */
    public static int getNowMonth() {
        return 1 + Calendar.getInstance().get(Calendar.MONTH);
    }

    /**
     * ��ø����ڵ��·�
     * @param dt ������
     * @return int �·ݣ�1-12��
     * @throws NullPointerException ���������Ϊnull���׳��쳣��
     */
    public static int getMonth(Date dt) throws NullPointerException {
        if (dt == null) {
            throw new NullPointerException("���ڲ���Ϊnull");
        } else {
            Calendar cld = Calendar.getInstance();
            cld.setTime(dt);
            return 1 + cld.get(Calendar.MONTH);
        }
    }

    /**
     * ��õ�ǰ���ڵĵ��µ�����
     * @return int ���µ�����
         /
         public static int getNowDate() {
        return 1 + Calendar.getInstance().get(Calendar.DATE);
         }

         /**
      * ��ø����ڵĵ��µ�����
      * @param dt ������
      * @return int ���µ�����
      * @throws NullPointerException ���������Ϊnull���׳��쳣��
      */
     public static int getDate(Date dt) throws NullPointerException {
         if (dt == null) {
             throw new NullPointerException("���ڲ���Ϊnull");
         } else {
             Calendar cld = Calendar.getInstance();
             cld.setTime(dt);
             return cld.get(Calendar.DATE);
         }
     }

    /**
     * ���õĸ�ʽ������
     *
     * @param date Date
     * @return String
     */
    public String formatDate(java.util.Date date) {
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    /**
     * ��ָ���ĸ�ʽ4��ʽ������
     *
     * @param date Date
     * @param format String
     * @return String
     */
    public static String formatDateByFormat(java.util.Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
            	ex.printStackTrace();
            }
        }
        return result;
    }

    public static String formatDateByFormat(String date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(getDateByStrYMDHMS(date));
            } catch (Exception ex) {
            	ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * �ж�}�������Ƿ���ͬһ��
     * */
    public static boolean isSameWeekDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) ==
                cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
// ���12�µ����һ�ܺ��4���һ�ܵĻ������һ�ܼ�����4��ĵ�һ��
            if (cal1.get(Calendar.WEEK_OF_YEAR) ==
                cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        } else if ( -1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) ==
                cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        }
        return false;
    }

    /**
     * ����������
     * */
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1) {
            week = "0" + week;
        }
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + week;

    }

    /**
     * �����һ������
     */
    public static String getMonday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * ������������
     */
    public static String getFriday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

    }

    /**
     * �������
     *
     * @param date
     * ����
     * @param day
     * ����
     * @return ������Ӻ������
     */
    public static Date changeDay(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR,
                     (calendar.get(Calendar.DAY_OF_YEAR) + offset));
        return calendar.getTime();
    }

    public static Calendar changeDay(Calendar calendar, int offset) {
        calendar.set(Calendar.DAY_OF_YEAR,
                     (calendar.get(Calendar.DAY_OF_YEAR) + offset));
        return calendar;
    }

//�ж�}�������Ƿ���ͬһ��
    public static boolean isSameWeekDates(Date date1, Date date2) {
        long diff = getMondayDate(date1).getTime() -
                    getMondayDate(date2).getTime();
        if (Math.abs(diff) < 1000 * 60 * 60 * 24) {
            return true;
        } else {
            return false;
        }
    }

//�����һ������
    public static Date getMondayDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return c.getTime();
    }

    /**
     * �������
     *
     * @param date
     * ����
     * @param date1
     * ����
     * @return �������������
     */
    public static int getDiffDate(java.util.Date date, java.util.Date date1) {
        int count = 0;
        if (date != null && date1 != null) {
            count = (int) ((date.getTime() - date1.getTime()) /
                           (24 * 3600 * 1000));
        }
        return count;
    }

    public static int getDiffDateHours(java.util.Date date,
                                       java.util.Date date1) {
        int count = 0;
        if (date != null && date1 != null) {
            count = (int) ((date.getTime() - date1.getTime()) / (3600 * 1000));
        }
        return count;
    }

    public static int getDiffDate(Calendar date, Calendar date1) {
        int count = 0;
        if (date != null && date1 != null) {
            count = getDiffDate(date.getTime(), date1.getTime());
        }
        return count;
    }

    /**
     * ��ʽ������
     *
     * @param dateStr
     * �ַ�������
     * @param formatStr
     * ��ʽ
     * @return ��������
     */
    public static java.util.Date parseDate(String dateStr, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isLeapYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 2, 1);
        calendar.add(Calendar.DATE, -1);
        if (calendar.get(Calendar.DAY_OF_MONTH) == 29) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * �õ���ǰ����ǰ�������
     * @return ���������ַ�
     */
    public static final String getBefDateStringUseDate(int day_i) {
        Date date = new Date(System.currentTimeMillis() -
                             day_i * 24 * 60 * 60 * 1000);
        SimpleDateFormat formattxt = new SimpleDateFormat("yyyy-MM-dd");
        return formattxt.format(date);
    }

    /**
     * �õ���ǰ����ǰ������� -�����ܺ��ϱߵ�һ��
     * @return ���������ַ�
     */
    public static final String getBefDateStringUseCalendar(int day_i) {
        Calendar day = Calendar.getInstance();
        day.add(Calendar.DATE, day_i);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(day.getTime());
    }

    /**
     * �õ���ǰ����ǰ������� -�����ܺ��ϱߵ�һ��
     * @return ���������ַ�
     */
    public static final String getBefDateTimeString(int day_i) {
        Calendar day = Calendar.getInstance();
        day.add(Calendar.DATE, day_i);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(day.getTime());
    }

    /**
     * �������������ĳ��ʱ���ʱ��
     * @param date Date
     * @param type String
     * @param offset int
     * @return Date
     */
    public static Date changeDay(Date date, String type, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (type.equalsIgnoreCase("year")) {
            calendar.add(Calendar.YEAR, offset);
        } else if (type.equalsIgnoreCase("month")) {
            calendar.add(Calendar.MONTH, offset);
        } else if (type.equalsIgnoreCase("week")) {
            calendar.add(Calendar.DATE, offset * 7);
        } else if (type.equalsIgnoreCase("day")) {
            calendar.add(Calendar.DATE, offset);
        }
        return calendar.getTime();
    }

    public static Date changeTime(Date date, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, min);
        return calendar.getTime();
    }


    /**
     * ����ת���� HHСʱMM����SS��
     * @param second long ��
     * @return String �����ַ�
     * */
    public static String getCommonTimeCNFromSecond(long second) {
        String result = "";
        long hour = second / 3600; //ʱ

        long leftSeconds = second % 3600;
        long minite = leftSeconds / 60; //��

        long miao = leftSeconds % 60; //��
        if (hour > 0) {
            result += hour + "ʱ";
        }
        if (minite >= 0) {
            result += minite + "��";
        }
        result += miao + "��";
        return result;
    }

    public static String getCommonTimeFromSecond(long second) {
        String result = "";
        long hour = second / 3600; //ʱ

        long leftSeconds = second % 3600;
        long minite = leftSeconds / 60; //��

        long miao = leftSeconds % 60; //��
        if (hour < 10) {
            result += "0" + hour + ":";
        } else {
            result += hour + ":";
        }
        if (minite < 10) {
            result += "0" + minite + ":";
        } else {
            result += minite + ":";
        }
        if (miao < 10) {
            result += "0" + miao;
        } else {
            result += miao;
        }
        return result;
    }

    /**
     * ��õ�ǰ��24Сʱ��
     * @return int
     */
    public static int getNowHour24() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    //�ж�ĳ�����Ƿ�����һ
    public static boolean isMonday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK);
        //��һ =2
        return i==2;
    }

    //�ж�ĳ�����Ƿ���ĳ�µĵ�һ��
    public static boolean isFirstOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day =c.get(Calendar.DAY_OF_MONTH);
        return day==1;
    }

    //�ж�ĳ�����Ƿ���ĳ��ĵ�һ��
    public static boolean isFirstOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day =c.get(Calendar.DAY_OF_YEAR);
        return day==1;
    }
    
    public static Date addDate(Date date, int field, int amount){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(field, amount);
    	return calendar.getTime();
    }
    

    public static void main(String[] args) {
//        Date d = new Date();
//        try {
//            System.out.println(isFirstOfYear(new SimpleDateFormat("yyyy-MM-dd").
//                                        parse("2008-01-01")));
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }
//      System.out.println(getDateStrYMDHMS24(DateUtil.changeDay(d,"year",1)));
//      System.out.println(getDateStrYMDHMS24(DateUtil.changeDay(d,"month",1)));
//      System.out.println(getDateStrYMDHMS24(DateUtil.changeDay(d,"week",1)));
//      System.out.println(getDateStrYMDHMS24(DateUtil.changeDay(d,"day",1)));
     	
    }

}
