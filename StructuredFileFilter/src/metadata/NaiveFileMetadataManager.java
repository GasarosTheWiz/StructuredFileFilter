package metadata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class NaiveFileMetadataManager implements MetadataManagerInterface{
	private String alias;
	private File file;
	private String separator;

	public NaiveFileMetadataManager(String pAlias, File pFile, String pSeparator) {
		this.alias = pAlias;		
		this.file = pFile;
		if (this.file.exists() == false)		
		     throw new NullPointerException();
		this.file = pFile;
		this.separator = pSeparator;
	}
	
	
	@Override
	public Map<String, Integer> getFieldPositions() {
		Map<String, Integer> temp = new HashMap<String, Integer>();
		String[] fields = getColumnNames();
		
		for (int i = 0; i < fields.length; i++) {
			temp.put(fields[i], i);
		}
		
		return temp;
	}

	@Override
	public File getDataFile() {
		return file;
	}

	@Override
	public String getSeparator() {
		return separator;
	}

	@Override
	public String[] getColumnNames() {
		String [] fields = null;
	    String line;
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	         if((line = br.readLine()) != null){
	            		fields = line.split(separator);
	          }
	     } catch (Exception e){
	            System.out.println(e);
	     }
		return fields;
	}

	public String getAlias() {
		return alias;
	}
}
