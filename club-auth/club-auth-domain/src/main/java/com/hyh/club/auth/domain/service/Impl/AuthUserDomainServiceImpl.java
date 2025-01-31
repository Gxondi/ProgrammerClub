package com.hyh.club.auth.domain.service.Impl;


import cn.dev33.satoken.secure.SaSecureUtil;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.hyh.club.auth.basic.entity.*;
import com.hyh.club.auth.basic.service.*;
import com.hyh.club.auth.common.enums.AuthUserStatusEnum;
import com.hyh.club.auth.common.enums.IsDeletedFlagEnum;
import com.hyh.club.auth.domain.constant.AuthUserRoleConstant;
import com.hyh.club.auth.domain.convert.AuthUserBOConvert;
import com.hyh.club.auth.domain.entity.AuthUserBO;
import com.hyh.club.auth.domain.redis.RedisUtil;
import com.hyh.club.auth.domain.service.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;
    @Resource
    private AuthUserRoleService authUserRoleService;
    @Resource
    private AuthRoleService authRoleService;
    @Resource
    private AuthRolePermissionService authRolePermissionService;
    @Resource
    private AuthPermissionService authPermissionService;
    @Resource
    private RedisUtil redisUtil;


    private String authPermissionPrefix = "auth.permission";

    private String authRolePrefix = "auth.role";
    private static final String SALT = "club-auth";

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        // 保存用户角色关联
        AuthRole authRole = authRoleService.queryByCondition(AuthUserRoleConstant.NORMAL_USER);
        Long userId = authUser.getId();
        Long authRoleId = authRole.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(authRoleId);
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authUserRoleService.insert(authUserRole);
        String roleKey = redisUtil.buildKey(authRolePrefix, authUser.getUserName());
        List<AuthRole> authRoleList = new ArrayList<>();
        authRoleList.add(authRole);
        redisUtil.set(roleKey, new Gson().toJson(authRoleList));

        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(authRoleId);
        List<AuthRolePermission> authRolePermissionList = authRolePermissionService.queryByCondition(authRolePermission);
        List<Long> permissionIds = authRolePermissionList.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());
        List<AuthPermission> permissionList = authPermissionService.queryByIds(permissionIds);
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, authUser.getUserName());
        redisUtil.set(permissionKey, new Gson().toJson(permissionList));
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
