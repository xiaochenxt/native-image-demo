@echo off
:: 启用延迟扩展，用于在循环中实时获取变量值
setlocal enabledelayedexpansion

:: 使用这种方式收集，可以避免开发工具的代理污染，如IDEA的idea_rt.jar
:: 设置编码为UTF-8
chcp 65001 >nul 2>&1

:: 先打包编译
call mvnd clean compile package

:: 检查 mvn 命令是否执行成功（0 表示成功）
if %errorlevel% equ 0 (
    :: 查找target目录下最大的jar文件（按大小降序，取第一个）
    set "max_jar="
    for /f "delims=" %%f in ('dir /b /s /o-s "target\*.jar" 2^>nul') do (
        set "max_jar=%%f"
        goto :found_jar  :: 找到第一个（最大的jar文件）即退出循环
    )

    :found_jar
    if defined max_jar (
        echo 开始启动应用收集native-image所需的配置信息...
        java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar "!max_jar!"
    ) else (
        echo 错误：在target目录下未找到JAR文件
        exit /b 1
    )
) else (
    echo Maven 构建失败，终止执行
    exit /b 1
)

:: 关闭延迟扩展
endlocal
