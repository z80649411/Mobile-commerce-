package com.rl.ecps.model;

public class EbFeature {
    private Long featureId;

    private Long catId;

    private String featureName;

    private Short isSpec;

    private Short isSelect;

    private Short isShow;

    private String selectValues;

    private Short inputType;

    private Integer featureSort;

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public Short getIsSpec() {
        return isSpec;
    }

    public void setIsSpec(Short isSpec) {
        this.isSpec = isSpec;
    }

    public Short getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Short isSelect) {
        this.isSelect = isSelect;
    }

    public Short getIsShow() {
        return isShow;
    }

    public void setIsShow(Short isShow) {
        this.isShow = isShow;
    }

    public String getSelectValues() {
        return selectValues;
    }

    public void setSelectValues(String selectValues) {
        this.selectValues = selectValues;
    }

    public Short getInputType() {
        return inputType;
    }

    public void setInputType(Short inputType) {
        this.inputType = inputType;
    }

    public Integer getFeatureSort() {
        return featureSort;
    }

    public void setFeatureSort(Integer featureSort) {
        this.featureSort = featureSort;
    }
}