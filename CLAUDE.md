# CLAUDE.md

本文件为 Claude Code (claude.ai/code) 在本仓库中工作时提供指导。

## 项目简介

YuanqiJava —— 腾讯元器（Tencent Yuanqi）智能体 OpenAPI 的 Java SDK。Group ID 为 `com.github.zhangchunsheng`，版本 1.0.2，通过 Sonatype OSSRH 发布到 Maven 中央仓库。

## 构建与测试

```bash
mvn clean install          # 构建所有模块
mvn clean package          # 只打包，不安装到本地仓库
mvn test                   # 不会运行任何测试 —— 根 pom 中 surefire 被硬编码为 <skip>true</skip>
```

根 pom 的 surefire 配置中写死了字面量 `<skip>true</skip>`（不是属性占位符），因此无法通过命令行 `-DskipTests=false` 开启测试。请在 IDE 中运行测试，或临时修改 `pom.xml` 去掉该配置。

测试使用 **TestNG**（不是 JUnit），通过 Guice 装配：每个模块的测试都依赖 `ApiTestModule`，它从测试 classpath 读取 `test-config.xml`。请复制 `src/test/resources/test-config.sample.xml` 为 `test-config.xml` 并填入真实的元器 API key —— `BaseYuanqiAgentServiceImplTest` 中的测试会真实请求线上接口（`https://yuanqi.tencent.com/openapi`），并非 mock。切勿提交 `test-config.xml`。

## 架构

三个 Maven 模块（构建顺序有依赖关系，下游使用前请先 install）：

- **`yuanqi-java-common`**（包名 `me.zhangchunsheng.yuanqi.common`）—— 传输层。`YuanqiService` 是基础接口（get/post/postJson/postForBytes）；`BaseServiceImpl` 持有共享状态（`YuanqiConfig`、用于记录请求日志的 `ThreadLocal<YuanqiApiData>`）；HTTP 实现有两种：`YuanqiServiceApacheHttpImpl`（Apache HttpClient，默认实现）和 `YuanqiServiceJoddHttpImpl`（jodd-http）。`YuanqiServiceImpl` 只是 Apache 实现的子类。本模块还包含 `YuanqiConfig`（baseUrl、超时、API key、HTTP 代理设置）、`YuanqiConstants`（接口路径常量）、`YuanqiException`、`BaseYuanqiResult`（Gson 反序列化，`isSuccess()` 即 `id` 非空）和 `YuanqiGsonBuilder`。
- **`yuanqi-java-agent`**（包名 `com.github.zhangchunsheng.yuanqiagent`）—— 智能体对话 API。`AgentService` / `AgentServiceImpl` 提供 `chat(ChatParams) -> ChatRet`，向 `baseUrl + /v1/agent/chat/completions` 发送 JSON POST 请求，请求头带 `X-Source: openapi` 和 `Authorization: Bearer <key>`。请求/响应 bean 位于 `bean/request` 和 `bean/result` 包下。
- **`spring-boot-starters/yuanqi-java-agent-spring-boot-starter`** —— Spring Boot 2.1 自动配置，通过 `META-INF/spring.factories` 注册。`YuanqiAgentAutoConfiguration` 根据 `yuanqi.*` 配置项创建 `AgentService` Bean（在 application.yml 中配置 `yuanqi.key`；`yuanqi.enabled=false` 可关闭）。

注意包名差异：common 模块用 `me.zhangchunsheng.*`，agent/starter 模块用 `com.github.zhangchunsheng.*` —— 两者都是有意为之，改动哪个模块就沿用哪个包名。

## 约定与限制

- **Java 8 编译级别**（`maven.compiler.source=1.8`，2026-09 从 1.7 升级，因为新版 JDK 已不再支持 source/target 7）。依赖版本仍锁定在较低版本（jodd-http 3.7.1、gson 2.8.0），pom 注释中已有说明 —— 不要随意升级这些依赖。Lombok 已升级到 1.18.34 以兼容新版 JDK，不要再降回 1.18.8。测试用 Guice 已升级到 5.1.0（3.0 内置的 cglib 在 JDK 16+ 上会因 JPMS 反射限制而崩溃）。
- 大量使用 **Lombok**（`@Data`、`@Slf4j`）—— IDE 需开启注解处理。
- JSON 序列化统一走 Gson 的 `YuanqiGsonBuilder`；bean 的 `toString()` 返回 JSON，并被直接用于构造请求体（如 `AgentServiceImpl.chat` 中的 `params.toString()`）。
- Checkstyle（google_checks.xml）已配置但被跳过（`<skip>true</skip>`）。
- 发布使用 `release` profile（source/javadoc jar + GPG 签名），经 nexus-staging 推送到 Sonatype；各模块版本通过 `autoVersionSubmodules` 保持一致。
- 部分 javadoc/注释是从早期的位置服务项目复制过来的（如“地理/逆地理编码”、“电费请求实现类”），与代码实际功能不符 —— 请以代码为准，不要轻信注释。
