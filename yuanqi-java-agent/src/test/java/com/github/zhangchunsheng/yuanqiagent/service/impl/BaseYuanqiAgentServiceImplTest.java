package com.github.zhangchunsheng.yuanqiagent.service.impl;

import com.github.zhangchunsheng.yuanqiagent.bean.request.ChatParams;
import com.github.zhangchunsheng.yuanqiagent.bean.request.Content;
import com.github.zhangchunsheng.yuanqiagent.bean.request.Message;
import com.github.zhangchunsheng.yuanqiagent.bean.result.ChatRet;
import com.github.zhangchunsheng.yuanqiagent.testbase.ApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.zhangchunsheng.yuanqi.common.exception.YuanqiException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试GEO相关接口
 * Created by Chunsheng Zhang on 2020/4/20.
 *
 * @author <a href="https://github.com/zhangchunsheng">Chunsheng Zhang</a>
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class BaseYuanqiAgentServiceImplTest {

    @Inject
    private com.github.zhangchunsheng.yuanqiagent.service.AgentService placeService;

    @Test
    public void testChat() throws YuanqiException {
        ChatParams params = new ChatParams();
        params.setAssistantId("NT61p8m6teGx");
        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        // user assistant
        message.setRole("user");
        Content content = new Content();
        content.setType("text");
        content.setText("hello");
        List<Content> contents = new ArrayList<>();
        contents.add(content);
        message.setContent(contents);
        messages.add(message);
        params.setMessages(messages);
        ChatRet result = this.placeService.chat(params);
        System.out.println(result);
    }
}
