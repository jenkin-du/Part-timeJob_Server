package com.stitp.model;



/**
 * 聊天消息
 * Created by Administrator on 2016/8/24.
 */
public class ChatRecord  {

    private String myId;
    private String friendId;
    private String message;

    private String time;


    public ChatRecord() {
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


   


}
