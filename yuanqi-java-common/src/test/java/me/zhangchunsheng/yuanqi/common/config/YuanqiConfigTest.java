package me.zhangchunsheng.yuanqi.common.config;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * <pre>
 *  Created by Chunsheng Zhang on 2020/4/20.
 * </pre>
 *
 * @author <a href="https://github.com/zhangchunsheng">Chunsheng Zhang</a>
 */
public class YuanqiConfigTest {
    private YuanqiConfig agentConfig = new YuanqiConfig();

    @Test
    public void test1() throws Exception {
        agentConfig.setKey("1");
    }
}
