#!/bin/bash

echo "=============================================="
echo "步骤1/2：执行AOT处理..."
echo "=============================================="
mvn clean compile spring-boot:process-aot -DskipTests

# 检查第一步是否成功
if [ $? -ne 0 ]; then
    echo "错误：AOT处理失败，终止执行"
    exit 1
fi

echo
echo "=============================================="
echo "步骤2/2：基于AOT结果构建原生镜像..."
echo "=============================================="
mvn native:compile-no-fork -Pnative

# 检查第二步是否成功
if [ $? -ne 0 ]; then
    echo "错误：原生镜像构建失败"
    exit 1
fi

echo
echo "原生镜像构建完成！生成路径：target/"
