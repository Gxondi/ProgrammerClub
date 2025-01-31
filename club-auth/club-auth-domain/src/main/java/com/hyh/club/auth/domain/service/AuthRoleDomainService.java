package com.hyh.club.auth.domain.service;

import com.hyh.club.auth.basic.entity.AuthRole;
import com.hyh.club.auth.domain.entity.AuthRoleBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (AuthRole)表服务接口
 *
 * @author Condix
 * @since 2025-01-30 11:53:36
 */
public interface AuthRoleDomainService {

    Boolean add(AuthRoleBO authRoleBO);

    Boolean delete(AuthRoleBO authRoleBO);

    Boolean update(AuthRoleBO authRoleBO);

}
