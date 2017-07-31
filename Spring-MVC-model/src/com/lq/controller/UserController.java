package com.lq.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lq.entity.Book;
import com.lq.entity.Reader;
import com.lq.entity.User;

import com.lq.service.CustomerService;
import com.lq.service.ReaderService;
import com.lq.service.UserService;



//注入controller
@Controller
@RequestMapping("/user")
public class UserController {

	//注入service
	@Autowired
	private UserService userService;

	@Autowired
	private CustomerService customerService;
	
	
//	@Autowired
//	private BookService bookService;
	
	
	@Autowired
	private ReaderService re;
	
	@RequestMapping("/getAllUser")
	public String getAllReader(HttpServletRequest request) {
		request.setAttribute("userList", userService.getAllUser());
		return "/index";
	}
	
	
	@RequestMapping("/getAllReader")
	public String getAllUser(HttpServletRequest request) {
		
		ArrayList<Reader> list = (ArrayList<Reader>)re.getAllReader();
	
		//System.out.println("list"+	list.get(0).toString());
		request.setAttribute("userList",list);		
		return "success";
	}

	
	@ResponseBody
	@RequestMapping("/getAllUserJson")
	public ArrayList<User> getAllUserJson(HttpServletRequest request) {
		ArrayList<User> list = (ArrayList<User>) userService.getAllUser();
		return list;
	}
	
	@RequestMapping("/getUser")
	public String getUser(String id, HttpServletRequest request) {

		request.setAttribute("user", userService.getUser(id));

		return "/editUser";
	}

	@RequestMapping("/toAddUser")
	public String toAddUser() {
		return "/addUser";
	}
	

	public String addReader( @Valid Reader reader,Errors errors, HttpServletRequest request) {
		try {
			System.out.println("------"+reader.getMeno());
			if (errors.getErrorCount()>0) {
				System.out.println("出错了");
				for(FieldError error:errors.getFieldErrors()){
					System.out.println(error.getField() + ":" + error.getDefaultMessage());
				}
				return "AddReader";
			}
			re.addReader(reader);
			return "/success";
		} catch (Exception e) {
			return "/success";
		}		
	}
	
	
	@RequestMapping(value = "/addReader",method = RequestMethod.POST)
	private String fildUpload(
			@Valid Reader reader,
			@RequestParam(value = "file", required = false) MultipartFile[] file,
			HttpServletRequest request,
			Errors errors) throws Exception {
		// 基本表单
		System.out.println(reader.toString());

		// 获得物理路径webapp所在路径
		String pathRoot = request.getSession().getServletContext()
				.getRealPath("");
		String path = "";
		List<String> listImagePath = new ArrayList<String>();
		
		if (errors.getErrorCount()>0) {
			System.out.println("出错了");
			for(FieldError error:errors.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "AddReader";
		}
	
		for (MultipartFile mf : file) {
			if (!mf.isEmpty()) {
				// 生成uuid作为文件名称
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				// 获得文件类型（可以判断如果不是图片，禁止上传）
				String contentType = mf.getContentType();
				// 获得文件后缀名称
				String imageName = contentType
						.substring(contentType.indexOf("/") + 1);

				path = "/static/images/" + uuid + "." + imageName;
				filejianli(path);
				mf.transferTo(new File(pathRoot + path));
				listImagePath.add(path);
			}
		}
		
		System.out.println(pathRoot + path);
		request.setAttribute("imagesPath", path);
		reader.setImgAvatar(pathRoot+path);
		re.addReader(reader);
		return "success";
	}

	public void filejianli(String filePath) {
		File file = new File(filePath);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//不存在");
			file.mkdir();
		} else {
			System.out.println("//目录存在");
		}
	}
	
	
	@RequestMapping(value = "/rea",method = RequestMethod.GET)
	public String input(Map<String, Object> map){
		map.put("reader", new Reader());
		return "AddReader";
	}
	
	
	
//	@RequestMapping("/addBook")
//	public String addBook(Book book, HttpServletRequest request) {
//		try {
//			System.out.println("------"+book.getTitle());
//			bookService.addBook(book);
//			return "/success";
//		} catch (Exception e) {
//			return "/success";
//		}		
//	}
//	
	
	@RequestMapping("/addUser")
	public String addUser(User user, HttpServletRequest request) {
		try {
			
			System.out.println("------"+user.getAge());
			userService.addUser(user);
			return "redirect:/user/getAllUser";
		} catch (Exception e) {
			return "/error";
		}		
	}

	
	
	
	@RequestMapping("/delUser")
	public void delUser(String id, HttpServletResponse response) {

		String result = "{\"result\":\"error\"}";

		if (userService.delUser(id)) {
			result = "{\"result\":\"success\"}";
		}

		response.setContentType("application/json");

		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/updateUser")
	public String updateUser(User user, HttpServletRequest request) {

		if (userService.updateUser(user)) {
			user = userService.getUser(user.getId());
			request.setAttribute("user", user);
			return "redirect:/user/getAllUser";
		} else {
			return "/error";
		}
	}
}
