package me.zhangchunsheng.yuanqi.common.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 腾讯元器接口请求数据封装对象
 * Created by Chunsheng Zhang on 2020/4/20.
 * </pre>
 *
 * @author <a href="https://github.com/zhangchunsheng">Chunsheng Zhang</a>
 */
@Data
@NoArgsConstructor
public class YuanqiApiData {
    /**
     * 接口请求地址
     */
    private String url;

    /**
     * 请求数据
     */
    private String requestData;

    /**
     * 响应数据
     */
    private String responseData;

    /**
     * 接口请求异常信息
     */
    private String exceptionMsg;

    /**
     * Instantiates a new Yuanqi agent api data.
     *
     * @param url          接口请求地址
     * @param requestData  请求数据
     * @param responseData 响应数据
     * @param exceptionMsg 接口请求异常信息
     */
    public YuanqiApiData(String url, String requestData, String responseData, String exceptionMsg) {
        this.url = url;
        this.requestData = requestData;
        this.responseData = responseData;
        this.exceptionMsg = exceptionMsg;
    }

    @Override
    public String toString() {
        if (this.exceptionMsg != null) {
            return String.format("\n【请求地址】：%s\n【请求数据】：%s\n【异常信息】：%s",
                    this.url, this.requestData, this.exceptionMsg);
        }

        return String.format("\n【请求地址】：%s\n【请求数据】：%s\n【响应数据】：%s",
                this.url, this.requestData, this.responseData);
    }
}
