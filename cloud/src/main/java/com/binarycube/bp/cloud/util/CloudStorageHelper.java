package com.binarycube.bp.cloud.util;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.channels.Channels;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItemStream;

import com.google.api.gax.paging.Page;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobListOption;
import com.google.cloud.storage.StorageOptions;
 

 
public class CloudStorageHelper {

  private static Storage storage = null;

 
  static {
    storage = StorageOptions.getDefaultInstance().getService();
  }
  
  /**
   * Uploads a file to Google Cloud Storage to the bucket specified in the BUCKET_NAME
   * environment variable, appending a timestamp to end of the uploaded filename.
   */
  @SuppressWarnings("deprecation")
  public String uploadFile(FileItemStream fileStream, final String bucketName)
      throws IOException, ServletException {
    checkFileExtension(fileStream.getName());

    ZonedDateTime date = ZonedDateTime.now();
 
    String dtString = DateTimeFormatter.ofPattern("-YYYY-MM-dd-HHmmssSSS").format(date);
    final String fileName = fileStream.getName() + dtString;

    // the inputstream is closed by default, so we don't need to close it here
    BlobInfo blobInfo =
        storage.create(
            BlobInfo
                .newBuilder(bucketName, fileName)
                // Modify access list to allow all users with link to read file
                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
                .build(),
            fileStream.openStream());
    // return the public download link
    return blobInfo.getMediaLink();
  }
   

  /**
   * Checks that the file extension is supported.
   */
  private void checkFileExtension(String fileName) throws ServletException {
    if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
      String[] allowedExt = {".csv"};
      for (String ext : allowedExt) {
        if (fileName.endsWith(ext)) {
          return;
        }
      }
      throw new ServletException("File must be a CSV.");
    }
  }
 
  
  /**
   * Get a reader for the file from Cloud Storage
   */
  public Reader getFileReader(final String filename, final String bucketName) {
	  Reader rdr = null;
	  Blob blob = storage.get(BlobId.of(bucketName, filename));
	  ReadChannel rc =blob.reader();
	  InputStream inputStream =  Channels.newInputStream(rc);
	  rdr = new InputStreamReader(inputStream);
	  return rdr;
  }
  
  
  /**
   * Get a reader for the file from Cloud Storage
   */
  public boolean deleteFile(final String filename, final String bucketName) {
	  return storage.delete(BlobId.of(bucketName, filename));
  }
  
  
  
  
  /** 
   * List all files in a bucket
   * 
   * @param bucketName
   * @return
   */
  public List<Map<String,Object>> getFiles( final String bucketName){
	  List<Map<String,Object>> files = new ArrayList<Map<String,Object>>();
	  
	  Page<Blob> blobs = storage.list(bucketName, BlobListOption.currentDirectory());  
	  
	 
	  for (Blob blob : blobs.iterateAll()) {
		 Map<String, Object> fileinfo = new HashMap<String,Object>();
		 fileinfo.put("name", blob.getName());
		 fileinfo.put("size", blob.getSize());
		 long dt = blob.getCreateTime();
		 Date date = new Date(dt);
		 fileinfo.put("created", date.toString());
		 
	    files.add(fileinfo);
	  }
	  return files;
  }
  
}
 