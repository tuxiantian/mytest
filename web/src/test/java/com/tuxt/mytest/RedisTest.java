package com.tuxt.mytest;

import redis.clients.jedis.Jedis;

public class RedisTest {
    public static void main(String[] args) {
        // Redis 服务器的地址和端口
        String host = "192.168.0.125";
        int port = 3379;
        // Redis 密码，如果没有密码可以设置为 null
        String password = "iVnc5Ntxpi2H0hxicpSx";

        // 创建 Jedis 实例
        try (Jedis jedis = new Jedis(host, port)) {
            // 如果设置了密码，则进行认证
            if (password != null && !password.isEmpty()) {
                jedis.auth(password);
            }

            // 测试 PING 命令，检查连接是否成功
            String pong = jedis.ping();
            if ("PONG".equalsIgnoreCase(pong)) {
                System.out.println("Connected to Redis successfully!");
            } else {
                System.out.println("Failed to connect to Redis.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while trying to connect to Redis: " + e.getMessage());
        }
    }
}
