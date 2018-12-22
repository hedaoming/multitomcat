package com.mmall.task;

import com.mmall.config.RedisOperator;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@CommonsLog
@Slf4j
public class Task {

    @Scheduled(cron="0 */1 * * * ?")
    public void testLock(){
        if (RedisOperator.lock("Lock", 500, String.valueOf(System.currentTimeMillis() + 500 * 1000)).intValue() > 0){
            log.info("get lock!");
        }
    }
}
