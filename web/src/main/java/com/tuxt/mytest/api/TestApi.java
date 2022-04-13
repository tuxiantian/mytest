package com.tuxt.mytest.api;

import com.tuxt.mytest.service.TestRetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestApi {
    @Autowired
    private TestRetryService testRetryService;

    /**
     * 测试 http://localhost/api/test/retry?code=0
     * @param code
     * @return
     * @throws Exception
     */
    @GetMapping("/retry")
    public String retry(Integer code) throws Exception {
        int value = testRetryService.test(code);
        return String.valueOf(value);
    }
}