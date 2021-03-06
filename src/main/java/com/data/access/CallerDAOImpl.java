package com.data.access;

import java.util.List;

import redis.clients.jedis.Jedis;

import com.wepage.info.WebPageInfo;

public class CallerDAOImpl implements CallerDAO {

	Jedis jedis = null;

	// TODO : Move to a configuration
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
		List<String> tags = info.getTags();
		for (String t : tags) {
			if (jedis.exists(t)) {
				String value = jedis.get(t);
				if (value.contains(info.getUrl())) {
					return;
				}
			}
			jedis.append(t, " " + info.getUrl());

		}
		removeConnection();
		return;
	}

}
