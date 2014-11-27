package com.data.access;

import redis.clients.jedis.Jedis;

import com.wepage.info.WebPageInfo;

public class CallerDAOImpl implements CallerDAO {

	Jedis jedis = null;

	private void getConnection() {
		jedis = new Jedis("localhost");
	}

	private void removeConnection() {
		jedis.close();
		System.out.println("Done");
	}

	public void write(WebPageInfo info) {
		if (jedis == null) 
				getConnection();
		jedis.set(info.getUrl(),info.getTags());
	}

}
