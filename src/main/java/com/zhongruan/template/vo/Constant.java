package com.zhongruan.template.vo;

/**
 * @Author: zhouchong
 * Created by 17578 on 22:59 2020/8/2.
 * @Description:
 */
public class Constant {
	public static final String SUFF_HTML = ".html";
	public static final String SUFF_FTL = ".ftl";
	public static final String SUFF_DOC = ".doc";
	public static final String DOT = ".";
	public static final String ASTERISK = "~";
	public static final String MAP_KEY_HTML = "htmlKey";
	public static final String MAP_KEY_XML = "xmlKey";


	public static final String SUCCESS = "success";
	public static final String FAILED = "failed";
	public static final String ERROR = "error";
	public static final String UTF_8 = "utf-8";


	public static final String FTL_REPLACE = "${rep_%s}";
	public static final String MARK_REPLACE = "rep_%s";
	public static final String HTML_REPLACE = "<a class=\"a\" href=\"javascript:void(0)\" name=\"%s\" onclick=\"alert('方式1, 执行一段代码.')\">X</a>";
	public static final String APPEND_STR = "<script>\n" +
			"  let targetA = document.getElementsByClassName('a');\n" +
			"  for(var i = 0 ; i < targetA.length; i++) {\n" +
			"    (function(i){\n" +
			"      targetA[i].onclick = function() {\n" +
			"      targetA[i].style.color = 'red'\n" +
			"        window.parent.a(targetA[i].name)\n" +
			"      }\n" +
			"    })(i)\n" +
			"  }\n" +
			"</script>";
}

