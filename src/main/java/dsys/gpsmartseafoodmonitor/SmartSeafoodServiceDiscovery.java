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
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

public class SmartSeafoodServiceDiscovery {
    private static JmDNS jmdns;
    private static ServiceInfo discoveredServiceInfo;

    // Discover a service by its type
    public static ServiceInfo discoverService(String serviceType) {
        //Start of Handling Exceptions - I saw this in Algorithm class
        try {
            // Create JmDNS instance if not already created
            if (jmdns == null) {
                jmdns = JmDNS.create(InetAddress.getLocalHost());
                System.out.println("JmDNS started for service discovery.");
            }

            // Add a listener for the given service type
            jmdns.addServiceListener(serviceType, new ServiceListener() {

                @Override
                public void serviceAdded(ServiceEvent event) {
                    // Expected: triggered when a new service of this type appears
                    System.out.println("Service added: " + event.getInfo());

                    // Request full resolution of the service
                    jmdns.requestServiceInfo(event.getType(), event.getName(), 1000);
                }

                @Override
                public void serviceRemoved(ServiceEvent event) {
                    // Expected: triggered when service goes offline
                    System.out.println("Service removed: " + event.getInfo());
                }

                @Override
                public void serviceResolved(ServiceEvent event) {
                    // Expected: triggered when service details are fully resolved
                    System.out.println("Service resolved: " + event.getInfo());
                    discoveredServiceInfo = event.getInfo();
                }
            });

            // Wait a little so the listener can discover the service
            Thread.sleep(2000);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return discoveredServiceInfo;
    }
}
    

