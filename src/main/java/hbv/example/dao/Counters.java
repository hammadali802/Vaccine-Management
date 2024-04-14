package hbv.example.dao;

import redis.clients.jedis.Jedis;

import java.util.Map;

public class Counters {
    private static final String COUNTER_HASH_KEY = "center_counter";
    private static final String vacCOUNTER_HASH_KEY = "vaccine_counter";
    private Jedis jedis;

    public Counters() {
        // Create a new Jedis instance
        jedis = new Jedis("localhost", 6379);
        jedis.auth("LO6fWt1BGJ7bdxrDFHzw");
    }

    public void centerCounter(String centerName) {
        jedis.hincrBy(COUNTER_HASH_KEY, centerName, 1);
    }
    public void vacCounter(String vacName) {
        jedis.hincrBy(vacCOUNTER_HASH_KEY, vacName, 1);
    }

    public Map<String, String> getCenterCounters() {
        return jedis.hgetAll(COUNTER_HASH_KEY);
    }

    public void closeConnection() {
        jedis.close();
    }

    public void incrementPageViews(String key, String pageUrl) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.auth("LO6fWt1BGJ7bdxrDFHzw");

        // Increment the counter for the given page URL
        jedis.incr(key + pageUrl);

        jedis.close();

    }
        public int getPageViews (String pageUrl){
            Jedis jedis = new Jedis("localhost", 6379);

            String count = jedis.get("pageViews:" + pageUrl);
            jedis.close();

            return count != null ? Integer.parseInt(count) : 0;
        }

        public void redisCounter (String key){
            Jedis jedis = new Jedis("localhost", 6379);
            jedis.auth("LO6fWt1BGJ7bdxrDFHzw");
            // Increment the counter
            jedis.incr(key);

            // Retrieve and print the current count
            String count = jedis.get("appointmentDataHits");
            System.out.println("Current count: " + count);

            jedis.close();
        }
    public static void main(String[] args) {
        // Create a new instance of the CenterCounter class
        Counters centerCounter = new Counters();

        // Dummy data for testing
        String centerNames = "Center D";

        // Increment the counter for each center name

            centerCounter.centerCounter(centerNames);

//        Map<String, String> centerCounters = centerCounter.getCenterCounters();

        // Display the current counters for all centers
//        System.out.println("Current counters for all centers:");
//        for (Map.Entry<String, String> entry : centerCounters.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//
        // Close the connection to Redis
        centerCounter.closeConnection();
    }
}


