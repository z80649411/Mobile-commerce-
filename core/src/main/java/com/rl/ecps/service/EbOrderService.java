package com.rl.ecps.service;

import com.rl.ecps.model.*;
import com.rl.ecps.utils.Page;
import org.springframework.core.annotation.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */
public interface EbOrderService {

    /**
     * 因为在订单提交完毕后，我们是需要把购物车的数据清空的。
     * 将购物车的数据清空实际上就是清空cookie的数据！
     * 因此需要request和response对象！
     * @param ebOrder
     * @param details
     * @param request
     * @param response
     */
    void addOrder(EbOrder ebOrder, List<EbOrderDetail> details, HttpServletRequest request , HttpServletResponse response);

}
