package com.github.zhangchunsheng.yuanqiagent.service.impl;

import com.github.zhangchunsheng.yuanqiagent.bean.request.ChatParams;
import com.github.zhangchunsheng.yuanqiagent.bean.result.ChatRet;
import com.github.zhangchunsheng.yuanqiagent.service.AgentService;
import me.zhangchunsheng.yuanqi.common.constant.YuanqiConstants;
import me.zhangchunsheng.yuanqi.common.exception.YuanqiException;
import me.zhangchunsheng.yuanqi.common.service.impl.YuanqiServiceApacheHttpImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <pre>
 * 腾讯元器接口请求实现类，默认使用Apache HttpClient实现
 * Created by Chunsheng Zhang on 2020/4/20.
 * </pre>
 *
 * @author <a href="https://github.com/zhangchunsheng">Chunsheng Zhang</a>
 */
public class AgentServiceImpl extends YuanqiServiceApacheHttpImpl implements AgentService {
    @Override
    public ChatRet chat(ChatParams params) throws YuanqiException {
        String url = this.getConfig().getBaseUrl() + YuanqiConstants.Url.chat;
        String source = "openapi";
        String responseContent = this.postJson(url, source, this.getConfig().getKey(), params.toString());
        return ChatRet.fromJson(responseContent);
    }
}
