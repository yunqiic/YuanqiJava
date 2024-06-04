package me.zhangchunsheng.yuanqi.common.service.impl;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import jodd.io.ZipUtil;
import me.zhangchunsheng.yuanqi.common.bean.YuanqiApiData;
import me.zhangchunsheng.yuanqi.common.config.YuanqiConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipException;

/**
 * <pre>
 *  腾讯元器接口请求抽象实现类
 *  Created by Chunsheng Zhang on 2020/4/20.
 * </pre>
 *
 * @author <a href="https://github.com/zhangchunsheng">Chunsheng Zhang</a>
 */
public abstract class BaseServiceImpl implements me.zhangchunsheng.yuanqi.common.service.YuanqiService {
    /**
     * The Log.
     */
    final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * The constant yuanqiApiData.
     */
    static ThreadLocal<YuanqiApiData> yuanqiApiData = new ThreadLocal<>();

    /**
     * The Config.
     */
    protected YuanqiConfig config;

    @Override
    public YuanqiConfig getConfig() {
        return this.config;
    }

    @Override
    public void setConfig(YuanqiConfig config) {
        this.config = config;
    }

    @Override
    public String getBaseUrl() {
        return this.getConfig().getBaseUrl();
    }

    /**
     * 只要确保你的编码输入是正确的,就可以忽略掉 UnsupportedEncodingException
     *
     * @param source 参数
     * @return String
     */
    public static String getUrlParams(Map<String, Object> source) {
        Iterator<String> it = source.keySet().iterator();
        StringBuilder paramStr = new StringBuilder();
        while (it.hasNext()) {
            String key = it.next();
            String value = source.get(key).toString();
            if (value.isEmpty()) {
                continue;
            }
            try {
                // URL 编码
                value = URLEncoder.encode(value, "utf-8");
            } catch (UnsupportedEncodingException e) {
                // do nothing
            }
            paramStr.append("&").append(key).append("=").append(value);
        }
        // 去掉第一个&
        return paramStr.substring(1);
    }
}
