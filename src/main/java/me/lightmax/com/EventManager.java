package me.lightmax.com;

import me.lightmax.com.CommandsManager.BackPackCommands;
import me.lightmax.com.CommandsManager.XemKD;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class EventManager implements Listener {

    Main plugin;

    public EventManager(Main plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onGUIClose(InventoryCloseEvent e) {
        if(e.getView().getTitle().contains(ChatColor.GOLD + "Kho đồ 1 của " + ChatColor.AQUA + e.getPlayer().getName())) {
            BackPackCommands.menu.put(e.getPlayer().getUniqueId().toString(), e.getInventory().getContents());
        }
        if(e.getView().getTitle().contains(ChatColor.GOLD + "Kho đồ 2 của " + ChatColor.AQUA + e.getPlayer().getName())) {
            BackPackCommands.menu1.put(e.getPlayer().getUniqueId().toString(), e.getInventory().getContents());
        }
        if(e.getView().getTitle().contains(ChatColor.GOLD + "Kho đồ 3 của " + ChatColor.AQUA + e.getPlayer().getName())) {
            BackPackCommands.menu2.put(e.getPlayer().getUniqueId().toString(), e.getInventory().getContents());
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getCurrentItem() == null) return;
        if(e.getCurrentItem().getItemMeta() == null) return;
        e.getCurrentItem().getItemMeta().getDisplayName();

        if(e.getInventory().equals(BackPackCommands.inv1)) {
            if(e.getSlot() == 53) {
                e.setCancelled(true);
                if (BackPackCommands.menu1.containsKey(BackPackCommands.player.getUniqueId().toString()))
                    BackPackCommands.inv2.setContents(BackPackCommands.menu1.get(BackPackCommands.player.getUniqueId().toString()));
                BackPackCommands.player.openInventory(BackPackCommands.inv2);
            }
        }

        if(e.getInventory().equals(BackPackCommands.inv2)) {
            if(e.getSlot() == 53) {
                e.setCancelled(true);
                if (BackPackCommands.menu2.containsKey(BackPackCommands.player.getUniqueId().toString()))
                    BackPackCommands.inv3.setContents(BackPackCommands.menu2.get(BackPackCommands.player.getUniqueId().toString()));
                BackPackCommands.player.openInventory(BackPackCommands.inv3);
            }
            if(e.getSlot() == 45) {
                e.setCancelled(true);
                if (BackPackCommands.menu.containsKey(BackPackCommands.player.getUniqueId().toString()))
                    BackPackCommands.inv1.setContents(BackPackCommands.menu.get(BackPackCommands.player.getUniqueId().toString()));
                BackPackCommands.player.openInventory(BackPackCommands.inv1);
            }
        }

        if(e.getInventory().equals(BackPackCommands.inv3)) {
            if(e.getSlot() == 45) {
                e.setCancelled(true);
                if (BackPackCommands.menu1.containsKey(BackPackCommands.player.getUniqueId().toString()))
                    BackPackCommands.inv2.setContents(BackPackCommands.menu1.get(BackPackCommands.player.getUniqueId().toString()));
                BackPackCommands.player.openInventory(BackPackCommands.inv2);
            }
        }
        if(e.getInventory().equals(XemKD.inv)) {
            e.setCancelled(true);
        }
    }
}
