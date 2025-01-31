package com.hyh.club.auth.domain.convert;

import com.hyh.club.auth.basic.entity.AuthRole;
import com.hyh.club.auth.basic.entity.AuthUser;
import com.hyh.club.auth.domain.entity.AuthRoleBO;
import com.hyh.club.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserRoleBOConvert {

    AuthUserRoleBOConvert INSTANCE = Mappers.getMapper(AuthUserRoleBOConvert.class);

    AuthRole convertBOToEntity(AuthRoleBO authRoleBO);
}