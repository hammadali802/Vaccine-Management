package hbv.example.dao;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test {
//    public void incrementPageViews(String pageUrl) {
//        Jedis jedis = new Jedis("localhost", 6379); // replace "localhost" with your Redis server address
//
//        // Increment the counter for the given page URL
//        jedis.incr("pageViews:" + pageUrl);
//
//        jedis.close();
//    }
//
//    public int getPageViews(String pageUrl) {
//        Jedis jedis = new Jedis("localhost",6379); // replace "localhost" with your Redis server address
//
//        // Retrieve and return the page views for the given page URL
//        String count = jedis.get("pageViews:" + pageUrl);
//        jedis.close();
//
//        return count != null ? Integer.parseInt(count) : 0;
//    }
//
//    public static void testRedisCounter(String key) {
//        Jedis jedis = new Jedis("localhost", 6379); // replace "localhost" with your Redis server address
//
//        // Increment the counter
//        jedis.incr(key);
//
//        // Retrieve and print the current count
//        String count = jedis.get("appointmentDataHits");
//        System.out.println("Current count: " + count);
//
//        jedis.close();
//    }
//
//    public static void main(String[] args) {
//        Test appointmentData = new Test();
//
//        // Test incrementing page views
//        appointmentData.incrementPageViews("/RegisterServlet");
//        System.out.println("Page views for /RegisterServlet: " + appointmentData.getPageViews("/RegisterServlet"));
//
//        // Test again to see the counter increment
//        appointmentData.incrementPageViews("/RegisterServlet");
//        System.out.println("Page views for /RegisterServlet: " + appointmentData.getPageViews("/RegisterServlet"));
//    }

    private static final String COUNTERS_KEY_PREFIX = "center_counter:";
    private Jedis jedis;
    private Gson gson;

    public Test() {
        // Create a new Jedis instance
        jedis = new Jedis("localhost", 6379);
        // Create a new Gson instance
        gson = new Gson();
    }

    public Set<String> getAllCounterKeys() {
        // Get all keys matching the counter key prefix
        return jedis.keys(COUNTERS_KEY_PREFIX + "*");
    }

    public String getCounterValue(String key) {
        // Get the value of the counter for the given key
        return jedis.get(key);
    }

    public void closeConnection() {
        // Close the connection to Redis
        jedis.close();
    }

    public static void main(String[] args) {
        AdminDao adminDao = new AdminDao();
        adminDao.saveTimeSlot( "09:00","11:00" ,6, 5);
    }

}
