package me.zhangchunsheng.yuanqi.common.config;

import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URL;
import java.security.KeyStore;

/**
 * 腾讯元器配置
 *
 * @author Chunsheng Zhang (https://github.com/zhangchunsheng)
 */
@Data
public class YuanqiConfig {
    private static final String DEFAULT_BASE_URL = "https://yuanqi.tencent.com/openapi";

    /**
     * 腾讯元器接口请求地址域名部分.
     */
    private String baseUrl = DEFAULT_BASE_URL;

    /**
     * http请求连接超时时间.
     */
    private int httpConnectionTimeout = 5000;

    /**
     * http请求数据读取等待时间.
     */
    private int httpTimeout = 10000;

    /**
     * 腾讯元器key.
     */
    private String key;

    /**
     * 是否将接口请求日志信息保存到threadLocal中.
     * 默认不保存
     */
    private boolean ifSaveApiData = false;

    private String httpProxyHost;
    private Integer httpProxyPort;
    private String httpProxyUsername;
    private String httpProxyPassword;

    /**
     * 返回所设置的腾讯元器接口请求地址域名.
     *
     * @return 腾讯元器接口请求地址域名
     */
    public String getBaseUrl() {
        if (StringUtils.isEmpty(this.baseUrl)) {
            return DEFAULT_BASE_URL;
        }

        return this.baseUrl;
    }
}
