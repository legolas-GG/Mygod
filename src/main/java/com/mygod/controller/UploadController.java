package com.mygod.controller;

import com.mygod.model.CommonResponse;
import com.mygod.model.UploadDemoVo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by legolas on 2016/1/13.
 */
@Controller
public class UploadController{
    private final Logger Log = Logger.getLogger(AddressController.class);

    private void SaveFileFormInputStream(InputStream stream,String path, String filename) throws IOException{
        FileOutputStream outputStream = new FileOutputStream(path+ "/" + filename);
        int byteCount = 0 ;
        byte[] bytes=new byte[1024];
        while((byteCount=stream.read(bytes))!=-1){
            outputStream.write(bytes, 0 , byteCount);
        }
        outputStream.close();
        stream.close();

    }
    /*上传图片*/
    @RequestMapping(value="img_upload" , method= RequestMethod.POST)
    @ResponseBody
    public Object firstUpload(HttpServletRequest request, UploadDemoVo demo , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Log.info("firstUpload info:" + demo.toString());
        /*获取项目运行时tomcat容器的绝对路径*/
        String path =request.getSession().getServletContext().getRealPath("/img");
        /*如果该文件夹不存在则创一个*/
        File savefile =new File(path);
        if(!savefile.exists()){
            savefile.mkdirs();
        }

        Log.info(path);
        try {
            SaveFileFormInputStream(demo.getImgFile().getInputStream(), path, "merchandise_" + demo.getId()+".jpg");
        }
        catch(IOException e){
            e.printStackTrace();
            return new CommonResponse(0,"上传失败");
        }
        return new CommonResponse(1,"上传成功");
    }

}
