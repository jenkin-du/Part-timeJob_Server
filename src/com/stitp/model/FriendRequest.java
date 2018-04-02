package com.stitp.model;


/**
 * 好友申请
 * Created by Administrator on 2016/8/30.
 */
public class FriendRequest {


    private String myId;
    private String friendId;

    private String friendName;
    private String myName;

    private String requestReason;

    private String status;

    private String Time;

    public FriendRequest() {

    }


   

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
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

    public String getRequestReason() {
        return requestReason;
    }

    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "myId='" + myId + '\'' +
                ", friendId='" + friendId + '\'' +
                ", friendName='" + friendName + '\'' +
                ", myName='" + myName + '\'' +
                ", requestReason='" + requestReason + '\'' +
                ", status='" + status + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }



}
