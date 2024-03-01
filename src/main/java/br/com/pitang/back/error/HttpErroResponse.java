package br.com.pitang.back.error;

public class HttpErroResponse {
	
//	public static final String INVALID_LOGIN_PASSWORD = "Invalid login or password";
//	public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
//	public static final String LOGIN_ALREADY_EXISTS = "Login already exists";
//	public static final String INVALID_FIELDS = "Invalid fields";
//	public static final String MISSING_FIELDS = "Missing fields";
//	public static final String UNAUTHORIZED = "Unauthorized";
//	public static final String UNAUTHORIZED_INVALID_SESSION = "Unauthorized - invalid session";
//	public static final String LISENCE_PLATE_ALREADY_EXISTS = "License plate already exists";
	
	private String message;
	private Integer errorCode;
	
	public HttpErroResponse(String message, Integer errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
}
