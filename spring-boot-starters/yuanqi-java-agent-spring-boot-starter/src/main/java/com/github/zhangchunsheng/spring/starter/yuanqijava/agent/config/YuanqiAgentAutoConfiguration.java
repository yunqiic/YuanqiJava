package com.github.zhangchunsheng.spring.starter.yuanqijava.agent.config;

import me.zhangchunsheng.yuanqi.common.config.YuanqiConfig;
import com.github.zhangchunsheng.yuanqiagent.service.AgentService;
import com.github.zhangchunsheng.yuanqiagent.service.impl.AgentServiceImpl;
import com.github.zhangchunsheng.spring.starter.yuanqijava.agent.properties.YuanqiAgentProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 *  腾讯元器自动配置
 *  Created by Chunsheng Zhang on 2020/4/20.
 * </pre>
 *
 * @author <a href="https://github.com/zhangchunsheng">Chunsheng Zhang</a>
 */
@Configuration
@EnableConfigurationProperties(YuanqiAgentProperties.class)
@ConditionalOnClass(AgentService.class)
@ConditionalOnProperty(prefix = "yuanqi", value = "enabled", matchIfMissing = true)
public class YuanqiAgentAutoConfiguration {
    private YuanqiAgentProperties properties;

    @Autowired
    public YuanqiAgentAutoConfiguration(YuanqiAgentProperties properties) {
        this.properties = properties;
    }

    /**
     * 构造腾讯元器地理服务对象.
     *
     * @return 腾讯元器地理service
     */
    @Bean
    @ConditionalOnMissingBean(AgentService.class)
    public AgentService agentService() {
        final AgentServiceImpl agentService = new AgentServiceImpl();
        YuanqiConfig yuanqiConfig = new YuanqiConfig();
        yuanqiConfig.setKey(StringUtils.trimToNull(this.properties.getKey()));

        agentService.setConfig(yuanqiConfig);
        return agentService;
    }
}
