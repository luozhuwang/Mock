package com.mock.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mock.dao.ILogDao;
import com.mock.entity.ResponeBase;
import com.mock.entity.iChart;
import com.mock.entity.ILogEntity;
import com.mock.service.ILogService;
import com.mock.tool.DateFormat;

@Service
public class ILogServiceImpl implements ILogService{
	private Logger log =LoggerFactory.getLogger(ILogServiceImpl.class);
	
	@Autowired
	private ILogDao iLogDao;

	@Override
	public ResponeBase selectMockLogList(ILogEntity iLogEntity) {
		PageHelper.startPage(iLogEntity.getPageNum(), iLogEntity.getPageSize());
		List<ILogEntity> iLogEntitys=iLogDao.selectMockLogList(iLogEntity);
		if(iLogEntitys.isEmpty()) {
			log.info("查询【Mock日志】列表为空");
			return  ResponeBase.resultNull("查询【Mock日志】列表为空");
		}
		PageInfo<ILogEntity> pageinfo=new PageInfo<ILogEntity>(iLogEntitys);
		return ResponeBase.successResult(pageinfo);
	}

	
	@Override
	public ResponeBase selectMockLogById(int logId) {
		ILogEntity iLogEntity=iLogDao.selectMockLogById(logId);
		if(iLogEntity == null) {
			log.info("请求【Mock日志】查询为空");
			return  ResponeBase.resultNull("请求【Mock日志】查询为空");
		}
		return ResponeBase.successResult(iLogEntity);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponeBase insertMockLog(ILogEntity iLogEntity) {
		int count=-1;
		count=iLogDao.insertMockLog(iLogEntity);
		if(count>0) {
			List<ILogEntity> iLogEntitys=iLogDao.selectMockLogList(null);
			return ResponeBase.successResult(iLogEntitys);
		}else {	
			log.info("添加【Mock日志】失败");
			return ResponeBase.failResult("添加【Mock日志】失败", null);
		}
		
	}


	@Override
	public ResponeBase selectMockLogListByDate(ILogEntity iLogEntity) {
		List<iChart> iCharts=new ArrayList<iChart>();
		List<iChart> iChartsB=new ArrayList<iChart>();
		String begin=iLogEntity.getBeginTime();
		String End=iLogEntity.getEndTime();
		iLogEntity.setBeginTime(begin+" 00:00:00");
		iLogEntity.setEndTime(End+" 23:59:59");
		List<Map<String ,Object>> ll= iLogDao.selectMockLogListByDate(iLogEntity);
		
		if(ll.isEmpty()) {
			System.out.println("按日期统计为空");
		}else {			
			for(Map<String ,Object> map:ll) {
//				System.out.println(map.get("date"));
//				System.out.println(map.get("count"));
				String date1=map.get("date").toString();
				String count=map.get("count").toString();
				iChart chart=new iChart();
				chart.setKey(date1);
				chart.setValue(count);
				iChartsB.add(chart);
			}
		}
		
		int between=DateFormat.daysBetween(begin, End);
		for(int i=0;i<=between;i++) {
			String date=DateFormat.DateUtil(begin,"yyyy-MM-dd","day",i);
			iChart chart=new iChart();
			chart.setKey(date);
			chart.setValue("0");
			if(!ll.isEmpty()) {				
				for(iChart ic:iChartsB) {
					if(ic.getKey().equals(date)) {
						chart.setValue(ic.getValue());
					}
				}
			}			
			iCharts.add(chart);
		}
				
		return ResponeBase.successResult(iCharts);
	}


	@Override
	public ResponeBase selectMockLogListByName(ILogEntity iLogEntity) {
		List<iChart> iCharts=new ArrayList<iChart>();
		String begin=iLogEntity.getBeginTime();
		String End=iLogEntity.getEndTime();
		iLogEntity.setBeginTime(begin+" 00:00:00");
		iLogEntity.setEndTime(End+" 23:59:59");
		List<Map<String, Object>> ll=iLogDao.selectMockLogListByName(iLogEntity);
		if(ll.isEmpty()) {
			log.info("按名称统计为空");
			return  ResponeBase.resultNull("按名称统计为空");
		}else {
			for(Map<String ,Object> map:ll) {
//				System.out.println(map.get("name"));
//				System.out.println(map.get("count"));
				String name=map.get("name").toString();
				String value=map.get("count").toString();
				iChart chart=new iChart();
				chart.setKey(name);
				chart.setValue(value);
				iCharts.add(chart);
			}
		}
		return ResponeBase.successResult(ll);
	}

	
}
