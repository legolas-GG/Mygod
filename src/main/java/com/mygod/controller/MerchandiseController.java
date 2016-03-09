package com.mygod.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mygod.dao.CollectionDao;
import com.mygod.dao.MerchandiseDao;
import com.mygod.dao.PackageDao;
import com.mygod.entity.*;
import com.mygod.entity.Package;
import com.mygod.model.CommonResponse;
import com.mygod.model.MerchandiseAndPackageReq;
import com.mygod.model.PackageReq;
import com.mygod.model.TypeReq;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by legolas on 2016/1/10.
 */
@Controller
public class MerchandiseController
{

    private final Logger Log = Logger.getLogger(MerchandiseController.class);
    private final Gson gson = new GsonBuilder().create();
    @Autowired
    MerchandiseDao merchandiseDao;
    @Autowired
    PackageDao packageDao;
    @Autowired
    CollectionDao collectionDao;
    /*返回专区所有商品信息*/
    @RequestMapping(value="/get_by_type",method= RequestMethod.POST)
    @ResponseBody
    public List<TypeReq> findType(Integer customer_id , String type , HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        int allstock = 0;
        List<TypeReq> typeReqList = new ArrayList<TypeReq>();
        Log.info("/customer_id:"+customer_id+"\t"+"/type:"+type);
        List<Merchandise> merchandiseList = merchandiseDao.findByTypename(type);
        Log.info("merchandiseList:"+merchandiseList);
        if (customer_id > 0) {
            for (int i = 0; i < merchandiseList.size(); i++) {
                TypeReq typeReq = new TypeReq();
                if(merchandiseList.get(i).getPackages()==null||merchandiseList.get(i).getPackages().size()==0){
                    typeReq.setStock(0);
                    typeReq.setPrice(0);
                }
                else {
                    for (int j = 0; j < merchandiseList.get(i).getPackages().size(); j++) {
                        allstock += merchandiseList.get(i).getPackages().get(j).getStock();
                    }
                    typeReq.setStock(allstock);
                    typeReq.setPrice(merchandiseList.get(i).getPackages().get(0).getPrice());
                }

                List<Collection> collectionList = collectionDao.findByMerchandise_id(merchandiseList.get(i).getId());
                for (int m = 0; m < collectionList.size(); m++) {
                    if (collectionList.get(m).getCustomer().getId() == customer_id) {
                        typeReq.setIscollected(true);
                        break;
                    }
                }
                typeReq.setName(merchandiseList.get(i).getName());
                typeReq.setMerchandise_id(merchandiseList.get(i).getId());
                typeReq.setDescription(merchandiseList.get(i).getDescription());
                Log.info("/typeReq/id>0:"+typeReq);
                typeReqList.add(typeReq);
            }

            return typeReqList;

        } else {
            for (int i = 0; i < merchandiseList.size(); i++) {
                TypeReq typeReq = new TypeReq();
                if(merchandiseList.get(i).getPackages()==null){
                    typeReq.setStock(0);
                    typeReq.setPrice(0);
                }
                else {
                    for (int j = 0; j < merchandiseList.get(i).getPackages().size(); j++) {
                        allstock += merchandiseList.get(i).getPackages().get(j).getStock();
                    }
                    typeReq.setStock(allstock);
                    typeReq.setPrice(merchandiseList.get(i).getPackages().get(0).getPrice());
                }
                typeReq.setName(merchandiseList.get(i).getName());
                typeReq.setMerchandise_id(merchandiseList.get(i).getId());
                typeReq.setDescription(merchandiseList.get(i).getDescription());
                typeReqList.add(typeReq);
                Log.info("typeReq/id=0:"+typeReq);
            }
            return typeReqList;
        }
    }

    /*返回商品套餐详情*/
    @RequestMapping(value = "get_packages" , method = RequestMethod.POST)
    @ResponseBody
    public List<MerchandiseAndPackageReq> showPackages(Integer merchandise_id , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Package> packageList =packageDao.findByMerchandise_id(merchandise_id);
        List<MerchandiseAndPackageReq> merchandiseAndPackageReqList = new ArrayList<MerchandiseAndPackageReq>();
        for(int i = 0 ; i<packageList.size(); i++){
            MerchandiseAndPackageReq merchandiseAndPackageReq = new MerchandiseAndPackageReq();
            merchandiseAndPackageReq.setPackage_id(packageList.get(i).getId());
            merchandiseAndPackageReq.setPackage_description(packageList.get(i).getDescription());
            merchandiseAndPackageReq.setPrice(packageList.get(i).getPrice());
            merchandiseAndPackageReq.setStock(packageList.get(i).getStock());
            merchandiseAndPackageReqList.add(merchandiseAndPackageReq);
        }
        Log.info(merchandiseAndPackageReqList);
        return merchandiseAndPackageReqList;
    }

    /*返回所有商品*/
    @RequestMapping(value = "show_all_merchandise" , method = RequestMethod.GET)
    @ResponseBody
    public List<Merchandise> showAllMerchandise(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Iterator<Merchandise> iterator = merchandiseDao.findAll().iterator();;
        List<Merchandise> merchandiseList = new ArrayList<Merchandise>();
        while(iterator.hasNext()){
            Merchandise merchandise = new Merchandise();
            Merchandise merchandise1 =iterator.next();
            merchandise.setId(merchandise1.getId());
            merchandise.setName(merchandise1.getName());
            merchandise.setTypename(merchandise1.getTypename());
            merchandise.setDescription(merchandise1.getDescription());
            merchandiseList.add(merchandise);
        }
        return merchandiseList;

    }

    /*添加商品*/
    @RequestMapping(value = "update_merchandise",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse updateMerchandise(MerchandiseAndPackageReq request ,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Merchandise merchandise = new Merchandise();
        Log.info(request);
        if(request.getMerchandise_id()!=0) {
            merchandise.setId(request.getMerchandise_id());
        }
            merchandise.setName(request.getName());
        merchandise.setTypename(request.getType());
            merchandise.setDescription(request.getMerchandise_description());
            merchandiseDao.save(merchandise);
        /*添加商品成功，返回商品id和name*/
        return new CommonResponse(merchandise.getId(),merchandise.getName());
    }

    /*删除商品*/
    @RequestMapping(value = "delete_merchandise",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse updateMerchandise(int merchandise_id , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        merchandiseDao.delete(merchandise_id);
        return new CommonResponse(1,"商品删除成功");
    }

    /*添加套餐*/
    @RequestMapping(value = "update_package",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse updatePackage(String request , HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        /*Log.info("/String-request:"+request);*/
        /*PackageReq模型构造一个MerchandiseAndPackageReq的List类型属性，
        使用gson类将request按packageReq的数据结构解析到packageReqList中*/
        PackageReq packageReq = gson.fromJson(request , PackageReq.class);
        Merchandise merchandise = new Merchandise();
        /*Log.info("/Json-packageReq:"+packageReq);*/
        for(int i = 0; i < packageReq.getMapReqList().size(); i++) {
            /*添加之后对象已被绑定id，下次必须new出新对象，否则信息会覆盖更新*/
            Package pkg = new Package();
            merchandise.setId(packageReq.getMapReqList().get(i).getMerchandise_id());
            pkg.setPrice(packageReq.getMapReqList().get(i).getPrice());
            pkg.setDescription(packageReq.getMapReqList().get(i).getPackage_description());
            pkg.setStock(packageReq.getMapReqList().get(i).getStock());
            pkg.setMerchandise(merchandise);
            packageDao.save(pkg);
        }
        return new CommonResponse(1 ,"套餐添加成功");
    }

    /*删除套餐*/
    @RequestMapping(value = "delete_package",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse deletePackage(int package_id ,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        merchandiseDao.delete(package_id);
        return new CommonResponse(1,"套餐删除成功");
    }
}
