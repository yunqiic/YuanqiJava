package com.github.zhangchunsheng.yuanqiagent.bean.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zhangchunsheng.yuanqi.common.util.json.YuanqiGsonBuilder;

@Data
public class Content {
    private static final long serialVersionUID = -1070939403109776551L;

    protected String type;
    protected String text;
    @SerializedName("file_url")
    protected FileUrl fileUrl;

    @Override
    public String toString() {
        return YuanqiGsonBuilder.create().toJson(this);
    }
}
