package com.example.commutils.error.customize;

/**
 * 新增資源失敗
 * @author charles.chen
 * @date 2018年5月22日 下午6:18:50
 */
public class InsertFailedException extends BasicException{
	public InsertFailedException(String[] detail, String[] data, String log) {
		super(detail, data, log);
	}

	private static final long serialVersionUID = 1L;


}
