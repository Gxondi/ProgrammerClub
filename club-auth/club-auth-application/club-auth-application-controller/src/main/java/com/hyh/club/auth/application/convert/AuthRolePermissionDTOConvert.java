package com.hyh.club.auth.application.convert;

import com.hyh.club.auth.application.dto.AuthPermissionDTO;
import com.hyh.club.auth.application.dto.AuthRolePermissionDTO;
import com.hyh.club.auth.domain.entity.AuthPermissionBO;
import com.hyh.club.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRolePermissionDTOConvert {
    AuthRolePermissionDTOConvert INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConvert.class);
    AuthRolePermissionBO convertDTOtoBO(AuthRolePermissionDTO authRolePermissionDTO);
}