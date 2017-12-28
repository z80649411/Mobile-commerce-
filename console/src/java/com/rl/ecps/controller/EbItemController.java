package com.rl.ecps.controller;

import com.rl.ecps.model.*;
import com.rl.ecps.service.EbBrandService;
import com.rl.ecps.service.EbFeatureService;
import com.rl.ecps.service.EbItemService;

import com.rl.ecps.utils.Page;
import com.rl.ecps.utils.ResourcesUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ozc on 2017/9/6.
 */

@Controller
@RequestMapping("/item")
public class EbItemController {

    @Autowired
    private EbItemService itemService;

    @Autowired
    private EbBrandService ebBrandService;

    @Autowired
    private EbFeatureService featureService;

    /**
     * 根据多条件查询商品的数据
     *
     * @param queryCondition
     * @param model
     * @return
     */
    @RequestMapping("/listItem.do")
    public String listItem(QueryCondition queryCondition, Model model) {

        //拿到所有的品牌，用于给用户下拉框选择
        List<EbBrand> ebBrands = ebBrandService.selectBrand();

        //如果是第一次访问的话，那么默认是没有当前页号的，因此赋值为1
        if (queryCondition.getPageNo() == null) {
            queryCondition.setPageNo(1);
        }
        //得到分页数据
        Page page = itemService.selectItemByCondition(queryCondition);

        model.addAttribute("page", page);
        model.addAttribute("ebBrands", ebBrands);

        //回显条件数据
        model.addAttribute("queryCondition", queryCondition);

        return "item/list";
    }

    /**
     * 跳转到添加商品页面
     *
     * @return
     */
    @RequestMapping("/toAddItem.do")
    public String toAddItem(Model model) {

        List<EbBrand> ebBrands = ebBrandService.selectBrand();
        List<EbFeature> commFeature = featureService.selectCommFeature();
        List<EbFeature> specFeature = featureService.selectSpecFeature();
        model.addAttribute("ebBrands", ebBrands);
        model.addAttribute("commFeature", commFeature);
        model.addAttribute("specFeature", specFeature);

        return "item/addItem";
    }

    @RequestMapping("/listAudit.do")
    public String listAudit(QueryCondition queryCondition,Model model) {

        //查询出所有的品牌
        List<EbBrand> ebBrands = ebBrandService.selectBrand();

        //如果是第一次访问的话，那么默认是没有当前页号的，因此赋值为1
        if (queryCondition.getPageNo() == null) {
            queryCondition.setPageNo(1);
        }
        //得到分页数据
        Page page = itemService.selectItemByCondition(queryCondition);

        model.addAttribute("page", page);
        model.addAttribute("ebBrands", ebBrands);
        //回显条件数据
        model.addAttribute("queryCondition", queryCondition);

        return "item/listAudit";
    }

    @RequestMapping("/addItem.do")
    public String  addItem(EbItem ebItem, EbItemClob ebItemClob, HttpServletRequest request, Integer divNum) {


        //为商品设置编号
        ebItem.setItemNo(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));

        //查询出所有的属性数据
        List<EbFeature> commFeature = featureService.selectCommFeature();

        //创建一个集合来装载我们所有的参数数据
        List<EbParaValue> ebParaValues = new ArrayList<EbParaValue>();

        for (EbFeature feature : commFeature) {
            //得到录入方式
            Short inputType = feature.getInputType();

            //复选，复选的值是有多个的
            if (inputType == 3) {
                String[] parameterValues = request.getParameterValues(feature.getFeatureId() + "");

                //装载参数数据
                String value = "";

                //如果得到的数据不为null，那么就使用一个字符串来对其进行拼接
                if (parameterValues != null && parameterValues.length > 0) {
                    for (String parameterValue : parameterValues) {
                        value = value + parameterValue + ",";
                    }
                    //去除最后一个逗号
                    value = value.substring(0, value.length() - 1);

                    //把数据封装到参数对象上
                    EbParaValue ebParaValue = new EbParaValue();
                    ebParaValue.setFeatureId(feature.getFeatureId());
                    ebParaValue.setParaValue(value);

                    //装载到集合中
                    ebParaValues.add(ebParaValue);
                }

            } else {
                //非复选的值都只有一个
                String parameter = request.getParameter(feature.getFeatureId() + "");

                //如果拿到的数据不为null
                if (StringUtils.isNotBlank(parameter)) {

                    //把数据封装到参数对象上
                    EbParaValue ebParaValue = new EbParaValue();
                    ebParaValue.setFeatureId(feature.getFeatureId());
                    ebParaValue.setParaValue(parameter);

                    //装载到集合中
                    ebParaValues.add(ebParaValue);
                }
            }
        }

        //使用集合来进行装sku的对象
        List<EbSku> skuList = new ArrayList<EbSku>();

        //遍历出特殊属性的值
        List<EbFeature> specList = featureService.selectSpecFeature();

        //获取页面有多少个单元格，对其进行遍历，取出对应的值
        for (int i = 1; i <= divNum; i++) {

            //获得商城价和库存，他们是必填的字段
            String skuPrice = request.getParameter("skuPrice" + i);
            String stock = request.getParameter("stockInventory" + i);

            //如果上面的必填字段不是空说明数据有效
            if (StringUtils.isNotBlank(skuPrice) && StringUtils.isNotBlank(stock)) {

                String skuType = request.getParameter("skuType" + i);
                String showStatus = request.getParameter("showStatus" + i);
                String sort = request.getParameter("sort" + i);
                String marketPrice = request.getParameter("marketPrice" + i);
                String skuUpperLimit = request.getParameter("skuUpperLimit" + i);
                String sku = request.getParameter("sku" + i);
                String location = request.getParameter("location" + i);

                //创建最小销售单元的对象，并且赋值
                EbSku skuObj = new EbSku();
                skuObj.setSkuPrice(new BigDecimal(skuPrice));
                skuObj.setStockInventory(new Integer(stock));

                if (StringUtils.isNotBlank(skuType) && !StringUtils.equals(skuType, "")) {
                    skuObj.setSkuType(new Short(skuType));
                }
                if (StringUtils.isNotBlank(showStatus) && !StringUtils.equals(showStatus, "")) {
                    skuObj.setShowStatus(new Short(showStatus));
                }
                if (StringUtils.isNotBlank(sort) && !StringUtils.equals(sort, "")) {
                    skuObj.setSkuSort(new Integer(sort));
                }
                if (StringUtils.isNotBlank(marketPrice) && !StringUtils.equals(marketPrice, "")) {
                    skuObj.setMarketPrice(new BigDecimal(marketPrice));
                }
                if (StringUtils.isNotBlank(skuUpperLimit) && !StringUtils.equals(skuUpperLimit, "")) {
                    skuObj.setSkuUpperLimit(new Integer(skuUpperLimit));
                }
                skuObj.setSku(sku);
                skuObj.setLocation(location);

                //装取特殊属性的集合
                List<EbSpecValue> specValList = new ArrayList<EbSpecValue>();

                for (EbFeature feature : specList) {
                    Long featureId = feature.getFeatureId();
                    //获得所选规格属性的值
                    String specVal = request.getParameter(featureId + "specradio" + i);
                    if (StringUtils.isNotBlank(specVal)) {
                        //创建规格对象
                        EbSpecValue spec = new EbSpecValue();
                        spec.setFeatureId(featureId);
                        spec.setSpecValue(specVal);
                        specValList.add(spec);
                    }
                }
                skuObj.setSpecList(specValList);
                skuList.add(skuObj);
            }
        }

        itemService.saveItem(ebItem, ebItemClob, skuList, ebParaValues);
        return "redirect:listItem.do";


    }
    @RequestMapping("/auditItem.do")
    public String  auditItem(Long itemId, Short auditStatus, String notes) throws IOException {
        itemService.updateItem(itemId, auditStatus, notes);
        return "redirect:listAudit.do";
    }

    @RequestMapping("/showItem.do")
    public String  showItem(Long itemId, Short showStatus) throws IOException {
        itemService.showItem(itemId, showStatus);
        return "redirect:listItem.do";
    }

    @RequestMapping("/publishItem.do")
    public void publishItem(Long itemId, PrintWriter out) throws Exception {
        String password = ResourcesUtils.readProp("ws_pass");
        String result = itemService.publishItem(itemId, password);
        out.write(result);
    }


}
