package at.izanamineko.system.cmd;


import at.izanamineko.system.utils.MessagesConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class BugSystem implements CommandExecutor {



    MessagesConfigManager mcm = new MessagesConfigManager();



    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;
        String bug = "";

        Random rand = new Random();
        int upperbound = 2147483647;
        int int_random = rand.nextInt(upperbound);


        if (sender instanceof Player) {
            if (args.length < 1) {
                String msg = this.mcm.getConfig().getString("Messages.BugCMD.Usage").replace("&", "§");
                p.sendMessage(msg);
                return true;
            }
            for (int i = 0; i < args.length; i++)
                bug = bug + args[i] + " ";
            for (Player players : Bukkit.getOnlinePlayers()) {
                String sendmsg = this.mcm.getConfig().getString("Messages.BugCMD.BugReported").replace("&", "§").replaceAll("%id%", String.valueOf(int_random));
                p.sendMessage(sendmsg);
                if (players.hasPermission("LobbySystem.ReceiveBug")) {
                    String msg = this.mcm.getConfig().getString("Messages.BugCMD.ID-Message").replaceAll("%player%", sender.getName()).replace("&", "§").replaceAll("%id%", String.valueOf(int_random));
                    players.sendMessage(msg);
                    //String msg2 = this.plugin.getConfig().getString("BugCMD.BugReport").replace("&", "§").replaceAll("%bug%", bug);
                    //players.sendMessage(msg2);
                    players.playSound(players.getLocation(), Sound.BLOCK_ANVIL_USE, 10.0F, 10.0F);
                    File file = new File("plugins/system/Bugs/ID" + int_random + ".yml");
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                            Bukkit.getConsoleSender().sendMessage("[System] A new Bug got reported");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Bukkit.getConsoleSender().sendMessage("");
                    }
                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    cfg.options().header("Bug-Report by IzanamiNeko");
                    cfg.set("Displayname", p.getDisplayName());
                    cfg.set("UUID", p.getUniqueId());
                    cfg.set("Bug-Report", bug);
                    cfg.set("Ping (MS)", p.getPing());
                    cfg.set("Location", p.getLocation());
                    cfg.set("Time", new Date(System.currentTimeMillis()));
                    try {
                        cfg.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    continue;
                }

            }
        } else {
            System.out.println(ChatColor.RED + "This CMD is only for In-Game usable");
        }
        return true;
    }
}