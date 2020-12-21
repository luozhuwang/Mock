package com.mock.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class FileUtils {
	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);


	/**
	 * 将text文件写入到指定文件
	 * @param	text
	 * @param	file
	 * */
	public static void WriteStrToFile(String text, String file) {
		File outFile = CreateFile_Del(file);
		try {
			FileOutputStream	fos = new FileOutputStream(outFile);
			// 跟据网站不同的编码格式，需要进行设置
			Writer os = new OutputStreamWriter(fos, Constant.UTF_8);
			os.write(text);
			os.flush();
			os.close();
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			logger.info("文件没有找到！");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			logger.info("文件编码格式错误");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("IO读写出错");
			e.printStackTrace();
		}
		
	}
	/**
	 * 如果文件存在，直接在文件后进行追加，如果文件不存在，创建文件
	 * @param	fileName	文件名
	 * @param	content		文件内容
	 * @param	isappend	是否往文件追加
	 * */
	public static void AppendFile(String fileName, String content,boolean isappend) {
		try {
			File outFile = CreateFile(fileName);			
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(outFile, isappend);
			writer.write(content);	
			writer.write(System.getProperty("line.separator"));	
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建文件,如果文件存在，则删除
	 * */
	public static File CreateFile_Del(String file) {		
		File outFile = new File(file);
		File pf = outFile.getParentFile();		
		try {
			if (!pf.exists()) {				
				pf.mkdirs();
				logger.info(pf+"文件夹创建成功！");
			}else{
				logger.info(pf+"文件夹已存在！");
			}
			if (!outFile.exists()) {
				outFile.createNewFile();
				logger.info(outFile + "文件创建成功！");
			} else {
				outFile.delete();
				logger.info("成功删除文件" + outFile);
				outFile.createNewFile();
				logger.info(outFile + "文件创建成功");
			}
			return outFile;
		} catch (IOException e) {
			logger.info("文件创建失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 创建文件,如果文件存在，不处理
	 * */
	public static File CreateFile(String file) {		
		File outFile = new File(file);
		File pf = outFile.getParentFile();		
		try {
			if (!pf.exists()) {				
				pf.mkdirs();
				logger.info(pf+"文件夹创建成功！");
			}else{
//				logger.info(pf+"文件夹已存在！");
			}
			if (!outFile.exists()) {
				outFile.createNewFile();
				logger.info(outFile + "文件创建成功！");
			} else {
//				logger.info(outFile + "文件夹已存在");
			}
			return outFile;
		} catch (IOException e) {
			logger.info("文件创建失败！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 创建文件,如果文件存在，不处理
	 * */
	public static File CreateFileDir(String file) {		
		File outFile = new File(file);
			if (!outFile.exists()) {				
				outFile.mkdirs();
				logger.info(outFile+"文件夹创建成功！");
			}else{
				logger.info(outFile+"文件夹已存在！");
			}			
			return outFile;
		
	}
	public static void main(String args[]){
//			String file ="./abc/abc.txt";
//			AppendFile(file, "55555", true);
//			AppendFile(file, "66666", true);
//			
			WriteStrToFile("12313", "./abc/word.txt");
		String file="E:\\apache-tomcat-8.0.36/webapps/upload/testproject/";
		CreateFileDir(file);
			
	}
}
