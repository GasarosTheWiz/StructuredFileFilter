package filtering;

import java.util.List;
import java.util.Map;

import metadata.MetadataManagerInterface;


public interface FilteringEngineInterface {

	
	public int setupFilteringEngine(Map<String, List<String>> pAtomicFilters,
			MetadataManagerInterface pMetadataManager);

	
	public List<String[]> workWithFile();

}