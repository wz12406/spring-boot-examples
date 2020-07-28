package com.springcloud.springcloudopenfeign;

import com.springcloud.springcloudopenfeign.feign.HelloFeign;
import com.springcloud.springcloudopenfeign.model.PHCommonRespDTO;
import com.springcloud.springcloudopenfeign.model.PhyxAssetsVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringCloudOpenfeignApplicationTests {

    @Autowired
    public HelloFeign helloFeign;

    @Test
    void contextLoads() {
        String index = helloFeign.index();
        System.out.println(index);
    }


    /**
     * 测试feign返回复杂对象
     */
    @Test
    void contextLoads2() {
        PHCommonRespDTO<PhyxAssetsVO> pHCommonRespDTO = helloFeign.queryYxAsset("1203");
        System.out.println(pHCommonRespDTO);
    }

}
