package com.rk.filters;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyCustomHttpResponseWrapper extends  HttpServletResponseWrapper{

	
	public MyCustomHttpResponseWrapper(HttpServletResponse response) {
		super(response);
		// TODO Auto-generated constructor stub
	}

}
