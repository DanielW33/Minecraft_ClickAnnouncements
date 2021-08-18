package me.MuchDan.StrellaMCAnnouncer.Util;

import me.MuchDan.StrellaMCAnnouncer.ClickAnnouncements;
import me.MuchDan.StrellaMCAnnouncer.ConfigManager.AnnouncementConfig;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

public class Timer {
    private int runTime;
    private int TaskID;
    private int Counter;
    private ClickAnnouncements plugin;
    private AnnouncementObj Obj;

    public Timer(ClickAnnouncements plugin) {
        this.plugin = plugin;
        Counter = 0;
        runTime = 0;
    }

    public void StartTimer() {
        BukkitScheduler Scheduler = Bukkit.getServer().getScheduler();
        TaskID = Scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (runTime <= 0) {
                    if(Counter == plugin.Announcements.size()){
                        Counter = 0;
                    }
                    Obj = plugin.Announcements.get(Counter);
                    runTime = Obj.getTime();
                    Counter++;
                    for(String message : Obj.getAnnouncement()){
                        TextComponent msg = new TextComponent(message);
                        if(Obj.getActionType().equalsIgnoreCase("PlayerAction")){
                            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Obj.getAction()));
                            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Obj.getHoverMessage()).create()));
                        }
                        else if(Obj.getActionType().equalsIgnoreCase("OpenWebLink")){
                            msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Obj.getAction()));
                            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Obj.getHoverMessage()).create()));
                        }
                        else if(Obj.getActionType().equalsIgnoreCase("NoAction")){
                            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Obj.getHoverMessage()).create()));
                        }

                        for(Player player : Bukkit.getOnlinePlayers()){
                            player.spigot().sendMessage(msg);
                        }
                    }
                }
                runTime --;
            }
        }, 20L, 20L);

    }

    public void setTime(int Time) {
        this.runTime = Time;
    }

    public int getTime() {
        return runTime;
    }
}
