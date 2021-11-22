package at.izanamineko.system.cmd;

import at.izanamineko.system.managers.DefaultConfigManager;
import at.izanamineko.system.managers.MessagesConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class HomeSystem implements CommandExecutor {

    DefaultConfigManager dcm = new DefaultConfigManager();
    MessagesConfigManager mcm = new MessagesConfigManager();



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

            File home = new File("plugins/system/homes/" + p.getUniqueId() + ".yml");
            if (!home.exists()) {
                try {
                    home.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(home);
                yamlConfiguration.set("Home.Location.X", Double.valueOf(p.getLocation().getX()));
                yamlConfiguration.set("Home.Location.Y", Double.valueOf(p.getLocation().getY()));
                yamlConfiguration.set("Home.Location.Z", Double.valueOf(p.getLocation().getZ()));
                yamlConfiguration.set("Home.Location.Yaw", Float.valueOf(p.getLocation().getYaw()));
                yamlConfiguration.set("Home.Location.Pitch", Float.valueOf(p.getLocation().getPitch()));
                yamlConfiguration.set("Home.Location.World", p.getWorld().getName());
                try {
                    yamlConfiguration.save(home);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String msg = this.mcm.getConfig().getString("MessageSystem.HomeSystem.SetHome").replace("&", "§");
                p.sendMessage(msg);
            } else if(home.exists()) {
                home.delete();
                if(!home.exists()){
                    try {
                        home.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(home);
                yamlConfiguration.set("Home.Location.X", Double.valueOf(p.getLocation().getX()));
                yamlConfiguration.set("Home.Location.Y", Double.valueOf(p.getLocation().getY()));
                yamlConfiguration.set("Home.Location.Z", Double.valueOf(p.getLocation().getZ()));
                yamlConfiguration.set("Home.Location.Yaw", Float.valueOf(p.getLocation().getYaw()));
                yamlConfiguration.set("Home.Location.Pitch", Float.valueOf(p.getLocation().getPitch()));
                yamlConfiguration.set("Home.Location.World", p.getWorld().getName());
                try {
                    yamlConfiguration.save(home);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String msg2 = this.mcm.getConfig().getString("MessageSystem.HomeSystem.NewSetHome").replace("&", "§");
                p.sendMessage(msg2);
            }
        return false;
    }
}
