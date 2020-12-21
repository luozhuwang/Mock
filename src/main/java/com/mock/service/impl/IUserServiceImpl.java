package com.mock.service.impl;

import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mock.dao.IUserDao;
import com.mock.entity.IUserEntity;
import com.mock.entity.ResponeBase;
import com.mock.service.IUserService;
import com.mock.tool.Constant;

import cn.hutool.core.util.StrUtil;

@Service
public class IUserServiceImpl implements IUserService{
	private Logger log =LoggerFactory.getLogger(IUserServiceImpl.class);

	@Autowired
	private IUserDao iUserDao;
	
	@Override
	public ResponeBase selectUserList(IUserEntity IUser) {
		PageHelper.startPage(IUser.getPageNum(), IUser.getPageSize());
		List<IUserEntity> iUsers=iUserDao.selectUserList(IUser);
		if(iUsers.isEmpty()) {
			log.info("查询【用户列表】为空");
			return  ResponeBase.resultNull("【用户列表】为空");
		}
		PageInfo<IUserEntity> pageinfo=new PageInfo<IUserEntity>(iUsers);
		return ResponeBase.successResult(pageinfo);
	}

	@Override
	public ResponeBase selectUserById(int userId) {
		IUserEntity iUser=iUserDao.selectUserById(userId);
		if(iUser == null) {
			log.info("【查询用户】为空");
			return  ResponeBase.resultNull("【查询用户】为空");
		}
		return ResponeBase.successResult(iUser);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponeBase resetUserPwd(int updateBy,int userId, String password) {
		int count=-1;	
		IUserEntity iUser=iUserDao.selectUserById(userId);
		String LoginName=iUser.getLoginName();
//		String passWD=iUser.getPassword();
		String newPassWD=encryptPWD(LoginName, password, Constant.salt);		
		count=iUserDao.resetUserPwd(updateBy,userId, newPassWD);
		if(count>0) {
			return ResponeBase.successResult("【重置用户密码】成功");
		}else {			
			log.info("【重置用户密码】失败");
			return ResponeBase.failResult("【重置用户密码】失败", null);
		}
	}
	
	@Override
	public ResponeBase editUserPwd(int updateBy ,int userId, String oldPWD, String newPWD) {
		int count=-1;	
		IUserEntity iUser=iUserDao.selectUserById(userId);
		String LoginName=iUser.getLoginName();
		String passWD=iUser.getPassword();
//		String PassWD=encryptPWD(LoginName, passWD, Constant.salt);
		String oldPassWD=encryptPWD(LoginName, oldPWD, Constant.salt);
		if(!oldPassWD.equals(passWD)) {
			return ResponeBase.failResult("【旧密码错误】，请重新输入");
		}
		String newPassWD=encryptPWD(LoginName, newPWD, Constant.salt);		
		count=iUserDao.resetUserPwd(updateBy,userId, newPassWD);
		if(count>0) {
			return ResponeBase.successResult("【用户密码】修改成功");
		}else {			
			log.info("【用户密码】修改失败");
			return ResponeBase.failResult("【用户密码】修改失败", null);
		}
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponeBase updateUser(IUserEntity IUser) {
		int count=-1;
		count=iUserDao.updateUser(IUser);
		if(count>0) {
			return ResponeBase.successResult("【修改用户】成功");
		}else {			
			log.info("【修改用户】成功");
			return ResponeBase.failResult("【修改用户】成功", null);
		}
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponeBase register(IUserEntity iUser) {
		int count=-1;

		String LoginName=iUser.getLoginName();
		String userName=iUser.getUserName();
		String userPWD=iUser.getPassword();
		if(LoginName==null || LoginName.equals("")) {
    		return ResponeBase.failResult("【登陆名】不能为空", null);
    	}
    	if(userName==null || userName.equals("")) {
    		return ResponeBase.failResult("【用户名称】不能为空", null);
    	}
    	if(userPWD==null|| userPWD.equals("")) {
    		return ResponeBase.failResult("【密码】不能为空", null);
    	}
		
		int isUnique=iUserDao.checkLoginName(LoginName);
		if(isUnique>0) {
			return ResponeBase.failResult(LoginName+"【已注册】，不能重复", null);
		}
		String newPassWD=encryptPWD(LoginName, userPWD, Constant.salt);
		iUser.setPassword(newPassWD);
		count=iUserDao.register(iUser);
		if(count>0) {
			return ResponeBase.resultNull("【用户注册】成功");
		}else {	
			log.info("【用户注册】失败");
			return ResponeBase.failResult("【用户注册】失败", null);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponeBase delUserById(int updateBy,int userId) {
		int count=-1;
		count=iUserDao.delUserById(updateBy,userId);
		if(count>0) {
			return ResponeBase.successResult("【删除用户】成功");
		}else {			
			log.info("【删除用户】失败");
			return ResponeBase.failResult("【删除用户】失败", null);
		}
	}

	@Override
	public ResponeBase userLogin(IUserEntity iUser) {
		String loginName=iUser.getLoginName();
		String userPWD=iUser.getPassword();
		
		// 用户名或密码为空 错误
        if (StrUtil.isEmpty(loginName) || StrUtil.isEmpty(userPWD)) {
        	return ResponeBase.failResult("【用户名或密码为空】", null);
        }
		List<IUserEntity> iUsers=iUserDao.selectUserList(iUser);
		if (0==iUsers.size()) {
			return ResponeBase.failResult("【用户不存在】", null);
        }
		if(1==iUsers.get(0).getStatus()) {
			return ResponeBase.failResult("【用户已锁定】，请联系管理员", null);
		}
		String message=validate(iUsers.get(0), userPWD);
		if(message.equals("用户验证通过")) {
			return ResponeBase.successResult(iUsers.get(0));
		}else {
			return ResponeBase.failResult(message, null);
		}
		
	}
	

	public String validate(IUserEntity iUser, String password) {
        if (!matches(iUser, password)) {
            return "【用户和密码不匹配】";
        }else {        	
        	return "用户验证通过";
        }
    }

    public boolean matches(IUserEntity iUser, String newPassword) {
        return iUser.getPassword().equals(encryptPWD(iUser.getLoginName(), newPassword, Constant.salt));
    }
    
	private String encryptPWD(String loginName, String password, String salt) {
		 return new Md5Hash(loginName + password + salt).toHex();
	}
	
}
