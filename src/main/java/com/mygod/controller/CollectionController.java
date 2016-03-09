package com.mygod.controller;

import com.mygod.dao.CollectionDao;
import com.mygod.entity.Collection;
import com.mygod.entity.Customer;
import com.mygod.entity.Merchandise;
import com.mygod.model.CommonResponse;
import com.mygod.model.MerchandiseAndPackageReq;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

/**
 * Created by legolas on 2016/1/11.
 */
@Controller
public class CollectionController {
    @Autowired
    CollectionDao collectionDao;
    /*收藏商品*/
    @RequestMapping(value = "/love_goods" , method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse loveGoods(int customer_id , int merchandise_id ,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Customer customer = new Customer(customer_id);
        Merchandise merchandise  = new Merchandise(merchandise_id);
        Collection collection = new Collection();
        collection.setCustomer(customer);
        collection.setMerchandise(merchandise);
        collectionDao.save(collection);
        return new CommonResponse(1,"收藏成功");
    }

    /*取消收藏商品*/
    @RequestMapping(value = "/not_love_goods" , method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse notLoveGoods(int customer_id , int merchandise_id , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Collection collection = collectionDao.findByCustomer_idAndMerchandise_id(customer_id, merchandise_id);
        collectionDao.delete(collection.getId());
        return new CommonResponse(1,"取消收藏成功");
    }

    /*显示喜欢商品*/
    @RequestMapping(value = "/show_love_goods" , method = RequestMethod.POST)
    @ResponseBody
    public List<MerchandiseAndPackageReq> showLoveGoods(int customer_id , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        /*通过用户id取出收藏信息*/
        List<Collection> collectionList = collectionDao.findByCustomer_id(customer_id);
        List<MerchandiseAndPackageReq> merchandiseList = new ArrayList<MerchandiseAndPackageReq>();
        /*循环取出收藏列表中的商品对象并放入List列表中*/
        for (int i = 0 ;i < collectionList.size() ; i++){
            MerchandiseAndPackageReq merchandiseAndPackageReq = new MerchandiseAndPackageReq();
            merchandiseAndPackageReq.setMerchandise_id(collectionList.get(i).getMerchandise().getId());
            merchandiseAndPackageReq.setName(collectionList.get(i).getMerchandise().getName());
            merchandiseAndPackageReq.setMerchandise_description(collectionList.get(i).getMerchandise().getDescription());
            merchandiseList.add(merchandiseAndPackageReq);
        }
        return merchandiseList;
    }
}
