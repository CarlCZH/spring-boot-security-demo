package com.hui.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: CarlChen
 * @Despriction: 模拟将消息放置消息队列里
 * @Date: Create in 23:43 2019\2\7 0007
 */
@Component
public class DeferredResultHolder {

    private Map<String, DeferredResult<String>> resultMap = new HashMap<>();

    public Map<String, DeferredResult<String>> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, DeferredResult<String>> resultMap) {
        this.resultMap = resultMap;
    }
}
