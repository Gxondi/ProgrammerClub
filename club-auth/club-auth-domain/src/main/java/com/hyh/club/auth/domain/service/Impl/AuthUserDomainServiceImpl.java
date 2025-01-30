package com.hyh.club.auth.domain.service.Impl;


import cn.dev33.satoken.secure.SaSecureUtil;
import com.alibaba.fastjson.JSON;
import com.hyh.club.auth.basic.entity.AuthUser;
import com.hyh.club.auth.basic.service.AuthUserService;
import com.hyh.club.auth.common.enums.AuthUserStatusEnum;
import com.hyh.club.auth.common.enums.IsDeletedFlagEnum;
import com.hyh.club.auth.domain.convert.AuthUserBOConvert;
import com.hyh.club.auth.domain.entity.AuthUserBO;
import com.hyh.club.auth.domain.service.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;
    private static final String SALT = "club-auth";

    @Override
    public Boolean register(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()) {
            log.info("ServiceImpl.register.authUserBO:{}", JSON.toJSONString(authUserBO));
        }
        AuthUser authUser = AuthUserBOConvert.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setPassword(SaSecureUtil.sha256BySalt(authUser.getPassword(), SALT));
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);
        // 建立初步的角色关联，把当前用户的角色和权限都存到redis中
        return count > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()) {
            log.info("ServiceImpl.update.authUserBO:{}", JSON.toJSONString(authUserBO));
        }
        AuthUser authUser = AuthUserBOConvert.INSTANCE.convertBOToEntity(authUserBO);
        Integer count = authUserService.update(authUser);
        // 做好与缓存的同步
        return count > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()) {
            log.info("ServiceImpl.delete.authUserBO:{}", JSON.toJSONString(authUserBO));
        }
        AuthUser authUser = AuthUserBOConvert.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = authUserService.update(authUser);
        return count > 0;
    }

    @Override
    public Boolean changeStatus(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()) {
            log.info("ServiceImpl.changeStatus.authUserBO:{}", JSON.toJSONString(authUserBO));
        }
        AuthUser authUser = AuthUserBOConvert.INSTANCE.convertBOToEntity(authUserBO);
        if (authUser.getStatus() == 0) {
            authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        } else {
            authUser.setStatus(AuthUserStatusEnum.CLOSE.getCode());
        }
        Integer count = authUserService.update(authUser);
        return count > 0;
    }
}
