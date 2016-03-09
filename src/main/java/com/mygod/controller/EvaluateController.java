package com.mygod.controller;

import com.mygod.dao.EvaluateDao;
import com.mygod.entity.Customer;
import com.mygod.entity.Evaluate;
import com.mygod.entity.Merchandise;
import com.mygod.model.CommonResponse;
import com.mygod.model.EvaluateReq;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by legolas on 2016/1/10.
 */
@Controller
public class EvaluateController {

    private final Logger Log = Logger.getLogger(EvaluateController.class);

    @Autowired
    EvaluateDao evaluateDao;
    /*提交评价*/
    @RequestMapping(value="/save_evaluate" , method= RequestMethod.POST)
    @ResponseBody
    public CommonResponse saveEvaluate(EvaluateReq request , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Customer customer = new Customer(request.getCustomer_id());
        Merchandise merchandise = new Merchandise(request.getMerchandise_id());
        Evaluate evaluate =new Evaluate();
        evaluate.setCustomer(customer);
        evaluate.setMerchandise(merchandise);
        evaluate.setContent(request.getContent());
        evaluate.setDate(new Date());
        evaluateDao.save(evaluate);
        return new CommonResponse(1,"评价成功");
    }
    /*返回商品所有评价*/
    @RequestMapping(value="/all_evaluate",method=RequestMethod.POST)
    @ResponseBody
    public List<EvaluateReq> showEvaluate(Integer merchandise_id, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Evaluate> evaluateList = evaluateDao.findByMerchandise_id(merchandise_id);
        List<EvaluateReq> evaluateReqList = new ArrayList<EvaluateReq>();
        for (int i = 0 ; i<evaluateList.size(); i++){
          EvaluateReq evaluateReq = new EvaluateReq();
            evaluateReq.setUsername(evaluateList.get(i).getCustomer().getName());
            evaluateReq.setContent(evaluateList.get(i).getContent());
            evaluateReq.setDate(evaluateList.get(i).getDate());
            evaluateReqList.add(evaluateReq);
        }
        return evaluateReqList;
    }
}
