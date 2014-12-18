package com.data.access;

import com.wepage.info.WebPageInfo;
import redis.clients.jedis.Jedis;

import java.util.List;

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
            jedis.append(t, " " + info.getUrl());
        }
        removeConnection();
    }

}
