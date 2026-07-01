#!/bin/bash

# 执行Maven打包编译
echo "开始执行Maven构建..."
mvn clean compile package

# 检查Maven命令是否执行成功（0表示成功）
if [ $? -eq 0 ]; then
    # 查找target目录下最大的jar文件（按大小降序，取第一个）
    # echo "查找最新构建的JAR文件..."
    # 使用find查找所有jar文件，然后按大小排序，取最大的一个
    max_jar=$(find target -name "*.jar" -type f -print0 | xargs -0 ls -S | head -n 1)

    if [ -n "$max_jar" ]; then
        # echo "找到JAR文件: $max_jar"
        echo "开始启动应用收集native-image所需的配置信息..."
        java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar "$max_jar"
    else
        echo "错误：在target目录下未找到JAR文件"
        exit 1
    fi
else
    echo "Maven 构建失败，终止执行"
    exit 1
fi
