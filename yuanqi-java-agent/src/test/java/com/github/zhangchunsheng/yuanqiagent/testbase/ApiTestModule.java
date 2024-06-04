package com.github.zhangchunsheng.yuanqiagent.testbase;

import com.github.zhangchunsheng.yuanqiagent.service.AgentService;
import com.github.zhangchunsheng.yuanqiagent.service.impl.AgentServiceImpl;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.thoughtworks.xstream.XStream;
import me.zhangchunsheng.yuanqi.common.util.xml.XStreamInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type Api test module.
 */
public class ApiTestModule implements Module {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String TEST_CONFIG_XML = "test-config.xml";

    @Override
    public void configure(Binder binder) {
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(TEST_CONFIG_XML)) {
            if (inputStream == null) {
                throw new RuntimeException("测试配置文件【" + TEST_CONFIG_XML + "】未找到，请参照test-config-sample.xml文件生成");
            }

            XmlYuanqiConfig config = this.fromXml(XmlYuanqiConfig.class, inputStream);
            AgentService yuanqiService = new AgentServiceImpl();
            yuanqiService.setConfig(config);

            binder.bind(com.github.zhangchunsheng.yuanqiagent.service.AgentService.class).toInstance(yuanqiService);
            binder.bind(me.zhangchunsheng.yuanqi.common.config.YuanqiConfig.class).toInstance(config);
        } catch (IOException e) {
            this.log.error(e.getMessage(), e);
        }

    }

    @SuppressWarnings("unchecked")
    private <T> T fromXml(Class<T> clazz, InputStream is) {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.alias("xml", clazz);
        xstream.processAnnotations(clazz);
        return (T) xstream.fromXML(is);
    }

}
