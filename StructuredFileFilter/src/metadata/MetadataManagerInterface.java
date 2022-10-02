package metadata;

import java.io.File;
import java.util.Map;


public interface MetadataManagerInterface {

	
	public abstract Map<String, Integer> getFieldPositions();

	
	public abstract File getDataFile();

	
	public abstract String getSeparator();

	
	public abstract String[] getColumnNames();

}