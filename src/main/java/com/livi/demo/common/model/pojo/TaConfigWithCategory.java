package com.livi.demo.common.model.pojo;

public class TaConfigWithCategory {
	private String configName;
	private String key;
	private Integer value;

	public TaConfigWithCategory(String configName, String key, Integer value) {
		super();
		this.configName = configName;
		this.key = key;
		this.value = value;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
