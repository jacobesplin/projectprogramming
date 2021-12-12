
package com.project;

class TrackVisitors {

	private static String ipaddr = "";
	private static String pageVisited = "";

	public static void trackMe(String ip, String page) {
		ipaddr = ip;
		pageVisited = page;
		Runnable tracker = new Runnable() {
			@Override
			public void run() {
				try {
					API api = new API();
					api.postData("https://appsolutions.pythonanywhere.com/api/v12/data/projectprogramming/visitors",
							"{\"location\":\"projprog\",\"ip\":\"" + ipaddr + "\"}");
				} catch (Exception e2) {

				}
				try {
					APISelfSigned api_self = new APISelfSigned();
					api_self.sendDataToServer("https://jacobsmuzik.ddns.net/ReactSpringBoot/api/v12/data/collection",
							"{\"location\":\"projprog\",\"ip\":\"" + ipaddr + "\",\"page\":\"" + pageVisited + "\"}");
				} catch (Exception e) {

				}

			}
		};
		Thread track = new Thread(tracker);
		track.start();

	}
}