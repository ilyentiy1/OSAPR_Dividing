import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		boolean flag = false;
		int genCount; 
		File file, file2; 
		int[][] matrix; 
		int numOperation, n1, n2; 
		int n = 0; 
		String fileName, fileName2 = ""; 
		String word; 
		while (flag == false) { 
			showMenu.showList(); 
			numOperation = Integer.parseInt(sc.nextLine());
			if (numOperation == 1) {
				System.out.println("Введите название текстового файла:");
				fileName = sc.nextLine();
				file = new File(fileName);
				dataInput graf = new dataInput(file);
				graf.SetMatrix();
				n = graf.getN();
				matrix = graf.getMatrix();
				graf.printMatrix(matrix);
				System.out.println("Степень графа = " + n);
				System.out.println("Исполнить алгоритм?");
				word = sc.nextLine();
				if (word.equals("Да") || word.equals("да")) {
					System.out.println("Введите количество поколений:");
					genCount = Integer.parseInt(sc.nextLine());
					n1 = (int) Math.floor(n / 2);
					n2 = n - n1;
					if (fileName.equals("graf1")) {
						fileName2 = "result1";
					}
					if (fileName.equals("graf2")) {
						fileName2 = "result2";
					}
					if (fileName.equals("graf3")) {
						fileName2 = "result3";
					}
					file2 = new File(fileName2);
					dataOutput.deletetext(file2);
					Algorythm.runAlg(genCount, n1, n2, n, matrix, file2);
				}
				if (word.equals("Нет") || word.equals("нет")) {
					System.out.println("Выберите номер операции заново");
					continue;
				}
				continue;
			}
			if (numOperation == 2) {
				flag = true;
			}
		}
	}

}
