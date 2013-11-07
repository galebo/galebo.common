package com.galebo.common;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class UtilsOS {

	public static String sIP = ""; 
	public static Map<String,String> sIPMap = new TreeMap<String,String>();
	
	/**
	 * 判断当前操作是否Windows.
	 * 
	 * @return true---是Windows操作系统
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");

		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 获取本机ip地址，并自动区分Windows还是linux操作系统
	 * 
	 * @return String
	 */
	public static String getLocalIP() {
		if (sIP != null && sIP.length() > 0) return sIP;

		InetAddress ip = null;
		try {
			if (isWindowsOS()) {// 如果是Windows操作系统
				ip = InetAddress.getLocalHost();
			} else {// 如果是Linux操作系统
				boolean bFindIP = false;
				Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
				while (netInterfaces.hasMoreElements()) {
					if (bFindIP) {
						break;
					}
					NetworkInterface ni = netInterfaces.nextElement();
					// ----------特定情况，可以考虑用ni.getName判断
					// 遍历所有ip
					Enumeration<InetAddress> ips = ni.getInetAddresses();
					while (ips.hasMoreElements()) {
						ip = ips.nextElement();
						if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址
								&& ip.getHostAddress().indexOf(":") == -1) {
							bFindIP = true;
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != ip) {
			sIP = ip.getHostAddress();
		}
		return sIP;
	}

	/**
	 * 获取本机ip所有的地址，并自动区分Windows还是linux操作系统
	 * 
	 * @return String
	 */
	public static Map<String,String> getLocalAllIps() {
		if (sIPMap != null && sIPMap.size() > 0) return sIPMap;
		
		InetAddress ip = null;
		try {
			if (isWindowsOS()) {// 如果是Windows操作系统
				ip = InetAddress.getLocalHost();
				sIPMap.put(ip.getHostAddress(), ip.getHostName());
			} else {// 如果是Linux操作系统
				Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
				while (netInterfaces.hasMoreElements()) {
					NetworkInterface ni = netInterfaces.nextElement();
					Enumeration<InetAddress> ips = ni.getInetAddresses();

					while (ips.hasMoreElements()) {
						ip = ips.nextElement();
				
						if (!ip.isLoopbackAddress() // 127.开头的都是lookback地址
								&& ip.getHostAddress().indexOf(":") == -1) {
							sIPMap.put(ip.getHostAddress(), ni.getName());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sIPMap;
	}

	public static Map<String,String> getAllLocalIP() {
		Map<String,String> ar = new HashMap<String,String>();
		
		try {
			// 如果是Windows操作系统
			if (isWindowsOS()) {
				InetAddress ip = InetAddress.getLocalHost();
				ar.put(ip.getHostAddress(), ip.toString());
			}else{
				Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
				while (netInterfaces.hasMoreElements()) {
					NetworkInterface ni = netInterfaces.nextElement();
					InetAddress ip = (InetAddress) ni.getInetAddresses().nextElement();
					/*if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {
						System.out.println("Interface " + ni.getName()
								+ " seems to be InternetInterface. I'll take it...");
						;
					} else {
						ar.add(ip.getHostAddress());
					}*/
					ar.put(ip.getHostAddress(), ni.getName());
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("本机IP："+ar);
		return ar;
	}


	
	public static void main(String[] args) {
		String serverIP = UtilsOS.getLocalIP();
		System.out.println("serverIP:" + serverIP);
		
		Map<String,String> map = UtilsOS.getAllLocalIP();
		Set<String> set = map.keySet();
		for(String ip :set)
		System.out.println("getAllLocalIP --- ip:" + ip);
	}
}
