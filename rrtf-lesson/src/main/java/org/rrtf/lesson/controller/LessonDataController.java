package org.rrtf.lesson.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rrtf.lesson.entity.LessonData;
import org.rrtf.lesson.mapper.LessonDataRepository;
import org.rrtf.lesson.mapper.PubLessonRepository;
import org.rrtf.lesson.service.RoleTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/lessonData")
public class LessonDataController {//上传与下载

	@Autowired
	LessonDataRepository lessonDataRepository;
	
	@Autowired
	PubLessonRepository lessonRepository;
	
	@Autowired
	RoleTools tools;

	@Value("${upload.location}")
	String UPLOAD_LOCATION;//从属性文件中读取上传文件最终保存的位置
	@Value("${download.location}")
	String DOWNLOAD_LOCATION;//从属性文件中读取上传文件最终保存的位置

	@RequestMapping("/findByLessonId") // 显示所有资料:http://localhost:8080/lessonData/findByLessonId
	public List<LessonData> findByLessonId(int lessonId) {
		return lessonDataRepository.findByLessonId(lessonId);
	}
	
	//文件上传
	@PostMapping("/uploadFile") //上传资料:http://localhost:8080/lessonData/uploadFile
	public String uploadFile(HttpServletRequest request) throws IOException {
		//↓权限检测
		int lessonId = Integer.parseInt(request.getParameter("lessonId"));
		int lessonTeacherId = lessonRepository.findByLessonIdAndLessonStatus(lessonId,1).getTeacherId();
		int thisTeacherId = tools.getTeacherId();
		if(thisTeacherId!=lessonTeacherId) return "你不能胡乱给别人的课程上传资料";
		//↑权限检测
		//↓获取路径
		String urlPath = Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1);
		//↑获取路径
		//↓处理上传的文件,dataName是原文件名,fileName是保存时的文件名
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("lessonData");
		LessonData data = new LessonData();
		if (file.isEmpty()) {
			return "文件为空,原因未知";
		}
		byte[] bytes = file.getBytes();//获取上传文件的字节流
		String dataName = file.getOriginalFilename();//获取上传文件名称
		int beginIndex = dataName.length()-10<0?0:dataName.length()-10;
		String fileName = "LessonData"+String.valueOf(new Date().getTime())+dataName.substring(beginIndex);
		Path path = Paths.get(urlPath,UPLOAD_LOCATION, fileName);//定义保存路径nio包
		Files.write(path, bytes);//写入最终保存的位置
		data.setDataName(dataName);
		data.setFileName(fileName);
		data.setLessonId(Integer.parseInt(request.getParameter("lessonId")));
		lessonDataRepository.save(data);
		//↑处理上传的文件,dataName是原文件名,fileName是保存时的文件名
		return "文件上传成功,请不要重复上传";
	}
	
	//文件列表查看:localhost:8080/lessonData/showFiles?lessonId=20
	@RequestMapping("/showFiles")
	private List<LessonData> showFiles(int lessonId) {
		return lessonDataRepository.findByLessonId(lessonId);
	}

	//文件下载:localhost:8080/lessonData/downloadFile?dataName=1.txt
	@RequestMapping("/downloadFile")
	private String downloadFile(HttpServletResponse response,String dataName) {
		//↓获取路径
		String urlPath = Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1);
		//↑获取路径
		String downloadFilePath = DOWNLOAD_LOCATION;//被下载的文件在服务器中的路径
		String fileName = lessonDataRepository.findDataName(dataName);
		File file = new File(urlPath+downloadFilePath+fileName);
		System.out.println(urlPath+downloadFilePath+fileName);//测试
		if (file.exists()) {
			response.setContentType("application/force-download");//设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream outputStream = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					outputStream.write(buffer, 0, i);
					i = bis.read(buffer);
				}
				return "下载成功";
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "下载失败";
	}
}
