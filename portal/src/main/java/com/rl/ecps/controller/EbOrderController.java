package com.rl.ecps.controller;

import com.rl.ecps.model.*;
import com.rl.ecps.service.*;
import com.rl.ecps.service.impl.EbCartServiceImpl;
import com.rl.ecps.service.impl.EbOrderServiceImpl;
import com.rl.ecps.service.impl.EbShipAddrServiceImpl;
import com.rl.ecps.utils.ResourcesUtils;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ozc on 2017/9/6.
 */
@Controller
@RequestMapping("/order")
public class EbOrderController {


    @Autowired
    private EbShipAddrService addrService;
    @Autowired
    private EbCartService cartService;
    @Autowired
    private EbOrderService orderService;


    @RequestMapping("/toSubmitOrder.do")
    public String toSubmitOrder(HttpSession session, Model model, HttpServletRequest request,HttpServletResponse response) {

        //查询用户的地址
        TsPtlUser user = (TsPtlUser) session.getAttribute("user");
        List<EbShipAddr> userAddress = addrService.findUserAddress(user.getPtlUserId());

        //查询购物车清单的信息
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
        model.addAttribute("userAddress", userAddress);
        return "shop/confirmProductCase";

    }


    /**
     * 获取页面的参数、保存订单！
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/SubmitOrder.do")
    public String SubmitOrder(HttpSession session, Model model, HttpServletRequest request,HttpServletResponse response,EbOrder order,String address) throws InvocationTargetException, IllegalAccessException {

        //设置订单的信息
        TsPtlUser user = (TsPtlUser) session.getAttribute("user");
        if(user != null){
            order.setPtlUserId(user.getPtlUserId());
            order.setUsername(user.getUsername());
        }
        order.setOrderNum(new SimpleDateFormat().format(new Date()));

        //收集地址分为两种：一种是新增，一种是使用原有的。
        //如果使用原有的，那么带过来的是id，我们可以直接获取对应的数据，封装到order对象中。
        if(!StringUtils.equals("add", address)){
            EbShipAddr addr = addrService.selectByPrimaryKey(new Long(address));
            BeanUtils.copyProperties(order, addr);
        }

        //订单明细和购物车清单的数据是一样的。
        List<EbOrderDetail> detailList = new ArrayList<EbOrderDetail>();

        List<EbCart> cartList = cartService.listCart(request, response);

        //遍历购物车的清单，将数据加入到订单明细中
        for(EbCart cart:cartList){
            EbOrderDetail detail = new EbOrderDetail();
            detail.setItemId(cart.getSku().getItem().getItemId());
            detail.setItemName(cart.getSku().getItem().getItemName());
            detail.setItemNo(cart.getSku().getItem().getItemNo());
            detail.setSkuId(cart.getSkuId());
            String specVal = "";
            List<EbSpecValue> specList = cart.getSku().getSpecList();
            for(EbSpecValue spec : specList){
                specVal = specVal + spec.getSpecValue()+",";
            }
            specVal = specVal.substring(0, specVal.length() - 1);
            detail.setSkuSpec(specVal);
            detail.setQuantity(cart.getQuantity());
            detail.setSkuPrice(cart.getSku().getSkuPrice());
            detail.setMarketPrice(cart.getSku().getMarketPrice());
            detailList.add(detail);
        }
        orderService.addOrder(order, detailList, request, response);
        model.addAttribute("order", order);

        return "shop/confirmProductCase2";
    }

}
