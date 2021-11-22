package at.izanamineko.system.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpSystem implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender s, Command cmd, String string, String[] args) {
        Player p = (Player)s;
        if(p.hasPermission("System.Help")){

        }

        return false;
    }
}
