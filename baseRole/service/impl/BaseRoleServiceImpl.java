package com.qhit.baseRole.service.impl;

import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseRole.service.IBaseRoleService;

import java.util.List;
import com.qhit.baseRole.dao.IBaseRoleDao;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseRoleFunction.dao.IBaseRoleFunctionDao;
import com.qhit.baseRoleFunction.pojo.BaseRoleFunction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class BaseRoleServiceImpl  implements IBaseRoleService {
    @Resource
    IBaseRoleDao baseRoleDao;
    @Resource
    IBaseRoleFunctionDao baseRoleFunctionDao;

    @Override 
    public boolean insert(Object object) { 
        return baseRoleDao.insert(object);
    } 


    @Override 
    public boolean update(Object object) { 
        return baseRoleDao.update(object);
    } 


    @Override 
    public boolean updateSelective(Object object) { 
        return baseRoleDao.updateSelective(object);
    } 


    @Override 
    public boolean delete(Object id) { 
        BaseRole baseRole = findById(id); 
        return baseRoleDao.delete(baseRole);
    } 


    @Override 
    public List findAll() {
        return baseRoleDao.findAll();
    } 


    @Override 
    public BaseRole findById(Object id) { 
        List<BaseRole> list = baseRoleDao.findById(id);
        return  list.get(0); 
    }

    @Override
    public List<BaseFunction> distributeLeft(BaseRole baseRole) {
        return baseRoleDao.distributeLeft(baseRole);
    }

    @Override
    public List<BaseFunction> distributeRight(BaseRole baseRole) {
        return baseRoleDao.distributeRight(baseRole);
    }

    @Override
    public void distributeUpdate(Integer rid, String[] arr) {
        //        删除base_role_function表中所有rid记录
        List<BaseRoleFunction> list = baseRoleFunctionDao.findByRid(rid);
        for(BaseRoleFunction br:list){
            baseRoleFunctionDao.delete(br);
        }
//        批量插入
        for(String fid:arr){
            BaseRoleFunction baseRoleFunction = new BaseRoleFunction();
            baseRoleFunction.setFid(Integer.parseInt(fid));
            baseRoleFunction.setRid(rid);
            baseRoleFunctionDao.insert(baseRoleFunction);
        }
    }


}