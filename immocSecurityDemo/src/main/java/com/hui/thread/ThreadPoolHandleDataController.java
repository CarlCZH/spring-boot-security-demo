package com.hui.thread;

import com.hui.bean.UserInfo;
import com.hui.dao.UserInfoMapper;
import com.hui.dao.UserMsgMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Author: CarlChen
 * @Despriction: 多线程处理数据
 * @Date: Create in 22:42 2019\3\11 0011
 */
@RestController
public class ThreadPoolHandleDataController {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolHandleDataController.class);

    @Qualifier("threadPoolExecutor")
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserMsgMapper userMsgMapper;

    private volatile int numId = 3;

    @GetMapping("/thredPoolTest")
    public void handleData(){
        long l = System.currentTimeMillis();
        System.out.println(l);
        while (true) {
            threadPoolTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    if (numId > 4998) {
                        System.out.println((System.currentTimeMillis() - l) / 1000);
                        return;
                    }
                    numAdd();
                    UserInfo userInfo = userInfoMapper.findById(numId);
                    userMsgMapper.insertData(userInfo.getUsername());
                    logger.info(Thread.currentThread().getName());
                }
            });
        }
    }

    private synchronized void numAdd(){
        numId += 1;
    }

}
