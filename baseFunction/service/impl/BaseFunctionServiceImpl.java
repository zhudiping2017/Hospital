package com.qhit.baseFunction.service.impl;

import com.qhit.baseFunction.service.IBaseFunctionService;
import java.util.List;
import com.qhit.baseFunction.dao.IBaseFunctionDao;
import com.qhit.baseFunction.pojo.BaseFunction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseFunctionServiceImpl  implements IBaseFunctionService {
    @Resource
    IBaseFunctionDao dao;

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
        BaseFunction baseFunction = findById(id); 
        return dao.delete(baseFunction); 
    } 


    @Override 
    public List findAll() {
//        String sql = "SELECT * from base_function bf left JOIN base_module bm ON bf.mid = bm.mid";
        return dao.findAll();
    } 


    @Override 
    public BaseFunction findById(Object id) { 
        List<BaseFunction> list = dao.findById(id); 
        return  list.get(0); 
    }

    @Override
    public List<BaseFunction> findByMid(Integer mid,Integer userId) {
        return dao.findByMid(mid,userId);
    }


}