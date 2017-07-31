package com.lq.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lq.entity.TestExcel;
import com.lq.entity.User;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class TestFileController {
	

	
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String other(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest req) {
		List<TestExcel> list = new ArrayList<TestExcel>();
		 //|获取在Web服务器上的 绝对路径  
        String path1 =req.getSession().getServletContext().getRealPath("/");; 
        System.out.println(path1);  
		try {
			System.out.println(file.isEmpty());
			if (!file.isEmpty()) {
				
				/*if (file.isEmpty() == false) {
					return "true"+file.isEmpty();
				}*/
				
				System.out.println("fileName：" + file.getOriginalFilename());
				/*
				 * String uuid = UUID.randomUUID().toString().replaceAll("-",
				 * ""); String contentType = file.getContentType(); String
				 * imageName = contentType.substring(contentType.indexOf("/") +
				 * 1);
				 */
				String path = path1 + new Date().getTime()
						+ file.getOriginalFilename();

				// CreateFileUtil.createDir(pathRoot + path);
				file.transferTo(new File(path));
				System.out.println("------------success");

				File file2 = new File(path);

				jxl.Workbook readwb = null;
				// 构建Workbook对象, 只读Workbook对象
				// 直接从本地文件创建Workbook
				InputStream instream = new FileInputStream(file2);
				readwb = Workbook.getWorkbook(instream);
				// 获取第一张Sheet表
				Sheet sheet = readwb.getSheet(0);

				// 我们既可能通过Sheet的名称来访问它，也可以通过下标来访问它。如果通过下标来访问的话，要注意的一点是下标从0开始，就像数组一样。
				// 获取第一行，第一列的值
				Cell c00 = sheet.getCell(0, 0);
				String s = c00.getContents();
				System.out.println(s);

				// 行数(表头的目录不需要，从1开始)
				for (int i = 0; i < sheet.getRows(); i++) {
					/*Cell c = sheet.getCell(i,0);
					if (c.getContents() == null) {
						System.out.println("-------");
						break;
					}*/
					// 创建一个数组 用来存储每一列的值
					String[] str = new String[sheet.getColumns()];
					Cell cell = null;
					TestExcel excel = new TestExcel();
					// 列数
					for (int j = 0; j < sheet.getColumns(); j++) {
						// 获取第i行，第j列的值
						cell = sheet.getCell(j, i);
					    str[j] = cell.getContents();
					    if (str[j].equals("") || str[j] == null) {
							break;
						}
						excel.setID(str[0]);
						excel.setSTATIONNAME(str[1]);
						excel.setYEAR(str[2]);
						excel.setADDRESS(str[3]);
						excel.setBELONGCOUNTY(str[4]);
						excel.setBELONGCITY(str[5]);
						excel.setX(str[6]);
						excel.setY(str[7]);
						excel.setAREATYPE(str[8]);
						excel.setMAJORSTATION(str[9]);
						excel.setVOLTAGE(str[10]);
						excel.setSTATIONCAPACITY(str[11]);
						excel.setINLINENAME(str[12]);
						excel.setOUTLINENAME(str[13]);
						excel.setINLINENUMBER(str[14]);
						excel.setOUTLINENUMBER(str[15]);
						excel.setINLINEPOWER(str[16]);
						excel.setOUTLINEPOWER(str[17]);
						excel.setAREARATE(str[18]);
						excel.setVOLTAGERATE(str[19]);
						excel.setYEARMAXBURDEN(str[20]);
						excel.setPROVINCEMAXBURDEN(str[21]);
						excel.setCITYMAXBURDEN(str[22]);
						excel.setTRANSPORTELECTRICITY(str[23]);
						excel.setMAXBURDENRATE(str[24]);
						excel.setMEANBURDENRATE(str[25]);

					}
					// 把刚获取的列存入list
					if (excel.getID()!=null) {
						list.add(excel);						
					}
				}
				
				System.out.println(list.size());
		        ObjectMapper mapper = new ObjectMapper();  
		        String jsonfromList = mapper.writeValueAsString(list);  
		        System.out.println(jsonfromList);      
				
				return jsonfromList;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "sucess"+file.isEmpty();

	}

}
