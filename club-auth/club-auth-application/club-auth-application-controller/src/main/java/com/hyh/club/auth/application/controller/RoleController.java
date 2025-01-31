package com.hyh.club.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.hyh.club.auth.application.convert.AuthRoleDTOConvert;
import com.hyh.club.auth.application.dto.AuthRoleDTO;
import com.hyh.club.auth.common.result.Result;
import com.hyh.club.auth.domain.entity.AuthRoleBO;
import com.hyh.club.auth.domain.service.AuthRoleDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private AuthRoleDomainService authRoleDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Controller.add.dto.add:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()) ,"角色名称不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()) ,"角色key不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConvert.INSTANCE.convertDTOtoBO(authRoleDTO);
            return Result.OK( authRoleDomainService.add(authRoleBO));
        } catch (Exception e) {
            log.error("Controller.add.dto:.add{}",e.getMessage(),e);
            return Result.FAIL(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Controller.add.dto.delete:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getId().toString()) ,"ID不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConvert.INSTANCE.convertDTOtoBO(authRoleDTO);
            return Result.OK( authRoleDomainService.delete(authRoleBO));
        } catch (Exception e) {
            log.error("Controller.add.dto.delete:{}",e.getMessage(),e);
            return Result.FAIL(e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Controller.add.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getId().toString()) ,"ID不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()) ,"角色名称不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()) ,"角色key不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConvert.INSTANCE.convertDTOtoBO(authRoleDTO);
            return Result.OK( authRoleDomainService.update(authRoleBO));
        } catch (Exception e) {
            log.error("Controller.add.dto:{}",e.getMessage(),e);
            return Result.FAIL(e.getMessage());
        }
    }
}
