package com.zhongruan.template;

import com.zhongruan.template.util.FileUtil;
import com.zhongruan.template.vo.Constant;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

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


}
