package at.izanamineko.system;

import at.izanamineko.system.cmd.HomeSystem;
import at.izanamineko.system.cmd.SystemCommand;
import at.izanamineko.system.eventhandler.AntiCommand;
import at.izanamineko.system.managers.DefaultConfigManager;
import at.izanamineko.system.managers.MessagesConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class main extends JavaPlugin {

    private final MessagesConfigManager mcm = new MessagesConfigManager();
    private final DefaultConfigManager dcm = new DefaultConfigManager();

    @Override
    public void onEnable(){
        System.out.println(ChatColor.GREEN + "System-Plugin is starting!");
        createDirectory();
        createHomesDir();
        loadCommands();
        loadListener();
    }

    @Override
    public void onDisable(){
        System.out.print(ChatColor.RED + "System-Plugin is stopping");
    }

    public void loadListener(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new AntiCommand(), this);

    }
    public void loadCommands(){
        getCommand("sethome").setExecutor(new HomeSystem());
        getCommand("system").setExecutor(new SystemCommand());
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

}
