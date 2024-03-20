import java.io.*;


public class dataInput {
	private int n;
	private int[][] matrix;
	private File file;
	dataInput(File file) {
		this.file = file;
	}
	
	void SetMatrix() throws IOException{ 
		FileReader filereader = new FileReader(this.file);
		BufferedReader bufferedreader = new BufferedReader(filereader);
		int n = Integer.parseInt(bufferedreader.readLine());
		this.n = n;
		String line;
		int[][] matrix = new int[n][n];
		for(int i  = 0; i < n; i++) {
			line = bufferedreader.readLine();
			String [] matrixstrline = line.split(" ");
			for(int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(matrixstrline[j]);
			}
		}
		this.matrix = matrix;
		bufferedreader.close();
	}
	
	
	void printMatrix(int [][] matrix) { 
		System.out.println();
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				System.out.printf("%" + 2 + "d ", matrix[i][j]);
				if (j == matrix.length - 1) {
					System.out.println();
				}
			}
		}
	}
	int getN() { 
		return n;
	}
	int [][] getMatrix() { 
		return matrix;
	}
}