package com.example.commutils.error.customize;

/**
 * 資料已存在
 * @author charles.chen
 * @date 2018年6月7日 下午2:37:41
 */
public class DataExistedException extends BasicException{
	public DataExistedException(String[] detail, String[] data, String log) {
		super(detail, data, log);
	}
	private static final long serialVersionUID = 1L;

}
