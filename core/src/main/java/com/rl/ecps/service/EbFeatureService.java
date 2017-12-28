package com.rl.ecps.service;

import com.rl.ecps.model.EbFeature;
import com.rl.ecps.model.QueryCondition;
import com.rl.ecps.utils.Page;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */
public interface EbFeatureService {
    /**
     * 查询出普通属性
     */
    List<EbFeature> selectCommFeature();

    /**
     * 查询出特殊属性
     * @return
     */
    List<EbFeature> selectSpecFeature();


    /**
     * 查询出是否被选择【在前台页面做筛选条件】
     * @return
     */
    List<EbFeature> selectIsSelect();




}
