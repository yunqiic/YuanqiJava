package me.zhangchunsheng.yuanqi.common.constant;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.Format;
import java.util.List;

/**
 * <pre>
 * 腾讯元器常量类
 * Created by Chunsheng Zhang on 2020/4/20.
 * </pre>
 *
 * @author <a href="https://github.com/zhangchunsheng">Chunsheng Zhang</a>
 */
public class YuanqiConstants {

    /**
     * 结果代码.
     */
    public static class ResultStatus {
        /**
         * 此次请求的id
         */
        public static final String id = "1";

        /**
         * unix时间戳
         */
        public static final Integer created = 0;
    }

    public static class Url {
        public static final String chat = "/v1/agent/chat/completions";
    }
}
