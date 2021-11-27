package at.izanamineko.system.cmd;

import at.izanamineko.system.utils.DefaultConfigManager;
import at.izanamineko.system.utils.MessagesConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class SystemCommand implements CommandExecutor {

    MessagesConfigManager mcm = new MessagesConfigManager();
    DefaultConfigManager dcm = new DefaultConfigManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        Player p = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("system")) {
            /*String msg = this.mcm.getConfig().getString("MesssageSystem.SystemCommand.Message").replace("&", "§");
            p.sendMessage(msg);*/

            if (args[0].equalsIgnoreCase("reload")) {
                mcm.loadMessageConfig();
                dcm.loadDefaultConfig();
                String msg1 = this.mcm.getConfig().getString("MesssageSystem.SystemCommand.Reload");
                String altColor = ChatColor.translateAlternateColorCodes('&', msg1);
                p.sendMessage(altColor);
                return true;
            }
            if (args[0].equalsIgnoreCase("create")) {
                String msg2 = this.mcm.getConfig().getString("MessageSystem.SystemCommand.CreateHelp");
                String altColor2 = ChatColor.translateAlternateColorCodes('&', msg2);
                p.sendMessage(altColor2);

                if (args[1].equalsIgnoreCase("homes")) {
                    createHomesDir();
                    String msg3 = this.mcm.getConfig().getString("MessageSystem.SystemCommand.HomeDir");
                    String altColor3 = ChatColor.translateAlternateColorCodes('&', msg3);
                    p.sendMessage(altColor3);
                }
                if (args[1].equalsIgnoreCase("system")) {
                    createBugsDir();
                    String msg4 = this.mcm.getConfig().getString("MessageSystem.SystemCommand.BugsDir");
                    String altColor4 = ChatColor.translateAlternateColorCodes('&', msg4);
                    p.sendMessage(altColor4);
                }
                return true;
            }
                return true;
            }
        return true;
        }


    public void createHomesDir(){
        File homes = new File("/plugins/system/homes/");
        if(!homes.exists()){
            homes.mkdir();
        }
    }
    public void createBugsDir(){
        File bugs = new File("/plugins/system/bugs");
        if(!bugs.exists()){
            bugs.mkdir();
        }
    }
}
