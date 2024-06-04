package me.zhangchunsheng.yuanqi.common.bean.result;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import me.zhangchunsheng.yuanqi.common.util.json.YuanqiGsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import org.w3c.dom.*;

/**
 * <pre>
 * 腾讯元器结果共用属性类.
 * Created by Chunsheng Zhang on 2020/4/20.
 * </pre>
 *
 * @author <a href="https://github.com/zhangchunsheng">Chunsheng Zhang</a>
 */
@Data
public abstract class BaseYuanqiResult implements Serializable {
    private static final long serialVersionUID = 2101652152604850904L;
    protected String id;
    protected Integer created;

    /**
     * 请求是否成功.
     */
    public boolean isSuccess() {
        return !StringUtils.isEmpty(id);
    }

    public static BaseYuanqiResult fromJson(String json) {
        return YuanqiGsonBuilder.create().fromJson(json, BaseYuanqiResult.class);
    }

    @Override
    public String toString() {
        return YuanqiGsonBuilder.create().toJson(this);
    }
}
