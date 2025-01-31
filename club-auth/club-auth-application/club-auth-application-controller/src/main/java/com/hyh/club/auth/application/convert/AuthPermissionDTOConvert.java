package com.hyh.club.auth.application.convert;

import com.hyh.club.auth.application.dto.AuthPermissionDTO;
import com.hyh.club.auth.application.dto.AuthRoleDTO;
import com.hyh.club.auth.domain.entity.AuthPermissionBO;
import com.hyh.club.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthPermissionDTOConvert {
    AuthPermissionDTOConvert INSTANCE = Mappers.getMapper(AuthPermissionDTOConvert.class);
    AuthPermissionBO convertDTOtoBO(AuthPermissionDTO authPermissionDTO);
}