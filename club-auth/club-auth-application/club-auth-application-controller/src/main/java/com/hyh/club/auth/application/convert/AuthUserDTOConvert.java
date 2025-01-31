package com.hyh.club.auth.application.convert;

import com.hyh.club.auth.application.dto.AuthUserDTO;
import com.hyh.club.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserDTOConvert {
    AuthUserDTOConvert INSTANCE = Mappers.getMapper(AuthUserDTOConvert.class);

    AuthUserBO convertDTOtoBO(AuthUserDTO authUserDTO);
}