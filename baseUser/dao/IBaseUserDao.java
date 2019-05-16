package com.qhit.baseUser.dao;

import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.utils.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Repository
public interface IBaseUserDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    List findByUserName(Object userName);

    List findByPassword(Object password);

    List findByCname(Object cname);

    List findBySex(Object sex);

    List findByDeptId(Object deptId);

    List findByPostId(Object postId);

    List<BaseUser> search(BaseUser baseUser);

    List<BaseUser> login(BaseUser baseUser);

    List<BaseRole> findLeftRole(Integer userId);

    List<BaseRole> findRightRole(Integer userId);
}