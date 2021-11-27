package at.izanamineko.system;

import at.izanamineko.system.cmd.HomeSystem;
import at.izanamineko.system.cmd.SystemCommand;
import at.izanamineko.system.eventhandler.AntiCommand;
import at.izanamineko.system.eventhandler.JoinQuit;
import at.izanamineko.system.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;

public class main extends JavaPlugin implements Listener {

    private final MessagesConfigManager mcm = new MessagesConfigManager();
    private final DefaultConfigManager dcm = new DefaultConfigManager();
    private final mysqlConfigManager mysqlc = new mysqlConfigManager();
    public mysqlconnect sql = new mysqlconnect();
    public sqlgetter sqlget = new sqlgetter();



    @Override
    public void onEnable(){
        Bukkit.getLogger().info(ChatColor.GREEN + "[System-Plugin] Plugin is starting now....");
        this.sql = new mysqlconnect();
        this.sqlget = new sqlgetter();
        try {
            sql.connect();
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            Bukkit.getLogger().info(ChatColor.RED + "[System-Plugin] Database not connected");
        }
        if(sql.isConnected()) {
            sqlget.createTable();
            Bukkit.getLogger().info(ChatColor.GREEN + "Database is connected!");
        }
        PlaceHolder();
        createDirectory();
        createHomesDir();
        loadCommands();
        loadListener();


        Bukkit.getLogger().info(ChatColor.GREEN + "[System-Plugin] Plugin has started!");
    }

    @Override
    public void onDisable(){

        sql.disconnect();


        Bukkit.getLogger().info(ChatColor.RED + "[System-Plugin] Plugin is stopping");
    }

    public void loadListener(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new AntiCommand(), this);
        pm.registerEvents(new ChatFormatter(), this);
        pm.registerEvents(new JoinQuit(), this);

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

    public void PlaceHolder(){
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            /*
             * We register the EventListener here, when PlaceholderAPI is installed.
             * Since all events are in the main class (this class), we simply use "this"
             */
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            /*
             * We inform about the fact that PlaceholderAPI isn't installed and then
             * disable this plugin to prevent issues.
             */
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

}
