package com.rl.ecps.model;

public class EbParaValue {
    private Long paraId;

    private Long itemId;

    private Long featureId;

    private String paraValue;

    //关联与属性表的关系，由于通过featureId已经能够体现一对一的，我们这里就可以直接使用名称了。
    //当然了，我们也是可以直接使用对象的
    private String featureName;
    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public Long getParaId() {
        return paraId;
    }

    public void setParaId(Long paraId) {
        this.paraId = paraId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
    }

    public String getParaValue() {
        return paraValue;
    }

    public void setParaValue(String paraValue) {
        this.paraValue = paraValue;
    }
}