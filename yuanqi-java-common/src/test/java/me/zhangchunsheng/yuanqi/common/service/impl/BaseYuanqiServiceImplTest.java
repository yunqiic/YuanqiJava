package me.zhangchunsheng.yuanqi.common.service.impl;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.zhangchunsheng.yuanqi.common.exception.YuanqiException;
import me.zhangchunsheng.yuanqi.common.testbase.ApiTestModule;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

/**
 * 测试GEO相关接口
 * Created by Chunsheng Zhang on 2020/4/20.
 *
 * @author <a href="https://github.com/zhangchunsheng">Chunsheng Zhang</a>
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class BaseYuanqiServiceImplTest {

    @Inject
    private me.zhangchunsheng.yuanqi.common.service.YuanqiService yuanqiService;

    @Test
    public void testAgent() throws YuanqiException {
        String key = this.yuanqiService.getConfig().getKey();
        assertNotNull(key);
    }
}
