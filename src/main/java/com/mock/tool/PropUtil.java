package com.mock.tool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PropUtil {
	private static Logger logger = LoggerFactory.getLogger(PropUtil.class);
	private static Properties properties = null;
	private FileInputStream fileinput=null;

	/**
	 * 加载配置文件
	 * @param String path
	 * */
	public PropUtil(String path) {
		initialize(path);
	}
	
	private void initialize(String path){		
		try {
			fileinput=new FileInputStream(path);
			properties = new Properties();
			properties.load(fileinput);
		logger.info("完成配置文件的加载------>："+path);
		} catch (FileNotFoundException e) {
			logger.debug("文件加载失败------>");
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}finally{
			try {
				if(fileinput !=null){					
					fileinput.close();	
					logger.debug("关闭文件成功------>");
				}
			} catch (IOException e) {
				logger.info("关闭文件异常------>");
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * get specified key in config files
	 * 
	 * @param key
	 *            the key name to get value
	 */
	public String get(String key) {
		String keyValue = null;
		if (properties.containsKey(key)) {
			keyValue = (String) properties.get(key);
		}
		return keyValue;
	}
	
	
	public static void main(String args[]){
		String path="./config/db.properties";
				
		PropUtil PropUtil = new PropUtil(path);
		String dbDriver = PropUtil.get("Username");
		String dbConnectionURL = PropUtil.get("Username");
		
		System.out.println("sql_url:"+dbDriver);
		System.out.println("sql_user:"+dbConnectionURL);
	}
}
