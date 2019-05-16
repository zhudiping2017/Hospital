package com.qhit.baseUserRole.service.impl;

import com.qhit.baseUserRole.service.IBaseUserRoleService;
import java.util.List;
import com.qhit.baseUserRole.dao.IBaseUserRoleDao;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import org.springframework.stereotype.Service;
import javax.annotation.Resource; 



@Service 
public class BaseUserRoleServiceImpl  implements IBaseUserRoleService {

    @Resource 
    IBaseUserRoleDao dao;

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
        BaseUserRole baseUserRole = findById(id); 
        return dao.delete(baseUserRole); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BaseUserRole findById(Object id) { 
        List<BaseUserRole> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public List<BaseUserRole> search(BaseUserRole baseUserRole) { 
        return dao.search(baseUserRole); 
    } 


}