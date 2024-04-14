package hbv.example.dao;

import org.json.JSONObject;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RedisToJson {

    private Jedis jedis;

//    public RedisToJson(String host, int port, String password) {
//        this.jedis = new Jedis(host,port);
//    }


    public JSONObject getAllKeysAndValuesAsJson() {
         jedis = new Jedis("localhost", 6379);
        jedis.auth("LO6fWt1BGJ7bdxrDFHzw");
        Set<String> allKeys = jedis.keys("*");
        Map<String, String> keyValues = new HashMap<>();

        for (String key : allKeys) {
            String value = jedis.get(key);
            keyValues.put(key, value);
        }

        // Add status property to indicate success
        keyValues.put("status", "200");

        // Convert the map to a JSONObject and return
        return new JSONObject(keyValues);
    }

    public void closeConnection() {
        jedis.close();
    }
        public static void main(String[] args) {
//            RedisToJson converter = new RedisToJson("localhost", 6379);
//            JSONObject jsonObject = converter.getAllKeysAndValuesAsJson();
//            System.out.println(jsonObject.toString());
//            converter.closeConnection();
        }
    }
