package com.qhit.medicinePurchaseInfo.service.impl;

import com.qhit.common.CommonUtil;
import com.qhit.medicineInstock.pojo.MedicineInstock;
import com.qhit.medicinePurchaseInfo.service.IMedicinePurchaseInfoService;

import java.util.Date;
import java.util.List;
import com.qhit.medicinePurchaseInfo.dao.IMedicinePurchaseInfoDao;
import com.qhit.medicinePurchaseInfo.pojo.MedicinePurchaseInfo;
import com.qhit.medicineReqPlan.dao.IMedicineReqPlanDao;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;
import com.qhit.medicineStockinfo.dao.IMedicineStockinfoDao;
import com.qhit.medicineStockinfo.pojo.MedicineStockinfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
* Created by GeneratorCode on 2018/12/06
*/
@Service
public class MedicinePurchaseInfoServiceImpl  implements IMedicinePurchaseInfoService {
    @Resource
    IMedicinePurchaseInfoDao dao;
    @Resource
    IMedicineReqPlanDao medicineReqPlanDao;
    @Resource
    IMedicineStockinfoDao medicineStockinfoDao;

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
        MedicinePurchaseInfo medicinePurchaseInfo = findById(id); 
        return dao.delete(medicinePurchaseInfo); 
    } 


    @Override 
    public List findAll() {
        return dao.findAll();
    } 


    @Override 
    public MedicinePurchaseInfo findById(Object id) { 
        List<MedicinePurchaseInfo> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<MedicinePurchaseInfo> search(MedicinePurchaseInfo medicinePurchaseInfo) {

            List<MedicinePurchaseInfo> list = dao.search(medicinePurchaseInfo);
            return list;        
    }

    @Override
    public void collect(HttpSession session) {
//        String sql = "update medicine_req_plan set status=3 where status=2";
//        String sql2 = "select a.MEDICINE_CODEID,cast(sum(a.REQAMT) as char) as sumamt from medicine_req_plan a where a.status=2 group by a.MEDICINE_CODEID";
        List<MedicineReqPlan> medicineReqPlanList2 = medicineReqPlanDao.collectSearch();
        List<MedicineReqPlan> list = medicineReqPlanDao.findByStatus(2);
        for(MedicineReqPlan plan:list){
            plan.setStatus(3);
            medicineReqPlanDao.updateSelective(plan);
        }

        for(MedicineReqPlan medicineReqPlan:medicineReqPlanList2){
            MedicinePurchaseInfo purchaseInfo = new MedicinePurchaseInfo();
            purchaseInfo.setMedicineCodeid(medicineReqPlan.getMedicineCodeid());
            purchaseInfo.setPchAmt(Integer.parseInt(medicineReqPlan.getSumamt()));
            purchaseInfo.setStatus(1);
            purchaseInfo.setPchDate(CommonUtil.dateToStr(new Date()));
            purchaseInfo.setPchUserid(CommonUtil.getUserId(session));
            dao.insert(purchaseInfo);
        }
    }

    @Override
    public List<MedicinePurchaseInfo> preInstockList() {
        return dao.preInstockList();
    }

    @Override
    public void instock(HttpServletRequest request) {
        String[] infos = request.getParameterValues("info");
        for(String info:infos){
            String[] arr = info.split(",");
            String pchId = arr[0];
            String invno = arr[1];
            MedicinePurchaseInfo purchaseInfo = findById(pchId);
//            更新采购信息表状态为3
            purchaseInfo.setStatus(3);
            dao.update(purchaseInfo);
//            往入库记录表中插入数据
            MedicineInstock medicineInstock = new MedicineInstock();
            medicineInstock.setInamt(purchaseInfo.getPchAmt());
            medicineInstock.setInstockDate(CommonUtil.dateToStr(new Date()));
            medicineInstock.setInstockUserid(CommonUtil.getUserId(request.getSession()));
            medicineInstock.setInvno(invno);
            medicineInstock.setManCode(purchaseInfo.getManCode());
            medicineInstock.setMedicineCodeid(purchaseInfo.getMedicineCodeid());
            medicineInstock.setUnitprc(purchaseInfo.getPchPrice());
            medicineInstock.setZje(purchaseInfo.getPchTotal());
            dao.insert(medicineInstock);
//            更新库存表 如果库存表中存在药品则更新，如果不存在则插入
            List<MedicineStockinfo> list = medicineStockinfoDao.findByMedicinecodeId(purchaseInfo.getMedicineCodeid());
            if(list!=null && list.size()>0){
//              //库存中已经存在
                MedicineStockinfo stockinfo = list.get(0);
                stockinfo.setAmt(stockinfo.getAmt()+purchaseInfo.getPchAmt());
                stockinfo.setZje(stockinfo.getZje()+purchaseInfo.getPchTotal());
                stockinfo.setUnitprc(stockinfo.getZje()/stockinfo.getAmt());
                stockinfo.setSaleunitprc(stockinfo.getUnitprc()*1.5);
                dao.update(stockinfo);
            }else{
                //库存中不存在该药品
                MedicineStockinfo stockinfo = new MedicineStockinfo();
                stockinfo.setAmt(purchaseInfo.getPchAmt());
                stockinfo.setMedicinecodeId(purchaseInfo.getMedicineCodeid());
                stockinfo.setUnitprc(purchaseInfo.getPchPrice());
                stockinfo.setSaleunitprc(purchaseInfo.getPchPrice()*1.5);
                stockinfo.setZje(purchaseInfo.getPchTotal());
                dao.insert(stockinfo);
            }


        }
    }


}