package com.rk.filters;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

 

/**
 * @author RK
 *
 */

@Slf4j
@Component
@Order(1)
public class RequestResponseLoggers implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		log.info("Request Method - {}",httpServletRequest.getMethod());
//		log.info("Request Method - {}",httpServletRequest.getRequestURI());
//		log.info("Response Method - {}",new String(IOUtils.toByteArray( httpServletRequest.getInputStream())));
		
		MyCustomHttpRequestWrapper requestWrapper = new MyCustomHttpRequestWrapper((HttpServletRequest) request);
		
		log.info("Request Method - {}",requestWrapper.getMethod());
		log.info("Request Method - {}",requestWrapper.getRequestURI());
		log.info("Response Method - {}",new String(requestWrapper.getByteArray()).replaceAll("\n"," "));
		
		MyCustomHttpResponseWrapper responseWrapper = new MyCustomHttpResponseWrapper((HttpServletResponse)response);
		
		chain.doFilter(requestWrapper, responseWrapper);
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		
	}
	private class MyCustomHttpRequestWrapper extends HttpServletRequestWrapper{
		

		 private byte[] byteArray;
		
		public MyCustomHttpRequestWrapper(HttpServletRequest request) {
			super(request);
			try {
				byteArray = IOUtils.toByteArray(request.getInputStream());
			} catch (Exception e) {
				throw new RuntimeException("Issuse Whitle reding requset Stream");
			}
		}
		
		@Override
		public ServletInputStream getInputStream() throws IOException {
			return new MyDelegatingServletInputStream(new ByteArrayInputStream(byteArray));
		}

		public byte[] getByteArray() {
			return byteArray;
		}
		
	}

}

























