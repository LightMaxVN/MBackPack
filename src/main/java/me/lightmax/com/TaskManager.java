package me.lightmax.com;

import me.lightmax.com.CommandsManager.BackPackCommands;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskManager extends BukkitRunnable {

    Main plugin;

    public TaskManager(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public void run() {
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Tiến hành lưu trữ kho đồ..");
        if (!BackPackCommands.menu.isEmpty()) {
            plugin.saveinvmenu1();
        }
        if (!BackPackCommands.menu1.isEmpty()) {
            plugin.saveinvmenu2();
        }
        if (!BackPackCommands.menu2.isEmpty()) {
            plugin.saveinvmenu3();
        }
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Đã lưu trữ dữ liệu thành công");
        if (!Main.isOnSaving) {
            cancel();
       }
    }
}
