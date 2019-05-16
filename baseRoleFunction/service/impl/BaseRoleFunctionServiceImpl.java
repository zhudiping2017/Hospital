package com.qhit.baseRoleFunction.service.impl;

import com.qhit.baseRoleFunction.service.IBaseRoleFunctionService;
import java.util.List;
import com.qhit.baseRoleFunction.dao.IBaseRoleFunctionDao;
import com.qhit.baseRoleFunction.pojo.BaseRoleFunction;
import org.springframework.stereotype.Service;
import javax.annotation.Resource; 


@Service 
public class BaseRoleFunctionServiceImpl  implements IBaseRoleFunctionService {

    @Resource 
    IBaseRoleFunctionDao dao;

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
        BaseRoleFunction baseRoleFunction = findById(id); 
        return dao.delete(baseRoleFunction); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BaseRoleFunction findById(Object id) { 
        List<BaseRoleFunction> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public List<BaseRoleFunction> search(BaseRoleFunction baseRoleFunction) { 
        return dao.search(baseRoleFunction); 
    } 


}