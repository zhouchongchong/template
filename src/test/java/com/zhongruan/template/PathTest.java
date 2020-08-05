package com.zhongruan.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhongruan.template.entity.DatabaseInfo;
import com.zhongruan.template.util.FileUtil;
import com.zhongruan.template.vo.Constant;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.junit.Test;
import sun.reflect.generics.tree.VoidDescriptor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Author: zhouchong
 * Created by 17578 on 22:36 2020/8/2.
 * @Description:
 */
public class PathTest {
	@Test
	public void testPath(){
		String filePath = "d:/file/html/data.html";
		final int indexOf;
		if ((indexOf = filePath.indexOf(".")) != -1 && filePath.substring(indexOf+1).equals("htm")){
			System.out.println(filePath);
		}
	}
	@Test
	public void testFormat(){
		FileUtil.replaceTxtByStr("D:\\work_data\\data_w2.htm","D:\\work_data\\data_w222.html", Constant.ASTERISK,
				Constant.HTML_REPLACE,true, Constant.APPEND_STR);
		final String html3 = StringEscapeUtils.unescapeHtml4(Constant.HTML_REPLACE);
		System.out.println(html3);

	}
	@Test
	public void testChar(){
		String str = "d:/file/ftl/1596427857866.ftl";
		final int i = str.lastIndexOf("/");
		final File file = new File(str);
		final String parent = file.getParent();
		System.out.println(parent);
		final String name = file.getName();
		System.out.println(name);
		System.out.println(str.substring(0,i));
		System.out.println(str.substring(i + 1));
	}

	@Test
	public void testCreateWord() throws IOException, TemplateException {
		final HashMap<String, Object> word = new HashMap<>();

		for (int i = 0;i < 7; i++){
			String key = String.format(Constant.MARK_REPLACE,i);
			System.out.println(key);
			word.put(key,i << 2);
		}
		String ftlPath = "d:/file/ftl/1596427857866.ftl";
		String wordPath = "d:/file/word/" + System.currentTimeMillis() + ".doc";
		System.out.println(wordPath);

		FileUtil.createWord(ftlPath,wordPath,word);
	}

	@Test
	public void testBeanUtil(){
		final DatabaseInfo databaseInfo = new DatabaseInfo();
		databaseInfo.setDatabaseUrl("www.baidu.com");
		databaseInfo.setDatabaseType("mysql");
		databaseInfo.setDatabaseName("百度数据库");
		final String jsonString = JSONObject.toJSONString(databaseInfo);
		final DatabaseInfo databaseInfo2 = JSONObject.parseObject(jsonString).toJavaObject(DatabaseInfo.class);
		final DatabaseInfo databaseInfo1 = JSONObject.parseObject(jsonString, DatabaseInfo.class);
		System.out.println(jsonString);
	}
	@Test
	public void testSpilt(){
		String str = "周冲测试1.doc";
		final String[] split = str.split("\\.");
		System.out.println(split);
	}


}
