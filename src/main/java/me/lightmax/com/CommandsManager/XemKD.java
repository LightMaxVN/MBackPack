package me.lightmax.com.CommandsManager;

import me.lightmax.com.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class XemKD implements CommandExecutor {

    public static Map<String, ItemStack[]> inventory = new HashMap<>();

    public static Inventory inv;

    Main plugin;

    public XemKD(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("kd")) {
                if(args.length < 1) {
                    player.sendMessage(ChatColor.GREEN + "Cú pháp: /kd <người chơi>");
                    return true;
                }

                if(args.length == 1 && args[0].length() >= 3) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);

                    if(target != null) {
                        inv = Bukkit.createInventory(null, 45, ChatColor.GOLD + "Kho đồ của " + ChatColor.AQUA + "" + target.getName());
                        ItemStack[] item = target.getInventory().getContents();
                        inv.setContents(item);
                        ItemStack helmet = target.getInventory().getHelmet();
                        ItemStack chestplate = target.getInventory().getChestplate();
                        ItemStack leggings = target.getInventory().getLeggings();
                        ItemStack boost = target.getInventory().getBoots();

                        inv.setItem(36, helmet);
                        inv.setItem(37, chestplate);
                        inv.setItem(38, leggings);
                        inv.setItem(39, boost);

                        player.openInventory(inv);

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if(player.getOpenInventory().getTitle().equals("Kho đồ của " + target.getName())) {
                                    ItemStack[] item = target.getInventory().getContents();
                                    inv.setContents(item);
                                    ItemStack helmet = target.getInventory().getHelmet();
                                    ItemStack chestplate = target.getInventory().getChestplate();
                                    ItemStack leggings = target.getInventory().getLeggings();
                                    ItemStack boost = target.getInventory().getBoots();

                                    inv.setItem(36, helmet);
                                    inv.setItem(37, chestplate);
                                    inv.setItem(38, leggings);
                                    inv.setItem(39, boost);
                                }else{
                                    cancel();
                                }
                            }
                        }.runTaskTimer(plugin, 0, 10);
                    }
                    return true;
                }
                return true;
            }


        }else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&C&lKhông!"));
        }

        return true;
    }
}
