package me.lightmax.com.FileManager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FileMenu2 {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("MBackPack")).getDataFolder(), "Menu2.yml");

        if(!file.exists()) {
            try{
                file.createNewFile();
            }catch (IOException e) {
                //owo
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {return customFile;}

    public static void save() {
        try{
            customFile.save(file);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
