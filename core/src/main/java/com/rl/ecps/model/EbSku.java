package com.rl.ecps.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EbSku {
    private Long skuId;

    private Long itemId;

    private String sku;

    private BigDecimal skuPrice;

    private Short showStatus;

    private Integer stockInventory;

    private Integer skuUpperLimit;

    private String location;

    private String skuImg;

    private Integer skuSort;

    private String skuName;

    private BigDecimal marketPrice;

    private Date createTime;

    private Date updateTime;

    private Long createUserId;

    private Long updateUserId;

    private Long originalSkuId;

    private Short lastStatus;

    private Long merchantId;

    private Short skuType;

    private Long sales;

    private String resCode;

    private Integer packId;

    private List<EbSpecValue> specList;

    //与Item进行关联
    private EbItem item;
    public EbItem getItem() {
        return item;
    }
    public void setItem(EbItem item) {
        this.item = item;
    }

    public List<EbSpecValue> getSpecList() {
        return specList;
    }

    public void setSpecList(List<EbSpecValue> specList) {
        this.specList = specList;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public Short getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Short showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getStockInventory() {
        return stockInventory;
    }

    public void setStockInventory(Integer stockInventory) {
        this.stockInventory = stockInventory;
    }

    public Integer getSkuUpperLimit() {
        return skuUpperLimit;
    }

    public void setSkuUpperLimit(Integer skuUpperLimit) {
        this.skuUpperLimit = skuUpperLimit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSkuImg() {
        return skuImg;
    }

    public void setSkuImg(String skuImg) {
        this.skuImg = skuImg;
    }

    public Integer getSkuSort() {
        return skuSort;
    }

    public void setSkuSort(Integer skuSort) {
        this.skuSort = skuSort;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Long getOriginalSkuId() {
        return originalSkuId;
    }

    public void setOriginalSkuId(Long originalSkuId) {
        this.originalSkuId = originalSkuId;
    }

    public Short getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(Short lastStatus) {
        this.lastStatus = lastStatus;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Short getSkuType() {
        return skuType;
    }

    public void setSkuType(Short skuType) {
        this.skuType = skuType;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public Integer getPackId() {
        return packId;
    }

    public void setPackId(Integer packId) {
        this.packId = packId;
    }
}