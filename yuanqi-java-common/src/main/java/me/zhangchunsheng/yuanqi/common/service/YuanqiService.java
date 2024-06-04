package me.zhangchunsheng.yuanqi.common.service;

import me.zhangchunsheng.yuanqi.common.config.YuanqiConfig;
import me.zhangchunsheng.yuanqi.common.exception.YuanqiException;

import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * <pre>
 * 地理/逆地理编码
 * Created by Chunsheng Zhang on 2020/4/20.
 *
 * https://lbs.yuanqi.com/api/webservice/guide/api/agentreagent/
 *
 * </pre>
 *
 * @author <a href="https://github.com/zhangchunsheng">Chunsheng Zhang</a>
 */
public interface YuanqiService {

    /**
     * 获取腾讯元器请求url前缀
     *
     * @return the yuanqi base url
     */
    String getBaseUrl();

    public YuanqiConfig getConfig();

    public void setConfig(YuanqiConfig config);

    public byte[] postForBytes(String url, String requestStr) throws YuanqiException;

    /**
     *
     * @param url
     * @param requestStr a=1&b=1
     * @return
     * @throws YuanqiException
     */
    public String post(String url, String requestStr) throws YuanqiException;

    public String post(String url, Map<String, Object> params) throws YuanqiException;

    public String postJson(String url, String json) throws YuanqiException;

    public String postJson(String url, String source, String token, String json) throws YuanqiException;

    public String get(String url) throws YuanqiException;

}
