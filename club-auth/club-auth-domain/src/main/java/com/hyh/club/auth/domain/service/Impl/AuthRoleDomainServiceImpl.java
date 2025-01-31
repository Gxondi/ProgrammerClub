package com.hyh.club.auth.domain.service.Impl;

import com.hyh.club.auth.basic.entity.AuthRole;
import com.hyh.club.auth.basic.service.AuthRoleService;
import com.hyh.club.auth.common.enums.IsDeletedFlagEnum;
import com.hyh.club.auth.domain.convert.AuthUserRoleBOConvert;
import com.hyh.club.auth.domain.entity.AuthRoleBO;
import com.hyh.club.auth.domain.service.AuthRoleDomainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {
    @Resource
    private AuthRoleService authRoleService;

    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthUserRoleBOConvert.INSTANCE.convertBOToEntity(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count = authRoleService.insert(authRole);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthUserRoleBOConvert.INSTANCE.convertBOToEntity(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authRoleService.update(authRole);
        return count > 0;
    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthUserRoleBOConvert.INSTANCE.convertBOToEntity(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authRoleService.update(authRole);
        return count > 0;
    }
}
