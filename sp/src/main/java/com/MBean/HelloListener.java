package com.MBean;

import javax.management.Notification;
import javax.management.NotificationListener;

public class HelloListener implements NotificationListener{

    public void handleNotification(Notification notification, Object handback) {
         if(handback instanceof Hello){
             Hello h =   (Hello) handback;
             h.printHello(notification.getMessage());
         }
    }

}
