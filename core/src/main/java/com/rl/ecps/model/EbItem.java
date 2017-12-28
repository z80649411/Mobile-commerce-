package com.rl.ecps.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EbItem  implements Serializable {
    private Long itemId;

    private String itemName;

    private String itemNo;

    private Long brandId;

    private Long catId;

    private Long tagImgId;

    private Short tagImg;

    private Short isNew;

    private Short isGood;

    private Short isHot;

    private String promotion;

    private Short auditStatus;

    private Short showStatus;

    private String imgs;

    private String keywords;

    private String pageDesc;

    private Short itemRecycle;

    private Date onSaleTime;

    private Date checkTime;

    private Date updateTime;

    private Long updateUserId;

    private Date createTime;

    private Long checkerUserId;

    private String fullPathDeploy;

    private String fullPathDeployOffer;

    private Long originalItemId;

    private Short lastStatus;

    private Long merchantId;

    private Long itemSort;

    private Long sales;

    private Long createUserId;

    private Short simLevel;

    private String giftDesc;

    private String giftImg;

    private String giftShowType;

    private String imgSize1;

    //对应大字段关系
    private EbItemClob clob;

    //对应最小销售单元关系
    private List<EbSku> ebSkus;

    //参数值数据
    private List<EbParaValue> paraList;

    public EbItemClob getClob() {
        return clob;
    }

    public void setClob(EbItemClob clob) {
        this.clob = clob;
    }

    public List<EbSku> getEbSkus() {
        return ebSkus;
    }

    public void setEbSkus(List<EbSku> ebSkus) {
        this.ebSkus = ebSkus;
    }

    public List<EbParaValue> getParaList() {
        return paraList;
    }

    public void setParaList(List<EbParaValue> paraList) {
        this.paraList = paraList;
    }

    private BigDecimal skuPrice;

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Long getTagImgId() {
        return tagImgId;
    }

    public void setTagImgId(Long tagImgId) {
        this.tagImgId = tagImgId;
    }

    public Short getTagImg() {
        return tagImg;
    }

    public void setTagImg(Short tagImg) {
        this.tagImg = tagImg;
    }

    public Short getIsNew() {
        return isNew;
    }

    public void setIsNew(Short isNew) {
        this.isNew = isNew;
    }

    public Short getIsGood() {
        return isGood;
    }

    public void setIsGood(Short isGood) {
        this.isGood = isGood;
    }

    public Short getIsHot() {
        return isHot;
    }

    public void setIsHot(Short isHot) {
        this.isHot = isHot;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public Short getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Short auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Short getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Short showStatus) {
        this.showStatus = showStatus;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPageDesc() {
        return pageDesc;
    }

    public void setPageDesc(String pageDesc) {
        this.pageDesc = pageDesc;
    }

    public Short getItemRecycle() {
        return itemRecycle;
    }

    public void setItemRecycle(Short itemRecycle) {
        this.itemRecycle = itemRecycle;
    }

    public Date getOnSaleTime() {
        return onSaleTime;
    }

    public void setOnSaleTime(Date onSaleTime) {
        this.onSaleTime = onSaleTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCheckerUserId() {
        return checkerUserId;
    }

    public void setCheckerUserId(Long checkerUserId) {
        this.checkerUserId = checkerUserId;
    }

    public String getFullPathDeploy() {
        return fullPathDeploy;
    }

    public void setFullPathDeploy(String fullPathDeploy) {
        this.fullPathDeploy = fullPathDeploy;
    }

    public String getFullPathDeployOffer() {
        return fullPathDeployOffer;
    }

    public void setFullPathDeployOffer(String fullPathDeployOffer) {
        this.fullPathDeployOffer = fullPathDeployOffer;
    }

    public Long getOriginalItemId() {
        return originalItemId;
    }

    public void setOriginalItemId(Long originalItemId) {
        this.originalItemId = originalItemId;
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

    public Long getItemSort() {
        return itemSort;
    }

    public void setItemSort(Long itemSort) {
        this.itemSort = itemSort;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Short getSimLevel() {
        return simLevel;
    }

    public void setSimLevel(Short simLevel) {
        this.simLevel = simLevel;
    }

    public String getGiftDesc() {
        return giftDesc;
    }

    public void setGiftDesc(String giftDesc) {
        this.giftDesc = giftDesc;
    }

    public String getGiftImg() {
        return giftImg;
    }

    public void setGiftImg(String giftImg) {
        this.giftImg = giftImg;
    }

    public String getGiftShowType() {
        return giftShowType;
    }

    public void setGiftShowType(String giftShowType) {
        this.giftShowType = giftShowType;
    }

    public String getImgSize1() {
        return imgSize1;
    }

    public void setImgSize1(String imgSize1) {
        this.imgSize1 = imgSize1;
    }
}