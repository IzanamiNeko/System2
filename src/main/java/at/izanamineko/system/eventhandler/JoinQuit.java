package at.izanamineko.system.eventhandler;

import at.izanamineko.system.utils.MessagesConfigManager;
import at.izanamineko.system.utils.sqlgetter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinQuit implements Listener {

    MessagesConfigManager mcm = new MessagesConfigManager();
    sqlgetter sqlget = new sqlgetter();

    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        sqlget.createPlayer(player);
    }
}
