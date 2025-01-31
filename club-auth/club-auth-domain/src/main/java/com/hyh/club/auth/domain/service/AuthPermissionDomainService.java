package com.hyh.club.auth.domain.service;

import com.hyh.club.auth.basic.entity.AuthPermission;
import com.hyh.club.auth.domain.entity.AuthPermissionBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (AuthPermission)表服务接口
 *
 * @author Condix
 * @since 2025-01-30 18:42:18
 */
public interface AuthPermissionDomainService {
    Boolean add(AuthPermissionBO authPermissionBO);

    Boolean delete(AuthPermissionBO authPermissionBO);

    Boolean update(AuthPermissionBO authPermissionBO);

//    /**
//     * 通过ID查询单条数据
//     *
//     * @param id 主键
//     * @return 实例对象
//     */
//    AuthPermission queryById(Long id);
//
//    /**
//     * 分页查询
//     *
//     * @param authPermission 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    Page<AuthPermission> queryByPage(AuthPermission authPermission, PageRequest pageRequest);
//
//    /**
//     * 新增数据
//     *
//     * @param authPermission 实例对象
//     * @return 实例对象
//     */
//    AuthPermission insert(AuthPermission authPermission);
//
//    /**
//     * 修改数据
//     *
//     * @param authPermission 实例对象
//     * @return 实例对象
//     */
//    AuthPermission update(AuthPermission authPermission);
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param id 主键
//     * @return 是否成功
//     */
//    boolean deleteById(Long id);

}
