package com.example.commutils.error.customize;

/**
 * 找不到資源
 * @author charles.chen
 * @date 2018年5月22日 下午6:18:50
 */
public class DataNotFoundException extends BasicException{
	public DataNotFoundException(String[] detail, String[] data, String log) {
		super(detail, data, log);
	}
	private static final long serialVersionUID = 1L;

}
