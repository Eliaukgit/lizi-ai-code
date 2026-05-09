package com.bear.liziaicode.model.dto;

import com.bear.liziaicode.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

//@EqualsAndHashCode(callSuper = true) 是 Lombok 提供的注解，用于自动生成 equals() 和 hashCode() 方法。
//比较时包含父类 PageRequest 的字段（pageNum、pageSize）
//✅确保两个 UserQueryRequest 对象在所有字段（包括继承的分页参数）都相同时才判定为相等
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}

