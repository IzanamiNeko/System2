package at.izanamineko.system.cmd;

import at.izanamineko.system.main;
import at.izanamineko.system.managers.DefaultConfigManager;
import at.izanamineko.system.managers.MessagesConfigManager;
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

            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                mcm.loadMessageConfig();
                dcm.loadDefaultConfig();
                String msg1 = this.mcm.getConfig().getString("MesssageSystem.SystemCommand.Reload").replace("&", "§");
                p.sendMessage(msg1);
                return true;
            }
            if (args.length == 1 && args[0].equalsIgnoreCase("create")) {
                String msg2 = this.mcm.getConfig().getString("MessageSystem.SystemCommand.CreateHelp").replace("&", "§");
                p.sendMessage(msg2);
                return true;
            }
            if (args.length == 2 && args[0].equalsIgnoreCase("homes")) {
                createHomesDir();
                String msg3 = this.mcm.getConfig().getString("MessageSystem.SystemCommand.HomeDir").replace("&", "§");
                p.sendMessage(msg3);
                return true;
            }
            if (args.length == 2 && args[0].equalsIgnoreCase("system")) {
                createDirectory();
                String msg4 = this.mcm.getConfig().getString("MessageSystem.SystemCommand.SystemDir").replace("&", "§");
                p.sendMessage(msg4);
                return true;
            }
        }
        return true;
    }

    public void createDirectory(){
        File system = new File("/plugins/system");
        if(!system.exists()){
            system.mkdir();
        }
    }
    public void createHomesDir(){
        File homes = new File("/plugins/system/homes/");
        if(!homes.exists()){
            homes.mkdir();
        }
    }

    public void test(){

    }

}
