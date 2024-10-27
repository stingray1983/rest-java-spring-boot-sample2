# ビルドステージ
FROM gradle:8.10.2-jdk21-alpine AS builder
COPY --chown=gradle:gradle ./ /home/gradle/
WORKDIR /home/gradle
RUN gradle build --no-daemon 

# 実行ステージ
FROM eclipse-temurin:21-jre-jammy 

# 作業ディレクトリ
WORKDIR /app

# ビルドステージからSpring Bootアプリケーションのjarファイルをコピー
COPY --from=builder /home/gradle/build/libs/example-0.0.1-SNAPSHOT.jar app.jar

# ポートを公開 (Spring Bootのデフォルトポート)
EXPOSE 8080

# アプリケーションの実行コマンド
ENTRYPOINT ["java", "-jar", "app.jar"]
