package com.rl.ecps.controller;

import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbFeature;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.service.EbBrandService;
import com.rl.ecps.service.EbFeatureService;
import com.rl.ecps.service.EbItemService;
import com.rl.ecps.service.EbSkuService;
import com.rl.ecps.service.impl.EbBrandServiceImpl;
import com.rl.ecps.service.impl.EbFeatureServiceImpl;
import com.rl.ecps.service.impl.EbItemServiceImpl;
import com.rl.ecps.service.impl.EbSkuServiceImpl;
import com.rl.ecps.utils.ResourcesUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ozc on 2017/9/6.
 */
@Controller
@RequestMapping("/item")
public class EbItemController {

    @Autowired
    private EbBrandService brandService;

    @Autowired
    private EbFeatureService featureService;
    @Autowired
    private EbItemService itemService;
    @Autowired
    private EbSkuService skuService;


    /**
     * 跳转到首页
     * @param model
     * @return
     */
    @RequestMapping("/toIndex.do")
    public String toIndex(Model model) {
        List<EbBrand> ebBrands = brandService.selectBrand();
        List<EbFeature> isSelect = featureService.selectIsSelect();
        model.addAttribute("ebBrands", ebBrands);
        model.addAttribute("isSelect", isSelect);

        return "index";
    }

    /**
     * 更新页面上的库存和价钱
     *
     * @return
     */
    @RequestMapping("/updatePriceAndStock.do")
    public void updatePriceAndStock(Long skuId, HttpServletResponse response) {

        if (skuId != null) {

            //得到具体的库存对象
            EbSku ebSku = skuService.selectByPrimaryKey(skuId);

            //将对象写成JSON返回出去
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("sku", ebSku);
            String result = jsonObject.toString();

            ResourcesUtils.printJSON(result,response);
        }
    }


    /**
     * 接收条件，查询出符合条件的商品
     *
     * @param brandId 品牌id
     * @param price   价钱
     * @param paraStr   被选中的参数
     * @return
     */
    @RequestMapping("/listItem.do")
    public String listItem(Long brandId, String price, String paraStr, Model model) {

        List<EbItem> items = itemService.listItem(brandId, price, paraStr);

        model.addAttribute("items", items);

        return "phoneClassification";
    }

    /**
     * 查看商品的单品页信息
     *
     * @return
     */
    @RequestMapping("/toProductDetail.do")
    public String toProductDetail(Long itemId,Model model) {

        EbItem ebItem = itemService.selectItemDetail(itemId);
        model.addAttribute("item", ebItem);



        return "productDetail";
    }


}
