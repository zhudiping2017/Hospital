package com.qhit.baseRoleFunction.dao;

import org.springframework.stereotype.Repository;
import com.qhit.baseRoleFunction.pojo.BaseRoleFunction;
import java.util.List;



@Repository  
public interface IBaseRoleFunctionDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<BaseRoleFunction> search(BaseRoleFunction baseRoleFunction);

    List findByRid(Object rid);

    List findByFid(Object fid);

}