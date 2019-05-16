package com.qhit.baseModule.service.impl;

import com.qhit.baseModule.service.IBaseModuleService;
import java.util.List;
import com.qhit.baseModule.dao.IBaseModuleDao;
import com.qhit.baseModule.pojo.BaseModule;
import org.springframework.stereotype.Service;
import javax.annotation.Resource; 



@Service 
public class BaseModuleServiceImpl  implements IBaseModuleService {

    @Resource 
    IBaseModuleDao dao;

    @Override 
    public boolean insert(Object object) { 
        return dao.insert(object); 
    } 


    @Override 
    public boolean update(Object object) { 
        return dao.update(object); 
    } 


    @Override 
    public boolean updateSelective(Object object) { 
        return dao.updateSelective(object); 
    } 


    @Override 
    public boolean delete(Object id) { 
        BaseModule baseModule = findById(id); 
        return dao.delete(baseModule); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BaseModule findById(Object id) { 
        List<BaseModule> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public List<BaseModule> search(BaseModule baseModule) { 
        return dao.search(baseModule); 
    } 


}