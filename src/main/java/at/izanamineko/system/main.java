package at.izanamineko.system;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class main extends JavaPlugin {

    @Override
    public void onEnable(){
        System.out.println(ChatColor.GREEN + "System-Plugin is starting!");
    }

    @Override
    public void onDisable(){
        System.out.print(ChatColor.RED + "System-Plugin is stopping");
    }

    public void loadListener(){

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
