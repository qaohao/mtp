package com.allyes.mtp.utils.ip;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

import com.allyes.mtp.common.error.SystemException;

/**
 * @author qaohao
 */
public class IPHelper {
	public static final String UNKOWN = "00000000";
	public static final String CN_UNKOWN = "CN000000";
	private static IPHelper ipHelper = null;
	private UnionIPStore ipStore;
	
	IPHelper(URL url) throws IOException {
		ipStore = new UnionIPStore(url);
		ipStore.init();
	}

	/**
	 * 将ip转换成地区代号。
	 */
	public String toRegion(String ip) {
		String regionId = UNKOWN;
		try {
			regionId = ipStore.getIPInfo(ip).getRegionId();
			if (StringUtils.isBlank(regionId)) {
				regionId = UNKOWN;
			} else if (UNKOWN.equals(regionId)) {
				regionId = CN_UNKOWN;
			}
		} catch (Exception e) {
		}
		if (StringUtils.startsWithIgnoreCase(regionId, "CN")) {
			return regionId;
		} else {
			return UNKOWN;
		}
	}
	
	public static synchronized IPHelper getIPHelper() {
		if (ipHelper == null) {
			URL url = IPHelper.class.getClassLoader()
					.getResource("db/union.ipdb");
			try {
				ipHelper = new IPHelper(url);
			} catch (IOException e) {
				e.printStackTrace();
				throw new SystemException("fail to load ip table db.", e);
			}
		}
		return ipHelper;
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println(getIPHelper().toRegion("10.200.34.103"));
		System.out.print(System.currentTimeMillis()-start);
		System.out.println("ms");
	}
	
}
