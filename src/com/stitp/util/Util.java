package com.stitp.util;

import com.stitp.model.Datagram;

public class Util {

	/**
     * 将数据封装成数据包形式发送出去
     */
    
    public static Datagram PacketData(String request,String jsonStream){

        Datagram datagram=new Datagram();
        datagram.setJsonStream(jsonStream);
        datagram.setRequest(request);
        
        return datagram;
        
    }
}
