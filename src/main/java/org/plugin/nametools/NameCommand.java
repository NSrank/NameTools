package org.plugin.nametools;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.List;
import java.util.Optional;

public class NameCommand implements SimpleCommand {
    private final NameTools plugin;

    public NameCommand(NameTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();

        if (args.length < 2) {
            source.sendMessage(Component.text("用法: /nametools set <玩家> <前缀> 或 /nametools del <玩家>", NamedTextColor.RED));
            return;
        }

        String action = args[0].toLowerCase();
        String targetName = args[1];
        Optional<Player> targetOpt = plugin.getServer().getPlayer(targetName); // 调用 getServer()

        if (targetOpt.isEmpty()) {
            source.sendMessage(Component.text("玩家不在线", NamedTextColor.RED));
            return;
        }
        Player target = targetOpt.get();

        switch (action) {
            case "set":
                if (!source.hasPermission("nametools.command.set")) {
                    source.sendMessage(Component.text("权限不足", NamedTextColor.RED));
                    return;
                }
                if (args.length < 3) {
                    source.sendMessage(Component.text("用法: /nametools set <玩家> <前缀>", NamedTextColor.RED));
                    return;
                }
                String prefix = args[2];
                plugin.getPlayerPrefixes().put(target.getUsername(), prefix); // 调用 getPlayerPrefixes()
                plugin.saveConfig();
                plugin.getServer().getEventManager().fire(new LoginEvent(target)); // 更新显示名
                source.sendMessage(Component.text("前缀设置成功", NamedTextColor.GREEN));
                break;

            case "del":
                if (!source.hasPermission("nametools.command.del")) {
                    source.sendMessage(Component.text("权限不足", NamedTextColor.RED));
                    return;
                }
                plugin.getPlayerPrefixes().remove(target.getUsername());
                plugin.saveConfig();
                plugin.getServer().getEventManager().fire(new LoginEvent(target)); // 更新显示名
                source.sendMessage(Component.text("前缀删除成功", NamedTextColor.GREEN));
                break;

            default:
                source.sendMessage(Component.text("无效指令", NamedTextColor.RED));
        }
    }

    @Override
    public List<String> suggest(Invocation invocation) {
        String[] args = invocation.arguments();
        if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
            return plugin.getServer().getAllPlayers().stream() // 调用 getServer()
                    .map(Player::getUsername)
                    .filter(name -> name.toLowerCase().startsWith(args[1].toLowerCase())) // 修正大小写敏感问题
                    .toList();
        }
        return List.of("set", "del");
    }
}