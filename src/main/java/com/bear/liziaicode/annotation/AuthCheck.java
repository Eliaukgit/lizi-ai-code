package com.bear.liziaicode.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 权限校验
//权限校验一般会通过SpringAOP切面 + 定义权限校验注解实现统一的接口拦截和权限校验。
// 如果有特殊的权限校验逻辑，再单独在接口中编码。
// ... existing code ...
/**
 * 权限校验注解
 * 用于标记需要进行角色权限验证的方法
 */
// 指定注解的使用目标：只能应用于方法上
@Target(ElementType.METHOD)
// 指定注解的保留策略：在运行时可通过反射获取，支持动态权限校验
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     * 必须有某个角色
     * 用于指定访问该方法所需的用户角色（如 "admin"、"user" 等）
     * 默认值为空字符串，表示不限制角色
     */
    String mustRole() default "";
}

