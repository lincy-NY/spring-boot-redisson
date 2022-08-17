package xyz.lincy.springbootredission.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/muti")
public class MutiController {

    public static int i = 1;

    @Resource
    private RedissonClient redissonClient;

    @RequestMapping("/test")
    public String test(){



        //分布式公平锁
        RLock rLock=redissonClient.getFairLock("wkxt:sjcrm:mipush");
        try{
            if(rLock.tryLock(60,10, TimeUnit.SECONDS)){
                //log.error("进入到checkAndSendMsg,当前时间为:" + new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
                System.out.println(i);
                return ""+i++;
            }
        }catch (Exception e){

        } finally {
            rLock.unlock();
        }
        return "err";
    }
}
