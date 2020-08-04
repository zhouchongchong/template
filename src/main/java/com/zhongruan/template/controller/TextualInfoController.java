package com.zhongruan.template.controller;

import com.zhongruan.template.entity.TemplateInfo;
import com.zhongruan.template.entity.TextualInfo;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.TextualInfoService;
import com.zhongruan.template.util.StringUtil;
import com.zhongruan.template.vo.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

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

	@ResponseBody
	@GetMapping("/findAll")
	public ResultData findAll() {
		List<TextualInfo> all = textualInfoService.getAll();


		return ResultData.success(all);
	}

	//通过名称获取模板信息
	@GetMapping("/findByName")
	@ResponseBody
	public ResultData findByName(@RequestParam(value = "templateName") String templateName) {
		List<TextualInfo> templateInfos = textualInfoService.findByName(templateName);
		return ResultData.success(templateInfos);
	}

	@GetMapping("/download")
	public void downloadWord(HttpServletRequest request, HttpServletResponse response, @RequestParam int wordId){
		final TextualInfo word = textualInfoService.findById(wordId);

		if (StringUtils.isEmpty(word)){
			return ;
		}

		String fileName = word.getTextualName();

		final File file = new File(word.getTextualUrl());
		log.info("下载文件：{},path:{}",fileName,file);
		if (file.exists()){
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=" + LocalDate.now() + StringUtil.randomStr() + Constant.SUFF_DOC);

			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				final ServletOutputStream os = response.getOutputStream();
				int read = bis.read(buffer);

				while (read != -1){
					os.write(buffer,0,read);
					read = bis.read(buffer);
				}
			}catch (Exception e){
				log.error("下载文件失败：{}",e.getMessage());
			}finally {
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
