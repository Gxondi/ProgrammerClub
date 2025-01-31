package com.hyh.club.auth.domain.service.Impl;

import com.hyh.club.auth.basic.entity.AuthRolePermission;
import com.hyh.club.auth.basic.service.impl.AuthRolePermissionServiceImpl;
import com.hyh.club.auth.common.enums.IsDeletedFlagEnum;
import com.hyh.club.auth.domain.convert.AuthRolePermissionBOConvert;
import com.hyh.club.auth.domain.entity.AuthRolePermissionBO;
import com.hyh.club.auth.domain.service.AuthRolePermissionDomainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {
    @Resource
    private AuthRolePermissionServiceImpl authRolePermissionService;
    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        AuthRolePermission authRolePermission = AuthRolePermissionBOConvert.INSTANCE.convertBOToEntity(authRolePermissionBO);
        Long roleId = authRolePermission.getRoleId();
        List<AuthRolePermission> rolePermissionList = new ArrayList<>();

        authRolePermissionBO.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission rolePermission = new AuthRolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            rolePermissionList.add(rolePermission);
        });
        int count = authRolePermissionService.batchInsert(rolePermissionList);
        return count > 0;
    }
}
