package com.hyh.club.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.hyh.club.auth.application.convert.AuthUserDTOConvert;
import com.hyh.club.auth.application.dto.AuthUserDTO;
import com.hyh.club.auth.domain.entity.AuthUserBO;
import com.hyh.club.auth.domain.service.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hyh.club.auth.common.result.Result;
import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private AuthUserDomainService authUserDomainService;
    private void checkUserInfo(AuthUserDTO authUserDTO) {
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "密码不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()), "邮箱不能为空");
    }
    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Controller.user.dto.register:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConvert.INSTANCE.convertDTOtoBO(authUserDTO);
            return Result.OK(authUserDomainService.register(authUserBO));
        } catch (Exception e) {
            log.error("Controller.user.dto.register.error", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Controller.user.dto.changeStatus:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkNotNull(authUserDTO.getId(),"删除用户ID不能为空");
            Preconditions.checkNotNull(authUserDTO.getStatus(),"用户状态不能为空");
            AuthUserBO authUserBO = AuthUserDTOConvert.INSTANCE.convertDTOtoBO(authUserDTO);
            return Result.OK(authUserDomainService.changeStatus(authUserBO));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Controller.user.dto.delete:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkNotNull(authUserDTO.getId(),"删除用户ID不能为空");
            AuthUserBO authUserBO = AuthUserDTOConvert.INSTANCE.convertDTOtoBO(authUserDTO);
            return Result.OK(authUserDomainService.delete(authUserBO));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Controller.user.dto.update:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConvert.INSTANCE.convertDTOtoBO(authUserDTO);
            return Result.OK(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
