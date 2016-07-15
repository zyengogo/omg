package com.omg.evn.validate;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
/**
 * Created with IntelliJ IDEA.
 * User: yangfei
 * Date: 15-4-27
 * Time: 上午10:33
 * 验证参数帮助类
 */
public class ParamValiDateUtils {
    //时间数据格式(长时间格式)
    private static final SimpleDateFormat[] DATEFORMATARRAYLONG = {
            new SimpleDateFormat("yyyyMMdd HH:mm:ss"),
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"),
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    };

    //时间数据格式(短时间格式)
    private static final SimpleDateFormat[] DATEFORMATARRAYSHORT = {
            new SimpleDateFormat("yyyyMMdd") ,
            new SimpleDateFormat("yyyy/MM/dd"),
            new SimpleDateFormat("yyyy-MM-dd")
    };

    //处理特殊字符
    public static boolean  StringCheckFilter(String   str){
        boolean  flag=false;
            if(str.equals(StringCheck(str))){
            flag=true;
        }
        return  flag;
    }



   // 过滤特殊字符
   public static String StringFilter(String   str){
         return  StringCheck(str);
   }


    private static String StringCheck(String   str){
        String newStr="";
       if (!"".equals(str)&&str!=null) {
           String regEx="[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

           System.out.println("| "+str.replaceAll("","").trim()+" |");
           Pattern   p   =   Pattern.compile(regEx);
           Matcher   m   =   p.matcher(str.replaceAll("","").trim());
           newStr= m.replaceAll("").trim();
        }
        return  newStr;
    }


    // 过滤特殊字符sql关键字
    public static String StringSqlFilter(String   str){
        return  StringSqlCheck(str);
    }
    // 过滤特殊字符sql关键字
    public static boolean StringSqCheckFilter(String   str){
        boolean  flag=false;
        if(str.equals(StringSqlCheck(str))){
            flag=true;
        }
        return  flag ;

    }

    // 过滤特殊字符sql关键字
    private static String StringSqlCheck(String   str)   throws   PatternSyntaxException   {
        String newStr="";
        if (!"".equals(str)&&str!=null) {
           /* String regEx="(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*//*)|"
                    + "(\\b(select|update|and|or|in|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";*/

            String regEx="(\\b(select|update|and|or|in|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute" +
                    "|SELECT|UPDATE|AND|OR|IN|DELETE|INSERT|TRANCATE|CHAR|INTO|SUBSTR|ASCII|DECLARE|EXEC|COUNT|MASTER|INTO|DROP|EXECUTE)\\b)";

            Pattern   p   =   Pattern.compile(regEx);
            Matcher   m   =   p.matcher(str.replaceAll("","").trim());
            newStr= m.replaceAll("").trim();
        }
        return  newStr;
    }


     //验证时间格式
    public static String DateStringForStrFilter(String dateStr){
        Date tempDate=null;
        tempDate  =DateStringCheck(dateStr);
        return tempDate==null?"":String.valueOf(tempDate);
    }
    //验证时间格式
    public static Date DateStringForDaFilter(String dateStr){
        return DateStringCheck(dateStr);
    }

    private static Date DateStringCheck(String dateStr){
        Date tempDate=null;
        SimpleDateFormat format = null;
        if(!"".equals(dateStr)){
            if(dateStr.length()>=10){        //出来时间时间长度小于等于10字节
                for (int i = 0; i < DATEFORMATARRAYSHORT.length; i++) {
                    format =DATEFORMATARRAYSHORT[i];
                    format.setLenient(false);
                    try {
                        tempDate= format.parse(dateStr);
                        break;
                    } catch (ParseException e) {
                    }
                }

            }else{     //出来时间时间长度大于10字节
                for (int i = 0; i < DATEFORMATARRAYLONG.length; i++) {
                    format =DATEFORMATARRAYLONG[i];
                    format.setLenient(false);
                    try {
                        tempDate=format.parse(dateStr);
                        break;
                    } catch (ParseException e) {
                    }
                }
            }
        }
        return tempDate;
    }




    //字符串装换
    public static String StringConvertFilter(String value) {
        if (value == null||"".equals(value)) {
            return null;
        }
        StringBuffer result = new StringBuffer(value.length());
        for (int i=0; i<value.length(); ++i) {
            switch (value.charAt(i)) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                case '\'':
                    result.append("&#39;");
                    break;
                case '%':
                    result.append("&#37;");
                    break;
                case ';':
                    result.append("&#59;");
                    break;
                case '(':
                    result.append("&#40;");
                    break;
                case ')':
                    result.append("&#41;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '+':
                    result.append("&#43;");
                    break;
                default:
                    result.append(value.charAt(i));
                    break;
            }
        }
        return String.valueOf(result);
    }



   /*public static void main(String age[]){


      // System.out.println(ParamValiDateUtils.DateStringFilter("2015- --14:39:12--11")+"---------1");
          //System.out.println(ParamValiDateUtils.StringSqlFilter("2015  select")+"---------1");
         //System.out.println(StringValiDateUtils.StringCheckFilter("*[lisi //]"));
         //System.out.println(StringValiDateUtils.StringFilter("//[lisi ''//]   ")+"---------1");
    }
*/
}
