package me.lightmax.com;

import me.lightmax.com.CommandsManager.BackPackCommands;
import me.lightmax.com.CommandsManager.XemKD;
import me.lightmax.com.FileManager.FileMenu1;
import me.lightmax.com.FileManager.FileMenu2;
import me.lightmax.com.FileManager.FileMenu3;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Main extends JavaPlugin {

    public static Boolean isOnSaving = true;

    private TaskManager taskManager;


    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("backpack").setExecutor(new BackPackCommands(this));
        this.getCommand("kd").setExecutor(new XemKD(this));
        this.getServer().getPluginManager().registerEvents(new EventManager(this), this);

        FileMenu1.setup();
        FileMenu1.get().options().copyDefaults();
        FileMenu1.save();

        FileMenu2.setup();
        FileMenu2.get().options().copyDefaults();
        FileMenu2.setup();

        FileMenu3.setup();
        FileMenu3.get().options().copyDefaults();
        FileMenu3.setup();


        if(FileMenu1.get().contains("data")) {
            this.restoremenu1();
            FileMenu1.get().set("data", null);
        }

        if(FileMenu2.get().contains("data")) {
            this.restoremenu2();
            FileMenu2.get().set("data", null);
        }

        if(FileMenu3.get().contains("data")) {
            this.restoremenu3();
            FileMenu3.get().set("data", null);
        }

        this.taskManager = new TaskManager(this);
        this.taskManager.runTaskTimer(this, 0, 12000);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (!BackPackCommands.menu.isEmpty()) {
            this.saveinvmenu1();
        }

        if (!BackPackCommands.menu1.isEmpty()) {
            this.saveinvmenu2();
        }

        if (!BackPackCommands.menu2.isEmpty()) {
            this.saveinvmenu3();
        }
        isOnSaving = false;
    }

    public void saveinvmenu1() {
        for(Map.Entry<String, ItemStack[]> entry : BackPackCommands.menu.entrySet()) {
            FileMenu1.get().set("data." + entry.getKey(), entry.getValue());
            FileMenu1.save();
        }
    }

    public void saveinvmenu2() {
        for(Map.Entry<String, ItemStack[]> entry : BackPackCommands.menu1.entrySet()) {
            FileMenu2.get().set("data." + entry.getKey(), entry.getValue());
            FileMenu2.save();
        }
    }

    public void saveinvmenu3() {
        for(Map.Entry<String, ItemStack[]> entry : BackPackCommands.menu1.entrySet()) {
            FileMenu3.get().set("data." + entry.getKey(), entry.getValue());
            FileMenu3.save();
        }
    }


    public void restoremenu1() {
        Objects.requireNonNull(FileMenu1.get().getConfigurationSection("data")).getKeys(false).forEach(key ->{
            @SuppressWarnings("unchecked")
            ItemStack[] content = ((List<ItemStack>) Objects.requireNonNull(FileMenu1.get().get("data." + key))).toArray(new ItemStack[0]);
            BackPackCommands.menu.put(key, content);
        });
    }

    public void restoremenu2() {
        Objects.requireNonNull(FileMenu2.get().getConfigurationSection("data")).getKeys(false).forEach(key ->{
            @SuppressWarnings("unchecked")
            ItemStack[] content = ((List<ItemStack>) Objects.requireNonNull(FileMenu2.get().get("data." + key))).toArray(new ItemStack[0]);
            BackPackCommands.menu1.put(key, content);
        });
    }

    public void restoremenu3() {
        Objects.requireNonNull(FileMenu3.get().getConfigurationSection("data")).getKeys(false).forEach(key ->{
            @SuppressWarnings("unchecked")
            ItemStack[] content = ((List<ItemStack>) Objects.requireNonNull(FileMenu3.get().get("data." + key))).toArray(new ItemStack[0]);
            BackPackCommands.menu2.put(key, content);
        });
    }

    public static ItemStack nextpage() {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("Nhấn để đi tới trang tiếp");
        meta.setLore(lore);
        meta.setDisplayName("Trang tiếp");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack previouspage() {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("Nhấn để đi trở về trang trước");
        meta.setLore(lore);
        meta.setDisplayName("Trở về");
        item.setItemMeta(meta);
        return item;
    }


}
