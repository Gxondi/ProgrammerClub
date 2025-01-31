package com.hyh.club.auth.basic.service;

import com.hyh.club.auth.basic.entity.AuthUser;


/**
 * 用户信息表(AuthUser)表服务接口
 *
 * @author Condix
 * @since 2025-01-29 22:46:41
 */
public interface AuthUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUser queryById(Long id);

    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    Integer insert(AuthUser authUser);

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    Integer update(AuthUser authUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Integer  deleteById(Long id);

}
