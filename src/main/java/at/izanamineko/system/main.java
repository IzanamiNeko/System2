package at.izanamineko.system;

import at.izanamineko.system.eventhandler.AntiCommand;
import at.izanamineko.system.managers.DefaultConfigManager;
import at.izanamineko.system.managers.MessagesConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class main extends JavaPlugin {

    main plugin;

    private MessagesConfigManager mcm = new MessagesConfigManager();
    private DefaultConfigManager dcm = new DefaultConfigManager();

    @Override
    public void onEnable(){
        System.out.println(ChatColor.GREEN + "System-Plugin is starting!");
        createDirectory();
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

    }
    public void createDirectory(){
        File system = new File("system");
        if(!system.exists()){
            system.mkdir();
        }
    }

}
