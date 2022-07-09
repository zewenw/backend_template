package com.backend.service.domain.consumer.impl;

import com.backend.service.domain.consumer.DemoConsumer;
import com.backend.service.dto.DemoDto;
import com.backend.service.provider.DemoFacade;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class DemoConsumerImpl implements DemoConsumer {

    @DubboReference
    private DemoFacade demoFacade;
    @Override

    public String sayName() {
        DemoDto demoDto = new DemoDto();
        demoDto.setName("hello world three two one");
        return demoFacade.sayName(demoDto);
    }
}
