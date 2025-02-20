package com.hyh.club.auth.basic.service;

import com.hyh.club.auth.basic.entity.AuthRolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)表服务接口
 *
 * @author Condix
 * @since 2025-01-30 18:43:13
 */
public interface AuthRolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRolePermission queryById(Long id);

    /**
     * 分页查询
     *
     * @param authRolePermission 筛选条件
     * @return 查询结果
     */
    Page<AuthRolePermission> queryByPage(AuthRolePermission authRolePermission);

    /**
     * 新增数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    AuthRolePermission insert(AuthRolePermission authRolePermission);

    /**
     * 修改数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    AuthRolePermission update(AuthRolePermission authRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    int batchInsert(List<AuthRolePermission> rolePermissionList);

    List<AuthRolePermission> queryByCondition(AuthRolePermission authRolePermission);
}
