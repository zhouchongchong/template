package com.zhongruan.template.util;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zhongruan.template.vo.Constant;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @Author: 周冲
 * Created by 17578 on 21:03 2020/8/2.
 * @Description:
 */
@Slf4j
public class FileUtil {
	/**
	 * 生成 word 文档
	 *
	 * @param ftlPath  ftl 在文件中的绝对路径
	 * @param wordFile word 生成的位置
	 * @param word     传入的占位替代
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void createWord(String ftlPath, String wordFile, Map<String, Object> word)
			throws IOException, TemplateException {

		final Configuration configuration = new Configuration(new Version("2.3.0"));
		final File file = new File(wordFile);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		final File ftlFile = new File(ftlPath);

		configuration.setDefaultEncoding(Constant.UTF_8);
		configuration.setDirectoryForTemplateLoading(ftlFile.getParentFile());

		final Template template = configuration.getTemplate(ftlFile.getName(), Constant.UTF_8);

		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Constant.UTF_8), 10240);
		template.process(word, out);
		out.close();
	}

	/**
	 * 保存上传的文件
	 *
	 * @param file     文件
	 * @param savePath 保存路径
	 * @return
	 */
	public static String saveFile(MultipartFile file, String savePath) {

		if (file.isEmpty()) {
			return null;
		}
		String path = "";
		try {

			String fileName = file.getOriginalFilename();
			final String suffixName = fileName.substring(fileName.lastIndexOf("."));
			log.info("上传的文件名：{}  文件后缀为：{}", fileName, suffixName);
			path = savePath + System.currentTimeMillis() + suffixName;
			final File dest = new File(path);

			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}

			file.transferTo(dest);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("上传 HTML 文件失败");
		} finally {
			return path;
		}

	}

	/**
	 * 解压到指定目录
	 */
	public static Map<String,String> unZipFiles(String zipPath, String descDir) throws IOException {
		return unZipFiles(new File(zipPath), descDir);
	}

	/**
	 * 解压文件到指定目录
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,String> unZipFiles(File zipFile, String descDir) throws IOException {
		File pathFile = new File(descDir);
		final HashMap<String, String> retMap = new HashMap<>();
		String htmlFilePath = null;
		String xmlFilePath = null;
		log.info("***********开始解压文件：{}*********", zipFile.getName());
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		//解决zip文件中有中文目录或者中文文件
		ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
		for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");

			//判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			//判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			//输出文件路径信息
			log.info("解压文件路径：{}", outPath);
			final int indexOf;
			if ((indexOf = outPath.indexOf(Constant.DOT)) != -1 && (outPath.substring(indexOf + 1).equals("html")
					|| outPath.substring(indexOf + 1).equals("htm"))) {
				htmlFilePath = outPath;
			} else if (outPath.substring(indexOf + 1).equals("xml")){
				final File xmlFile = new File(outPath);
				if (!xmlFile.getName().equals("colorschememapping.xml") && !xmlFile.getName().equals("filelist.xml")){
					xmlFilePath = outPath;
				}
			}

			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
		log.info("******************文件：{}，解压到：{}完毕********************", zipFile.getName(), descDir);
		retMap.put(Constant.MAP_KEY_HTML,htmlFilePath);
		retMap.put(Constant.MAP_KEY_XML,xmlFilePath);
		log.info("**** html file :{},xml file :{}",htmlFilePath,xmlFilePath);
		return retMap;
	}

	/**
	 * 替换文件中的特定字段
	 *
	 * @param filePath    文件路径
	 * @param newFilePath 新文件路径
	 * @param oldStr      老字符
	 */
	public static int replaceTxtByStr(String filePath, String newFilePath, String oldStr, String newString, boolean isAppend, String appendStr) {
		String temp = "";
		int len = oldStr.length();
		int size_$ = 0;

		try {
			File file = new File(filePath);
			File newFile = new File(newFilePath);
			if (!newFile.getParentFile().exists()) {
				newFile.getParentFile().mkdirs();
			}
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();
			while ((temp = br.readLine()) != null) {
				if (temp.contains(oldStr)) {
					StringBuffer tempBuf = new StringBuffer();
					tempBuf.append(temp);
					int index = temp.indexOf(oldStr);
					while (tempBuf.toString().contains(oldStr)) {
						tempBuf = tempBuf.replace(index, index + len, String.format(newString, size_$++));
						index = tempBuf.toString().indexOf(oldStr);
					}
					buf.append(tempBuf);
					tempBuf.setLength(0);
				} else {
					buf.append(temp);
				}

				buf = buf.append(System.getProperty("line.separator"));
			}
			if (isAppend) {
				buf.append(appendStr);
			}
			br.close();
			FileOutputStream fos = new FileOutputStream(newFile);
			PrintWriter pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
			size_$ = 0;
		} finally {
			return size_$;
		}
	}


	public static String readFileContent(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer sbf = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				sbf.append(tempStr);
			}
			reader.close();
			return sbf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return sbf.toString();
	}

}
