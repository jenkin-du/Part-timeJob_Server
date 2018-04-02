package com.stitp.util;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.stitp.model.Datagram;

/**
 * 数据包解析器
 * Created by Administrator on 2016/8/26.
 */
public class DatagramParser {

    /**
     * 装包
     */
    public static<T> String toJsonDatagram(String request, T t){

        Datagram datagram=new Datagram();
        datagram.setRequest(request);
        datagram.setJsonStream(JSONParser.toJSONString(t));

        return JSONParser.toJSONString(datagram);
    }

    /**
     * 从json格式的数据包流中获取Javabean对象
     */

    public static<T> T getEntity(String jsonDatagram,Type type){

    	Datagram datagram=JSONParser.toJavaBean(jsonDatagram, new TypeToken< Datagram>(){}.getType());
    	String jsonStream=datagram.getJsonStream();
    	
        T t=JSONParser.toJavaBean(jsonStream,type);

        
        return t;
    }

    /**
     * 从json格式的数据包流中获取请求
     * @param jsonDatagram
     * @return
     */
    public static String getRequest(String jsonDatagram){

    	Datagram datagram=JSONParser.toJavaBean(jsonDatagram, new TypeToken<Datagram>(){}.getType());
    	String request=datagram.getRequest();

        return request;
    }
}
