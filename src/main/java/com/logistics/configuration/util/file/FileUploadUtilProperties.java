package com.logistics.configuration.util.file; 

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("uploadfile")
public class FileUploadUtilProperties {

	/**
	 * Folder location for storing files
	 */
	private Map<String, String> path;
	private Map<String, String> fileconfig;
	private Map<String, String> imgconfig;
	private Map<String, String> typeConfig;

	
	public Map<String, String> getPath() {
		return this.path;
	}
	
	public void setPath(Map<String, String> path) {
		this.path = path;
	}
	
	public Map<String, String> getImgConfig() {
		return this.imgconfig;
	}
	
	public void setImgConfig(Map<String, String> imgconfig) {
		this.imgconfig = imgconfig;
	}

	public Map<String, String> getTypeConfig() {
		return this.imgconfig;
	}
	
	public void setTypeConfig(Map<String, String> typeConfig) {
		this.typeConfig = typeConfig;
	}
	
	public Map<String, String> getFileConfig(){
		return this.fileconfig;
	}
	
	public void setFileConfig(Map<String, String> fileconfig) {
		this.fileconfig = fileconfig;
	}
} 