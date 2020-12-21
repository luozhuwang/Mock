package com.mock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mock.entity.IUserEntity;

public interface IUserDao {
	 /**
     * 	根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<IUserEntity> selectUserList(IUserEntity IUser);
    /**
     * 	通过userId查询用户
     * */
    IUserEntity selectUserById(int userId);
    /**
     *	 修改用户密码信息
     *
     * @param user 用户信息
     * @return 结果
     */
    Integer resetUserPwd(@Param("updateBy")int updateBy,@Param("userId")int userId,@Param("password")String password);
    /**
     *	 修改用户信息
     * @param user 用户信息
     * @return 结果
     */
    Integer updateUser(IUserEntity IUser);
    /**
     *	 用户注册
     * @param username       用户名
     * @param password       密码
     * @return 是否注册成功
     */
    Integer register(IUserEntity IUser);
    /**
     * 	校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    Integer checkLoginName(String loginName);
    /**
     * 	通过userId删除用户
     * */
    Integer delUserById(@Param("updateBy")int updateBy,@Param("userId")int userId);
}
