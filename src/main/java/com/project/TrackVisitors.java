
package com.project;

class TrackVisitors{

    private static String ipaddr= ""; 

    public static void trackMe(String ip){
        ipaddr = ip;
        Runnable tracker = new Runnable() {
			@Override
			public void run() {
				API api = new API();
                System.out.println(api.postData("https://appsolutions.pythonanywhere.com/api/v12/data/projectprogramming/visitors", "{\"ip\":\""+ipaddr+"\"}"));
                System.out.println(ipaddr);
			}
		};
		Thread track = new Thread(tracker);
		track.start();
        
    }
}