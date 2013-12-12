package com.allyes.mtp.common.error;

/**
 * 系统级错误。
 * 
 * @author qaohao
 */
public class SystemException extends RuntimeException {
	public SystemException(String errorMsg) {
		super(errorMsg);
	}
	public SystemException(String errorMsg, Exception e) {
		super(errorMsg, e);
	}
}