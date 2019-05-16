package com.qhit.baseUser.service.impl;

import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseUser.service.IBaseUserService;
import java.util.List;
import com.qhit.baseUser.dao.IBaseUserDao;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUserRole.dao.IBaseUserRoleDao;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.utils.MD5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class BaseUserServiceImpl  implements IBaseUserService {
    @Resource
    IBaseUserDao dao;
    @Resource
    IBaseUserRoleDao baseUserRoleDao;

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
        BaseUser baseUser = findById(id); 
        return dao.delete(baseUser); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BaseUser findById(Object id) { 
        List<BaseUser> list = dao.findById(id); 
        return  list.get(0); 
    }

    @Override
    public List<BaseUser> search(BaseUser baseUser) {
        return dao.search(baseUser);
    }

    @Override
    public BaseUser login(BaseUser baseUser) {
        MD5 md5 = new MD5();
        String password = md5.getMD5ofStr(baseUser.getPassword());
        /*String sql = "SELECT * from base_user bu LEFT JOIN base_user_role bur ON bu.user_id=bur.uid\n" +
                "\t\t\t   LEFT JOIN base_role br ON bur.rid=br.rid\n" +
                "\t\t\t   LEFT JOIN base_role_function brf ON br.rid = brf.rid\n" +
                "\t\t\t   LEFT JOIN base_function bf ON brf.fid=bf.fid";
        sql += " where bu.user_name ='"+baseUser.getUserName()+"' and bu.password='"+password+"'";*/
        baseUser.setPassword(password);
        List<BaseUser> list = dao.login(baseUser);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean findOldPassword(BaseUser baseUser) {
        MD5 md5 = new MD5();
        String password = md5.getMD5ofStr(baseUser.getPassword());
        baseUser.setPassword(password);
//        String sql = "select * from base_user where user_id='"+baseUser.getUserId()+"' and password='"+password+"'";
        List list = dao.login(baseUser);
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<BaseRole> findLeftRole(Integer userId) {
        return dao.findLeftRole(userId);
    }

    @Override
    public List<BaseRole> findRightRole(Integer userId) {
        return dao.findRightRole(userId);
    }

    @Override
    public void distributeUpdate(Integer userId, String[] rids) {
        List<BaseUserRole> list = baseUserRoleDao.findByUid(userId);
        for(BaseUserRole bur:list){
            baseUserRoleDao.delete(bur);
        }

//        baseUserRoleService.freeUpdate(sql);
        for(String rid:rids){
            BaseUserRole baseUserRole = new BaseUserRole();
            baseUserRole.setUid(userId);
            baseUserRole.setRid(Integer.parseInt(rid));
            baseUserRoleDao.insert(baseUserRole);
        }
    }

    @Override
    public List<BaseUser> findByDeptId(Integer deptId) {
        return dao.findByDeptId(deptId);
    }


}