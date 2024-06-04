package com.github.zhangchunsheng.yuanqiagent.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zhangchunsheng.yuanqi.common.util.json.YuanqiGsonBuilder;

import java.io.Serializable;
import java.util.List;

@Data
public class FileUrl implements Serializable {
    private static final long serialVersionUID = -1070939403109771555L;

    protected String type;
    protected String url;

    @Override
    public String toString() {
        return YuanqiGsonBuilder.create().toJson(this);
    }
}
