package com.rl.ecps.controller;

import com.rl.ecps.model.EbCart;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.service.EbCartService;
import com.rl.ecps.service.EbSkuService;
import com.rl.ecps.utils.ResourcesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ozc on 2017/9/6.
 */
@Controller
@RequestMapping("/cart")
public class EbCartController {


    @Autowired
    private EbCartService cartService;
    @Autowired
    private EbSkuService skuService;


    @RequestMapping("/listCart.do")
    public String listCart(HttpServletRequest request, HttpServletResponse response, Model model) {

        List<EbCart> carts = cartService.listCart(request, response);

        //算出购物车总价和商品数量
        int itemNum = 0;
        BigDecimal totalPrice = new BigDecimal(0);
        for (EbCart cart : carts) {
            totalPrice = totalPrice.add(cart.getSku().getSkuPrice().multiply(new BigDecimal(cart.getQuantity())));

            itemNum++;
        }
        model.addAttribute("carts", carts);
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("itemNum",itemNum);
        return "shop/car";
    }

    @RequestMapping("/addCart.do")
    public void  addCart(Long skuId, Integer quantity, PrintWriter writer, HttpServletRequest request, HttpServletResponse response) {

        cartService.addCart(request, response, skuId, quantity);

        writer.write("addSuccess");
    }

    @RequestMapping("/validateCookie.do")
    public void validateCookie(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {
        String result = cartService.validCookie(request, response);
        writer.write(result);
    }

    @RequestMapping("/updateQuantity.do")
    public String updateQuantity(Long skuId, Integer quantity,HttpServletRequest request, HttpServletResponse response) {
        cartService.updateQuantity(request, response, skuId, quantity);

        return "redirect:/cart/listCart.do";
    }

    @RequestMapping("/validateStock.do")
    public void validateStock(Long skuId, Integer quantity,PrintWriter writer) {
        String result = "noStock";
        EbSku sku = skuService.selectByPrimaryKey(skuId);
        //判断数量多还是库存多
        if (sku.getStockInventory() >= quantity) {
            result = "hasStock";
        }
        writer.write(result);
    }


    @RequestMapping("/validateCar.do")
    public void validateCar(HttpServletRequest request, HttpServletResponse response) {

        String result = cartService.validateCar(request, response);

        if (result != null) {
            ResourcesUtils.printJSON(result, response);
        }


    }

}
