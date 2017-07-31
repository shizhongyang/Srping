package com.lq.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lq.entity.Customer;
import com.lq.entity.Reader;
import com.lq.service.CustomerService;
import com.lq.service.CustomerServiceImpl;

@Controller
public class LoginController {

	@Autowired(required=true)
	CustomerService customerServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/loginReader",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object loginReader(@Valid Reader reader,HttpServletRequest request,Errors errors){
		System.out.println("------"+reader.getMeno()+reader.getName());
		if (errors.getErrorCount()>0) {
			System.out.println("出错了");
			for(FieldError error:errors.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "success";
		}
		
		return "hello world";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST , produces = "application/json; charset=utf-8")
	public Object login(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws JsonProcessingException {
		String username = httpServletRequest.getParameter("userName");
		String password = httpServletRequest.getParameter("passWord");
		System.out.println("username:" + username + "password:" + password);
		//httpServletResponse.setContentType("text/html;charset=utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		  ObjectMapper mapper = new ObjectMapper(); 
		if (username==null&&password==null) {
			map.put("code", -1);
			map.put("msg", "用户名或密码为空");
			map.put("data", null);
			String string = mapper.writeValueAsString(map);
			return string;	
		}else {
			
			HttpSession session = httpServletRequest.getSession(true);
			map.put("code", 200);
			map.put("msg", "登录成功");
			Customer customer = customerServiceImpl.getCustomer(username, password);
			customer.setToken("hello"+Math.random()*1000);
			map.put("data", customer );
			
			session.setAttribute("token", customer.getToken());
			
			String string = mapper.writeValueAsString(map);
			return string;	
		}
	}
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String other(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession(true);
		String name = (String) session.getAttribute("token");
		System.out.println("------------"+name);
		return "success";
	}
	
	
}
