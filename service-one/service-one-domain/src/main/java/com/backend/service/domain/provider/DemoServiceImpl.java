package com.backend.service.domain.provider;

import cn.hutool.json.JSONUtil;
import com.backend.service.dto.DemoDto;
import com.backend.service.provider.DemoFacade;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class DemoServiceImpl implements DemoFacade {
    @Override
    public String sayName(DemoDto dto) {
        return JSONUtil.toJsonStr(dto);
    }
}
