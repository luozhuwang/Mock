package com.mock.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mock.entity.IUserEntity;
import com.mock.entity.ResponeBase;

public interface IUserService {
	/**
     * 	根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
	ResponeBase selectUserList(IUserEntity iUser);
    /**
     * 	通过userId查询用户
     * */
	ResponeBase selectUserById(int userId);
    /**
     *	 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
	ResponeBase resetUserPwd(int updateBy,int userId,String password);
	 /**
     *	 修改用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
	ResponeBase editUserPwd(int updateBy,int userId,String oldPWD,String newPWD);
    /**
     *	 修改用户信息
     * @param user 用户信息
     * @return 结果
     */
	ResponeBase updateUser(IUserEntity iUser);
    /**
     *	 用户注册
     * @param username       用户名
     * @param password       密码
     * @return 是否注册成功
     */
	ResponeBase register(IUserEntity iUser);
	/**
     * 	通过userId删除用户
     * */
	ResponeBase delUserById(int updateBy,int userId);
	/**
	 * 用户登陆
	 * */
	ResponeBase userLogin(IUserEntity iUser) ;
}
