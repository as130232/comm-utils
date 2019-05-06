package com.example.commutils.error.customize;

/**
 * 基礎Exception
 * 所有錯誤Exception都繼承至此
 * @author charles.chen
 * @date 2018年6月7日 下午2:37:41
 */
public class BasicException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	String[] detail;
	String[] data;
	String log;
	
	public BasicException(String[] detail, String[] data, String log){
		this.detail = detail;
		this.data = data;
		this.log = log;
    }

	@Override
	public String toString() {
		return getMessage();
	}

	public String[] getDetail() {
		return detail;
	}

	public void setDetail(String[] detail) {
		this.detail = detail;
	}

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
	
	
	

}
