package com.example.commutils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	/**正整數驗證正則表達式**/
	public static final String NUMBER_REG = "[0-9]+";
	
	/**電腦IP驗證正則表達式**/
	public static final String IP_REG = "(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))";
	
	/**
	 * 將有底線的值轉為物件駝峰式命名
	 * @author charles.chen
	 * @date 2017年7月31日 上午9:35:33
	 * @param str
	 * @return String
	 */
	public static String toCamelCase(String str) {
		String[] parts = str.split("_");
		String camelCaseString = "";
		for (String part : parts) {
			if (!"".equals(part)) {
				camelCaseString = camelCaseString + toProperCase(part);
			}
		}
		return camelCaseString;
	}
	
	/** 
     * 駝峰名轉為大寫，間格加入底線
     *  
     * @param camelCaseName 
     * @return String
     */  
    public static String toUnderscore(String camelCaseName) {  
        StringBuilder result = new StringBuilder();  
        if (camelCaseName != null && camelCaseName.length() > 0) {  
            result.append(camelCaseName.substring(0, 1).toLowerCase());  
            for (int i = 1; i < camelCaseName.length(); i++) {  
                char ch = camelCaseName.charAt(i);  
                if (Character.isUpperCase(ch)) {  
                    result.append("_");  
                    result.append(Character.toLowerCase(ch));  
                } else {  
                    result.append(ch);  
                }  
            }  
        }  
        return result.toString().toUpperCase();  
    }  
    
	/**
	 * 字首轉大寫
	 * @author charles.chen
	 * @date 2017年7月31日 上午9:35:33
	 * @param str
	 * @return String
	 */
	public static String toProperCase(String str) {
		String result = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
		return result;
	}
	
	/**
	 * 字首轉小寫
	 * @author charles.chen
	 * @date 2017年7月31日 上午9:35:33
	 * @param str
	 * @return String
	 */
	public static String toHeadLowerCase(String str) {
		String result = str.substring(0, 1).toLowerCase() + str.substring(1);
		return result;
	}
	
	/**
	 * 轉成字串
	 * @author charles.chen
	 * @date 2018年8月27日 下午1:31:49
	 */
	public static String toString(Object obj) {
		if(obj == null) {
			return null;
		}else
		return obj.toString();
	}
	
	/**
	 * 去除null<p>
	 * 
	 * @author andy.wen
	 * @date 2018年9月6日 上午11:08:18
	 */
	public static String trim(String str) {
		if(str!=null) {
			return str.trim();
		}
		return "";
	}
	
	/**
	 * 轉換顯示字串為給定位數字串。例如：AAAAB或者AAC<p>
	 * 
	 * @author andy.wen
	 * @date 2018年9月13日 下午3:24:12
	 */
	public static String otherClass(int number, int srno) {

		char[] strList = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		String ret = "";
		Double initSrno = Math.pow(26, number);
		int tempSerno = initSrno.intValue() + srno;
		for (int i = 0; i < number; i++) {
			int ans = tempSerno % 26;
			ret = strList[ans] + ret;
			tempSerno = tempSerno / 26;
		}
		return ret;
	}
	
    /**
	 * 測試
	 * @author charles.chen
	 * @date 2018年8月22日 下午3:50:22
	 */
    public static void main(String[] args) {
//    	String camelCaseName = "taskStatus";
//    	String result = StringUtil.toUnderscore(camelCaseName);
//    	System.out.println("result:" + result);
    	System.out.println(StringUtil.toString(null));
    	
    	System.out.println(StringUtil.isNumber(null));
    	System.out.println(StringUtil.isNumber(""));
    	System.out.println(StringUtil.isNumber("123"));
    	System.out.println(StringUtil.isNumber("123s"));
    	
    	System.out.println(StringUtil.isIp("String"));
    	
    	System.out.println(StringUtil.isIp("256.168.1"));
	}

    /**
	 * 檢查輸入的字符是否是數字<p>
	 * @author eric.wang
	 * @date 2018年11月29日 下午1:40:45
	 */
	public static boolean isNumber(String numberStr) {
		// TODO Auto-generated method stub
		
		if(numberStr==null) {
			
			return false;
		}
				
		return regexStr(NUMBER_REG, numberStr);
	}
	
	/**
	 * 檢查輸入的文字是否服務電腦IP格式<p>
	 * @author eric.wang
	 * @date 2018年12月10日 上午10:22:53
	 */
	public static boolean isIp(String ip) {
		
		if(ip==null) {
			
			return false;
		}
		
		return regexStr(IP_REG, ip);
	}
	
	/**
	 * 正則表達式<p>
	 * @author eric.wang
	 * @date 2018年11月29日 下午2:31:10
	 */
	public static boolean regexStr(String regex,String chat) {
		
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(chat);
		
		return matcher.matches();
	}
	
   /**
    * 給予字串和正規表示式, 看是否符合字串
    * @author lucas.wang
    * @date 2019年1月2日
    * @param content
    *           要驗證的文字
    * @param region
    *           正規表示式
    * @return boolean
    */
   public static boolean parseRegion(String content, String region) {
      Pattern pattern = Pattern.compile(region);
      Matcher matcher = pattern.matcher(content);
      boolean isExist = matcher.find();
      if (isExist) {
         return true;
      } else {
         return false;
      }
   }
}
