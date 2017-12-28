package com.rl.ecps.service.impl;

import com.rl.ecps.dao.*;

import com.rl.ecps.model.*;
import com.rl.ecps.service.EbItemService;
import com.rl.ecps.stu.EbWSItemService;
import com.rl.ecps.stu.EbWSItemServiceService;
import com.rl.ecps.stu.ObjectFactory;
import com.rl.ecps.stu.PublishItem;
import com.rl.ecps.utils.Page;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ozc on 2017/9/6.
 */

@Service
public class EbItemServiceImpl implements EbItemService {

    @Autowired
    private EbItemDao itemDao;

    @Autowired
    private EbItemClobDao clobDao;

    @Autowired
    private EbParaValueDao paraValueDao;

    @Autowired
    private EbSkuDao skuDao;
    @Autowired
    private EbConsoleLogDao logDao;


    public Page selectItemByCondition(QueryCondition queryCondition) {

        //查询当前条件下的总页数
        int count = itemDao.selectItemByConditionCount(queryCondition);

        //根据总页数和当前页数【qc从前端拿到的】，计算得出其他分页属性的数据
        Page page = new Page();
        page.setTotalCount(count);
        page.setPageNo(queryCondition.getPageNo());
        int startNum = page.getStartNum();
        int endNum = page.getEndNum();

        //将计算出来的开始页数和结束页数封装到qc中，获取数据库中的数据
        queryCondition.setStartNum(startNum);
        queryCondition.setEndNum(endNum);
        List<EbItem> ebItems = itemDao.selectItemByCondition(queryCondition);

        //设置到page对象中
        page.setList(ebItems);

        return page;

    }

    public void saveItem(EbItem ebItem, EbItemClob clob, List<EbSku> skus, List<EbParaValue> ebParaValues) {
        itemDao.saveItem(ebItem);

        clobDao.saveItemClob(clob, ebItem.getItemId());

        skuDao.saveEbSku(skus, ebItem.getItemId());

        paraValueDao.saveParaValue(ebParaValues, ebItem.getItemId());
    }

    public void updateItem(Long itemId, Short auditStatus, String notes) {

        //设置item的属性
        EbItem ebItem = new EbItem();
        ebItem.setItemId(itemId);
        ebItem.setAuditStatus(auditStatus);
        ebItem.setCheckerUserId((long) 222);
        ebItem.setCheckTime(new Date());

        //更新item
        itemDao.updateItem(ebItem);

        //操作日志
        EbConsoleLog ebConsoleLog = new EbConsoleLog();
        ebConsoleLog.setEntityId(itemId);
        ebConsoleLog.setEntityName("商品表");
        ebConsoleLog.setNotes(notes);
        ebConsoleLog.setOpTime(new Date());
        if (auditStatus == 1) {
            ebConsoleLog.setOpType("审核通过");
        } else {
            ebConsoleLog.setOpType("审核不通过");
        }
        ebConsoleLog.setTableName("EB_ITEM");
        ebConsoleLog.setUserId(1l);
        logDao.saveConsoleLog(ebConsoleLog);

    }

    public void showItem(Long itemId, Short showStatus) {

        //设置item的属性
        EbItem ebItem = new EbItem();
        ebItem.setItemId(itemId);
        ebItem.setShowStatus(showStatus);
        ebItem.setCheckerUserId((long) 222);
        ebItem.setCheckTime(new Date());

        //更新item
        itemDao.updateItem(ebItem);

        //操作日志
        EbConsoleLog ebConsoleLog = new EbConsoleLog();
        ebConsoleLog.setEntityId(itemId);
        ebConsoleLog.setEntityName("商品表");
        ebConsoleLog.setOpTime(new Date());
        if (showStatus == 1) {
            ebConsoleLog.setOpType("下架");
        } else {
            ebConsoleLog.setOpType("上架");
        }
        ebConsoleLog.setTableName("EB_ITEM");
        ebConsoleLog.setUserId(1l);
        logDao.saveConsoleLog(ebConsoleLog);
    }

    public List<EbItem> listItem(Long brandId, String price, String param) {

        Map<String, Object> map = new HashedMap();
        map.put("brandId", brandId);

        //将价钱进行分割成两部分
        if (StringUtils.isNotBlank(price)) {
            String[] strings = price.split("-");
            map.put("minPrice", strings[0]);
            map.put("maxPrice", strings[1]);
        }

        //分割并装载到map中
        List<String> list = new ArrayList<String>();
        if (StringUtils.isNotBlank(param)) {
            String[] paras = param.split(",");
            for (String para : paras) {
                list.add(para);
            }
            map.put("paraList", list);
        }
        return itemDao.listItem(map);
    }
    public EbItem selectItemDetail(Long itemId) {
        return itemDao.selectItemDetail(itemId);
    }

    public String publishItem(Long itemId, String password) throws Exception {

        EbWSItemServiceService itemServiceService = new EbWSItemServiceService();
        EbWSItemService ebWSItemServicePort = itemServiceService.getEbWSItemServicePort();
        return ebWSItemServicePort.publishItem(itemId, password);
    }


}
