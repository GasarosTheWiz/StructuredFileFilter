package file.manager;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import filtering.FilteringEngine;
import filtering.FilteringEngineInterface;
import metadata.NaiveFileMetadataManager;

public class StructuredFileManager implements StructuredFileManagerInterface {
	private ArrayList<NaiveFileMetadataManager> files = new ArrayList<NaiveFileMetadataManager>();

	@Override
	public File registerFile(String pAlias, String pPath, String pSeparator) {
		File file = new File(pPath);
		
		NaiveFileMetadataManager newMetadataManagerInterface = new NaiveFileMetadataManager(pAlias, file, pSeparator);
		
		files.add(newMetadataManagerInterface);
		
		System.out.println("Registered alias:");
		for (int i = 0; i < files.size(); i++) {
			System.out.println((i+1) + ". " + files.get(i).getAlias() + "\n");
		}
		
		return file;
	}

	@Override
	public String[] getFileColumnNames(String pAlias) {
		String[] temp = new String[0];
		for (NaiveFileMetadataManager r:files) {
			if (r.getAlias().equals(pAlias)) {
				return r.getColumnNames();
			}
		}
		return temp;
	}

	@Override
	public List<String[]> filterStructuredFile(String pAlias, Map<String, List<String>> pAtomicFilters) {
		NaiveFileMetadataManager a = null;
		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).getAlias().equals(pAlias)) {
				a = files.get(i);
			}
		}
		
		FilteringEngineInterface newFilteringEngine = new FilteringEngine(pAtomicFilters, a);
		
		List<String[]> returnLines = newFilteringEngine.workWithFile();
		
		return returnLines;
	}

	@Override
	public int printResultsToPrintStream(List<String[]> recordList, PrintStream pOut) {
		String text = "";
		for (int i=0; i<recordList.size(); i++) {
			String[] temp = recordList.get(i);
			for (int j = 0; j<temp.length; j++) {
				if (j==0)
					text = text + temp[j];
				else
					text = text + " " + temp[j];
			}
			if (i < recordList.size() - 1)
				text = text + "\n";
		}
		System.out.println(text);
		pOut.print(text);

		return recordList.size();
	}
	
	public ArrayList<String> getFilesAlias() {
		ArrayList<String> fil = new ArrayList<String>();
		
		for (NaiveFileMetadataManager r:files) {
			fil.add(r.getAlias());
		}
		
		return fil;
	}
}
