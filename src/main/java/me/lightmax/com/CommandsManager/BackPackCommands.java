package me.lightmax.com.CommandsManager;

import me.lightmax.com.FileManager.FileMenu1;
import me.lightmax.com.FileManager.FileMenu2;
import me.lightmax.com.FileManager.FileMenu3;
import me.lightmax.com.Main;
import me.lightmax.com.TaskManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BackPackCommands implements CommandExecutor {

    public static Map<String, ItemStack[]> menu = new HashMap<>();
    public static Map<String, ItemStack[]> menu1 = new HashMap<>();
    public static Map<String, ItemStack[]> menu2 = new HashMap<>();


    public static Inventory inv1;
    public static Inventory inv2;
    public static Inventory inv3;


    public static Player player;

    Main plugin;

    private TaskManager taskManager;


    public BackPackCommands(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {

            player = (Player) sender;
            inv1 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Kho đồ 1 của " + ChatColor.AQUA + player.getName());
            inv2 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Kho đồ 2 của " + ChatColor.AQUA + player.getName());
            inv3 = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Kho đồ 3 của " + ChatColor.AQUA + player.getName());

            inv1.setItem(53, Main.nextpage());
            inv2.setItem(53, Main.nextpage());
            inv2.setItem(45, Main.previouspage());
            inv3.setItem(45, Main.previouspage());

            if(cmd.getName().equalsIgnoreCase("backpack")) {

                if(args.length == 1 && args[0].equalsIgnoreCase("start")) {
                    if(player.hasPermission("backpack.start") || player.hasPermission("backpack.admin")) {
                        if (!Main.isOnSaving) {
                            this.taskManager = new TaskManager(plugin);
                            this.taskManager.runTaskTimer(plugin, 0, 12000);
                            player.sendTitle(ChatColor.GREEN + "Tự Động Lưu: " + ChatColor.AQUA + "Bật", "", 10, 20, 15);
                        } else {
                            player.sendMessage(ChatColor.GREEN + "Tự động lưu đã được bật! Sử dụng ./backpack stop để dừng!");
                        }
                    }else{
                        player.sendMessage(ChatColor.RED + "Bạn không có quyền để sử dụng lệnh này!");
                    }
                }

                if(args.length == 1 && args[0].equalsIgnoreCase("stop")) {
                    if(player.hasPermission("backpack.stop") || player.hasPermission("backpack.admin")) {
                        if (Main.isOnSaving) {
                            Main.isOnSaving = false;
                            player.sendTitle(ChatColor.GREEN + "Tự Động Lưu: " + ChatColor.RED + "Tắt", "", 10, 20, 15);
                        } else {
                            player.sendMessage(ChatColor.GREEN + "Tự động lưu đã được tắt! Sử dụng ./backpack start để bắt đầu!");
                        }
                    }else{
                        player.sendMessage(ChatColor.RED + "Bạn không có quyền để sử dụng lệnh này!");
                    }
                }

                if(args.length == 1 && args[0].equalsIgnoreCase("save")) {

                    if (player.hasPermission("backpack.save") || player.hasPermission("backpack.admin")) {


                        if (!BackPackCommands.menu.isEmpty()) {
                            plugin.saveinvmenu1();
                        }
                        if (!BackPackCommands.menu1.isEmpty()) {
                            plugin.saveinvmenu2();
                        }
                        if (!BackPackCommands.menu2.isEmpty()) {
                            plugin.saveinvmenu3();
                        }
                        player.sendMessage(ChatColor.GREEN + "Đã lưu trữ thành công!");
                    }else{
                        player.sendMessage(ChatColor.RED + "Bạn không có quyền để sử dụng lệnh này!");
                    }
                }

                if(args.length < 1) {
                    if(player.hasPermission("backpack.admin")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aBackPack được làm bởi &bLightMax_Vn"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack help: &7hiện ra tin nhắn này"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack open: &7Mở kho đồ"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack open <số> : &7Mở kho đồ số bạn đề cập (từ 1 - 3)"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/kd <tên>: &7xem kho đồ của người đó"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack debug: &7xem các debug của plugin (hiện tại chưa có debug)" ));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack save: &7Buộc lưu kho đồ"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack remove <player>: &7xóa dữ liệu kho đồ của người chơi đó"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack stop: &7Dừng tự động lưu kho đồ"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack start: &7Bắt đầu tự động lưu kho đồ"));

                        return true;
                    }else{
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aBackPack được làm bởi &bLightMax_Vn"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack help: &7hiện ra tin nhắn này"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack open: &7Mở kho đồ"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack open <số> : &7Mở kho đồ số bạn đề cập (từ 1 - 3)"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/kd <tên>: &7xem kho đồ của người đó"));
                    }
                }
                if(args.length >= 1 && args[0].equalsIgnoreCase("open")) {
                    if(args[1].length() > 1) {
                        if (isNum(args[1])) {
                            switch (args[1].toLowerCase()) {
                                case "1":
                                    if (menu.containsKey(player.getUniqueId().toString())) {
                                        inv1.setContents(menu.get(player.getUniqueId().toString()));
                                    }
                                    player.openInventory(inv1);
                                    break;
                                case "2":
                                    if (menu1.containsKey(player.getUniqueId().toString())) {
                                        inv2.setContents(menu1.get(player.getUniqueId().toString()));
                                    }
                                    player.openInventory(inv2);
                                    break;
                                case "3":
                                    if (menu2.containsKey(player.getUniqueId().toString())) {
                                        inv3.setContents(menu2.get(player.getUniqueId().toString()));
                                    }
                                    player.openInventory(inv3);
                                    break;
                                default:
                                    sender.sendMessage(ChatColor.RED + "Nhập một con số từ 1 - 3");
                                    break;
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "Nhập một con số. Thay vì là " + args[1]);
                        }
                    }
                    if(args[1].length() == 0) {
                        if (menu.containsKey(player.getUniqueId().toString()))
                            inv1.setContents(menu.get(player.getUniqueId().toString()));
                        player.openInventory(inv1);
                    }
                    return true;
                }

                if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                    if (player.hasPermission("backpack.reload") || player.hasPermission("backpack.admin")) {
                        FileMenu1.save();
                        FileMenu1.reload();
                        FileMenu2.save();
                        FileMenu2.reload();
                        FileMenu3.save();
                        FileMenu3.reload();
                        plugin.saveConfig();


                        sender.sendMessage(ChatColor.GREEN + "Đã reload plugin");
                        return true;
                    }else{
                        player.sendMessage(ChatColor.RED + "Bạn không có quyền để sử dụng lệnh này!");
                    }
                }

                if (args[0].equalsIgnoreCase("help")) {
                    if(player.hasPermission("backpack.admin")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aBackPack được làm bởi &bLightMax_Vn"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack help: &7hiện ra tin nhắn này"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack open: &7Mở kho đồ"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack open <số> : &7Mở kho đồ số bạn đề cập (từ 1 - 3)"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/kd <tên>: &7xem kho đồ của người đó"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/xembp <tên>: &7xem backpack của người chơi đó"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack debug: &7xem các debug của plugin"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack save: &7Buộc lưu kho đồ"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack remove <player>: &7xóa dữ liệu kho đồ của người chơi đó"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack stop: &7Dừng auto save kho đồ"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack start: &7Bắt đầu auto save kho đồ"));

                        return true;
                    }else{
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aBackPack được làm bởi &bLightMax_Vn"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack help: &7hiện ra tin nhắn này"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack open: &7Mở kho đồ"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/backpack open <số> : &7Mở kho đồ số bạn đề cập (từ 1 - 3)"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/kd <tên>: &7xem kho đồ của người đó"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/xembp <tên>: &7xem backpack của người chơi đó"));
                    }
                    
                }

                return true;
            }

        }else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lKhông"));
        }
        return true;
    }

    public boolean isNum(String num) {
        try{
            Integer.parseInt(num);
        }catch (Exception e) {
            return false;
        }
        return true;
    }

}
