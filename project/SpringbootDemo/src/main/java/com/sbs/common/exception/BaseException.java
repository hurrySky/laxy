package com.sbs.common.exception;

public class BaseException extends RuntimeException{

	/**
	 * RuntimeException的父类Exception的父类Throwable实现了 Serializable，所以在此声明Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 异常码
	 */
	private String code;
	
	/**
     * 错误消息
     */
    private String exceptionMessage;

	/**
     * 无参构造犯法
     */
    public BaseException () {
    	
    }
    
    public BaseException (String code, String exceptionMessage) {
    	this.code = code;
    	this.exceptionMessage = exceptionMessage;
    }
    
    @Override
    public String toString() {
		
    	return "\tException:  " + this.getClass()+ "\t" + "ExceptionCode:" +code + "\t" + "ExceptionMessage:" + this.exceptionMessage;
    }
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
