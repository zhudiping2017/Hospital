package com.qhit.baseModule.service;

import java.util.List;
import com.qhit.baseModule.pojo.BaseModule;


public interface IBaseModuleService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BaseModule findById(Object id);

    List<BaseModule> search(BaseModule baseModule);

}