package com.mock.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;

public class ReadFromFile {
	

	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 */
	public static void readFileByBytes(String fileName) {
		File file = new File(fileName);
		InputStream in = null;
		try {
			System.out.println("以字节为单位读取文件内容，一次读一个字节：");
			System.out.println();
			// 一次读一个字节
			in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte = in.read()) != -1) {
				 System.out.println(tempbyte);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			System.out.println("以字节为单位读取文件内容，一次读多个字节：");
			// 一次读多个字节
			byte[] tempbytes = new byte[100];
			int byteread = 0;
			in = new FileInputStream(fileName);
			ReadFromFile.showAvailableBytes(in);
			// 读入多个字节到字节数组中，byteread为一次读入的字节数
			while ((byteread = in.read(tempbytes)) != -1) {
				System.out.write(tempbytes, 0, byteread);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 */
	public static void readFileByChars(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		try {
			System.out.println("以字符为单位读取文件内容，一次读一个字节：");
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，\r\n这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
				if (((char) tempchar) != '\r') {
					System.out.println((char) tempchar);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			StringBuffer new_String = new StringBuffer();
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				new_String.append(tempString);
				line++;
			}
			System.out.println("new_String:" + new_String);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 随机读取文件内容
	 */
	public static void readFileByRandomAccess(String fileName) {
		RandomAccessFile randomFile = null;
		try {
			System.out.println("随机读取一段文件内容：");
			// 打开一个随机访问文件流，按只读方式
			randomFile = new RandomAccessFile(fileName, "r");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 读文件的起始位置
			int beginIndex = (fileLength > 4) ? 4 : 0;
			// 将读文件的开始位置移到beginIndex位置。
			randomFile.seek(beginIndex);
			byte[] bytes = new byte[10];
			int byteread = 0;
			// 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
			// 将一次读取的字节数赋给byteread
			while ((byteread = randomFile.read(bytes)) != -1) {
				System.out.write(bytes, 0, byteread);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 显示输入流中还剩的字节数
	 */
	private static void showAvailableBytes(InputStream in) {
		try {
			System.out.println("当前字节输入流中的字节数为:" + in.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将html文件转换成String
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getHtmlContent(String FileName) {
		String temp;
		BufferedReader br=null;
		StringBuffer sb = new StringBuffer();
		System.out.println("将html文件转换成String开始----->");
		if (FileName.isEmpty() || FileName == null) {
			System.out.println("html内容为空----->");
			return null;
		} else {
			try {
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(FileName), Constant.UTF_8));
				while ((temp = br.readLine()) != null) {
					sb.append(temp);
				}
			} catch (IOException e) {
				System.out.println("将html文件转换成String失败----->");
			}finally{
					try {
						if(br!=null){
							br.close();
						}
					} catch (IOException e) {
						System.out.println("关闭文件流失败----->");
				}
			}
			System.out.println("将html文件转换成String结束----->");
			return sb.toString();
		}

	}
	/**    
     * @param filePath 文件路径    
     * @return 读出的txt的内容    
     */   
	public static String readTxtFile(String filePath) {
		FileReader fr=null;
		BufferedReader br=null;
		StringBuffer buff=null;	
		try {
				fr = new FileReader(filePath);
				br = new BufferedReader(fr);      
				buff= new StringBuffer();      
				String temp = null;      
				while((temp = br.readLine()) != null){      
					buff.append(temp);      
				}      
				          
			} catch (FileNotFoundException e) {
				System.out.println("文件不存在");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("读取异常");
			}finally{
					try {
						if(br!=null){
						br.close();
						}if(fr!=null){
							fr.close();
						}
					} catch (IOException e) {
						System.out.println("关闭文件异常");
					} 
			}      
	        return buff.toString();             

	}

	public static void main(String[] args) {
		String fileName = "C:\\testTools\\phone.txt";
		String abc=readTxtFile(fileName);
		String ss []=abc.split(",");
		System.out.println("abc:"+ss.length);
		for(String phone:ss) {
			System.out.println("phone:"+phone);
		}
		// ReadFromFile.readFileByBytes(fileName);
		// ReadFromFile.readFileByChars(fileName);
//		System.out.println(ReadFromFile.getHtmlContent(fileName).trim());
		// ReadFromFile.readFileByLines(fileName);
		// ReadFromFile.readFileByRandomAccess(fileName);
	}
}
