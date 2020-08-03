package com.zhongruan.template.vo;

/**
 * @Author: zhouchong
 * Created by 17578 on 22:59 2020/8/2.
 * @Description:
 */
public class Constant {
	public static final String SUFF_HTML = ".html";
	public static final String DOT = ".";
	public static final String ASTERISK = "$";

	public static String HTML_REPLACE = "<a class=\"a\" href=\"javascript:void(0)\" value=\"%s\" onclick=\"alert('方式1, 执行一段代码.')\">X</a>";
	public static String APPEND_STR = "<script>\n" +
			"  let targetA = document.getElementsByClassName(\"a\")[0]\n" +
			"  targetA.onclick = function(){\n" +
			"    window.parent.a()\n" +
			"  }\n" +
			"</script>";
}
