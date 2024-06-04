package com.github.zhangchunsheng.yuanqiagent.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zhangchunsheng.yuanqi.common.util.json.YuanqiGsonBuilder;

import java.io.Serializable;
import java.util.List;

@Data
public class Message implements Serializable {
    private static final long serialVersionUID = -1070939403109776555L;

    protected String role;
    protected List<Content> content;

    @Override
    public String toString() {
        return YuanqiGsonBuilder.create().toJson(this);
    }
}
