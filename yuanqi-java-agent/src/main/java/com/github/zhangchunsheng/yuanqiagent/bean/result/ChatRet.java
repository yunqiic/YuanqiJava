package com.github.zhangchunsheng.yuanqiagent.bean.result;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zhangchunsheng.yuanqi.common.bean.result.BaseYuanqiResult;
import me.zhangchunsheng.yuanqi.common.util.json.YuanqiGsonBuilder;
import com.google.gson.annotations.SerializedName;

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
public class ChatRet extends BaseYuanqiResult {
    private static final long serialVersionUID = -7083914585539687746L;

    protected List<Object> choices;
    @SerializedName("assistant_id")
    protected String assistantId;
    protected Object usage;

    public static ChatRet fromJson(String json) {
        return YuanqiGsonBuilder.create().fromJson(json, ChatRet.class);
    }

    @Override
    public String toString() {
        return YuanqiGsonBuilder.create().toJson(this);
    }

}
