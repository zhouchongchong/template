package com.zhongruan.template.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhongruan.template.entity.TextualInfo;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.FileService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author: zhou chong
 * Created by 17578 on 17:14 2020/8/2.
 * @Description:
 */
@RestController
@RequestMapping("/fileOperate")
@Slf4j
public class FileOperateController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/upload_template_html", method = RequestMethod.POST)
	@ApiOperation(value = "上传html展示模板")
	public ResultData uploadHTMLFile(@RequestParam MultipartFile file) {
		return fileService.dealHtmlFile(file);
	}


	@RequestMapping(value = "/upload_template_xml", method = RequestMethod.POST)
	@ApiOperation(value = "上传XML 文件 word生成模板")
	@ApiImplicitParams({@ApiImplicitParam(name = "templateId", required = true)})
	public ResultData uploadXMLFile(@RequestParam MultipartFile file, @RequestParam JSONObject params) {
		return fileService.dealXMLFile(file, params.getInteger("templateId"));
	}

	@PostMapping(value = "/create_word")
	@ApiOperation(value = "根据模板 ID 创建 word 模板")
	@ApiImplicitParams({@ApiImplicitParam(name = "templateId", required = true),
			@ApiImplicitParam(name = "dbSource_id", required = true)})
	public void createWordFile(HttpServletResponse response, @RequestBody JSONObject params) throws UnsupportedEncodingException {
//	public void createWordFile(HttpServletResponse response, @RequestParam Integer templateId,@RequestParam int dbSource_id) throws UnsupportedEncodingException {
		final ResultData resultData = fileService.createWord(params.getInteger("templateId"), params.getInteger("dbSource_id"));
//		final ResultData resultData = fileService.createWord(templateId, dbSource_id);
		final TextualInfo word = (TextualInfo) resultData.getBody();
		if (StringUtils.isEmpty(word)) {
			return;
		}

		String fileName = word.getTextualName();

		final File file = new File(word.getTextualUrl());
		log.info("下载文件：{},path:{}", fileName, file);
		if (file.exists()) {
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", URLEncoder.encode(fileName, "utf-8")));

			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				final ServletOutputStream os = response.getOutputStream();
				int read = bis.read(buffer);

				while (read != -1) {
					os.write(buffer, 0, read);
					read = bis.read(buffer);
				}
			} catch (Exception e) {
				log.error("下载文件失败：{}", e.getMessage());
			} finally {
				try {
					bis.close();
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return;
		}
	}
}
