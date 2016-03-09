package com.mygod.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mygod.dao.AddressDao;
import com.mygod.dao.OrderDao;
import com.mygod.dao.PackageDao;
import com.mygod.entity.*;
import com.mygod.entity.Package;
import com.mygod.model.CommonResponse;
import com.mygod.model.OrderReq;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by legolas on 2016/1/12.
 */
@Controller
public class OrderController {
    private final Logger Log = Logger.getLogger(AddressController.class);
    private final Gson gson = new GsonBuilder().create();
    @Autowired
    OrderDao orderDao;
    @Autowired
    AddressDao addressDao;
    @Autowired
    PackageDao packageDao;

    /*生成订单*/
    @RequestMapping(value = "save_order" , method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse saveOrder(OrderReq orderReq , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Customer customer = new Customer(orderReq.getCustomer_id());
        Package packages = new Package(orderReq.getPackage_id());
        Order order = new Order();
        order.setProvince(orderReq.getProvince());
        order.setCity(orderReq.getCity());
        order.setDistrict(orderReq.getDistrict());
        order.setStreet(orderReq.getStreet());
        order.setCustomer(customer);
        order.setPackages(packages);
        order.setCount(orderReq.getCount());
        order.setPhone(orderReq.getPhone());
        order.setDate(new Date());
        orderDao.save(order);
        Package aftpkg = packageDao.findOne(orderReq.getPackage_id());
        aftpkg.setStock(aftpkg.getStock()-1);
        packageDao.save(aftpkg);
        return new CommonResponse(order.getId(),"订单生成成功");
    }

    /*显示用户已完成订单*/
    @RequestMapping(value="show_order" , method= RequestMethod.POST)
    @ResponseBody
    public List<OrderReq> showOrder(Integer customer_id , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<OrderReq> orderReqList = new ArrayList<OrderReq>();
        List<Order> orderList = orderDao.findByCustomer_id(customer_id);
        for(int i =0; i<orderList.size(); i++){
            OrderReq orderReq = new OrderReq();
            orderReq.setMerchandise_id(orderList.get(i).getPackages().getMerchandise().getId());
            orderReq.setDescription(orderList.get(i).getPackages().getDescription());
            orderReq.setCount(orderList.get(i).getCount());
            orderReq.setProvince(orderList.get(i).getProvince());
            orderReq.setCity(orderList.get(i).getCity());
            orderReq.setDistrict(orderList.get(i).getDistrict());
            orderReq.setStreet(orderList.get(i).getStreet());
            orderReq.setPhone(orderList.get(i).getPhone());
            orderReq.setPrice(orderList.get(i).getPackages().getPrice());
            orderReqList.add(orderReq);
        }
        Log.info("/orderList:"+orderReqList);
        return orderReqList;
    }

    /*显示所有订单*/
    @RequestMapping(value = "show_all_order" , method = RequestMethod.GET)
    @ResponseBody
    public List<OrderReq> showAllOrder(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Iterator<Order> orderIterator = orderDao.findAll().iterator();
        List<OrderReq> orderReqList = new ArrayList<OrderReq>();
        while(orderIterator.hasNext()){
            Order ordersub =  orderIterator.next();
            OrderReq orderReq = new OrderReq();
            orderReq.setOrder_id(ordersub.getId());
            orderReq.setCustomer_name((ordersub.getCustomer().getName()));
            orderReq.setDescription(ordersub.getPackages().getDescription());
            orderReq.setCount(ordersub.getCount());
            orderReq.setPrice(ordersub.getPackages().getPrice());
            orderReq.setProvince(ordersub.getProvince());
            orderReq.setCity(ordersub.getCity());
            orderReq.setDistrict(ordersub.getDistrict());
            orderReq.setStreet(ordersub.getStreet());
            orderReq.setPhone(ordersub.getPhone());
            orderReq.setDate(ordersub.getDate());
            Log.info("/orderReq:"+orderReq);
            orderReqList.add(orderReq);
        }
        Log.info("/orderReqList:"+orderReqList);
        return orderReqList;
    }
}
