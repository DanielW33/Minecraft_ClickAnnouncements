package me.MuchDan.StrellaMCAnnouncer.Util;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementObj {
    private String Title;
    private List<String> Announcement;
    private String HoverMessage;
    private String ActionType;
    private String Action;
    private int Time;

    public AnnouncementObj(){
        Title = "";
        Announcement = new ArrayList<>();
        HoverMessage = "";
        ActionType = "";
        Action = "";
        Time = 0;
    }
    public void setTitle(String Title){
        this.Title = Title;
    }
    public String getTitle(){
        return Title;
    }
    public void setAnnouncement(List<String> Announcement){
        this.Announcement = Announcement;
    }
    public List<String> getAnnouncement(){
        return Announcement;
    }
    public void setHoverMessage(String HoverMessage){
        this.HoverMessage = HoverMessage;
    }
    public String getHoverMessage(){
        return HoverMessage;
    }
    public void setActionType(String ActionType){
        this.ActionType = ActionType;
    }
    public String getActionType(){
        return ActionType;
    }
    public void setAction(String Action){
        this.Action = Action;
    }
    public String getAction(){
        return Action;
    }
    public void setTime(int Time){
        this.Time = Time;
    }
    public int getTime(){
        return Time;
    }

}
