package com.hyh.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyh.club.gateway.entity.AuthPermission;
import com.hyh.club.gateway.entity.AuthRole;
import com.hyh.club.gateway.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义权限加载接口实现类
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";

    private String authRolePrefix = "auth.role";

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return getAuth(loginId.toString(), authPermissionPrefix);
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return getAuth(loginId.toString(), authRolePrefix);
    }

    private List<String> getAuth(String loginId, String prefix) {
        String key = redisUtil.buildKey(prefix, loginId);
        String value = redisUtil.get(key);
        if (StringUtils.isBlank(value)) {
            Collections.emptyList();
        }
        List<String> authList = new ArrayList<>();
        if (authPermissionPrefix.equals(prefix)) {
            List<AuthRole> authRoleList = new Gson().fromJson(value, new TypeToken<List<AuthRole>>() {
            }.getType());
            authList = authRoleList.stream().map(AuthRole::getRoleKey).collect(Collectors.toList());
        } else if (authRolePrefix.equals(prefix)) {
            List<AuthPermission> authPermissionList = new Gson().fromJson(value, new TypeToken<List<AuthRole>>() {
            }.getType());
            authList = authPermissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        }
        return authList;
    }


}
