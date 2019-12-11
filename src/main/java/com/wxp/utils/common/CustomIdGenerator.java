package com.wxp.utils.common;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.wxp.utils.tools.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomIdGenerator  implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        return  SnowflakeIdWorker.generateId();
    }
}
