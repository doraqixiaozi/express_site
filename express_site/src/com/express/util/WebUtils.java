package com.express.util;

import java.io.File;
import java.util.UUID;

/**
* @author 几米 E-mail:862965251@qq.com
* @version 创建时间：2018年11月30日 下午5:40:17
* 类说明
*/
public class WebUtils {
	public static String makeFilename(String filename) {
		return UUID.randomUUID().toString() + "_" + filename;
	}

	public static String makeRealPath(String filename, String hostpath) {
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf;
		int dir2 = (hashcode & 0xf0) >> 4;
		String realPath = hostpath + "\\" + dir1 + "\\" + dir2+"\\"+filename;
		File file=new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		return realPath;
	}

	public static String makeVirtualPath(String filename) {
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf;
		int dir2 = (hashcode & 0xf0) >> 4;
		String realPath =dir1 + "/" + dir2+"/"+filename;
		return realPath;
	}
}
