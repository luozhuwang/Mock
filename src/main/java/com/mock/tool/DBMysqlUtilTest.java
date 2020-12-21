package com.mock.tool;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

public class DBMysqlUtilTest {
	DBMysqlUtil DBUtil =null;
    private   String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    private   String DBURL = "jdbc:mysql://62.234.165.48:3306/rocket_wxm?useUnicode=true&characterEncoding=utf8"; //
    private   String DBUSER = "rocket_op";
    private   String DBPASSWORD = "root123456";
	
//    private   String DBURL = "jdbc:mysql://188.131.176.150:3306/rocket_wxm?useUnicode=true&characterEncoding=utf8"; //
//    private   String DBUSER = "rocket";
//    private   String DBPASSWORD = "MeKH9f$VyyN";

	public void init(){
//		 DBUtil=new DBMysqlUtil(DBDRIVER,DBURL,DBUSER,DBPASSWORD);
//		 DBUtil=new DBMysqlUtil();
	}
	


//	@Test(description="62.234.165.48通过手机号查询cuser_phone表批量修改insurance_policyinfo ex_cuserid数据")
	public void testquery() {
//		String file ="C:\\testTools\\phone_sql.txt";
//		AppendFile(file, "55555", true);
//		FileUtils fileutil=new FileUtils();
		DBUtil=new DBMysqlUtil(DBDRIVER,DBURL,DBUSER,DBPASSWORD);
		
		String fileName = "C:\\testTools\\phone.txt";
		ReadFromFile read=new ReadFromFile();
		String abc=read.readTxtFile(fileName);
		String ss []=abc.split(",");		
		for(String phone:ss) {
//			System.out.println("phone:"+phone);
			String updatesql="";
			String sql="select * from cuser_phone where phone='"+phone+"'";
			ResultSet rs=DBUtil.select(sql);	
			try {
				while(rs.next()){
//					String wx_name=rs.getString("wx_name");
//					System.out.println("wx_name:"+wx_name);
					String cuser_id=rs.getString("cuser_id");
					String update = "update insurance_policyinfo set  ex_cuserid="+cuser_id+" where ins_from_phone ='"+phone+"';";
//					fileutil.AppendFile(file, update, true);
//					System.out.println("cuser_id:"+cuser_id);
					System.err.println(update);
				}
				DBUtil.executeupdate(updatesql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		DBUtil.close();		
	}
	
	@Test
	public void testcuser_phone() {
		DBUtil=new DBMysqlUtil(DBDRIVER,DBURL,DBUSER,DBPASSWORD);
		
		String fileName = "C:\\testTools\\phone.txt";
		ReadFromFile read=new ReadFromFile();
		String abc=read.readTxtFile(fileName);
		String ss []=abc.split(",");		
		for(String phone:ss) {
//			System.out.println("phone:"+phone);
			String sql="select * from cuser_phone where phone='"+phone+"'";
			ResultSet rs=DBUtil.select(sql);	
			try {
				while(rs.next()){
						int cuser_id=rs.getInt("cuser_id");
		    		   System.err.println("cuser_id:"+cuser_id);					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		DBUtil.close();		
	}
	
//	@Test(description="62.234.165.48通过手机号查询cuser表批量修改insurance_policyinfo ex_cuserid数据")
	public void testupdate222() {
		DBUtil=new DBMysqlUtil();
//		String phone="13069253009";
		String fileName = "C:\\testTools\\modify.txt";
		ReadFromFile read=new ReadFromFile();
		String abc=read.readTxtFile(fileName);
		String ss []=abc.split(",");		
		for(String phone:ss) {
			
			String sql="select * from cuser where phone='"+phone+"'";
			ResultSet rs=DBUtil.select(sql);	
			String updatesql="";
			try {
				while(rs.next()){					
					String cuser_id=rs.getString("id");
					updatesql = "update insurance_policyinfo set  ex_cuserid="+cuser_id+" where ins_from_phone ='"+phone+"';";					
					System.err.println(updatesql);
				}
				DBUtil.executeupdate(updatesql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	

//	@Test(description="188.131.176.150查询")
	public void testGetCount(){
		DBUtil=new DBMysqlUtil(DBDRIVER,DBURL,DBUSER,DBPASSWORD);
		String sql="select * from rocketai_wechat.activity_msg where user_phone='15915449165'";
		int num=DBUtil.getRecordCount(sql);
		System.out.println("查询数量："+num);
	}
	
	
//	@Test(description="批量执行SQL")
	public void testBatch(){
		DBUtil=new DBMysqlUtil(DBDRIVER,DBURL,DBUSER,DBPASSWORD);
		String sql1="select * from rocketai_wechat.activity_msg where user_phone='15915449165'";
		String sql2="select * from rocketai_wechat.activity_submit where mobile='15915449165';";
		List<String>  sqlList=new ArrayList<String>();
		sqlList.add(sql1);
		sqlList.add(sql2);
		
		DBUtil.executeBatch(sqlList);
		
		
	}
	
//	@Test(description="插入")
	public void testinsert() throws Exception{
		String sql="insert into users(name,age) values('lisi',22);";
		System.out.println("执行SQL："+sql);
		
		
		DBUtil.executeupdate(sql);
		
	}
	
//	@Test(description="删除")
	public void testdelete() throws Exception{
		String sql="delete from  users where name ='lisi';";
		System.out.println("执行SQL："+sql);	
		DBUtil.executeupdate(sql);
		
	}
	
//	@Test(description="修改")
	public void testupdate() throws Exception{
		DBUtil=new DBMysqlUtil(DBDRIVER,DBURL,DBUSER,DBPASSWORD);
		
		String sql="update insurance_policyinfo set  ex_cuserid=101338 where ins_from_phone ='13955343773';";
		System.out.println("执行SQL："+sql);
				
		DBUtil.executeupdate(sql);
		
	}
	
//	@Test
	public void testEnv(){
		DBUtil=new DBMysqlUtil(DBDRIVER,DBURL,DBUSER,DBPASSWORD);
		
		String sql="select * from rocketai_wechat.activity_msg where user_phone='15915449165'";
		ResultSet rs=DBUtil.select(sql);	
		try {
			while(rs.next()){
				String unionid=rs.getString("unionid");
				System.out.println("unionid:"+unionid);
				String user_wx=rs.getString("user_wx");
				System.out.println("user_wx"+user_wx);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close();	
		
	}
	
}
