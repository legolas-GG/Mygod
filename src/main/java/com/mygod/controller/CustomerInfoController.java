package com.mygod.controller;

import com.mygod.dao.AddressDao;
import com.mygod.dao.CustomerDao;
import com.mygod.dao.OrderDao;
import com.mygod.entity.Address;
import com.mygod.entity.Customer;
import com.mygod.entity.Order;
import com.mygod.model.CommonResponse;
import com.mygod.model.RegisterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.smartcardio.CommandAPDU;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by legolas on 2016/1/11.
 */
@Controller
public class CustomerInfoController{
    @Autowired
    CustomerDao customerDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    AddressDao addressDao;
    /*返回用户基本信息*/
    @RequestMapping(value="customer_info" ,method= RequestMethod.POST)
    @ResponseBody
    public Customer showCustomerInfo(int customer_id , HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Customer customer = customerDao.findOne(customer_id);
        return customer;
    }

    /*返回所有用户*/
    @RequestMapping(value = "show_all_customer" , method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Customer> showAllCustomer(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Iterable<Customer> customerIterable = customerDao.findAll();
        return customerIterable;
    }

    /*修改用户基本信息*/
    @RequestMapping(value="update_customer_info" , method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse showCustomerInfo(RegisterReq request ,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin" , "*");
        Customer customer = new Customer(request.getId());
        customer.setPassword(request.getPassword());
        customer.setName(request.getName());
        try {
            Date date= new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirthday());
            customer.setBirthDay(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(request.getGender()==0){
        customer.setGender(Customer.Gender.男);
        }
        if(request.getGender()==1){
            customer.setGender(Customer.Gender.女);
        }
        /*电话在数据表中为非空唯一约束，必须因此设置*/
        customer.setPhone(request.getPhone());
        customerDao.save(customer);
        if(request.getProvince()!=null){
            Address address = new Address();
            address.setCustomer(customer);
            address.setProvince(request.getProvince());
            address.setCity(request.getCity());
            address.setDistrict(request.getDistrict());
            address.setStreet(request.getStreet());
            addressDao.save(address);
        }
        return new CommonResponse(1, "基本信息修改成功");
    }

    /*验证电话*/
    @RequestMapping(value="/check_phone" ,method= RequestMethod.POST)
    @ResponseBody
    public CommonResponse showCustomerInfo(String phone , HttpServletResponse response){
        //实际部署项目，前后端代码放在同一个服务器中不会出现跨域访问错误，该行代码可省略，*号可指定特定域名的请求
        response.setHeader("Access-Control-Allow-Origin", "*");
        Customer customer = customerDao.findByPhone(phone);
        if(customer!=null)
        return new CommonResponse(0, "该号码不可用");
        return new CommonResponse(1, "该号码可用");
    }


}
