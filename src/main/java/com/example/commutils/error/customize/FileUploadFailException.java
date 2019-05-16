package com.example.commutils.error.customize;

/**
 * 檔案上傳失敗
 * @author charles.chen
 * @date 2018年8月22日 下午2:37:41
 */
public class FileUploadFailException extends BasicException{
	public FileUploadFailException(String[] detail, String[] data, String log) {
		super(detail, data, log);
	}
	private static final long serialVersionUID = 1L;

}
