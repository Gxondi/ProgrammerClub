package com.hyh.club.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.hyh.club.auth.application.dto.AuthRoleDTO;
import com.hyh.club.auth.common.result.Result;
import com.hyh.club.auth.domain.entity.AuthRoleBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody AuthRoleDTO authRoleDTO) {

    }

    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO) {

    }

    @PostMapping("/changeRole")
    public Result<Boolean> changeRole(@RequestBody AuthRoleDTO authRoleDTO) {

    }

    @PostMapping("/changeRoleStatus")
    public Result<Boolean> changeRoleStatus(@RequestBody AuthRoleDTO authRoleDTO) {

    }
}
