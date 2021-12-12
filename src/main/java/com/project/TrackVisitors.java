
package com.project;

class TrackVisitors {

	private static String ipaddr = "";

	public static void trackMe(String ip) {
		ipaddr = ip;
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
							"{\"location\":\"projprog\",\"ip\":\"" + ipaddr + "\"}");
				} catch (Exception e) {

				}

			}
		};
		Thread track = new Thread(tracker);
		track.start();

	}
}