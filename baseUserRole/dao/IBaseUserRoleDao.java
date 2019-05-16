package com.qhit.baseUserRole.dao;

import org.springframework.stereotype.Repository;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import java.util.List;



@Repository  
public interface IBaseUserRoleDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<BaseUserRole> search(BaseUserRole baseUserRole);

    List findByUid(Object uid);

    List findByRid(Object rid);

}