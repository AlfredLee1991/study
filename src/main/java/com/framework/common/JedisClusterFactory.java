package com.framework.common;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * Redis集群便捷初始化类<br>
 *
 * @author lixu
 */
public class JedisClusterFactory implements FactoryBean<JedisCluster> {

	private static final Pattern IP_PORT = Pattern.compile("^.+:\\d{1,5}$");

	private String servers;

	private JedisCluster jedisCluster;

	private GenericObjectPoolConfig genericObjectPoolConfig;

	@Override
	public JedisCluster getObject() throws Exception {
		return jedisCluster;
	}

	@Override
	public Class<? extends JedisCluster> getObjectType() {
		return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void init() {
		String[] strings = servers.split(",");
		Set<HostAndPort> haps = new HashSet<>();
		for (String string : strings) {
			if (!IP_PORT.matcher(string).matches()) {
				throw new IllegalArgumentException("redis servers format error!");
			}
			String[] ipAndPort = string.split(":");
			haps.add(new HostAndPort(ipAndPort[0].trim(), Integer.parseInt(ipAndPort[1].trim())));
		}
		jedisCluster = new JedisCluster(haps, genericObjectPoolConfig);
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public void setGenericObjectPoolConfig(GenericObjectPoolConfig genericObjectPoolConfig) {
		this.genericObjectPoolConfig = genericObjectPoolConfig;
	}

}
