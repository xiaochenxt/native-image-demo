@echo off
:: 分两步构建Spring Boot原生镜像：1.AOT处理 2.构建原生镜像
:: 设置编码为UTF-8
chcp 65001 >nul 2>&1

echo ==============================================
echo 步骤1/2：执行AOT处理...
echo ==============================================
call mvn clean compile spring-boot:process-aot -DskipTests

:: 检查第一步是否成功
if %errorlevel% neq 0 (
    echo 错误：AOT处理失败，终止执行
    exit /b 1
)

echo.
echo ==============================================
echo 步骤2/2：基于AOT结果构建原生镜像...
echo ==============================================
call mvn native:compile-no-fork -Pnative

:: 检查第二步是否成功
if %errorlevel% neq 0 (
    echo 错误：原生镜像构建失败
    exit /b 1
)

echo.
echo 原生镜像构建完成！生成路径：target/
