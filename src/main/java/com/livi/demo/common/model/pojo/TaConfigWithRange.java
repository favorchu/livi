package com.livi.demo.common.model.pojo;

public class TaConfigWithRange {

	private String configName;
	private Integer form;
	private Integer to;
	private Integer value;

	public TaConfigWithRange(String configName, Integer form, Integer to, Integer value) {
		super();
		this.configName = configName;
		this.form = form;
		this.to = to;
		this.value = value;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public Integer getForm() {
		return form;
	}

	public void setForm(Integer form) {
		this.form = form;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
