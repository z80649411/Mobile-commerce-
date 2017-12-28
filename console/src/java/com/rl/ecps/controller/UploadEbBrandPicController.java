package com.rl.ecps.controller;

import com.rl.ecps.utils.ResourcesUtils;
import com.rl.ecps.utils.UploadResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ozc on 2017/8/27.
 */

@Controller
@RequestMapping("/upload")
public class UploadEbBrandPicController {
    @RequestMapping("/uploadPic.do")
    public void uploadPic(@RequestParam MultipartFile imgsFile, Writer writer) throws IOException {

        //上传文件的名字是不能相同的，因此我们设置一下文件的名称
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        for(int i = 0; i < 3; i++){
            fileName = fileName + random.nextInt(10);
        }
        //拿到该文件的原始名称
        String originalFilename = imgsFile.getOriginalFilename();

        //获取该文件的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        /***
         * 绝对路径是留给页面src属性做显示的
         * 相对路径是保存在数据库中，通过input来进行提交的。
         */
        //获得上传文件的绝对路径
        String realPath = ResourcesUtils.readProp("file_path")+"/upload/"+fileName+suffix;
        //获得相对路径
        String relativePath = "/upload/"+fileName+suffix;

        //创建jersy的客户端
        Client client = Client.create();
        //创建web资源对象
        WebResource wr = client.resource(realPath);

        //拿到文件的二进制数据，使用web资源对象上传
        byte[] bytes = imgsFile.getBytes();
        wr.put(bytes);

        //使用JSON格式把我们的绝对路径和相对路径返回出去。
        JSONObject jo = new JSONObject();
        jo.accumulate("realPath", realPath);
        jo.accumulate("relativePath", relativePath);
        String result = jo.toString();
        System.out.println(result);
        writer.write(result);
    }


    @RequestMapping("/uploadForFck.do")
    public void uploadForFck(HttpServletRequest request, HttpServletResponse response) throws IOException {


        //把request转换成复杂request
        MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
        //获得文件
        Map<String, MultipartFile> map = mr.getFileMap();
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        String fileInputName = it.next();
        MultipartFile imgsFile = map.get(fileInputName);


        //上传文件的名字是不能相同的，因此我们设置一下文件的名称
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        for(int i = 0; i < 3; i++){
            fileName = fileName + random.nextInt(10);
        }
        //拿到该文件的原始名称
        String originalFilename = imgsFile.getOriginalFilename();

        //获取该文件的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        /***
         * 绝对路径是留给页面src属性做显示的
         * 相对路径是保存在数据库中，通过input来进行提交的。
         */
        //获得上传文件的绝对路径
        String realPath = ResourcesUtils.readProp("file_path")+"/upload/"+fileName+suffix;
        //获得相对路径
        String relativePath = "/upload/"+fileName+suffix;

        //创建jersy的客户端
        Client client = Client.create();
        //创建web资源对象
        WebResource wr = client.resource(realPath);

        //拿到文件的二进制数据，使用web资源对象上传
        byte[] bytes = imgsFile.getBytes();
        wr.put(bytes);

        /**
         * 在FCK中，我们就不再是使用JSON来返回了，我们使用的是内部的对象
         */
        UploadResponse ur = new UploadResponse(UploadResponse.EN_OK,realPath);
        response.getWriter().print(ur);

    }


}
