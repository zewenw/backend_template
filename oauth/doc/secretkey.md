### 生成命令
keytool -genkey -alias scaffold -keyalg RSA -keypass 123456 -keystore scaffold.jks -storepass 123456

---

### 打包需要排除jks文件：

    <build>
        <finalName>${project.artifactId}</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
                <excludes>
                    <exclude>**/*.jks</exclude>
                </excludes>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>false</filtering>
                <includes>
                    <include>**/*.jks</include>
                </includes>
            </resource>
        </resources>
    </build>

---

### oauth密码模式请求
localhost:8080/oauth/token?client_id=cms&client_secret=secret&username=admin&password=123456&grant_type=password&scope=all