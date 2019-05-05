package com.hui.async;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author: CarlChen
 * @Despriction: 模拟监听处理队列里面的数据消息
 * @Date: Create in 23:55 2019\2\7 0007
 */
//将其加入容器
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(QueueListener.class);

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(() ->{
            while (true){
                if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())){
                    String orderNum = mockQueue.getCompleteOrder();
                    logger.info("返回订单处理结果....." + orderNum);
                    DeferredResult<String> deferredResult = deferredResultHolder.getResultMap().get(orderNum);
                    //返回处理结果
                    deferredResult.setResult("the order is success");
                    mockQueue.setCompleteOrder(null);
                }else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}


