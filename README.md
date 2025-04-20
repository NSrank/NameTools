# NameTools - Velocity 玩家前缀管理插件
![Velocity](https://img.shields.io/badge/Velocity-3.x-blue) ![Java](https://img.shields.io/badge/Java-11-green) ![License](https://img.shields.io/badge/License-GPLv3-green.svg)

**NameTools** 是一个专为 Minecraft Velocity 服务端设计的玩家前缀管理插件。支持通过指令动态设置玩家前缀，并兼容颜色代码（如 `&b[VIP]`），可与跨服聊天插件 [**ChatTools**](https://github.com/NSrank/ChatTools) 无缝协同。

> **注意**：本插件由 AI 开发，旨在简化玩家身份标识管理。

---  

## 功能特性
- **动态前缀管理**：
    - 使用 `/nametools set <玩家> <前缀>` 和 `/nametools del <玩家>` 命令实时管理前缀。
    - 前缀支持 Minecraft 颜色代码（如 `&a[Admin]`、`&6[VIP]`）。
- **持久化存储**：
    - 前缀数据实时写入配置文件 `plugins/NameTools/config.yml`。
- **权限控制**：
    - 管理员权限节点 `nametools.command.set` 和 `nametools.command.del`。
- **兼容性**：
    - 适配 Velocity 3.3+ 和 Minecraft 1.16+。
    - 与 [**ChatTools**](https://github.com/NSrank/ChatTools) 协同显示跨服前缀（需 ChatTools 1.4+）。

---  

## 安装步骤
### 1. 下载插件
从 [GitHub](https://github.com/NSrank/NameTools) 或其他分发渠道下载最新版本的 `NameTools.jar`。

### 2. 安装插件
将下载的 `NameTools.jar` 文件放入 Velocity 服务端的 `plugins/` 目录中。

### 3. 启动服务器
启动 Velocity 服务端，插件会自动生成默认配置文件 `plugins/NameTools/config.yml`。

---  

## 使用方法
### 命令列表
| 命令                     | 权限               | 描述                                                                 |  
|--------------------------|--------------------|----------------------------------------------------------------------|  
| `/nametools set <玩家> <前缀>` | `nametools.command.set` | 为玩家设置带颜色代码的前缀（如 `/nametools set Steve &b[VIP]`）。    |  
| `/nametools del <玩家>`       | `nametools.command.del` | 删除玩家的前缀。                                                    |  
| `/nt set <玩家> <前缀>`        | `nametools.command.set` | 同 `/nametools set`，命令别名。                                     |  

### 示例
1. 为玩家 `Steve` 设置 VIP 前缀：
   ```bash  
   /nametools set Steve &b[VIP]  
   ```
2. 删除玩家 `Steve` 的前缀：
   ```bash
   /nametools del Steve
   ```

---

## 配置文件
配置文件 `plugins/NameTools/config.yml` 用于管理前缀数据。
```yaml
# 玩家前缀配置
prefixes: 
  Steve: "&b[VIP]"
  Alex: "&c[管理员]"
```  
- 修改说明： 
- 键为玩家用户名，值为带颜色代码的前缀（如 `&a[Builder]`）。

---

## 技术支持与反馈
如果您在使用插件过程中遇到任何问题，或希望提出改进建议，请通过以下方式联系：

- **GitHub Issues** : [提交问题](https://github.com/NSrank/NameTools/issues)

---

### 版权声明
- 开发声明 ：本插件由 AI 开发，旨在为 Minecraft Velocity 社区提供高效的玩家自定义头衔工具。
- 许可证 ：本插件遵循 GNU General Public License v3.0 许可证，您可以自由使用、修改和分发，但需遵守许可证条款。
- 免责条款 ：开发者不对因使用本插件而导致的任何问题负责。

---

### 特别感谢
感谢以下技术和工具对本插件的支持：

- [Velocity API](https://papermc.io/software/velocity)
- [Adventure API](https://github.com/KyoriPowered/adventure?spm=a2ty_o01.29997173.0.0.7c5733f51H3mj8)

---

# NameTools - Player Prefix Management Plugin for Velocity
![Velocity](https://img.shields.io/badge/Velocity-3.x-blue) ![Java](https://img.shields.io/badge/Java-11-green) ![License](https://img.shields.io/badge/License-GPLv3-green.svg)

**NameTools** is a player prefix management plugin designed for Minecraft Velocity servers. It supports dynamically setting player prefixes via commands with color codes (e.g., `&b[VIP]`) and seamlessly integrates with cross-server chat plugins like [**ChatTools**](https://github.com/NSrank/ChatTools).

> **Note**: This plugin is AI-developed to simplify player identity management.

---  

## Features
- **Dynamic Prefix Management**:
    - Use `/nametools set <player> <prefix>` and `/nametools del <player>` to manage prefixes in real-time.
    - Supports Minecraft color codes (e.g., `&a[Admin]`, `&6[VIP]`).
- **Persistent Storage**:
    - Prefix data is saved to `plugins/NameTools/config.yml` automatically.
- **Permission Control**:
    - Admin permission nodes: `nametools.command.set` and `nametools.command.del`.
- **Compatibility**:
    - Works with Velocity 3.3+ and Minecraft 1.16+.
    - Displays prefixes across servers with [**ChatTools**](https://github.com/NSrank/ChatTools) (requires ChatTools 1.4+).

---  

## Installation
### 1. Download the Plugin
Download the latest `NameTools.jar` from [GitHub](https://github.com/NSrank/NameTools) or other distribution channels.

### 2. Install the Plugin
Place `NameTools.jar` into the `plugins/` directory of your Velocity server.

### 3. Start the Server
Launch the Velocity server. The plugin will auto-generate the default configuration file `plugins/NameTools/config.yml`.

---  

## Usage
### Commands
| Command                     | Permission            | Description                                                                 |  
|-----------------------------|-----------------------|-----------------------------------------------------------------------------|  
| `/nametools set <player> <prefix>` | `nametools.command.set` | Set a color-coded prefix for a player (e.g., `/nametools set Steve &b[VIP]`). |  
| `/nametools del <player>`       | `nametools.command.del` | Remove a player's prefix.                                                   |  
| `/nt set <player> <prefix>`        | `nametools.command.set` | Alias for `/nametools set`.                                                 |  

### Examples
1. Set a VIP prefix for player `Steve`:
   ```bash  
   /nametools set Steve &b[VIP]  
   ```
2. Remove the prefix from player `Steve`:
   ```bash
   /nametools del Steve
   ```

---

## Configuration File
The `config.yml` file in `plugins/NameTools/` is used to manage player prefixes.
```yaml
# Player Prefix Configuration
prefixes:
  Steve: "&b[VIP]"
  Alex: "&c[admin]"
```
- Notes:
- Keys are player usernames, values are color-coded prefixes (e.g., `&a[Builder]`).

---

## Support and Feedback
If you encounter any issues while using the plugin or have suggestions for improvements, please contact us via:
- **GitHub Issues** : [Submit an Issue](https://github.com/NSrank/NameTools/issues)

---

### License & Disclaimer
- **Development Statement** : This plugin is AI-developed to provide an efficient player custom title tool for Minecraft Velocity servers.
- **License** : This plugin is licensed under the GNU General Public License v3.0. You are free to use, modify, and distribute the plugin, but must comply with the terms of the license.
- **Disclaimer** : The developer is not responsible for any issues arising from the use of this plugin.

---

### Acknowledgments
Special thanks to the following technologies and tools that support this plugin:
- [Velocity API](https://papermc.io/software/velocity)
- [Adventure API](https://github.com/KyoriPowered/adventure?spm=a2ty_o01.29997173.0.0.7c5733f51H3mj8)
