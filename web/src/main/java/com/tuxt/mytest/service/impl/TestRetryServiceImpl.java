package com.tuxt.mytest.service.impl;

import com.tuxt.mytest.service.TestRetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@Slf4j
public class TestRetryServiceImpl implements TestRetryService {

    @Override
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public int test(int code) throws Exception {
        log.info("test被调用,时间：" + LocalTime.now());
        if (code == 0) {
            throw new Exception("情况不对头！");
        }
        log.info("test被调用,情况对头了！");

        return 200;
    }

    @Recover
    public int recover(Exception e, int code) {
        log.info("回调方法执行！！！！");
        //记日志到数据库 或者调用其余的方法
        return 400;
    }
}