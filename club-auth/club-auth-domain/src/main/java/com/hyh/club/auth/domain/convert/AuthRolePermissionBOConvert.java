package com.hyh.club.auth.domain.convert;

import com.hyh.club.auth.basic.entity.AuthPermission;
import com.hyh.club.auth.basic.entity.AuthRolePermission;
import com.hyh.club.auth.domain.entity.AuthPermissionBO;
import com.hyh.club.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRolePermissionBOConvert {

    AuthRolePermissionBOConvert INSTANCE = Mappers.getMapper(AuthRolePermissionBOConvert.class);

    AuthRolePermission convertBOToEntity(AuthRolePermissionBO authRolePermissionBO);
}