# Nami

这是 Nami 为 Minecraft 1.21.11 制作的最后公开版本  
不会再收到任何更新

### 加入我们的 Discord - https://discord.gg/auHTtNAqRq

<p>
  <a href="https://github.com/NamiDevelopment/nami/releases">
    <img src="https://img.shields.io/github/downloads/NamiDevelopment/nami/total?color=green&label=Total%20Downloads" alt="Total Downloads" />
  </a>
  <a href="https://github.com/NamiDevelopment/nami/commits">
  <img src="https://img.shields.io/github/commit-activity/m/NamiDevelopment/nami?label=Commits%20(last%20month)&color=yellow" alt="month" />
  </a>
  <a href="https://github.com/NamiDevelopment/nami/releases">
    <img src="https://img.shields.io/github/v/release/Kiriyaga7615/nami?color=blue&label=Latest%20Release" alt="Latest Release" />
  </a>
  <a href="https://discord.gg/auHTtNAqRq">
    <img src="https://img.shields.io/discord/1298742596633497744?color=7289DB&label=Discord" alt="Discord" />
  </a>
</p>

![# badge](assets/readme/no-stops-no-regrets.svg)
![# badge](assets/readme/ensuring-code-integrity.svg)
![# badge](assets/readme/works-on-selfmerging.svg)


**Nami** 是一个模块化、轻量级的无政府客户端基础，专为 PVE 和自动化而构建。

大多数流行的 Minecraft 客户端都是闭源、付费且经过混淆的，这使得它们难以审计或信任。有些可能包含后门或恶意代码。

本项目作为一个干净的开源替代方案而诞生，旨在提供透明、安全且易于扩展的体验，无需依赖不安全的第三方客户端。

---

## 截图

<details>
<summary>查看截图</summary>

<img width="1920" height="1080" alt="ClickGUI" src="assets/clickgui.png" />
<img width="1920" height="1080" alt="HudEditor" src="assets/hudeditor.png" />
<img width="1920" height="1080" alt="Friends" src="assets/friends.png" />
<img width="1920" height="1080" alt="Friends" src="assets/configs.png" />

</details>

---

## 插件开发

请参阅 https://github.com/NamiDevelopment/template-plugin 获取相关信息

---

## 常见问题

<details>
<summary>如何打开 ClickGUI？</summary>

默认快捷键是：P

</details>

<details>
<summary>命令前缀是什么？</summary>

默认命令前缀是 `-`。

</details>

---

## 环境要求

- Java 21  
- Gradle 8+  
- Minecraft 1.21.11 
- Fabric loader、API

---

## 如何构建

1. 克隆仓库：

    ```bash
    git clone https://github.com/NamiDevelopment/Nami.git  
    cd nami
    ```
2. 为了获取 nami-api 依赖，你需要在根目录的 .gradle/gradle.dependency 中配置你的 PAT 令牌

3. 使用 Gradle 构建：

    ```bash
    ./gradlew build
    ```

编译后的 JAR 文件位于：  
`build/libs/nami-<version>.jar`

nami-client 已将 nami-api 打包在其中。

---

## 许可证

本项目采用 MIT 许可证授权。你可以自由地贡献、分发、复刻或复用其中的任何部分。

---

## 特别感谢

- [cattyngmd](https://github.com/cattyngmd)

- [CatFormat](https://github.com/cattyngmd/CatFormat)
