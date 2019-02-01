package com.binarycube.buspayment.util;

import java.util.List;

/**
 * Representation of result set from datastore
 * @author chris
 *
 * @param <K>
 */
 
public class ResultList<K> {

	public String cursor;
	public List<K> result;

	public ResultList(List<K> result, String cursor) {
		this.result = result;
		this.cursor = cursor;
	}

	public ResultList(List<K> result) {
		this.result = result;
		this.cursor = null;
	}
}
