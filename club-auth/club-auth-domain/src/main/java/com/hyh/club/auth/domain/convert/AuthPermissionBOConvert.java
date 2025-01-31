package com.hyh.club.auth.domain.convert;

import com.hyh.club.auth.basic.entity.AuthPermission;
import com.hyh.club.auth.basic.entity.AuthUser;
import com.hyh.club.auth.domain.entity.AuthPermissionBO;
import com.hyh.club.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthPermissionBOConvert {

    AuthPermissionBOConvert INSTANCE = Mappers.getMapper(AuthPermissionBOConvert.class);

    AuthPermission convertBOToEntity(AuthPermissionBO authPermissionBO);
}