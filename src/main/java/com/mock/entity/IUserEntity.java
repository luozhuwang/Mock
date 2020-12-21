package com.mock.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import lombok.Data;
import lombok.NonNull;


@Data
public class IUserEntity extends BaseEntity<IUserEntity>{

	private static final long serialVersionUID = -4848104912009963651L;

	
	private int userId;

    /**
     * 	登录名称
     */
    @NotBlank(message = "登录账号不能为空")
    @Size(max = 30, message = "登录账号长度不能超过30个字符")
    private String loginName;

    /**
     * 	用户名称
     */
    @Size(max = 30, message = "用户昵称长度不能超过30个字符")
    private String userName;
    /**
 	* 	用户类型：普通用户、admin
 	*	普通用户：0、管理员：1
 	*/
    private Integer userType;
    /**
     *	 用户邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过50个字符")
    private String email;

    /**
     * 手机号码
     */
    @Size(max = 11, message = "手机号码长度不能超过11个字符")
    private String phone;

    /**
     	* 用户性别：0=男,1=女,2=未知
     */
    private Integer sex;

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 密码
     */
    private String password;


    /**
     * 	帐号状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 	最后登录IP
     */
    private String loginIp;

    /**
     	* 最后登录时间yyyy-MM-dd HH:mm:ss
     */
    private String loginDate;

    
}
