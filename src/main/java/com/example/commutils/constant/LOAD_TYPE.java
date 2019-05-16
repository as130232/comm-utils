package com.example.commutils.constant;

import com.example.commutils.utils.StringUtil;

/**
 * 方式
 * @author charles.chen
 * @date 2019年5月16日 下午2:06:16
 */
public enum LOAD_TYPE {
	/*** 主軸(1) */
	MAC_OS(1, "MAC_OS"),
	/*** 內部ATC(2) */
	WINDOWS(2, "WINDOWS");

	
	public final Integer intValue;
	public final String i18n;
	
	private LOAD_TYPE(Integer intValue, String i18n){
		this.i18n = i18n;
		this.intValue = intValue;
	}

	public static String getI18n(String columnName, String status){
		String result = MSG.UNKNOW.i18n;
		LOAD_TYPE[] array = LOAD_TYPE.values();
		for(LOAD_TYPE enumObj : array){
			String enumName = enumObj.toString();
			String camelName = StringUtil.toCamelCase(enumName);
			camelName = StringUtil.toHeadLowerCase(camelName);
			if(camelName.indexOf(columnName) != -1){
				String enumValue = enumObj.intValue.toString();
				if(enumValue.equals(status)){
					return enumObj.i18n;
				}
			}
		}
		return result;
	}
	
}
