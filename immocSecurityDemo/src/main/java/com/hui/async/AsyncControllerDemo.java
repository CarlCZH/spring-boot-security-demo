package com.hui.async;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;


/**
 * @Author: CarlChen
 * @Despriction: 异步调用demo
 * @Date: Create in 23:18 2019\2\7 0007
 */
@Controller
public class AsyncControllerDemo {

    private Logger logger = LoggerFactory.getLogger(AsyncControllerDemo.class);

    /**
     * Callable<T>
     */

//    @GetMapping(value = "/order")
//    @ResponseBody
//    public Callable<String> asyncOrder(){
//        logger.info("主线程 start .....");
//
//        Callable<String> callable = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                logger.info("副线程 start .....");
//                Thread.sleep(1000);
//                logger.info("副线程 end .....");
//                return "success";
//            }
//        };
//
//        logger.info("主线程 end .....");
//
//        return callable;
//    }

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    /**
     * DeferredResult<String>
     */
    @GetMapping(value = "/order")
    @ResponseBody
    public DeferredResult<String> asyncOrder() throws Exception {
        logger.info("主线程 start .....");

        //生成8位随机
        String orderNum = RandomStringUtils.randomAlphanumeric(8);
        mockQueue.setProgressOrder(orderNum);

        DeferredResult<String> deferredResult = new DeferredResult<>();

        deferredResultHolder.getResultMap().put(orderNum, deferredResult);

        logger.info("主线程 end .....");

        return deferredResult;
    }

}
