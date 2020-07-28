package com.neo.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author wangz
 * @date 2020/7/1 17:31
 * @desc  普惠优选资产
 */
@Data
@ToString
public class PhyxAssetsVO {

    /**
     * 总资产
     */
    private String uaTotalProperty;
    /**
     * 累计回报
     */
    private String uaProfit;
    /**
     * 昨日回报
     */
    private String uaYesterdayEarn;

}
