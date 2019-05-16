package com.qhit.baseRole.dao;

import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.utils.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IBaseRoleDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    List findByRname(Object rname);

    List<BaseFunction> distributeLeft(BaseRole baseRole);

    List<BaseFunction> distributeRight(BaseRole baseRole);
}