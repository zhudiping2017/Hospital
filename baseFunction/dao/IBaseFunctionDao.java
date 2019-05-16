package com.qhit.baseFunction.dao;

import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.utils.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IBaseFunctionDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    List findByFname(Object fname);

    List<BaseFunction> findByMid(Integer mid,Integer userId);

    List findByUrl(Object url);

}