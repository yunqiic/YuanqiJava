package com.github.zhangchunsheng.yuanqiagent.service;

import com.github.zhangchunsheng.yuanqiagent.bean.request.ChatParams;
import com.github.zhangchunsheng.yuanqiagent.bean.result.ChatRet;
import me.zhangchunsheng.yuanqi.common.exception.YuanqiException;
import me.zhangchunsheng.yuanqi.common.service.YuanqiService;

/**
 * <pre>
 * 搜索API
 * Created by Chunsheng Zhang on 2020/4/20.
 *
 * https://lbs.yuanqi.com/api/webservice/guide/api/search/
 *
 * </pre>
 *
 * @author <a href="https://github.com/zhangchunsheng">Chunsheng Zhang</a>
 */
public interface AgentService extends YuanqiService {

    public ChatRet chat(ChatParams params) throws YuanqiException;

}
