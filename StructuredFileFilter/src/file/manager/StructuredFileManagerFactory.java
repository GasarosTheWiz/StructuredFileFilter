package file.manager;

public class StructuredFileManagerFactory {
	private StructuredFileManagerInterface newStructuredFileManager = new StructuredFileManager(); 
	
	public StructuredFileManagerInterface createStructuredFileManager() {
		return newStructuredFileManager;
	}
}