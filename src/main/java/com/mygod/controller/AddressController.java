package com.mygod.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mygod.dao.AddressDao;
import com.mygod.dao.CustomerDao;
import com.mygod.entity.Address;
import com.mygod.entity.Customer;
import com.mygod.model.CommonResponse;
import com.mygod.model.RegisterReq;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by legolas on 2016/1/9.
 */
@Controller
public class AddressController {
    private final Logger Log = Logger.getLogger(AddressController.class);
    private final Gson gson = new GsonBuilder().create();

    @Autowired
    private AddressDao addressDao;
    /*返回用户所有地址*/
    @RequestMapping(value="/all_address" , method = RequestMethod.POST)
    @ResponseBody
    public List<Address> showAddr(int customer_id ,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Address> addressList = addressDao.findByCustomer_id(customer_id);
        /*如果实体customer中与Address存在OneToMany约束关系，
        在Address实体类的customer加上@JsonIgnore注解，
        在返回时忽略解析该字段，避免循环引用造成的栈溢出*/
        /*List<RegisterReq> registerReqList = new ArrayList<RegisterReq>();
        for(int i = 0 ; i<addressList.size(); i++){
            RegisterReq registerReq = new RegisterReq();
            registerReq.setId(addressList.get(i).getId());
            registerReq.setProvince(addressList.get(i).getProvince());
            registerReq.setCity(addressList.get(i).getCity());
            registerReq.setDistrict(addressList.get(i).getDistrict());
            registerReq.setStreet(addressList.get(i).getStreet());
            registerReqList.add(registerReq);
            }*/
        return addressList;
    }
    /*修改地址*/
    @RequestMapping(value = "/modify_address", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse modifyAddr(Address request , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Address address=new Address(request.getId());
        address.setProvince(request.getProvince());
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());
        address.setStreet(request.getStreet());
        addressDao.save(address);
        return new CommonResponse(1,"修改成功");
    }
    /*删除地址*/
    @RequestMapping(value = "/delete_address", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse deleteAddr(Address request , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Address address=new Address(request.getId());
        addressDao.delete(address);
        return new CommonResponse(1,"删除成功");
    }
}
