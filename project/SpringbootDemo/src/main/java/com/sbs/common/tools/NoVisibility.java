package com.sbs.common.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhengbinMac
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while(!ready) {
                Thread.yield();
                System.out.println("vvv");
            }
            System.out.println(number);
        }
    }
    public static void main(String[] args) {
    	Timer time = new Timer();
        new ReaderThread().start();
        number = 42;
        ready = true;
        
        
        Map map = new HashMap();
         
        map.put("success", "true");
        map.put("photoList", "ccc");
        map.put("currentUser", "zhang");
        
        //net.sf.json.JSONObject 将Map转换为JSON方法
        String json = JSONObject.toJSONString(map);

        //org.json.JSONObject 将Map转换为JSON方法
        //JSONObject json =new JSONObject(map);
        System.out.println(json);
    }
}