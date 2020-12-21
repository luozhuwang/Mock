package com.mock.entity;


import java.util.Map;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;

@Data
public class BaseEntity <T extends Model<?>> extends Model<T>{

	private static final long serialVersionUID = -550192909520311646L;

    /**
     * 	创建者
     */
    private int createBy;

    /**
     * 	创建时间
     */
    private String createTime;
    /**
     * 	结束时间
     */
    private String beginTime;
    /**
     * 	结束时间
     */
    private String endTime;
    /**
     * 	更新者
     */
    private int updateBy;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Integer delFlag;

    /**
     * 备注
     */
    private String remark;
    private int pageNum;
    private int pageSize;
    private int count;
//    private Map<String, Object> params = CollUtil.newHashMap();
}
