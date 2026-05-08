# What's This? (这是啥？)

一个 Minecraft Forge 1.20.1 客户端模组，用于显示方块信息。

## 📖 功能介绍

当你将准心对准任意方块时，在屏幕顶部中央会显示一个信息框，包含：
- **方块名称**：优先显示中文本地化名称
- **ID名**：方块的注册名称（如：stone、diamond_ore）
- **模组**：方块所属的模组ID（如：minecraft、tconstruct）

## 🎮 使用方法

1. 将 `whats_this-1.0.0.jar` 文件复制到 `.minecraft/mods` 文件夹
2. 使用 Forge 1.20.1 启动游戏
3. 进入游戏后对准任意方块即可查看信息

## ⚙️ 配置

配置文件位置：`.minecraft/config/whats_this-common.toml`

```toml
# 是否显示方块信息HUD
showBlockInfo = true
```

## 📋 模组信息

- **模组名称**：What's This? (这是啥？)
- **模组ID**：whats_this
- **版本**：1.0.0
- **作者**：satania
- **许可证**：MIT
- **适用版本**：Minecraft 1.20.1 + Forge 47.x

## 🔧 开发环境

- Minecraft: 1.20.1
- Forge: 47.4.20
- Java: 17
- 构建工具: Gradle

### 编译命令

```bash
# 清理并构建
gradlew clean build

# 只打包 jar
gradlew jar

# 运行客户端测试
gradlew runClient
```

## 📝 项目结构

```
src/main/java/com/satania/whats_this/
├── WhatsThis.java          # 主类
├── Config.java             # 配置系统
├── util/
│   └── ModUtils.java       # 工具类
└── client/
    └── HudOverlay.java     # HUD渲染
```

## 💡 特性

- ✅ 纯客户端模组，无需服务端安装
- ✅ 支持中文本地化名称
- ✅ 极简风格UI设计
- ✅ 可配置的显示开关
- ✅ 零依赖，轻量级

## 📄 许可证

本项目采用 MIT 许可证。详见 LICENSE 文件。

## 👤 作者

**satania**

---

随便开发的Mod，Forge 1.20.1版本类似的模组需要前置，我没找到，前置模组应该出问题吗，就开发了这个，可能有bug，出了自己修。
