package com.hyh.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.google.gson.Gson;
import com.hyh.club.gateway.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.premission";

    private String authRolePrefix = "auth.role";

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return getAuth(loginId.toString(),authPermissionPrefix);
    }

    private List<String> getAuth(String loginId, String prefix) {
        String key = redisUtil.buildKey(prefix, loginId);
        String value = redisUtil.get(key);
        if (StringUtils.isBlank(value)) {
            Collections.emptyList();
        }
        List list = new Gson().fromJson(value, List.class);
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return getAuth(loginId.toString(),authRolePrefix);
    }

}
