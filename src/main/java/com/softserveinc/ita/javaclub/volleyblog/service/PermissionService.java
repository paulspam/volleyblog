package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();
}
