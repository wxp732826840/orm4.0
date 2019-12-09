package com.wxp.mybatis;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
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
