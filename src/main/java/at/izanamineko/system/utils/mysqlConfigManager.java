package at.izanamineko.system.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class mysqlConfigManager {

    public final File file;

    public final FileConfiguration config;

    public mysqlConfigManager(){
        this.file = new File("plugins/system/mysql.yml");
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
        this.config.addDefault("MySQL.Host", "localhost");
        this.config.addDefault("MySQL.Port", "3306");
        this.config.addDefault("MySQL.Database", "database_name");
        this.config.addDefault("MySQL.username", "database_username");
        this.config.addDefault("MySQL.password", "database_password");
        this.config.options().header("System-Plugin | mysqlConfigManager by IzanamiNeko");
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

    public void loadDefaultConfig() {
        try {
            this.config.load(this.file);
        } catch (IOException|org.bukkit.configuration.InvalidConfigurationException iOException) {
            iOException.printStackTrace();
        }
    }
}
