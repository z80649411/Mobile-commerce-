package com.rl.ecps.dao;

import com.rl.ecps.model.EbBrand;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */
public interface EbBrandDao {

    void saveBrand(EbBrand brand);

    EbBrand getBrandById(Long brandId);

    void updateBrand(EbBrand brand);

    void deleteBrand(Long brandId);

    List<EbBrand> selectBrand();

    List<EbBrand> selectBrandByName(String brandName);
}
