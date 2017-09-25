package com.small.tag.utils;

import android.text.TextUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static final String TAG = "StringUtils";

    /**
     * 返回具体的格式
     * @param pattern
     * @param input
     * @return
     */
    public static String getSpecificFormat(String pattern, double input){
        DecimalFormat df = new DecimalFormat(pattern);

        return df.format(input);
    }

    /**
     * 字符串是否为空，null也视为空
     */
    public static boolean isEmpty(String string) {
        return null == string || "".equals(string.replace(" ", "")) || "null".equals(string.trim());
    }

    /**
     * 解析double类型字符串
     *
     * @param strNum
     * @return
     */
    public static double parseDouble(String strNum) {
        if (isEmpty(strNum)) {
            return 0;
        }//有的金额带格式符‘,’固先去除此符号。
        else if (NumberUtils.isNumber(strNum.replace(",", "").replace(" ", ""))) {
            return Double.parseDouble(strNum);
        } else {
            return 0;
        }
    }

    public static int parseInteger(String strNum) {
        return (int) parseDouble(strNum);
    }

    /**
     * 字符串长度
     *
     * @param str
     * @return
     */
    public static int length(CharSequence str) {
        return str == null ? 0 : str.length();
    }

    /**
     * null转换成空字符串
     *
     * @param str
     * @return
     */
    public static String nullStrToEmpty(Object str) {
        return (str == null ? "" : (str instanceof String ? (String) str : str.toString()));
    }

    /**
     * 设置金额，添加元
     *
     * @return
     */
    public static String setAmount(Object amount) {

        if (amount instanceof Double) {
            //格式化double为两位小数
            return formatDouble((Double) amount) + "元";
        }
        return amount + "元";
    }

    public static String hideMobileInfo(String phone) {
        if (isMobilePhone(phone))
            phone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        else
            phone = hideUserInfo(phone);
        return phone;
    }

    /**
     * 隐藏用户信息
     * 张三 --> 张**
     *
     * @param text
     * @return
     */
    public static String hideUserInfo(String text) {
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(text.substring(0,1));
        sb.append("**");
        return sb.toString();
    }

    public static String hideAsStarCenter(String text){
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        char[] strs = text.toCharArray();
        if (strs.length<4){
            return hideAsStar(text);
        }
        for(int i = 0; i < strs.length; i++){
            if (i > 0 && i < strs.length - 1) strs[i] = '*';
        }
        String shiftName = new String(strs);
        return shiftName;
    }

    public static String hideAsStar(String text){
        char[] strs = text.toCharArray();
        for(int i = 0; i < strs.length; i++){
            if(i != 0) strs[i] = '*';
        }
        String shiftName = new String(strs);
        return shiftName;
    }

    public static String hideAsAllStar(String text){
        char[] strs = text.toCharArray();
        for(int i = 0; i < strs.length; i++){
            strs[i] = '*';
        }
        String shiftName = new String(strs);
        return shiftName;
    }


    public static String hideEndStar(String text, int num){
        if (isEmpty(text)) return "";
        if (text.length()<num) hideAsStar(text);
        String headStr = text.substring(0,text.length()-num);
        StringBuffer sb = new StringBuffer(headStr);
        for (int i=0; i<num; i++){
                sb.append("*");
        }
        return sb.toString();
    }
    /**
     * 设置金额，添加变成 3位数分割
     *
     * @return
     */
    public static String setAmountFormWithTrimChar(Object amount, int trimNum, String trimChar) {
        if (amount instanceof Double) {
            //格式化double为两位小数
            return getFormNumThree(formatDouble((Double) amount),trimNum,trimChar);
        }else if (amount instanceof String){
            return getFormNumThree(amount+"", trimNum,trimChar) ;
        }
        return amount + "";
    }


    public static String getFormNumThree(String result, int trimNum, String trimChar) {
        String num ="";
        String endSection ="";
        if (TextUtils.isEmpty(trimChar)) trimChar =".";
        int counter = 0;
        int startIndex = result.indexOf(".") - 1;
        if (result.indexOf(".")==-1){
            startIndex = result.length()-1;
        }
        if (startIndex != result.length() - 1)
             endSection = result.substring(startIndex+1,result.length());
        for (int i = startIndex; i >= 0; i--) {
            counter++;
            num = result.charAt(i) + num;
            if (counter % trimNum == 0 && i != 0) {
                num = trimChar + num;
            }
        }
        num += endSection;
        return num;
    }


    public static String getFormBankAccount(String result, int trimNum, String trimChar) {
        String num ="";
        String endSection ="";
        if (TextUtils.isEmpty(trimChar)) trimChar =".";
        int counter = 0;
        int endIndex = (result.length()/trimNum)*trimNum-1;
        StringBuffer sb = new StringBuffer();
        endSection = result.substring(endIndex+1,result.length());
        for (int i = 0; i <= endIndex; i++) {
            counter++;
            sb.append("*");
            if (counter % trimNum == 0 && i != 0) {
                sb.append(trimChar);
            }else if (i==endIndex){
                sb.append(trimChar);
            }
        }
       sb.append(endSection);
        return sb.toString();
    }

    /**
     * 对金额的格式调整到分
     * @param money
     * @return
     */
    public static String moneyFormat(String money){//23->23.00
        StringBuffer sb=new StringBuffer();
        if(money==null){
            return "0.00";
        }
        int index=money.indexOf(".");
        if(index==-1){
            return money+".00";
        }else{
            String s0=money.substring(0,index);//整数部分
            String s1=money.substring(index+1);//小数部分
            if(s1.length()==1){//小数点后一位
                s1=s1+"0";
            }else if(s1.length()>2){//如果超过3位小数，截取2位就可以了
                s1=s1.substring(0,2);
            }
            sb.append(s0);
            sb.append(".");
            sb.append(s1);
        }
        return sb.toString();
    }
    /**
     * 格式化为两位小数
     *
     * @param num
     * @return
     */
    public static String formatDouble(double num) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        return df.format(num);
    }

    /**
     * 格式化为一位小数
     *
     * @param num
     * @return
     */
    public static String formatDouble2(double num) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.0");
        return df.format(num);
    }

    /**
     * 将将所有的数字、字母及标点全部转为全角字符避免TextView自动换行出现的排版混乱问题
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    public static boolean isMobilePhone(String telNum) {
        String regex = "^(1[2-9][0-9])\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(telNum);
        return m.matches();
    }
}
