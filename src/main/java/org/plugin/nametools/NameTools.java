package org.plugin.nametools;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Plugin(
        id = "nametools",
        name = "NameTools",
        version = "1.0"
)
public class NameTools {
    private final ProxyServer server;
    private final Logger logger;
    private final Map<String, String> playerPrefixes = new ConcurrentHashMap<>();
    private final Path configPath = Path.of("plugins/NameTools/config.yml");
    private boolean chatToolsLoaded = false;

    @Inject
    public NameTools(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
        loadConfig();
        logger.info("===================================");
        logger.info("NameTools 插件已加载");
        logger.info("版本：1.0 | 作者：NSrank & Qwen2.5-Max");
        logger.info("===================================");
    }

    //重载插件配置文件
    private class ReloadCommand implements SimpleCommand {
        @Override
        public void execute(Invocation invocation) {
            var source = invocation.source();
            if (!source.hasPermission("nametools.reload")) {
                source.sendMessage(Component.text("权限不足", NamedTextColor.RED));
                return;
            }

            loadConfig(); // 重新加载配置
            source.sendMessage(Component.text("配置已重载", NamedTextColor.GREEN));
            logger.info("配置已由 {} 重载", source);
        }
    }

    // 检测 ChatTools 是否存在
    private void checkChatTools() {
        chatToolsLoaded = server.getPluginManager().isLoaded("chattools");
    }

    // 独立模式：处理聊天事件
    @Subscribe(order = PostOrder.LATE) // 低优先级，避免与 ChatTools 冲突
    public void onPlayerChat(PlayerChatEvent event) {
        if (chatToolsLoaded) return; // 如果 ChatTools 存在，禁用自身处理

        Player player = event.getPlayer();
        String prefix = playerPrefixes.get(player.getUsername());
        if (prefix == null) return;

        Component message = LegacyComponentSerializer.legacyAmpersand().deserialize(prefix + " " + player.getUsername())
                .append(Component.text(": ").color(NamedTextColor.WHITE))
                .append(Component.text(event.getMessage()));

        event.setResult(PlayerChatEvent.ChatResult.message(String.valueOf(message)));
    }

    // 联动模式：暴露公共方法供 ChatTools 调用
    public Component getFormattedPrefix(Player player) {
        String prefix = playerPrefixes.get(player.getUsername());
        return prefix != null ? LegacyComponentSerializer.legacyAmpersand().deserialize(prefix) : null;
    }

    private void loadConfig() {
        if (Files.notExists(configPath)) {
            try (InputStream in = getClass().getResourceAsStream("/config.yml")) {
                Files.createDirectories(configPath.getParent());
                Files.copy(in, configPath);
                logger.info("默认配置文件已生成");
            } catch (IOException e) {
                logger.error("配置文件创建失败", e);
            }
        }
        Yaml yaml = new Yaml();
        try (InputStream in = Files.newInputStream(configPath)) {
            Map<String, String> prefixes = yaml.loadAs(in, Map.class);
            if (prefixes != null) playerPrefixes.putAll(prefixes);
        } catch (IOException e) {
            logger.error("配置加载失败", e);
        }
    }

    public void saveConfig() {
        try {
            Files.write(configPath, new Yaml().dump(playerPrefixes).getBytes());
        } catch (IOException e) {
            logger.error("配置保存失败", e);
        }
    }
}