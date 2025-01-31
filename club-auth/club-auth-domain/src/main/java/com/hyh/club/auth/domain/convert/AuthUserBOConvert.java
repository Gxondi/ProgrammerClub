package com.hyh.club.auth.domain.convert;

import com.hyh.club.auth.basic.entity.AuthUser;
import com.hyh.club.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserBOConvert {

    AuthUserBOConvert INSTANCE = Mappers.getMapper(AuthUserBOConvert.class);

    AuthUser convertBOToEntity(AuthUserBO authUserBO);
}