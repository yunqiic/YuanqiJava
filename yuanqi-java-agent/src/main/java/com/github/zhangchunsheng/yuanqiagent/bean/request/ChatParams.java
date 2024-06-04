package com.github.zhangchunsheng.yuanqiagent.bean.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zhangchunsheng.yuanqi.common.bean.result.BaseYuanqiResult;
import me.zhangchunsheng.yuanqi.common.util.json.YuanqiGsonBuilder;

import java.util.List;

/**
 * <pre>
 * 转换短链接结果对象类
 * Created by Chunsheng Zhang on 2020/4/20.
 * </pre>
 *
 * @author <a href="https://github.com/zhangchunsheng">Chunsheng Zhang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChatParams extends BaseYuanqiResult {
    private static final long serialVersionUID = -7083914585539687741L;

    @SerializedName("assistant_id")
    protected String assistantId;
    @SerializedName("user_id")
    protected Object userId;
    protected List<Message> messages;

    public static ChatParams fromJson(String json) {
        return YuanqiGsonBuilder.create().fromJson(json, ChatParams.class);
    }

    @Override
    public String toString() {
        return YuanqiGsonBuilder.create().toJson(this);
    }

}
