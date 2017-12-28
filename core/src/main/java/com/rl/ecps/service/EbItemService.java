package com.rl.ecps.service;

import com.rl.ecps.model.*;
import com.rl.ecps.utils.Page;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */
public interface EbItemService {
    /**
     * 根据条件查询商品
     *
     * @param queryCondition
     * @return
     */
    Page selectItemByCondition(QueryCondition queryCondition);


    /**
     * 添加商品
     *
     * @param ebItem
     * @param clob
     * @param skus
     * @param ebParaValues
     */
    void saveItem(EbItem ebItem, EbItemClob clob, List<EbSku> skus, List<EbParaValue> ebParaValues);


    /**
     * 修改商品的审核状态
     *
     * @param itemId
     * @param auditStatus
     * @param notes
     */
    void updateItem(Long itemId, Short auditStatus, String notes);

    /**
     * 修改商品上下价
     *
     * @param itemId
     * @param showStatus
     */
    void showItem(Long itemId, Short showStatus);

    /**
     * 根据价钱、品牌、多个参数查询商品
     *
     * @param brandId
     * @param price
     * @param param
     * @return
     */
    List<EbItem> listItem(Long brandId, String price, String param);

    EbItem selectItemDetail(Long itemId);


    /**
     * 发布静态页面
     * @param itemId
     * @param password
     * @return
     */
    public String publishItem(Long itemId, String password) throws Exception;

}
