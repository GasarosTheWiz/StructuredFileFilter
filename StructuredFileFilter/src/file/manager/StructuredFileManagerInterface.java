package file.manager;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;


public interface StructuredFileManagerInterface {
	
	
	
	
	public File registerFile(String pAlias, String pPath, String pSeparator) throws IOException, NullPointerException;
	
	
	public String[] getFileColumnNames(String pAlias);

	
	public List<String[]> filterStructuredFile(String pAlias, Map<String, List<String>> pAtomicFilters);

	
	public int printResultsToPrintStream(List<String[]> recordList, PrintStream pOut);

}