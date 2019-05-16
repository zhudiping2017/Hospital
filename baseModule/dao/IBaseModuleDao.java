package com.qhit.baseModule.dao;

import org.springframework.stereotype.Repository;
import com.qhit.baseModule.pojo.BaseModule;
import java.util.List;



@Repository  
public interface IBaseModuleDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<BaseModule> search(BaseModule baseModule);

    List findByMname(Object mname);

}