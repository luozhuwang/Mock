package test;

import java.util.Iterator;
import org.apache.commons.codec.digest.Crypt;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mock.tool.JsonUtils;

public class toolsTest {
	
	@Test
	public void MD5Test() {
//		String passwd="123456";
//		System.out.println(Crypt.crypt(passwd, ""));;
		
//		System.out.println(encryptPassword("", "123456", ""));
//		String json="{\"code\":0,\"pagination\":{\"total\":2,\"current\":1,\"code\":0,\"pageSize\":10},\"list\":[{\"advisorName\":\"\\u674E\\u5E86\\u5B97\",\"businessData\":\"\",\"businessType\":1,\"category1\":\"\",\"createDate\":\"2020-11-19 20:51:03\",\"creator\":\"7\",\"currentAdviser\":0,\"currentAdviserName\":\"\",\"customerId\":\"6325\",\"eventType\":0,\"exApplicantEmail\":\"merrydoctor@163.com\",\"exApplicantGender\":\"0\",\"exBrokerSn\":\"20201119034169\",\"exCoverage\":300000000,\"exCuserid\":7620,\"exEnddate\":null,\"exHesitation\":15,\"exInsProd2\":2526,\"exInsurantIdnum\":\"510122200604070027\",\"exInsurantName\":\"\\u590F\\u8BED\\u6B23\",\"exInsurantRelation\":\"MQ\",\"exIsBxbStaff\":0,\"exObservation\":60,\"exPaymethod\":\"\\u4E00\\u6B21\\u7F34\\u6E05\",\"exProdname\":\"\\u836F\\u65E0\\u5FE7\",\"exServicefeeCent\":432,\"exServicefeePercent\":12,\"extra\":\"\",\"hasBaodan\":1,\"hesitationPeriod\":15,\"id\":\"10006068\",\"insBackFee\":0,\"insCheckDate\":\"2020-11-20 00:00:00\",\"insCompanyName\":\"\",\"insDate\":\"2020-11-19 20:50:03\",\"insExamine\":1,\"insFor\":\"[{\\\"card\\\": \\\"510122200604070027\\\", \\\"phone\\\": \\\"13541161884\\\", \\\"cardType\\\": \\\"\\u8EAB\\u4EFD\\u8BC1\\\", \\\"name\\\": \\\"\\u590F\\u8BED\\u6B23\\\", \\\"birth\\\": \\\"2006-04-07\\\"}]\",\"insFromBirth\":\"1984-10-03\",\"insFromCard\":\"510122198410030262\",\"insFromCardType\":\"\\u8EAB\\u4EFD\\u8BC1\",\"insFromName\":\"\\u4E8E\\u6797\",\"insFromPhone\":\"13541161884\",\"insHesitate\":15,\"insLimit\":\"1\\u5E74\",\"insLimitDate\":\"2021-11-20 00:00:00\",\"insNo\":\"P972000920607\",\"insPayStatus\":1,\"insPremium\":\"3000000.0\",\"insProd\":0,\"insProdName\":\"\\u836F\\u65E0\\u5FE7\",\"insSupplier\":2,\"insType\":1,\"insYearFee\":\"3600\",\"insuranceType\":\"\\u533B\\u7597\\u9669\",\"isAddGrowth\":1,\"isTest\":0,\"needSecondNotify\":0,\"observationPeriod\":60,\"paymentMethod\":1,\"phone\":\"\",\"renewalLink\":\"\",\"renewalStatus\":0,\"source\":\"qixin\",\"updateDate\":null,\"username\":\"liqingzong\",\"wx\":\"\"},{\"advisorName\":\"\\u674E\\u5E86\\u5B97\",\"businessData\":\"\",\"businessType\":1,\"category1\":\"\",\"createDate\":\"2020-11-19 20:49:08\",\"creator\":\"7\",\"currentAdviser\":0,\"currentAdviserName\":\"\",\"customerId\":\"6325\",\"eventType\":0,\"exApplicantEmail\":\"merrydoctor@163.com\",\"exApplicantGender\":\"0\",\"exBrokerSn\":\"20201119034848\",\"exCoverage\":300000000,\"exCuserid\":7620,\"exEnddate\":null,\"exHesitation\":15,\"exInsProd2\":2526,\"exInsurantIdnum\":\"510122200612310019\",\"exInsurantName\":\"\\u5170\\u5B87\\u661F\",\"exInsurantRelation\":\"MQ\",\"exIsBxbStaff\":0,\"exObservation\":60,\"exPaymethod\":\"\\u4E00\\u6B21\\u7F34\\u6E05\",\"exProdname\":\"\\u836F\\u65E0\\u5FE7\",\"exServicefeeCent\":432,\"exServicefeePercent\":12,\"extra\":\"\",\"hasBaodan\":1,\"hesitationPeriod\":15,\"id\":\"10006067\",\"insBackFee\":0,\"insCheckDate\":\"2020-11-20 00:00:00\",\"insCompanyName\":\"\",\"insDate\":\"2020-11-19 20:47:46\",\"insExamine\":1,\"insFor\":\"[{\\\"card\\\": \\\"510122200612310019\\\", \\\"phone\\\": \\\"13541161884\\\", \\\"cardType\\\": \\\"\\u8EAB\\u4EFD\\u8BC1\\\", \\\"name\\\": \\\"\\u5170\\u5B87\\u661F\\\", \\\"birth\\\": \\\"2006-12-31\\\"}]\",\"insFromBirth\":\"1984-10-03\",\"insFromCard\":\"510122198410030262\",\"insFromCardType\":\"\\u8EAB\\u4EFD\\u8BC1\",\"insFromName\":\"\\u4E8E\\u6797\",\"insFromPhone\":\"13541161884\",\"insHesitate\":15,\"insLimit\":\"1\\u5E74\",\"insLimitDate\":\"2021-11-20 00:00:00\",\"insNo\":\"P972000920603\",\"insPayStatus\":1,\"insPremium\":\"3000000.0\",\"insProd\":0,\"insProdName\":\"\\u836F\\u65E0\\u5FE7\",\"insSupplier\":2,\"insType\":1,\"insYearFee\":\"3600\",\"insuranceType\":\"\\u533B\\u7597\\u9669\",\"isAddGrowth\":1,\"isTest\":0,\"needSecondNotify\":0,\"observationPeriod\":60,\"paymentMethod\":1,\"phone\":\"\",\"renewalLink\":\"\",\"renewalStatus\":0,\"source\":\"qixin\",\"updateDate\":null,\"username\":\"liqingzong\",\"wx\":\"\"}]}";
//		String json2="{\"code\":0,\"pagination\":{\"total\":2,\"current\":1,\"code\":0,\"pageSize\":10},\"list\":[{\"advisorName\":\"\\u674E\\u5E86\\u5B97\",\"businessData\":\"\",\"businessType\":1,\"category1\":\"\",\"createDate\":\"2020-11-19 20:51:03\",\"creator\":\"7\",\"currentAdviser\":0,\"currentAdviserName\":\"\",\"customerId\":\"6325\",\"eventType\":0,\"exApplicantEmail\":\"merrydoctor@163.com\",\"exApplicantGender\":\"0\",\"exBrokerSn\":\"20201119034169\",\"exCoverage\":300000000,\"exCuserid\":7620,\"exEnddate\":null,\"exHesitation\":15,\"exInsProd2\":2526,\"exInsurantIdnum\":\"510122200604070027\",\"exInsurantName\":\"\\u590F\\u8BED\\u6B23\",\"exInsurantRelation\":\"MQ\",\"exIsBxbStaff\":0,\"exObservation\":60,\"exPaymethod\":\"\\u4E00\\u6B21\\u7F34\\u6E05\",\"exProdname\":\"\\u836F\\u65E0\\u5FE7\",\"exServicefeeCent\":432,\"exServicefeePercent\":12,\"extra\":\"\",\"hasBaodan\":1,\"hesitationPeriod\":15,\"id\":\"10006068\",\"insBackFee\":0,\"insCheckDate\":\"2020-11-20 00:00:00\",\"insCompanyName\":\"\",\"insDate\":\"2020-11-19 20:50:03\",\"insExamine\":1,\"insFor\":\"[{\\\"card\\\": \\\"510122200604070027\\\", \\\"phone\\\": \\\"13541161884\\\", \\\"cardType\\\": \\\"\\u8EAB\\u4EFD\\u8BC1\\\", \\\"name\\\": \\\"\\u590F\\u8BED\\u6B23\\\", \\\"birth\\\": \\\"2006-04-07\\\"}]\",\"insFromBirth\":\"1984-10-03\",\"insFromCard\":\"510122198410030262\",\"insFromCardType\":\"\\u8EAB\\u4EFD\\u8BC1\",\"insFromName\":\"\\u4E8E\\u6797\",\"insFromPhone\":\"13541161884\",\"insHesitate\":15,\"insLimit\":\"1\\u5E74\",\"insLimitDate\":\"2021-11-20 00:00:00\",\"insNo\":\"P972000920607\",\"insPayStatus\":1,\"insPremium\":\"3000000.0\",\"insProd\":0,\"insProdName\":\"\\u836F\\u65E0\\u5FE7\",\"insSupplier\":2,\"insType\":1,\"insYearFee\":\"3600\",\"insuranceType\":\"\\u533B\\u7597\\u9669\",\"isAddGrowth\":1,\"isTest\":0,\"needSecondNotify\":0,\"observationPeriod\":60,\"paymentMethod\":1,\"phone\":\"\",\"renewalLink\":\"\",\"renewalStatus\":0,\"source\":\"qixin\",\"updateDate\":null,\"username\":\"liqingzong\",\"wx\":\"\"},{\"advisorName\":\"\\u674E\\u5E86\\u5B97\",\"businessData\":\"\",\"businessType\":1,\"category1\":\"\",\"createDate\":\"2020-11-19 20:49:08\",\"creator\":\"7\",\"currentAdviser\":0,\"currentAdviserName\":\"\",\"customerId\":\"6325\",\"eventType\":0,\"exApplicantEmail\":\"merrydoctor@163.com\",\"exApplicantGender\":\"0\",\"exBrokerSn\":\"20201119034848\",\"exCoverage\":300000000,\"exCuserid\":7620,\"exEnddate\":null,\"exHesitation\":15,\"exInsProd2\":2526,\"exInsurantIdnum\":\"510122200612310019\",\"exInsurantName\":\"\\u5170\\u5B87\\u661F\",\"exInsurantRelation\":\"MQ\",\"exIsBxbStaff\":0,\"exObservation\":60,\"exPaymethod\":\"\\u4E00\\u6B21\\u7F34\\u6E05\",\"exProdname\":\"\\u836F\\u65E0\\u5FE7\",\"exServicefeeCent\":432,\"exServicefeePercent\":12,\"extra\":\"\",\"hasBaodan\":1,\"hesitationPeriod\":15,\"id\":\"10006067\",\"insBackFee\":0,\"insCheckDate\":\"2020-11-20 00:00:00\",\"insCompanyName\":\"\",\"insDate\":\"2020-11-19 20:47:46\",\"insExamine\":1,\"insFor\":\"[{\\\"card\\\": \\\"510122200612310019\\\", \\\"phone\\\": \\\"13541161884\\\", \\\"cardType\\\": \\\"\\u8EAB\\u4EFD\\u8BC1\\\", \\\"name\\\": \\\"\\u5170\\u5B87\\u661F\\\", \\\"birth\\\": \\\"2006-12-31\\\"}]\",\"insFromBirth\":\"1984-10-03\",\"insFromCard\":\"510122198410030262\",\"insFromCardType\":\"\\u8EAB\\u4EFD\\u8BC1\",\"insFromName\":\"\\u4E8E\\u6797\",\"insFromPhone\":\"13541161884\",\"insHesitate\":15,\"insLimit\":\"1\\u5E74\",\"insLimitDate\":\"2021-11-20 00:00:00\",\"insNo\":\"P972000920603\",\"insPayStatus\":1,\"insPremium\":\"3000000.0\",\"insProd\":0,\"insProdName\":\"\\u836F\\u65E0\\u5FE7\",\"insSupplier\":2,\"insType\":1,\"insYearFee\":\"3600\",\"insuranceType\":\"\\u533B\\u7597\\u9669\",\"isAddGrowth\":1,\"isTest\":0,\"needSecondNotify\":0,\"observationPeriod\":60,\"paymentMethod\":1,\"phone\":\"\",\"renewalLink\":\"\",\"renewalStatus\":0,\"source\":\"qixin\",\"updateDate\":null,\"username\":\"liqingzong\",\"wx\":\"\"}]}";
//		System.err.println(json);
//		System.err.println(json2);
//		JSONObject Object1 = JSON.parseObject(json);
//		JSONObject Object2 = JSON.parseObject(json2);
//
//		JsonUtils jsonUtil=new JsonUtils();
//		System.out.println(jsonUtil.compareTwoDeviceInfo(Object1, Object2));
		String json3="{\"Id\":\"53\",\"name\":\"get请求\",\"requestMethod\":\"POST\",\"requestHeader\":\"\",\"paramType\":\"1\",\"requestParam\":\"{\\\"action\\\":\\\"nestList\\\",\\\"currentPage\\\":\\\"1\\\",\\\"querySaleTime\\\":\\\"2020-11\\\",\\\"queryAdviser\\\":\\\"3\\\",\\\"pageSize\\\":\\\"50\\\"}\",\"delay\":\"0\",\"content\":\"123\",\"updateBy\":\"18\",\"description\":\"Body参数排序\"}";
		System.err.println(json3);
		JSONObject Object3 = JSON.parseObject(json3);
		System.out.println(Object3.getString("requestParam")); 

//		String contentType=Object3.getString("content-type");
//		System.out.println(contentType);
	}
	

	
	/**
     * 	生成随机盐
     */
    public String randomSalt() {
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        String hex = secureRandom.nextBytes(3).toHex();
        return hex;
    }

    public String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex();
    }
}
