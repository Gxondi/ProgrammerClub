package com.hyh.club.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.hyh.club.auth.application.convert.AuthPermissionDTOConvert;
import com.hyh.club.auth.application.convert.AuthRolePermissionDTOConvert;
import com.hyh.club.auth.application.dto.AuthPermissionDTO;
import com.hyh.club.auth.application.dto.AuthRolePermissionDTO;
import com.hyh.club.auth.basic.service.AuthRolePermissionService;
import com.hyh.club.auth.common.result.Result;
import com.hyh.club.auth.domain.entity.AuthPermissionBO;
import com.hyh.club.auth.domain.entity.AuthRolePermissionBO;
import com.hyh.club.auth.domain.service.Impl.AuthPermissionDomainServiceImpl;
import com.hyh.club.auth.domain.service.Impl.AuthRolePermissionDomainServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController {
    @Resource
    private AuthRolePermissionDomainServiceImpl authRolePermissionDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Controller.add.dto:{}", JSON.toJSONString(authRolePermissionDTO));
            }
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "角色不能为空");
            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConvert.INSTANCE.convertDTOtoBO(authRolePermissionDTO);
            return Result.OK(authRolePermissionDomainService.add(authRolePermissionBO));
        } catch (Exception e) {
            log.error("Controller.add.dto:.add{}", e.getMessage(), e);
            return Result.FAIL(e.getMessage());
        }
    }

}
