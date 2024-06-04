package me.zhangchunsheng.yuanqi.common.exception;

import com.google.common.base.Joiner;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 腾讯元器异常结果类
 * Created by Chunsheng Zhang on 2020-4-20.
 * </pre>
 *
 * @author ChunshengZhang
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class YuanqiException extends Exception {
    private static final long serialVersionUID = 2214381471513460742L;

    /**
     * 自定义错误讯息.
     */
    private String customErrorMsg;
    /**
     * 返回状态码.
     */
    private String returnStatus;
    /**
     * 返回信息.
     */
    private String returnInfo;

    /**
     * 业务结果.
     */
    private String returnInfoCode;

    /**
     * Instantiates a new Yuanqi exception.
     *
     * @param customErrorMsg the custom error msg
     */
    public YuanqiException(String customErrorMsg) {
        super(customErrorMsg);
        this.customErrorMsg = customErrorMsg;
    }

    /**
     * Instantiates a new Yuanqi exception.
     *
     * @param customErrorMsg the custom error msg
     * @param tr             the tr
     */
    public YuanqiException(String customErrorMsg, Throwable tr) {
        super(customErrorMsg, tr);
        this.customErrorMsg = customErrorMsg;
    }

    private YuanqiException(Builder builder) {
        super(builder.buildErrorMsg());
        returnStatus = builder.returnStatus;
        returnInfo = builder.returnInfo;
        returnInfoCode = builder.returnInfoCode;
    }

    /**
     * 通过BaseYuanqiResult生成异常对象.
     *
     * @param baseResult the base result
     * @return the yuanqi exception
     */
    public static YuanqiException from(me.zhangchunsheng.yuanqi.common.bean.result.BaseYuanqiResult baseResult) {
        return YuanqiException.newBuilder()
                .returnStatus(baseResult.getId())
                .build();
    }

    /**
     * New builder builder.
     *
     * @return the builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * The type Builder.
     */
    public static final class Builder {
        private String returnStatus;
        private String returnInfo;
        private String returnInfoCode;

        private Builder() {
        }

        /**
         * Return status builder.
         *
         * @param returnStatus the return status
         * @return the builder
         */
        public Builder returnStatus(String returnStatus) {
            this.returnStatus = returnStatus;
            return this;
        }

        /**
         * Return info builder.
         *
         * @param returnInfo the return info
         * @return the builder
         */
        public Builder returnInfo(String returnInfo) {
            this.returnInfo = returnInfo;
            return this;
        }

        /**
         * Result code builder.
         *
         * @param returnInfoCode the result code
         * @return the builder
         */
        public Builder returnInfoCode(String returnInfoCode) {
            this.returnInfoCode = returnInfoCode;
            return this;
        }

        /**
         * Build yuanqi agent exception.
         *
         * @return the yuanqi agent exception
         */
        public YuanqiException build() {
            return new YuanqiException(this);
        }

        /**
         * Build error msg string.
         *
         * @return the string
         */
        public String buildErrorMsg() {
            return Joiner.on("，").skipNulls().join(
                    returnStatus == null ? null : String.format("返回代码：[%s]", returnStatus),
                    returnInfo == null ? null : String.format("返回信息：[%s]", returnInfo),
                    returnInfoCode == null ? null : String.format("结果代码：[%s]", returnInfoCode)
            );
        }
    }
}
