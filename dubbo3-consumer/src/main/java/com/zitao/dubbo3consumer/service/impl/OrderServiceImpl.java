package com.zitao.dubbo3consumer.service.impl;

import com.zitao.dubbo3consumer.service.OrderService;
import com.zitao.dubbocommon.service.UserService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
//    @Autowired
//    private RestTemplate restTemplate;
    // 注意当服务提供者声明了version时，不论其是否真的有多个实现类，在消费者这边引用的时候都要带上version
    @DubboReference(group ="userServiceImpl2", version = "2.0.0")
    private UserService userService;

    @Override
    public String getOrder(Integer userId, Integer orderId) {
//        String user = restTemplate.getForObject("http:localhost:8002/getUser", String.class); 此行代码出错，因为请求方式不对，缺少了参数
//        Map<String,Integer> params = new HashMap<>();
//        params.put("userId",userId);

        // 使用restTemplate来调用其他微服务的接口时，如果是get请求，在请求路径中要像下面一样，请求参数也要加入其中
//        String user = restTemplate.getForObject("http://localhost:8002/getUser?userId={userId}", String.class, params);
        String user = userService.getUser(userId);  // UNARY 普通调用方式
        String order = "orderId:" + orderId.toString();  // 这里就是使用dubbo进行远程调用其他服务提供的方法

        // 使用SERVER_STREAM，服务端流式调用方式
        userService.sayHelloServerStream(userId.toString(), new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                // 进行业务操作
                System.out.println("服务端流式调用，接收到信息：" + data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("调用结束");
            }
        });

        // 使用客户端流CLIENT_STREAM
        StreamObserver<String> stringStreamObserver = userService.sayHelloStream(new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println("消费者接收到："+data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });
        stringStreamObserver.onNext("发送data001");
        stringStreamObserver.onNext("发送data002");
        stringStreamObserver.onNext("发送data003");
        stringStreamObserver.onCompleted();
        return order + ", UNARY普通方式， " + user;
    }
}
