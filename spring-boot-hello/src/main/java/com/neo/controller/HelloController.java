package com.neo.controller;

import com.neo.model.PHCommonRespDTO;
import com.neo.model.PhyxAssetsVO;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
	
    @RequestMapping("/")
    public String index() {
        return "Hello Spring Boot 2.0!";
    }

    /**
     * 测试feign解析复杂对象
     */
    @PostMapping("/asset/newMyAssetStatistics")
    @ResponseBody
    public PHCommonRespDTO<PhyxAssetsVO> queryYxAsset(@RequestParam("memberId") String memberId) throws InterruptedException {
        PhyxAssetsVO phyxAssetsVO = new PhyxAssetsVO();
        phyxAssetsVO.setUaTotalProperty("123029");
        phyxAssetsVO.setUaProfit("1293");
        phyxAssetsVO.setUaYesterdayEarn("12332");
        PHCommonRespDTO phCommonRespDTO = new PHCommonRespDTO("000000","成功",phyxAssetsVO);
        Thread.sleep(3000);
        return phCommonRespDTO;
    }
}