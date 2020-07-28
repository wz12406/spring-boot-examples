package com.springcloud.springcloudopenfeign.feign;

import com.springcloud.springcloudopenfeign.model.PHCommonRespDTO;
import com.springcloud.springcloudopenfeign.model.PhyxAssetsVO;
import org.springframework.stereotype.Component;

/**
 * @author wangz
 * @date 2020/7/1 23:04
 * @desc
 */
@Component
public class HelloFeignFallBack implements HelloFeign {


    @Override
    public String index() {
        return null;
    }

    @Override
    public PHCommonRespDTO<PhyxAssetsVO> queryYxAsset(String memberId) {
        PHCommonRespDTO phCommonRespDTO = new PHCommonRespDTO("99999","网络开小差 ");
        return phCommonRespDTO;
    }
}
