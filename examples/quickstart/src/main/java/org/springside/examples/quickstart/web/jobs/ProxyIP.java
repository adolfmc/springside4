package org.springside.examples.quickstart.web.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProxyIP {
	public static List<String[]> iplist = new ArrayList<String[]>();

	public ProxyIP() {
	}

	public static int getRandom(int min, int max) {
		Random random = new Random();
		return random.nextInt(max) % (max - min + 1) + min;
	}

	public static String[] getRandomProxyIP() {
		iplist.add(new String[] { "118.200.71.231", "8080" });
		iplist.add(new String[] { "222.170.17.74", "2226" });
		iplist.add(new String[] { "42.6.38.255", "8080" });
		iplist.add(new String[] { "58.21.179.88", "8118" });
		iplist.add(new String[] { "122.228.179.178", "80" });
		iplist.add(new String[] { "122.96.59.104", "80" });
		iplist.add(new String[] { "124.240.187.84", "83" });
		iplist.add(new String[] { "119.6.136.122", "80" });
		iplist.add(new String[] { "139.196.108.68", "80" });
		iplist.add(new String[] { "122.96.59.106", "80" });
		iplist.add(new String[] { "119.1.170.3", "80" });
		iplist.add(new String[] { "36.251.50.104", "8118" });
		iplist.add(new String[] { "221.0.10.242", "808" });
		iplist.add(new String[] { "218.30.99.209", "81" });
		iplist.add(new String[] { "171.35.36.245", "8118" });
		iplist.add(new String[] { "180.103.131.65", "808" });
		iplist.add(new String[] { "1.193.82.162", "8118" });
		iplist.add(new String[] { "60.169.78.218", "808" });
		iplist.add(new String[] { "124.88.67.20", "80" });
		iplist.add(new String[] { "123.57.52.171", "80" });
		iplist.add(new String[] { "182.39.197.79", "8080" });
		iplist.add(new String[] { "222.198.155.63", "3128" });
		iplist.add(new String[] { "58.58.188.36", "9999" });
		iplist.add(new String[] { "220.133.150.86", "3128" });
		iplist.add(new String[] { "59.34.2.92", "3128" });
		iplist.add(new String[] { "123.139.59.85", "9999" });
		iplist.add(new String[] { "27.46.49.17", "8888" });
		iplist.add(new String[] { "183.49.120.241", "9999" });
		iplist.add(new String[] { "180.152.115.73", "9797" });
		iplist.add(new String[] { "61.162.223.41", "9797" });
		iplist.add(new String[] { "183.19.47.109", "3128" });
		iplist.add(new String[] { "120.132.71.212", "80" });
		iplist.add(new String[] { "112.126.65.26", "12345" });
		iplist.add(new String[] { "120.25.163.76", "3128" });
		iplist.add(new String[] { "218.240.53.54", "81" });
		iplist.add(new String[] { "122.72.18.160", "80" });
		iplist.add(new String[] { "1.82.216.134", "80" });
		iplist.add(new String[] { "115.28.101.22", "3128" });
		iplist.add(new String[] { "114.215.150.13", "3128" });
		iplist.add(new String[] { "58.247.125.205", "80" });
		iplist.add(new String[] { "43.254.52.210", "808" });
		iplist.add(new String[] { "183.89.50.117", "8080" });
		iplist.add(new String[] { "180.183.60.16", "8080" });
		iplist.add(new String[] { "47.89.53.92", "3128" });
		iplist.add(new String[] { "141.85.220.108", "8080" });
		iplist.add(new String[] { "183.89.120.157", "8080" });
		iplist.add(new String[] { "192.241.171.252", "8080" });
		iplist.add(new String[] { "88.157.149.250", "8080" });
		iplist.add(new String[] { "180.183.143.183", "8080" });
		iplist.add(new String[] { "108.59.10.129", "55555" });
		iplist.add(new String[] { "176.31.96.198", "3128" });
		iplist.add(new String[] { "180.183.181.112", "8080" });
		iplist.add(new String[] { "183.89.226.249", "8080" });
		iplist.add(new String[] { "119.59.103.102", "3128" });
		iplist.add(new String[] { "43.241.209.231", "8118" });
		iplist.add(new String[] { "180.248.99.223", "8080" });
		iplist.add(new String[] { "183.89.74.21", "8080" });
		iplist.add(new String[] { "181.140.96.15", "8089" });
		iplist.add(new String[] { "54.251.169.247", "80" });
		iplist.add(new String[] { "89.184.66.45", "3128" });
		iplist.add(new String[] { "164.132.28.153", "3128" });
		iplist.add(new String[] { "164.132.28.157", "3128" });
		iplist.add(new String[] { "79.188.42.46", "8080" });
		iplist.add(new String[] { "64.137.181.83", "8888" });
		iplist.add(new String[] { "200.46.86.66", "3128" });
		iplist.add(new String[] { "111.90.179.42", "8080" });
		iplist.add(new String[] { "187.44.1.54", "8080" });
		iplist.add(new String[] { "217.61.1.237", "8090" });
		iplist.add(new String[] { "159.203.2.1", "8888" });
		iplist.add(new String[] { "94.177.230.192", "3128" });
		iplist.add(new String[] { "109.224.54.180", "8080" });
		iplist.add(new String[] { "200.61.216.248", "3128" });
		iplist.add(new String[] { "211.110.127.210", "3128" });
		iplist.add(new String[] { "36.85.181.202", "8080" });
		iplist.add(new String[] { "123.108.14.94", "80" });
		iplist.add(new String[] { "181.196.51.232", "8080" });
		iplist.add(new String[] { "200.68.27.100", "3128" });
		iplist.add(new String[] { "31.220.53.97", "3128" });
		iplist.add(new String[] { "118.172.0.197", "8080" });
		iplist.add(new String[] { "188.165.62.1", "3128" });

		return iplist.get(getRandom(0, 79));
	}

	public static void main(String[] args) {
		System.out.println(getRandomProxyIP()[0]);
	}

}
