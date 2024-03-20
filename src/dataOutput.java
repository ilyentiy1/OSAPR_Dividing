import java.io.*;
public class dataOutput {
	
	private File file;
	dataOutput(File file) {
		this.file = file;
	}
	void output(String str) throws IOException { 
		FileWriter filewriter = new FileWriter(this.file, true);
		BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
		bufferedwriter.append(str);
		bufferedwriter.newLine();
		bufferedwriter.flush();
		bufferedwriter.close();
		
		
	}
	static void outputClose(BufferedWriter bufferedwriter) throws IOException { 
		bufferedwriter.flush();
		bufferedwriter.close();
	}
	static void deletetext(File file) throws IOException { 
		FileWriter filewriter = new FileWriter(file);
		filewriter.write("");
		filewriter.flush();
		filewriter.close();
	}
}
