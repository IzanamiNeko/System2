package at.izanamineko.system.eventhandler;

import at.izanamineko.system.managers.DefaultConfigManager;
import at.izanamineko.system.managers.MessagesConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class AntiCommand implements Listener {

    MessagesConfigManager mcm = new MessagesConfigManager();
    DefaultConfigManager dcm = new DefaultConfigManager();

    @EventHandler
    public void onCMD(PlayerCommandPreprocessEvent onCMD){
        Player p = onCMD.getPlayer();
        if(this.dcm.getConfig().getString("AntiCommand.Enabled").equals("true"));
        if(onCMD.getMessage().equals("/help system") && !p.isOp() || !p.hasPermission("System.AntiCommand")) {
            String msg = this.mcm.getConfig().getString("Message.nohelp").replace("&", "§");
            p.sendMessage(msg);
            onCMD.setCancelled(true);
        } else {
            onCMD.setCancelled(false);
        }
    }
}
