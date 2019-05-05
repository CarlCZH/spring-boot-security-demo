package com.hui.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @Author: CarlChen
 * @Despriction: WireMock学习  其主要是生成Restful接口,
 * @Date: Create in 0:26 2019\2\8 0008
 */
public class WireMockDemo {

    public static void main(String[] args) throws IOException {

        //绑定端口 (本地的话只需要绑定端口，无需配置ip地址)
        WireMock.configureFor(8082);
        //移除所有配置
        WireMock.removeAllMappings();

        mock("/order/1", "01");
        mock("/order/2", "02");

    }

    private static void mock(String url, String fileName) throws IOException {
        //资源路径
        ClassPathResource classPathResource = new ClassPathResource("mock/response/" + fileName + ".txt");
        //获取资源路径下文件里的内容
        String context = StringUtils.join(FileUtils.readLines(classPathResource.getFile(), "UTF-8").toArray(), '\n');
        //用WireMock生成Restful接口(传入返回内容与响应吗等参数)
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url)).willReturn(WireMock.aResponse().withBody(context).withStatus(200)));

    }
}
