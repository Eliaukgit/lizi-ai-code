package com.bear.liziaicode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//切面编程注解
//exposeProxy=true：通过SpringAOP提供对当前代理对象的访问，使得可以在业务逻辑中访问到当前的代理对象。
//你可以在方法执行时通过AopContext.currentProxy(）获取当前的代理对象。
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class LiziAiCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiziAiCodeApplication.class, args);
    }

}
