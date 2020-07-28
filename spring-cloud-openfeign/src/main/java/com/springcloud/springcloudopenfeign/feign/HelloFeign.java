package com.springcloud.springcloudopenfeign.feign;

import com.springcloud.springcloudopenfeign.model.PHCommonRespDTO;
import com.springcloud.springcloudopenfeign.model.PhyxAssetsVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangz
 * @date 2020/6/17 13:55
 * @desc
 */
@FeignClient(name = "hello",url = "http://localhost:8080/", fallback = HelloFeignFallBack.class)
public interface HelloFeign {

    @RequestMapping("/")
     public String index();

    @PostMapping("/asset/newMyAssetStatistics")
    public PHCommonRespDTO<PhyxAssetsVO> queryYxAsset(@RequestParam("memberId") String memberId);
}
