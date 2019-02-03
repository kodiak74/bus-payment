package com.binarycube.bp.cloud.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultIterator;
 
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DatastoreHelper {

	private DatastoreService _datastore = null;
	private String _kind = null;

	public DatastoreHelper(String kind) {
		_kind = kind;
		_datastore = DatastoreServiceFactory.getDatastoreService(); // Authorized Datastore service
	}

	public <T> Object entityToObject(Class<T> clazz, Entity entity) {
		Map<String, Object> props = entity.getProperties();
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(props);
		return gson.fromJson(json, clazz);
	}

	public void create(String id, Map<String, Object> data) {
		Entity entity = new Entity(_kind,id);
		for (String key : data.keySet()) {
			entity.setProperty(key, data.get(key));
		}
		_datastore.put(entity);
	}

	public void update(UUID uid, Map<String, Object> data) {
		Entity entity = new Entity(_kind, uid.toString());
		for (String key : data.keySet()) {
			entity.setProperty(key, data.get(key));
		}
		_datastore.put(entity);
	}

	public <T> Object read(Class<T> clazz, UUID uid) {
		try {
			Entity entity = _datastore.get(KeyFactory.createKey(_kind, uid.toString()));
			return entityToObject(clazz, entity);
		} catch (EntityNotFoundException e) {
			return null;
		}

	}

	public void delete(UUID uid) {
		Key key = KeyFactory.createKey(_kind, uid.toString()); // Create the Key
		_datastore.delete(key); // Delete the Entity
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> entitiesToObjects(Class<T> clazz, Iterator<Entity> results) {
	    List<T> result = new ArrayList<>();
	    while (results.hasNext()) {  // We still have data
	    	result.add((T) entityToObject(clazz, results.next()));      // Add the Book to the List
	    }
	    return result;
	  }

	public <T> ResultList<T> list(Class<T> clazz, String startCursorString, String sortby, String filter, String value) {
	    FetchOptions fetchOptions = FetchOptions.Builder.withLimit(10); // Only show 10 at a time
	    if (startCursorString != null && !startCursorString.equals("")) {
	      fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
	    }
	    Query query = new Query(_kind); 
	    if (sortby != null) query.addSort(sortby, SortDirection.ASCENDING);  
	    
	    if (filter != null) {
	    	query.setFilter(new Query.FilterPredicate(filter, Query.FilterOperator.EQUAL, value));
	    }
	    
	    PreparedQuery preparedQuery = _datastore.prepare(query);
	    QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);

	    List<T> resultObjects = entitiesToObjects(clazz, results);     // Retrieve and convert Entities
	    Cursor cursor = results.getCursor();              			   // Where to start next time
	    if (cursor != null && resultObjects.size() == 10) {           // Are we paging? Save Cursor
	      String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
	      return new ResultList<>(resultObjects, cursorString);
	    } else {
	      return new ResultList<>(resultObjects);
	    }
	  }
	
	
}
