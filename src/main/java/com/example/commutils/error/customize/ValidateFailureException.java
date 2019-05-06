package com.example.commutils.error.customize;

/**
 * 驗證錯誤Exception
 * @author charles.chen
 * @date 2018年6月5日 下午3:07:03
 */
public class ValidateFailureException extends BasicException{
	public ValidateFailureException(String[] detail, String[] data, String log) {
		super(detail, data, log);
	}
	private static final long serialVersionUID = 1L;

}
