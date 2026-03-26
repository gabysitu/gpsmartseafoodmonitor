/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsys.gpsmartseafoodmonitor;

/**
 *
 * @author gport
 */

import java.io.IOException;
import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class SmartSeafoodServiceRegistration {
    private static JmDNS jmdns;

    // Register a service name, type, port and description
    public static void registerService(String serviceType, String serviceName, int port, String description) {
        //Start of Handling Exceptions - I saw this in Algorithm class
        try {
            // Create JmDNS instance using localhost
            if (jmdns == null) {
                jmdns = JmDNS.create(InetAddress.getLocalHost());
                System.out.println("JmDNS started for service registration.");
            }

            // Create service info object
            ServiceInfo serviceInfo = ServiceInfo.create(
                    serviceType,   // e.g. "_oceanmonitoring._tcp.local."
                    serviceName,   // e.g. "OceanMonitoringService"
                    port,          // e.g. 50051
                    description    // human-readable description
            );

            // Register service with JmDNS
            jmdns.registerService(serviceInfo);

            // Expected: message confirming successful registration
            System.out.println("Registered service:");
            System.out.println("Type: " + serviceType);
            System.out.println("Name: " + serviceName);
            System.out.println("Port: " + port);
            System.out.println("Description: " + description);

        } catch (IOException e) {
            System.err.println("Failed to register service: " + serviceName);
            e.printStackTrace();
        }
    }
}
    

