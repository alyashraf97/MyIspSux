package com.example.myispsux;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class TraceRoute {

    // Attributes
    private List<String> hopIPs; // List of IP addresses of the hops
    private List<Double> hopRTTs; // List of round-trip times of the hops in milliseconds
    private int maxHops; // Maximum number of hops to try

    // Constructor
    public TraceRoute(int maxHops) {
        this.maxHops = maxHops;
        this.hopIPs = new ArrayList<>();
        this.hopRTTs = new ArrayList<>();
    }

    // Method to calculate the route statistics
    public void calculateRouteStats(String destination) {
        try {
            // Get the destination IP address
            InetAddress destIP = InetAddress.getByName(destination);

            // Initialize the current hop IP and TTL
            InetAddress currIP = null;
            int ttl = 1;

            // Loop until reaching the destination or exceeding the maximum hops
            while (!destIP.equals(currIP) && ttl <= maxHops) {

                // Get the current IP address
                currIP = InetAddress.getByName(destination);

                // Record the start time of the ping
                long startTime = System.currentTimeMillis();

                // Ping the destination with the current TTL
                currIP.isReachable(null, ttl, 5000);
                // Record the end time of the ping
                long endTime = System.currentTimeMillis();


                // Calculate the round-trip time in milliseconds
                double rtt = (endTime - startTime);

                // Add the current hop IP and RTT to the lists
                hopIPs.add(currIP.getHostAddress());
                hopRTTs.add(rtt);

                // Increment the TTL
                ttl++;
            }

        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + destination);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Getters for the attributes
    public List<String> getHopIPs() {
        return hopIPs;
    }

    public List<Double> getHopRTTs() {
        return hopRTTs;
    }

    public int getMaxHops() {
        return maxHops;
    }
}
