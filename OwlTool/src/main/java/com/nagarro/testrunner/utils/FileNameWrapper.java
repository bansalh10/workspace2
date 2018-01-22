/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.utils;

import org.springframework.stereotype.Component;

/**
 * 
 * @author anujmehra
 *
 */
@Component("fileNameWrapper")
public class FileNameWrapper {

	private String fileName;

	/**
	 * @return the name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param name the name to set
	 */
	public void setFileName(String name) {
		this.fileName = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileNameWrapper [name=");
		builder.append(fileName);
		builder.append("]");
		return builder.toString();
	}
	
	public boolean hadValidFileName(){
		return getFileName()!=null;
	}
}
