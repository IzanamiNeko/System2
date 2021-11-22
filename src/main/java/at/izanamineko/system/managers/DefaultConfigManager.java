package at.izanamineko.system.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DefaultConfigManager {
    public final File file;

    public final FileConfiguration config;

    public DefaultConfigManager(){
        this.file = new File("plugins/system/config.yml");
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
        this.config.addDefault("SettingsSystem.AntiCommand.Enabled", "true");
        this.config.options().header("System-Plugin | DefaultConfigManager by IzanamiNeko");
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

    public void load() {
        try {
            this.config.load(this.file);
        } catch (IOException|org.bukkit.configuration.InvalidConfigurationException iOException) {
            iOException.printStackTrace();
        }
    }
}
