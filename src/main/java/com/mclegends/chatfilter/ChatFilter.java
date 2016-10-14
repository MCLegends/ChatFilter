package com.mclegends.chatfilter;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatFilter extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        super.getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage().toLowerCase();
        for (String swears : super.getConfig().getStringList("blacklist")) {
            if(message.contains(swears)) {
                if(!player.hasPermission("chatfilter.bypass")) {
                    return;
                }
                event.setCancelled(false);
                player.sendMessage(ChatColor.RED + "You are not allowed to swear on this server.");
            }
        }
    }
}
