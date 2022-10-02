package filtering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import metadata.MetadataManagerInterface;
import metadata.NaiveFileMetadataManager;

public class FilteringEngine implements FilteringEngineInterface{
	private Map<String, List<String>> atomicFilters;
	private MetadataManagerInterface metadataManager;
	private ArrayList<String[]> lines;

	public FilteringEngine(Map<String, List<String>> atomicFilters2, NaiveFileMetadataManager metadataManager2) {
		this.atomicFilters = atomicFilters2;
		this.metadataManager = metadataManager2;
		lines = new ArrayList<String[]>();
	}

	public List<String[]> workWithFile() {
		String [] lineArray = null;
		List<String[]> filterLine = new ArrayList<String[]>();
		
		int firstLine = 0;
	    String line;
	    
	    try (BufferedReader br = new BufferedReader(new FileReader(metadataManager.getDataFile()))) {
	         while((line = br.readLine()) != null){
	            	if (firstLine > 0) {
	            		lineArray = line.split(metadataManager.getSeparator());
	            		lines.add(lineArray);
	            	}
	            	firstLine = 1;
	          }
	     } catch (Exception e){
	            System.out.println(e);
	     }
	    	
		for (String name: atomicFilters.keySet()) {
				String key = name.toString();
				List<String> value = atomicFilters.get(name);
				filterLine.addAll(getLinesByFilter(key, value));
		}
			
	    return filterLine;
	}
	
	public List<String[]> getLinesByFilter(String filter, List<String> values) {
		String[] fields = metadataManager.getColumnNames();

		List<String[]> lines = new ArrayList<String[]>();
		int pos = 0, flag = 0; 
		
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].equals(filter)) {
				pos = i;
				flag = 1;
			}
		}
		
		if (flag == 1) {
			for (String val:values) {
				List<String[]> temp = new ArrayList<>();
				temp = getLineByValue(val, pos);
				
				for (int i = 0; i < temp.size(); i++) {
					lines.add(temp.get(i));
				}
			}
		}
		return lines;
	}
	
	public List<String[]> getLineByValue(String filter, int pos) {
		List <String[]> lines2 = new ArrayList<String[]>();
		
		for (int i = 0; i < lines.size(); i++) {
			if ((lines.get(i))[pos].equals(filter)) {
				lines2.add(lines.get(i));
			}
		}
		
		return lines2;
	}

	@Override
	public int setupFilteringEngine(Map<String, List<String>> pAtomicFilters,
			MetadataManagerInterface pMetadataManager) {
				this.atomicFilters = pAtomicFilters;
				this.metadataManager = (NaiveFileMetadataManager) pMetadataManager;
		return 1;
	}

}
