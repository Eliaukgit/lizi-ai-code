package com.bear.liziaicode.controller;

import com.bear.liziaicode.common.BaseResponse;
import com.bear.liziaicode.common.ResultUtils;
import com.bear.liziaicode.exception.ErrorCode;
import com.bear.liziaicode.exception.ThrowUtils;
import com.bear.liziaicode.model.dto.UserLoginRequest;
import com.bear.liziaicode.model.dto.UserRegisterRequest;
import com.bear.liziaicode.model.vo.LoginUserVO;
import com.mybatisflex.core.paginate.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.bear.liziaicode.model.entity.User;
import com.bear.liziaicode.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户 控制层。
 *
 * @author <a href="https://github.com/Eliaukgit">栗子</a>
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求
     * @return 注册结果
     */
    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        // 参数校验：如果注册请求对象为空，抛出参数错误异常
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);

        // 从请求对象中获取用户账号
        String userAccount = userRegisterRequest.getUserAccount();

        // 从请求对象中获取用户密码
        String userPassword = userRegisterRequest.getUserPassword();

        // 从请求对象中获取确认密码
        String checkPassword = userRegisterRequest.getCheckPassword();

        // 调用用户服务层的注册方法，执行注册逻辑并返回新用户ID
        long result = userService.userRegister(userAccount, userPassword, checkPassword);

        // 将注册成功后的用户ID封装到统一响应对象中返回给前端
        return ResultUtils.success(result);

    }

    // 用户登录
    @PostMapping("/login")
    /**
     * 用户登录接口
     *
     * @param userLoginRequest 用户登录请求参数（包含账号和密码）
     * @param request HTTP请求对象，用于存储会话信息
     * @return 登录成功后的用户脱敏信息
     */
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        // 参数校验：检查登录请求对象是否为空
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);

        // 从请求对象中获取用户账号
        String userAccount = userLoginRequest.getUserAccount();

        // 从请求对象中获取用户密码
        String userPassword = userLoginRequest.getUserPassword();

        // 调用用户服务层的登录方法，执行登录逻辑并返回脱敏后的用户信息（Service层会进行参数校验、密码验证等）
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);

        // 将登录成功后的用户信息封装到统一响应对象中返回给前端
        return ResultUtils.success(loginUserVO);
    }

    // 获取当前登录用户
    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(userService.getLoginUserVO(loginUser));
    }

    // 用户注销
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

}
