package com.hui.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: CarlChen
 * @Despriction: 模拟消息的实体类
 * @Date: Create in 23:34 2019\2\7 0007
 */
@Component
public class MockQueue {

    private Logger logger = LoggerFactory.getLogger(MockQueue.class);

    private String progressOrder;

    private String completeOrder;

    public String getProgressOrder() {
        return progressOrder;
    }

    public void setProgressOrder(String progressOrder) throws Exception {

        new Thread(() ->{
            logger.info("订单开始创建......" + progressOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("fial to sleep");
            }
            this.completeOrder = progressOrder;
            logger.info("订单创建完成......" + progressOrder);
        }).start();

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
