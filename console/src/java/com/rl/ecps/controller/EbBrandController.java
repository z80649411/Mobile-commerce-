package com.rl.ecps.controller;

import com.rl.ecps.model.EbBrand;
import com.rl.ecps.service.EbBrandService;
import com.rl.ecps.service.impl.EbBrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by ozc on 2017/8/27.
 */

@Controller
@RequestMapping("/brand")
public class EbBrandController {


    @Autowired
    private EbBrandService ebBrandService;


    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("/toItemIndex.do")
    public String toItemIndex(){
        return "item/index";
    }

    /**
     * 跳转到添加品牌的页面
     * @return
     */
    @RequestMapping("/toAddBrand.do")
    public String toAddBrand(){
        return "item/addbrand";
    }

    /**
     * 跳转到编辑品牌的页面
     *
     * @return
     */
    @RequestMapping("/toEditBrand.do")
    public String toEditBrand(String brandId, Model model) {
        //根据id查找出某特定的商品
        EbBrand brand = ebBrandService.getBrandById(Long.valueOf(brandId));
        model.addAttribute("brandId", brandId);
        model.addAttribute("brand", brand);
        return "item/editbrand";
    }

    /**
     * 校验名称是否唯一
     * @param brandName
     * @param out
     * @throws IOException
     */
    @RequestMapping("/validateBrandName.do")
    public void validateBrandName(String brandName, Writer out) throws IOException {

        //表示成功
        String responseTest = "yes";

        //根据名字去查找数据库
        List<EbBrand> brands = ebBrandService.selectBrandByName(brandName);
        //如果返回的集合有Brand了，那么就证明数据库有相同的品牌了
        if (brands != null && brands.size() > 0) {
            responseTest = "no";
        }

        out.write(responseTest);

    }

    /**
     * 更新品牌
     * @param ebBrand
     * @return
     * @throws IOException
     */
    @RequestMapping("/updateBrand.do")
    public String updateBrand(EbBrand ebBrand) throws IOException {
        ebBrandService.updateBrand(ebBrand);
        return "redirect:listBrand.do";
    }

    /**
     * 添加商品处理
     * @param ebBrand
     * @return
     * @throws IOException
     */
    @RequestMapping("/addBrand.do")
    public String  addBrand(EbBrand ebBrand) throws IOException {
        ebBrandService.saveBrand(ebBrand);
        return "redirect:listBrand.do";
    }

    /**
     * 列出所有的商品
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("/listBrand.do")
    public String  listBrand(Model model) throws IOException {
        List<EbBrand> brandList = ebBrandService.selectBrand();
        model.addAttribute("brandList", brandList);
        return "item/listbrand";
    }

    /**
     * 删除品牌
     * @param brandId
     * @return
     * @throws IOException
     */
    @RequestMapping("/deleteBrand.do")
    public String  deleteBrand(String brandId) throws IOException {
        ebBrandService.deleteBrand(Long.valueOf(brandId));
        return "redirect:listBrand.do";
    }



}
