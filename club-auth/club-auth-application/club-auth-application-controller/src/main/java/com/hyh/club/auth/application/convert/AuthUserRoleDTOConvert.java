package com.hyh.club.auth.application.convert;

import com.hyh.club.auth.application.dto.AuthRoleDTO;
import com.hyh.club.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserRoleDTOConvert {
    AuthUserRoleDTOConvert INSTANCE = Mappers.getMapper(AuthUserRoleDTOConvert.class);
    AuthRoleBO convertDTOtoBO(AuthRoleDTO authRoleDTO);
}