package com.qhit.baseUserRole.service;

import java.util.List;
import com.qhit.baseUserRole.pojo.BaseUserRole;


public interface IBaseUserRoleService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BaseUserRole findById(Object id);

    List<BaseUserRole> search(BaseUserRole baseUserRole);

}