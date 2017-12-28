package com.rl.ecps.service;

import com.rl.ecps.model.EbCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */
public interface EbCartService {

    /**
     * 校验Cookie是否被禁用了。
     * <p>
     * 其实就是新建一个Cookie，然后是否能获取得到Cookie的值。
     * 如果可以得到，那么就是Cookie没有被禁用
     * 如果没有得到，那么Cookie就被禁用了。
     *
     * @param request
     * @param response
     * @return
     */
    String validCookie(HttpServletRequest request, HttpServletResponse response);


    /**
     * 添加购物车，使用Cookie保存起来！
     * <p>
     * 使用到Cookie的话，那么就需要用到request和response对象了。
     *
     * @param request
     * @param response
     */
    void addCart(HttpServletRequest request, HttpServletResponse response, Long skuId, Integer quantity);


    /**
     * 查看购物车
     *
     * @param request
     * @param response
     */
    List<EbCart> listCart(HttpServletRequest request, HttpServletResponse response);


    /**
     * 修改商品的数量
     *
     * @param request
     * @param response
     * @return
     */
    void updateQuantity(HttpServletRequest request, HttpServletResponse response, Long skuId, Integer quantity);

    String validateCar(HttpServletRequest request, HttpServletResponse response);

    void clearCar(HttpServletRequest request, HttpServletResponse response);


}
