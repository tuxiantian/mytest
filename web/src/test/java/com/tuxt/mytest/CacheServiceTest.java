package com.tuxt.mytest;

import com.tuxt.mytest.service.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CacheServiceTest extends MytestApplicationTests{
    @Autowired
    CacheService cacheService;

    @Test
    public void test() {
        for (int i = 0; i < 11; i++) {
            try {
                cacheService.translate(String.valueOf(i),123L);

                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
