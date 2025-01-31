package com.hyh.club.auth.domain.service.Impl;

import com.hyh.club.auth.basic.entity.AuthPermission;
import com.hyh.club.auth.basic.service.AuthPermissionService;
import com.hyh.club.auth.common.enums.IsDeletedFlagEnum;
import com.hyh.club.auth.domain.convert.AuthPermissionBOConvert;
import com.hyh.club.auth.domain.entity.AuthPermissionBO;
import com.hyh.club.auth.domain.service.AuthPermissionDomainService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (AuthPermission)表服务接口
 *
 * @author Condix
 * @since 2025-01-30 18:42:18
 */
@Service
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {
    @Resource
    private AuthPermissionService authPermissionService;

    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConvert.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count = authPermissionService.insert(authPermission);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConvert.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authPermissionService.update(authPermission);
        return count > 0;
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConvert.INSTANCE.convertBOToEntity(authPermissionBO);
        Integer count = authPermissionService.update(authPermission);
        return count > 0;
    }
}
