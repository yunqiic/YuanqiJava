package com.github.zhangchunsheng.spring.starter.yuanqijava.agent.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <pre>
 *  腾讯元器属性配置类
 *  Created by Chunsheng Zhang on 2020/4/20.
 * </pre>
 *
 * @author <a href="https://github.com/zhangchunsheng">Chunsheng Zhang</a>
 */
@Data
@ConfigurationProperties(prefix = "yuanqi")
public class YuanqiAgentProperties {
  /**
   * 腾讯元器key.
   */
  private String key;
}
