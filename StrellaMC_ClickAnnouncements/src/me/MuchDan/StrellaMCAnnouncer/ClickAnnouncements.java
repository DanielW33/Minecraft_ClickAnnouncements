package me.MuchDan.StrellaMCAnnouncer;

import me.MuchDan.StrellaMCAnnouncer.ConfigManager.AnnouncementConfig;
import me.MuchDan.StrellaMCAnnouncer.Util.AnnouncementObj;
import me.MuchDan.StrellaMCAnnouncer.Util.Timer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ClickAnnouncements extends JavaPlugin {
    private AnnouncementConfig AConfig;
    public List<AnnouncementObj> Announcements;

    @Override
    public void onEnable(){
        Announcements = new ArrayList<>();
        AConfig = new AnnouncementConfig(this);
        AConfig.getConfig().options().copyDefaults(false);
        AConfig.saveDefaultConfig();

        buildAnnouncements();
        StartTimers();
    }
    @Override
    public void onDisable(){
        Announcements.clear();
    }

    public void buildAnnouncements(){
        AConfig.getConfig().getConfigurationSection("Announcements").getKeys(false).forEach(Section ->{
            AnnouncementObj Obj = new AnnouncementObj();
            Obj.setTitle(getTitle(Section));
            Obj.setAnnouncement(getAnnouncement(Section));
            Obj.setHoverMessage(getHoverMessage(Section));
            Obj.setAction(getAction(Section));
            Obj.setActionType(getActionType(Section));
            Obj.setTime(getTime(Section));

            Announcements.add(Obj);
        });
    }
    public void StartTimers(){
            Timer timer = new Timer(this);
            timer.StartTimer();
            this.getServer().getLogger().log(Level.INFO, "Starting Timers");
    }
    public String getTitle(String Section){
        return AConfig.getConfig().getString("Announcements." + Section);
    }
    public List<String> getAnnouncement(String Section){
        List<String> TempList = new ArrayList<>();
        for(String Temp : AConfig.getConfig().getStringList("Announcements." + Section + ".Announcement")){
            Temp = Temp.replace('&', '§');
            Temp = ChatColor.translateAlternateColorCodes('§', Temp);
            TempList.add(Temp);
        }
        return TempList;
    }
    public String getHoverMessage(String Section){
        String Temp = AConfig.getConfig().getString("Announcements." + Section + ".HoverMessage");
        Temp = Temp.replace('&', '§');
        Temp = ChatColor.translateAlternateColorCodes('§', Temp);
        return Temp;
    }
    public String getActionType(String Section){
        return AConfig.getConfig().getString("Announcements." + Section + ".ActionType");
    }
    public String getAction(String Section){
        return AConfig.getConfig().getString("Announcements." + Section + ".Action");
    }
    public int getTime(String Section){
        return AConfig.getConfig().getInt("Announcements." + Section + ".Time");
    }
    public AnnouncementConfig getAnnouncements(){
        return AConfig;
    }
}
