package com.hyh.club.auth.domain.service;

import com.hyh.club.auth.basic.entity.AuthUser;
import com.hyh.club.auth.domain.entity.AuthUserBO;


/**
 * 用户信息表(AuthUser)表服务接口
 *
 * @author Condix
 * @since 2025-01-29 22:46:41
 */
public interface AuthUserDomainService {
    Boolean update(AuthUserBO authUser);

    Boolean register(AuthUserBO authUserBO);

    Boolean changeStatus(AuthUserBO authUserBO);

    Boolean delete(AuthUserBO authUserBO);
}
