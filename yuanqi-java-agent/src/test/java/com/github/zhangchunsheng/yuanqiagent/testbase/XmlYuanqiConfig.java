package com.github.zhangchunsheng.yuanqiagent.testbase;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * The type Xml yuanqi config.
 */
@XStreamAlias("xml")
public class XmlYuanqiConfig extends me.zhangchunsheng.yuanqi.common.config.YuanqiConfig {
    private String key;

    /**
     * Gets openid.
     *
     * @return the openid
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets openid.
     *
     * @param openid the openid
     */
    public void setKey(String openid) {
        this.key = key;
    }

}
