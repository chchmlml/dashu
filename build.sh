#!/bin/bash
set -x

SOURCE_ROOT=$(cd "$(dirname "$0")";pwd)
MAVEN_FLAGS="-U -Dmaven.test.skip -am"
MAVEN_GOALS="clean package"
MAVEN_POM_FILE="${SOURCE_ROOT}/pom.xml"
TARGET_NAME="target"
BIN_NAME="bin"
TARGET_JAR=""
TARGET_PATH="${SOURCE_ROOT}/${TARGET_NAME}"
TARGET_BIN_PATH="${SOURCE_ROOT}/${TARGET_NAME}/${BIN_NAME}"
TARGET_VERSION="*"

TARGET_JAR="${TARGET_BIN_PATH}/app.jar"
BUILD_PATH="${SOURCE_ROOT}"
BUILD_TARGET_PATH="${BUILD_PATH}/${TARGET_NAME}"
BUILD_TARGET_JAR="${BUILD_TARGET_PATH}/${TARGET_VERSION}.jar"


echo ">>> Start build ..."

mvn -f ${MAVEN_POM_FILE} ${MAVEN_FLAGS}  ${MAVEN_GOALS}

echo ">>> Build completed."

if [[ ! -d ${TARGET_BIN_PATH} ]]; then
    echo ">>> Start create target directory..."
    echo ">>> mkdir -p ${TARGET_BIN_PATH}"
    mkdir -p ${TARGET_BIN_PATH}
fi

echo ">>> Move target files..."
echo ">>> cp ${BUILD_TARGET_JAR} ${TARGET_JAR}"
cp ${BUILD_TARGET_JAR} ${TARGET_JAR}

docker build -t chchmlml/stock-monitor-collection .
#docker push chchmlml/stock-monitor-collection:latest