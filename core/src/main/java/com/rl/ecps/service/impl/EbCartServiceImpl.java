package com.rl.ecps.service.impl;

import com.rl.ecps.model.EbCart;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.EbSpecValue;
import com.rl.ecps.service.EbCartService;
import com.rl.ecps.service.EbSkuService;
import com.rl.ecps.utils.ResourcesUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */

@Service
public class EbCartServiceImpl implements EbCartService {


    @Autowired
    private EbSkuService skuService;

    public String validCookie(HttpServletRequest request, HttpServletResponse response) {

        //默认被禁用
        String result = "ban";

        //只要是本主机下的，Cookie都会被发布出去
        Cookie cookie = new Cookie("test", "test");
        cookie.setPath("/");
        response.addCookie(cookie);

        //查看是否能获取得到Cookie
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie1 : cookies) {
                String name = cookie1.getName();
                String value = cookie1.getValue();
                /**
                 * 如果都拿到，那么Cookie就没有被禁用！
                 */
                if (name.equals("test") && value.equals("test")) {

                    result = "noBan";
                    return result;
                }
            }
        }
        return result;
    }

    public void addCart(HttpServletRequest request, HttpServletResponse response, Long skuId, Integer quantity) {

        List<EbCart> cartList = null;

        //json的配置对象
        JsonConfig jc = new JsonConfig();
        //设置要转换的类
        jc.setRootClass(EbCart.class);
        //设置不需要转换的属性
        jc.setExcludes(new String[]{"sku"});

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                String name = c.getName();

                //读取购物车的key，在配置文件中保存起来了。
                String cart_key = ResourcesUtils.readProp("cart_key");
                if (StringUtils.equalsIgnoreCase(name, cart_key)) {
                    //得到cookie里边的值【JSON格式的数组】
                    String value = c.getValue();

                    //将JSON数组转成是Java对象
                    JSONArray ja = JSONArray.fromObject(value);
                    cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);

                    //判断要添加的商品在购物车是否存在
                    String result = "noExist";
                    for (EbCart cart : cartList) {
                        //购物车中已存在
                        if (cart.getSkuId().longValue() == skuId.longValue()) {
                            cart.setQuantity(cart.getQuantity() + quantity);
                            result = "exist";
                        }
                    }
                    //购物车中的商品不存在
                    if (StringUtils.equalsIgnoreCase("noExist", result)) {
                        EbCart ebCart = new EbCart();
                        ebCart.setQuantity(quantity);
                        ebCart.setSkuId(skuId);
                        cartList.add(ebCart);
                    }
                }
            }
        }
        //上边已经判断了购物车存在，商品是否存在的两种情况了。以下是判断购物车是否存在的问题
        //如果上边的cookie为null，转换不了List集合的话，那么我们的List集合是为空的。List集合为空的话，那么我们的购物车是不存在的。
        if (cartList == null || cartList.size() == 0) {
            cartList = new ArrayList<EbCart>();
            EbCart ebCart = new EbCart();
            ebCart.setQuantity(quantity);
            ebCart.setSkuId(skuId);
            cartList.add(ebCart);
        }

        //最后将我们的Java对象重新转成JSON，将Cookie更新
        JSONArray ja = JSONArray.fromObject(cartList, jc);
        String result = ja.toString();
        Cookie cookie = new Cookie("cart_key", result);
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");
        response.addCookie(cookie);

    }


    public List<EbCart> listCart(HttpServletRequest request, HttpServletResponse response) {

        List<EbCart> cartList = null;

        //json的配置对象
        JsonConfig jc = new JsonConfig();
        //设置要转换的类
        jc.setRootClass(EbCart.class);
        //设置不需要转换的属性
        jc.setExcludes(new String[]{"sku"});

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                String name = c.getName();

                //读取购物车的key，在配置文件中保存起来了。
                String cart_key = ResourcesUtils.readProp("cart_key");
                if (StringUtils.equalsIgnoreCase(name, cart_key)) {
                    //得到cookie里边的值【JSON格式的数组】
                    String value = c.getValue();

                    //将JSON数组转成是Java对象
                    JSONArray ja = JSONArray.fromObject(value);
                    cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);

                    //根据购物车中商品的id获取详细信息
                    for (EbCart cart : cartList) {
                        Long skuId = cart.getSkuId();
                        EbSku sku = skuService.selectByPrimaryKeyForDetail(skuId);
                        cart.setSku(sku);
                    }
                }
            }

        }
        return cartList;

    }

    public void updateQuantity(HttpServletRequest request, HttpServletResponse response, Long skuId, Integer quantity) {

        List<EbCart> cartList = null;

        //json的配置对象
        JsonConfig jc = new JsonConfig();
        //设置要转换的类
        jc.setRootClass(EbCart.class);
        //设置不需要转换的属性
        jc.setExcludes(new String[]{"sku"});

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                String name = c.getName();

                //读取购物车的key，在配置文件中保存起来了。
                String cart_key = ResourcesUtils.readProp("cart_key");
                if (StringUtils.equalsIgnoreCase(name, cart_key)) {
                    //得到cookie里边的值【JSON格式的数组】
                    String value = c.getValue();

                    //将JSON数组转成是Java对象
                    JSONArray ja = JSONArray.fromObject(value);
                    cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);

                    //根据购物车中商品的id获取详细信息
                    for (EbCart cart : cartList) {
                        Long cartSkuId = cart.getSkuId();

                        //找到对应购物车中的商品，这里要使用longValue对数值进行比较。直接双等于是没有用的。
                        if (skuId.longValue() == cartSkuId.longValue()) {
                            cart.setQuantity(quantity);
                        }
                    }
                }
            }
        }
        //最后将我们的Java对象重新转成JSON，将Cookie更新
        JSONArray ja = JSONArray.fromObject(cartList, jc);
        String result = ja.toString();
        Cookie cookie = new Cookie("cart_key", result);
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");
        response.addCookie(cookie);


    }

    public String validateCar(HttpServletRequest request, HttpServletResponse response) {

        List<EbCart> cartList = null;


        StringBuilder builder = null;
        //json的配置对象
        JsonConfig jc = new JsonConfig();
        //设置要转换的类
        jc.setRootClass(EbCart.class);
        //设置不需要转换的属性
        jc.setExcludes(new String[]{"sku"});

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                String name = c.getName();

                //读取购物车的key，在配置文件中保存起来了。
                String cart_key = ResourcesUtils.readProp("cart_key");
                if (StringUtils.equalsIgnoreCase(name, cart_key)) {
                    //得到cookie里边的值【JSON格式的数组】
                    String value = c.getValue();

                    //将JSON数组转成是Java对象
                    JSONArray ja = JSONArray.fromObject(value);
                    cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);

                    //遍历购物车中的数据与库存做比较
                    for (EbCart cart : cartList) {

                        int cartQuantity = cart.getQuantity().intValue();

                        //根据购物车中商品的id获取详细信息
                        Long skuId = cart.getSkuId();
                        EbSku sku = skuService.selectByPrimaryKeyForDetail(skuId);

                        if (cartQuantity > sku.getStockInventory().intValue()) {

                            builder = new StringBuilder();

                            //这个商品的库存不足
                            String itemName = sku.getItem().getItemName();

                            builder.append(itemName);
                            List<EbSpecValue> specList = sku.getSpecList();
                            for (EbSpecValue ebSpecValue : specList) {
                                builder.append(ebSpecValue.getSpecValue());

                            }
                            builder.append("库存不足");
                            return builder.toString();
                        }
                    }
                }
            }
        }

        return "success";
    }

    public void clearCar(HttpServletRequest request, HttpServletResponse response) {

        List<EbCart> cartList = null;

        //json的配置对象
        JsonConfig jc = new JsonConfig();
        //设置要转换的类
        jc.setRootClass(EbCart.class);
        //设置不需要转换的属性
        jc.setExcludes(new String[]{"sku"});


        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                String name = c.getName();

                //读取购物车的key，在配置文件中保存起来了。
                String cart_key = ResourcesUtils.readProp("cart_key");
                if (StringUtils.equalsIgnoreCase(name, cart_key)) {
                    //得到cookie里边的值【JSON格式的数组】
                    String value = c.getValue();

                    //将JSON数组转成是Java对象
                    JSONArray ja = JSONArray.fromObject(value);
                    cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);

                    //清空购物车
                    cartList.clear();

                }
            }
        }

        //最后将我们的Java对象重新转成JSON，将Cookie更新
        JSONArray ja = JSONArray.fromObject(cartList, jc);
        String result = ja.toString();
        Cookie cookie = new Cookie("cart_key", result);
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");
        response.addCookie(cookie);


    }
}
