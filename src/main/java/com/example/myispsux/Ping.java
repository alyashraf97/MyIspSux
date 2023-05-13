package com.example.myispsux;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ping {
    private double avgPing;
    private double minPing;
    private double maxPing;
    private double jitter;
    private double packetLoss;

    public Ping()
    {
    }

    public void testPing(String host)
    {
        try {
            // Get the InetAddress object for the host
            InetAddress inetAddress = InetAddress.getByName(host);

            // Perform the ping
            long[] pingTimes = new long[10];
            int numPingsReceived = 0;
            for (int i = 0; i < 10; i++) {
                long startTime = System.currentTimeMillis();
                if (inetAddress.isReachable(5000)) {
                    pingTimes[i] = System.currentTimeMillis() - startTime;
                    numPingsReceived++;
                }
            }

            // Calculate the ping statistics
            double sum = 0;
            for (int i = 0; i < numPingsReceived; i++) {
                sum += pingTimes[i];
            }
            avgPing = sum / numPingsReceived;
            minPing = Double.MAX_VALUE;
            maxPing = Double.MIN_VALUE;
            for (int i = 0; i < numPingsReceived; i++) {
                if (pingTimes[i] < minPing) {
                    minPing = pingTimes[i];
                }
                if (pingTimes[i] > maxPing) {
                    maxPing = pingTimes[i];
                }
            }
            jitter = calculateJitter(pingTimes, numPingsReceived);
            packetLoss = ((double) (10 - numPingsReceived) / 10) * 100;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double calculateJitter(long[] pingTimes, int numPingsReceived) {
        double sum = 0;
        for (int i = 0; i < numPingsReceived - 1; i++) {
            sum += Math.abs(pingTimes[i + 1] - pingTimes[i]);
        }
        return sum / (numPingsReceived - 1);
    }

    public double getAvgPing() {
        return avgPing;
    }

    public double getMinPing() {
        return minPing;
    }

    public double getMaxPing() {
        return maxPing;
    }

    public double getJitter() {
        return jitter;
    }

    public double getPacketLoss() {
        return packetLoss;
    }
}
