package at.izanamineko.system.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormatter implements Listener {

    MessagesConfigManager mcm = new MessagesConfigManager();
    DefaultConfigManager dcm = new DefaultConfigManager();





    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (this.dcm.getConfig().getString("SettingsSystem.Chat-Format.Enabled").equals("true")) {
            String config = PlaceholderAPI.setPlaceholders(p, this.mcm.getConfig().getString("MessageSystem.Chat-Format.Format"));
            //String config = PlaceholderAPI.setPlaceholders(p, "%luckperms_prefix%") +  PlaceholderAPI.setPlaceholders(p, "%player_name%") + PlaceholderAPI.setPlaceholders(p, ": " );
            String altColor = ChatColor.translateAlternateColorCodes('&', config);

            String msg = altColor;

            if(p.hasPermission("System.ColorChat")) {
                e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
            }
            e.setFormat(msg + e.getMessage());

        }
    }
}
