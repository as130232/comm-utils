package com.example.commutils.error.constant;

import com.example.commutils.utils.StringUtil;

/**
 * 共用錯誤訊息
 * @author charles.chen
 * @date 2018年8月22日 上午10:22:56
 */
public enum ErrorMsgEnum {
	
	/*** 未知錯誤 (000)*/
	UNKNOW("000", "UNKNOW_ERROR"),
	/*** 找不到資料 (001)*/
	DATA_NOT_FOUND("001", "DATA_NOT_FOUND"),
	/*** 新增資料失敗 (002)*/
	INSERT_FAILED("002", "INSERT_FAILED"),
	/*** 更新資料失敗 (003)*/
	UPDATE_FAILED("003", "UPDATE_FAILED"),
	/*** 檢查資料有誤 (004)*/
	VALIDATE_FAILURE("004", "VALIDATE_FAILURE"),
	/*** 資料已存在 (005)*/
	DATA_EXISTED("005", "DATA_EXISTED"),
	/*** 取得其他微服務資料失敗 (006)*/
	GET_OTHER_SERVICE_DATA_FAIL("006", "GET_OTHER_SERVICE_DATA_FAIL"),
	/*** 檔案上傳失敗 (007)*/
	FILE_UPLOAD_FAIL("007", "FILE_UPLOAD_FAIL"),
	/*** 檔案上傳失敗 (007)*/
	CREATE_URL_FAIL("026", "CREATE_URL_FAIL"),
	
	/*** 檔案下載失敗 (008)*/
	FILE_DOWN_FAIL("008", "FILE_DOWN_FAIL"),
	
	/*** 任務已無效 (020)*/
	TASK_IS_INVALID("020", "TASK_IS_INVALID"),
	/*** 任務已凍結 (021)*/
	TASK_IS_FREEZE("021", "TASK_IS_FREEZE"),
	
	;
	
	
	
	public final String value;
	public final String i18n;
	public final Integer intValue;
	private ErrorMsgEnum(String value, String i18n){
		this.value = value;
		this.i18n = i18n;
		this.intValue = new Integer(value);
	}

	public static String getI18n(String columnName, String value){
		String result = "UNKNOW";
		ErrorMsgEnum[] array = ErrorMsgEnum.values();
		for(ErrorMsgEnum enumObj : array){
			String enumName = enumObj.toString();
			String camelName = StringUtil.toCamelCase(enumName);
			camelName = StringUtil.toHeadLowerCase(camelName);
			if(camelName.indexOf(columnName) != -1){
				String enumValue = enumObj.value;
				if(enumValue.equals(value)){
					return enumObj.i18n;
				}
			}
		}
		return result;
	}
}
