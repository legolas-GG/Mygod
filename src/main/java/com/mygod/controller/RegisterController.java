package com.mygod.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mygod.dao.AddressDao;
import com.mygod.dao.CustomerDao;
import com.mygod.entity.Address;
import com.mygod.entity.Customer;
import com.mygod.entity.Customer.Gender;
import com.mygod.model.CommonResponse;
import com.mygod.model.RegisterReq;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by siren93 on 16/1/6.
 */
@Controller
public class RegisterController {

    private final Logger Log = Logger.getLogger(RegisterController.class);
    private final Gson gson = new GsonBuilder().create();

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private AddressDao addressDao;

    @RequestMapping(value = "/get-by-name", method = RequestMethod.GET)
    @ResponseBody
    public Customer getByName(String name , HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Customer user = customerDao.findByName("Jason");
        Log.debug("/get-by-name:"+gson.toJson(user));
        return user;
    }

    @RequestMapping(value="/register",method=RequestMethod.POST)
    @ResponseBody
    public CommonResponse register(RegisterReq request , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        //用getParameterMap方法将前端麻烦的非标识符通过字符串访问；函数参数HttpServletRequest request
       /* String name = request.getParameterMap().get("entry[field_1]")[0];*/
        Log.info("/register:"+gson.toJson(request));
        /*保存用户基本信息*/
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setPassword(request.getPassword());
        if(request.getGender()==0)
        customer.setGender(Gender.男);
        if(request.getGender()==1)
        customer.setGender(Gender.女);
        try {
            Date date= new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirthday());
            customer.setBirthDay(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        customerDao.save(customer);
        /*保存地址*/
        Address address = new Address();
        address.setCustomer(customer);
        address.setProvince(request.getProvince());
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());
        address.setStreet(request.getStreet());
        addressDao.save(address);
        /*注册成功，返回id和用户姓名*/
        return new CommonResponse(customer.getId(),request.getName());

    }



}
