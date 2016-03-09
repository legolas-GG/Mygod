package com.mygod.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mygod.dao.CustomerDao;
import com.mygod.entity.Customer;
import com.mygod.model.CommonResponse;
import com.mygod.model.RegisterReq;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by legolas on 2016/1/9.
 */
@Controller
public class LoginController {
    private final Logger Log = Logger.getLogger(RegisterController.class);

    @Autowired
    CustomerDao customerDao;
    /*登陆*/
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse login(RegisterReq request , HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Origin", "*");
        /*customerDao.findByPhone(request.getPhone());*/
       Customer customer = customerDao.findByPhone(request.getPhone());
        if(customer==null){
            return new CommonResponse(0,"号码不存在");
        }
        else if(request.getPassword().equals(customer.getPassword())){
            /*登陆成功，返回用户id和姓名*/
           return new CommonResponse(customer.getId(),customer.getName());
        }
        else return new CommonResponse(0,"登录失败");
    }
}
