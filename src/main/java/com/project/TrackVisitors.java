
package com.project;

class TrackVisitors{

    private static String ipaddr= ""; 

    public static void trackMe(String ip){
        ipaddr = ip;
        Runnable tracker = new Runnable() {
			@Override
			public void run() {
				API api = new API();
                api.postData("https://appsolutions.pythonanywhere.com/api/v12/data/projectprogramming/visitors", "{\"ip\":\""+ipaddr+"\"}");
				APISelfSigned api_self = new APISelfSigned();
    			System.out.println(api_self.sendDataToServer("https://jacobsmuzik.ddns.net/ReactSpringBoot/api/v12/data/collection",""));
                
			}
		};
		Thread track = new Thread(tracker);
		track.start();
        
    }
}