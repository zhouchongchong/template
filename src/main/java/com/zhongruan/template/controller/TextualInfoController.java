package com.zhongruan.template.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhongruan.template.entity.TextualInfo;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.TextualInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: zhou
 * Created by zhou chong on 10:56 2020/8/4.
 * @Description:
 */
@RestController
@RequestMapping("/textualInfo")
@Slf4j
public class TextualInfoController {
	@Autowired
	private TextualInfoService textualInfoService;

	@GetMapping("/findAll")
	@ApiOperation("获取所有电文信息")
	public ResultData findAll() {
		List<TextualInfo> all = textualInfoService.getAll();
		return ResultData.success(all);
	}

	//通过名称获取电文信息
	@PostMapping("/findByName")
	@ApiOperation("获取所有电文信息")
	@ApiImplicitParams({@ApiImplicitParam(name = "textualName", required = true)})
	public ResultData findByName(@RequestBody JSONObject jsonObject) {
		String textualName = jsonObject.getString("textualName");
		List<TextualInfo> textualNameInfos = textualInfoService.findByName(textualName);
		return ResultData.success(textualNameInfos);
	}

	//下载接口
	@PostMapping("/download")
	@ApiOperation("通过文档id对文档进行下载")
	@ApiImplicitParams({@ApiImplicitParam(name = "wordId", required = true)})
	public void downloadWord(HttpServletResponse response, @RequestBody JSONObject jsonObject) throws UnsupportedEncodingException {
//	 @GetMapping("/download")
//	 @ApiOperation("通过文档id对文档进行下载")
//	 @ApiImplicitParams({@ApiImplicitParam(name = "wordId",required = true)})
//	 public void downloadWord(HttpServletResponse response, int wordId) throws UnsupportedEncodingException {
		Integer wordId = jsonObject.getInteger("wordId");
		final TextualInfo word = textualInfoService.findById(wordId);

		if (StringUtils.isEmpty(word)) {
			return;
		}

		String fileName = word.getTextualName();

		final File file = new File(word.getTextualUrl());
		log.info("下载文件：{},path:{}", fileName, file);
		if (file.exists()) {
			response.setContentType("application/octet-stream");
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
		}
	}
}
