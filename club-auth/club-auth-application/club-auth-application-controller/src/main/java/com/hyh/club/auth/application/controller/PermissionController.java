package com.hyh.club.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.hyh.club.auth.application.convert.AuthPermissionDTOConvert;
import com.hyh.club.auth.application.convert.AuthRoleDTOConvert;
import com.hyh.club.auth.application.dto.AuthPermissionDTO;
import com.hyh.club.auth.common.result.Result;
import com.hyh.club.auth.domain.entity.AuthPermissionBO;
import com.hyh.club.auth.domain.entity.AuthRoleBO;
import com.hyh.club.auth.domain.service.AuthRoleDomainService;
import com.hyh.club.auth.domain.service.Impl.AuthPermissionDomainServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private AuthPermissionDomainServiceImpl authPermissionDomainService;

    private void checkPermission(AuthPermissionDTO authPermissionDTO) {
        Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getName()), "权限的状态status不能为空");
    }

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Controller.add.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            checkPermission(authPermissionDTO);
//            Preconditions.checkNotNull(authPermissionDTO.getId(), "权限Id不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConvert.INSTANCE.convertDTOtoBO(authPermissionDTO);
            return Result.OK(authPermissionDomainService.add(authPermissionBO));
        } catch (Exception e) {
            log.error("Controller.add.dto:.add{}", e.getMessage(), e);
            return Result.FAIL(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Controller.delete.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            checkPermission(authPermissionDTO);
//            Preconditions.checkNotNull(authPermissionDTO.getId(), "权限Id不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConvert.INSTANCE.convertDTOtoBO(authPermissionDTO);
            return Result.OK(authPermissionDomainService.delete(authPermissionBO));
        } catch (Exception e) {
            log.error("Controller.add.dto:.add{}", e.getMessage(), e);
            return Result.FAIL(e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Controller.update.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            checkPermission(authPermissionDTO);
//            Preconditions.checkNotNull(authPermissionDTO.getId(), "权限Id不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConvert.INSTANCE.convertDTOtoBO(authPermissionDTO);
            return Result.OK(authPermissionDomainService.update(authPermissionBO));
        } catch (Exception e) {
            log.error("Controller.add.dto:.add{}", e.getMessage(), e);
            return Result.FAIL(e.getMessage());
        }
    }
}
