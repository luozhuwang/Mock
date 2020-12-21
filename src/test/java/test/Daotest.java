package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mock.dao.ICategoryDao;
import com.mock.dao.ILogDao;
import com.mock.dao.IUrlDao;
import com.mock.dao.IUrlRespDao;
import com.mock.dao.IUserDao;
import com.mock.entity.ICategoryEntity;
import com.mock.entity.ILogEntity;
import com.mock.entity.IUrlEntity;
import com.mock.entity.IUserEntity;
import com.mock.entity.iChart;
import com.mock.tool.CustomerContextHolder;
import com.mock.tool.DateFormat;
import com.mock.tool.RegexUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class Daotest {

	@Autowired
	private IUrlDao Iurdao;
	
	@Autowired
	private ILogDao iLogdao;
	
	@Autowired
	private IUserDao iUserDao;
	
	@Autowired
	private IUrlRespDao iUrlRespDao;
	
	@Autowired
	private ICategoryDao iCategoryDao;
	
	
	@Test
	public void dataTest() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_DEFAULT);
		
//		ICategoryEntity iCategoryEntity=new ICategoryEntity();
//		iCategoryEntity.setId(2);
//		iCategoryEntity.setName("保单接口");
//		iCategoryEntity.setRemark("支付相关页面");
//		iCategoryEntity.setCreateBy(2);
//		iCategoryEntity.setUpdateBy(4);
//		iCategoryDao.insertCategory(iCategoryEntity);
		
//		iCategoryDao.updateCategory(iCategoryEntity);
		
//		List<ICategoryEntity> iCategoryEntitys= iCategoryDao.selectCategory(iCategoryEntity);
//		for(ICategoryEntity iCategory:iCategoryEntitys) {
//			System.out.println(iCategory.getCreateTime());
//		}
		ICategoryEntity iCategoryEntity	=iCategoryDao.selectCategoryById(1);
		System.out.println(iCategoryEntity.getName());
				
//		IUrlEntity mockUrlResp=new IUrlEntity();
//		mockUrlResp.setId(56);
//		mockUrlResp.setUrlId(58);
//		mockUrlResp.setRequestMethod("POST");
//		mockUrlResp.setParamType(3);
//		mockUrlResp.setDelay(2);
//		mockUrlResp.setRequestHeader("header123");
//		mockUrlResp.setRequestParam("{\"name\":\"张三\"}");
//		mockUrlResp.setUpdateBy(9);
//		mockUrlResp.setResponseHeader("ResponseHeader");
//		mockUrlResp.setName("{path}测试");
//		mockUrlResp.setContent("Content");
//		mockUrlResp.setStatusCode(400);

//		IUrlEntity mockUrlResp=iUrlRespDao.selectMockRespById(mockUrlResp);
		
//		iUrlRespDao.insertMockResp(mockUrlResp);
//		iUrlRespDao.updateMockResp(mockUrlResp);
		
//		System.out.println(mockUrlResp.getContent());
//		List<IUrlEntity> urls=iUrlRespDao.selectMockResp(1);
//		for(IUrlEntity url:urls) {
//			System.out.println(url.getName());
//			System.out.println(url.getRequestHeader());
//			System.out.println(url.getContent());
//			System.out.println(url.getResponseHeader());
//		}
//		iUrlRespDao.deleteMockRespById(1);
		
//		ILogEntity iLogEntity=new ILogEntity();
//		List<iChart> iCharts=new ArrayList<iChart>();
//		List<iChart> iChartsB=new ArrayList<iChart>();
//		String begin="2020-10-20";
//		String End="2020-10-23";
//		iLogEntity.setBeginTime("2020-11-20 00:00:00");
//		iLogEntity.setEndTime("2020-11-23 23:59:59");
//		List<Map<String ,Object>> ll= iLogdao.selectMockLogListByDate(iLogEntity);
//		if(ll.isEmpty()) {
//			System.out.println("按日期统计为空");
//		}else {			
//			for(Map<String ,Object> map:ll) {
//				System.out.println(map.get("date"));
//				System.out.println(map.get("count"));
//				String date1=map.get("date").toString();
//				String count=map.get("count").toString();
//				iChart chart=new iChart();
//				chart.setKey(date1);
//				chart.setValue(count);
//				iChartsB.add(chart);
//			}
//		}
//		
//		int between=DateFormat.daysBetween(begin, End);
//		for(int i=0;i<=between;i++) {
//			String date=DateFormat.DateUtil(begin,"yyyy-MM-dd","day",i);
//			iChart chart=new iChart();
//			chart.setKey(date);
//			chart.setValue("0");
//			if(!ll.isEmpty()) {				
//				for(iChart ic:iChartsB) {
//					if(ic.getKey().equals(date)) {
//						chart.setValue(ic.getValue());
//					}
//				}
//			}			
//			iCharts.add(chart);
//		}
//		
//		
//		
//		for(iChart ic:iCharts ) {
//			System.out.println(ic.getKey());
//			System.out.println(ic.getValue());
//		}
//		
		
//		List<Map<String ,Object>> ll2= iLogdao.selectMockLogListByName(iLogEntity);
//		for(Map<String ,Object> map:ll2) {
//			System.err.println(map.get("name"));
//			System.err.println(map.get("count"));
//		}
		
//		IUrlEntity mockUrl=new IUrlEntity();
//		mockUrl.setUrlId(54);
//		mockUrl.setUrl("/testcc/{path}");
//		mockUrl.setDescription("默认返回体");
//		mockUrl.setName("{path}测试11");
//		mockUrl.setContent("11{\"code\":0,\"data\":[\"添加产品：平安意外险1-6类（惠享版）\"],\"msg\":\"success\",\"errMsg\":null}");
//		mockUrl.setStatusCode(400);
//		mockUrl.setUpdateBy(2);
//		Iurdao.insertMockUrl(mockUrl);
//		IUrlEntity mockUrl=Iurdao.selectMockUrlById(51);
//		System.out.println(mockUrl.getUrl());
//		System.out.println(mockUrl.getName());
//		System.out.println(mockUrl.getDescription());
//		List<IUrlEntity> urls=Iurdao.selectMockUrlList(null);
//		for(IUrlEntity url: urls) {
//			System.out.println(url.getName());
//		}
//		IUrlEntity mockUrl=Iurdao.selectMockUrlByUrl("/testURL");
//		System.out.println(mockUrl.getUrl());
//		System.out.println(mockUrl.getName());
//		System.out.println(mockUrl.getDescription());
//		Iurdao.updateMockUrl(mockUrl);
//		Iurdao.deleteMockUrlById(54);
		
		
//		ILogEntity iLogEntity=new ILogEntity();
//		iLogEntity.setRequestIp("192.168.101.13");
//		iLogEntity.setRequestUrl("/path");
//		iLogEntity.setRequestMethod("GET");
//		iLogEntity.setRequestDetail("W12313");
//		iLogEntity.setRequestHeader("{\"code\": 0,\"data\": [\"添加产品：平安小顽童学平险2020\"],\"msg\": \"success\",\"errMsg\": null}");
//		iLogEntity.setResponseHeader("Q123");
//		iLogEntity.setResponseDetail("qwer");
//		iLogdao.insertMockLog(iLogEntity);
//		List<ILogEntity> ilogEntitys= iLogdao.selectMockLogList(null);
//		for(ILogEntity iLog :ilogEntitys) {
//			System.err.println(iLog.getRequestUrl());
//		}
//		ILogEntity iLog=iLogdao.selectMockLogById(1);
//		System.err.println(iLog.getRequestUrl());
		
//		IUserEntity IUser=new IUserEntity();
//		IUser.setLoginName("luo");
//		IUser.setUserId(2);
//		IUser.setUserName("罗三");
//		IUser.setUpdateBy(2);
//		IUser.setStatus(1);
//		IUser.setPassword("0000");
//		IUser.setPhone("13800138000");
//		IUser.setEmail("abc@163.com");
//		IUser.setSex(0);
//		IUser.setLoginIp("127.0.0.1");
//		IUser.setLoginDate("2020-10-21 16:47:10");
//		iUserDao.register(IUser);
//		System.out.println(iUserDao.checkLoginName("luo"));
//		List<IUserEntity> iUsers=iUserDao.selectUserList(null);
//		for(IUserEntity iUser:iUsers) {
//			System.out.println(iUser.getUserName());
//		}
//		IUserEntity iUser=iUserDao.selectUserById(2);
//		System.out.println(iUser.getUserName());
//		iUserDao.resetUserPwd(2,"456789");
//		iUserDao.updateUser(IUser);
//		iUserDao.delUserById(2);
	}
	
	
}
