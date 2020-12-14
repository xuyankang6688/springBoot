package top.javaguo.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载Util
 * 
 * @date 2017/7/21
 * @author Guo
 *
 */
public class GuoDownloadUtil {

	/**
	 * 下载文件
	 * 
	 * @param filePath
	 *            下载路径
	 * @param fileName
	 *            文件名
	 * @param fileType
	 *            文件类型
	 * @return
	 * @throws Exception
	 */
	public static boolean downLoadFile(HttpServletRequest request, String filePath, HttpServletResponse response,
			String fileName, String fileType) throws Exception {
		boolean flag = true;
		File file = new File(filePath);// 根据文件路径获得File文件
		// 设置文件类型(这样设置就不止是下Excel文件了，一举多得)
		if ("pdf".equals(fileType)) {
			response.setContentType("application/pdf;charset=GBK");
		} else if ("xls".equals(fileType)) {
			response.setContentType("application/msexcel;charset=GBK");
		} else if ("doc".equals(fileType)) {
			response.setContentType("application/msword;charset=GBK");
		} else if ("zip".equals(fileType)) {
			response.setContentType("application/ostet-stream;charset=GBK");
		}
		// 文件导出在浏览器中中文文件名乱码解决
		String filename = fileName + new Date().getTime() + ".zip";
		String userAgent = request.getHeader("User-Agent");
		// filename.getBytes("UTF-8")处理safari的乱码问题
		byte[] bytes = userAgent.contains("MSIE") ? filename.getBytes() : filename.getBytes("UTF-8");
		// 各浏览器基本都支持ISO编码
		filename = new String(bytes, "ISO-8859-1");
		// 文件名外的双引号处理firefox的空格截断问题
		response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", filename));

		byte[] buffer = new byte[4096];// 缓冲区
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		try {
			output = new BufferedOutputStream(response.getOutputStream());
			input = new BufferedInputStream(new FileInputStream(file));
			int n = -1;
			// 遍历，开始下载
			while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			}
			output.flush(); // 不可少
			response.flushBuffer();// 不可少
		} catch (Exception e) {
			// 异常自己捕捉
			flag = false;
		} finally {
			// 关闭流，不可少
			if (input != null)
				input.close();
			if (output != null)
				output.close();
		}
		return flag;
	}

}