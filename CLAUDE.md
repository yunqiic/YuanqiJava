# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

YuanqiJava — a Java SDK for the Tencent Yuanqi (腾讯元器) agent OpenAPI. Group ID `com.github.zhangchunsheng`, version 1.0.2, published to Maven Central via Sonatype OSSRH.

## Build & Test

```bash
mvn clean install          # build all modules
mvn clean package          # build jars without installing
mvn test                   # runs NO tests — surefire is hard-configured <skip>true</skip> in the root pom
```

Tests are skipped by the root pom's surefire configuration (a literal `<skip>true</skip>`, not a property), so they cannot be enabled from the command line with `-DskipTests=false`. Run tests from an IDE, or temporarily remove the skip from `pom.xml`.

Tests are **TestNG** (not JUnit), wired with Guice: each module's test suite has an `ApiTestModule` that reads `test-config.xml` from the test classpath. Copy `src/test/resources/test-config.sample.xml` to `test-config.xml` and fill in a real Yuanqi API key — the tests in `BaseYuanqiAgentServiceImplTest` hit the live API (`https://yuanqi.tencent.com/openapi`), they are not mocked. Do not commit `test-config.xml`.

## Architecture

Three Maven modules (build order matters, install before using downstream):

- **`yuanqi-java-common`** (package `me.zhangchunsheng.yuanqi.common`) — transport layer. `YuanqiService` is the base interface (get/post/postJson/postForBytes); `BaseServiceImpl` holds shared state (`YuanqiConfig`, a `ThreadLocal<YuanqiApiData>` request log); two HTTP implementations exist: `YuanqiServiceApacheHttpImpl` (Apache HttpClient, the default) and `YuanqiServiceJoddHttpImpl` (jodd-http). `YuanqiServiceImpl` is just a subclass of the Apache one. Also contains `YuanqiConfig` (baseUrl, timeouts, API key, HTTP proxy settings), `YuanqiConstants` (endpoint paths), `YuanqiException`, `BaseYuanqiResult` (Gson deserialization, `isSuccess()` = non-empty `id`), and `YuanqiGsonBuilder`.
- **`yuanqi-java-agent`** (package `com.github.zhangchunsheng.yuanqiagent`) — the agent chat API. `AgentService` / `AgentServiceImpl` expose `chat(ChatParams) -> ChatRet`, which POSTs JSON to `baseUrl + /v1/agent/chat/completions` with `X-Source: openapi` and `Authorization: Bearer <key>` headers. Request/response beans live under `bean/request` and `bean/result`.
- **`spring-boot-starters/yuanqi-java-agent-spring-boot-starter`** — Spring Boot 2.1 auto-configuration registered via `META-INF/spring.factories`. `YuanqiAgentAutoConfiguration` creates an `AgentService` bean from `yuanqi.*` properties (`yuanqi.key` in application.yml; `yuanqi.enabled=false` disables it).

Note the package split: common uses `me.zhangchunsheng.*` while agent/starter use `com.github.zhangchunsheng.*` — both are intentional, match the module you're editing.

## Conventions & Constraints

- **Java 7 source/target** (`maven.compiler.source=1.7`). Do not use lambdas, streams, `var`, or other Java 8+ language features. Dependency versions are pinned low for this reason (jodd-http 3.7.1, gson 2.8.0); the pom comments call this out — don't upgrade them casually.
- **Lombok** is used heavily (`@Data`, `@Slf4j`) — annotation processing must be enabled in the IDE.
- JSON serialization is Gson via `YuanqiGsonBuilder`; beans' `toString()` returns JSON and is used to build request bodies (e.g. `params.toString()` in `AgentServiceImpl.chat`).
- Checkstyle (google_checks.xml) is configured but skipped (`<skip>true</skip>`).
- Releasing uses the `release` profile (source/javadoc jars + GPG signing) and nexus-staging to Sonatype; versions across all modules are kept in sync via `autoVersionSubmodules`.
- Some javadoc/comments are copy-pasted from an earlier location-service project (e.g. "地理/逆地理编码", "电费请求实现类") and don't match what the code does — treat code, not comments, as truth.
