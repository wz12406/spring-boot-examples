package com.springcloud.springcloudopenfeign.controller;

import com.springcloud.springcloudopenfeign.feign.HelloFeign;
import com.springcloud.springcloudopenfeign.model.PHCommonRespDTO;
import com.springcloud.springcloudopenfeign.model.PhyxAssetsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangz
 * @date 2020/7/1 20:33
 * @desc
 */
@RestController
public class HelloClientController {

    @Autowired
    private  HelloFeign helloFeign;
    @RequestMapping("/")
    public String index() {
        return "Hello Spring Boot 2.0!";
    }

    /**
     * 测试feign解析复杂对象
     */
    @PostMapping("/asset/newMyAssetStatistics")
    @ResponseBody
    public PHCommonRespDTO<PhyxAssetsVO> queryYxAsset(@RequestParam("memberId") String memberId){
        return helloFeign.queryYxAsset("1203");
    }

}
