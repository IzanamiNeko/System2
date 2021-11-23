package at.izanamineko.system.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesConfigManager {
    public final File file;

    public final FileConfiguration config;

    public MessagesConfigManager(){
        this.file = new File("plugins/system/messages.yml");
        this.config = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
        addDefaultStrings();
        checkIfExists();
    }

    public File getFile(){
        return this.file;
    }
    public FileConfiguration getConfig(){
        return this.config;
    }
    public void checkIfExists(){
        if(!this.file.exists()){
            addDefaultStrings();
        }
    }

    private void addDefaultStrings(){

        this.config.addDefault("MessageSystem.Message.nohelp", "[System] There is no such a Command");
        this.config.addDefault("MessageSystem.HomeSystem.SetHome", "[System] &cHome has been set");
        this.config.addDefault("MessageSystem.HomeSystem.NewSetHome", "[System] &cHome has been set");
        this.config.addDefault("MesssageSystem.SystemCommand.Message", "[System] &c/system <create/reload>");
        this.config.addDefault("MesssageSystem.SystemCommand.Reload", "[System] &cThe System-Configs have been reloaded!");
        this.config.addDefault("MessageSystem.SystemCommand.CreateHelp", "[System] &cUse /system create <directory> (for now system/homes)");
        this.config.addDefault("MessageSystem.SystemCommand.HomeDir", "[System] &cHome-Directory has been created!");
        this.config.addDefault("MessageSystem.SystemCommand.SystemDir", "[System] &cSystem-Directory has been created!");
        this.config.options().header("System-Plugin | MessagesConfigManager by IzanamiNeko");
        this.config.options().copyDefaults(true);
        save();
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void loadMessageConfig() {
        try {
            this.config.load(this.file);
        } catch (IOException|org.bukkit.configuration.InvalidConfigurationException iOException) {
            iOException.printStackTrace();
        }
    }
}
