package com.tuxt.mytest.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @author jiaotd@asiainfo.com
 * @since 2016年3月24日 下午4:43:18
 */
public class ConvertCharacter {
private final static String targetCharset="UTF-8";
	/**
	 * 遍历文件夹，获取文件
	 * 
	 * @author jiaotd@asiainfo.com
	 * @since 2016年3月24日 下午4:49:09
	 * @param file
	 * @throws Exception
	 */
	public static void checkout(File file) throws Exception {
		if (file.exists()) {
			if (file.isDirectory()) {
				if(!file.getName().startsWith(".svn")){
					File[] listFiles = file.listFiles();
					for (File subfile : listFiles) {
						if (subfile.isFile()) {
							// 进行文件操作
							convertCharacter(subfile);
						} else if (subfile.isDirectory()) {
							checkout(subfile);
						}
					}
				}
			} else {
				convertCharacter(file);
			}
		}
	}

	/**
	 * 转换字符编码
	 * 
	 * @author jiaotd@asiainfo.com
	 * @since 2016年3月24日 下午4:49:53
	 * @param file
	 * @throws IOException
	 */
	public static void convertCharacter(File file) throws IOException {
		if(file.getName().endsWith(".gif") || file.getName().endsWith(".jpg") || file.getName().endsWith(".class") 
				|| file.getName().endsWith(".png") || file.getName().endsWith(".jar") || file.getName().endsWith(".xml")
				|| file.getName().endsWith(".properties") ){
			return;
		}
		// 文件为空时，Files.move会报错
		if (file.length() > 0) {
			String chartset = getFilecharset(file);
			if(targetCharset.equals(chartset)){
				return;
			}
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), chartset);// 获取文件格式
			BufferedReader reader = new BufferedReader(isr);

			File out = new File(file.getAbsolutePath() + ".bak");
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(out), targetCharset);
			BufferedWriter bw = new BufferedWriter(osw);

			String s = "";
			while ((s = reader.readLine()) != null) {
				osw.write(new String(s.getBytes(), targetCharset) + "\r\n");
			}
			bw.close();
			osw.close();
			reader.close();
			isr.close();
			// StandardCopyOption.ATOMIC_MOVE 将本操作标记为原子性操作，要么完成移动，要么源文件保持原来位置
			Files.move(out.toPath(), file.toPath(),	StandardCopyOption.ATOMIC_MOVE);
			System.out.println(out.getName());
		}
	}

	/**
	 * 获取文件编码
	 * @author jiaotd@asiainfo.com
	 * @since 2016年3月24日 下午6:01:44
	 * @param sourceFile
	 * @return
	 */
	public static String getFilecharset(File sourceFile) {
		String charset = "GBK";
		byte[] first3Bytes = new byte[3];
		BufferedInputStream bis = null; 
		try {
			boolean checked = false;
			bis = new BufferedInputStream(new FileInputStream(sourceFile));
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1) {
				return charset; // 文件编码为 ANSI
			} else if (first3Bytes[0] == (byte) 0xFF
					&& first3Bytes[1] == (byte) 0xFE) {
				charset = "UTF-16LE"; // 文件编码为 Unicode
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE
					&& first3Bytes[1] == (byte) 0xFF) {
				charset = "UTF-16BE"; // 文件编码为 Unicode big endian
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF
					&& first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				charset = "UTF-8"; // 文件编码为 UTF-8
				checked = true;
			}
			bis.reset();
			if (!checked) {
				while ((read = bis.read()) != -1) {
					if (read >= 0xF0)
						break;
					if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
							// (0x80
							// - 0xBF),也可能在GB编码内
							continue;
						else
							break;
					} else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}
			}
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != bis){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return charset;
	}

	public static void main(String[] args) throws Exception {
		File file = new File("F:/WorkSpace/MyEclipse/asiainfo/ol_java/");
		// File file = new File("F:/MyProject/HttpUtil.java");
		checkout(file);
		// convertCharacter(file);

	}
}
