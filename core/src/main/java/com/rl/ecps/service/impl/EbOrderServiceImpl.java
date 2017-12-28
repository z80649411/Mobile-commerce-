package com.rl.ecps.service.impl;

import com.rl.ecps.dao.EbOrderDao;
import com.rl.ecps.dao.EbOrderDetailDao;
import com.rl.ecps.dao.EbSkuDao;
import com.rl.ecps.dao.impl.EbOrderDaoImpl;
import com.rl.ecps.dao.impl.EbOrderDetailDaoImpl;
import com.rl.ecps.dao.impl.EbSkuDaoImpl;
import com.rl.ecps.exception.EbStockException;
import com.rl.ecps.model.*;
import com.rl.ecps.service.EbCartService;
import com.rl.ecps.service.EbOrderService;
import com.rl.ecps.service.EbSkuService;
import com.rl.ecps.utils.ResourcesUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ozc on 2017/8/26.
 */

@Service
public class EbOrderServiceImpl implements EbOrderService {


    @Autowired
    private EbOrderDetailDao detailDao;
    @Autowired
    private EbOrderDao orderDao;
    @Autowired
    private EbSkuDao skuDao;
    @Autowired
    private EbCartService cartService;


    public void addOrder(EbOrder ebOrder, List<EbOrderDetail> details, HttpServletRequest request, HttpServletResponse response) {

        orderDao.saveOrder(ebOrder);

        Map<String,Object> map = new HashMap<String,Object>();
        for(EbOrderDetail detail : details){
            detail.setOrderId(ebOrder.getOrderId());
            detailDao.saveOrderDetail(detail);

			/*EbSku sku = skuDao.getSkuById(detail.getSkuId());

			sku.setStockInventory(sku.getStockInventory() - detail.getQuantity());
			skuDao.update(sku);*/
            map.put("skuId", detail.getSkuId());
            map.put("quantity", detail.getQuantity());
            int flag = skuDao.updateStock(map);
            if(flag == 0){
                throw new EbStockException("库存不足");
            }

        }
        cartService.clearCar(request, response);
    }
}