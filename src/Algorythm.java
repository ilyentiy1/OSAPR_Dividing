import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;


public class Algorythm {
	
	static void runAlg(int genCount, int n1, int n2, int n, int[][] matrix, File file) throws IOException {
		dataOutput result = new dataOutput(file);
		int[] parrent1 = new int[n];  
		int[] parrent2 = new int[n];  
		for(int i = 0; i < n; i++) {  
			parrent1[i] = 0;
			parrent2[i] = 0;
		}
		for(int i = 0; i < n1; i++) { 
			int numRandom1 = (int) Math.floor(Math.random() * n);
			if (parrent1[numRandom1] == 1) {
				i--;
				continue;
			}
			else {
				parrent1[numRandom1] = 1;
			}
		}
		for(int i = 0; i < n1; i++) { 
			int numRandom2 = (int) Math.floor(Math.random() * n);
			if (parrent2[numRandom2] == 1) {
				i--;
				continue;
			}
			else {
				parrent2[numRandom2] = 1;
			}
		}
		System.out.print("родитель 1: " + Arrays.toString(parrent1) + "; ");
		System.out.println("родитель 2: " + Arrays.toString(parrent2));
		result.output("родитель 1: " + Arrays.toString(parrent1));
		result.output("родитель 2" + Arrays.toString(parrent2));
		for(int g = 0; g < genCount; g++) { 
			
			int[] child1 = new int[n]; 
			int[] child2 = new int[n];  
			System.out.println((g+1) + "-е поколение:");
			int alpha = (int) Math.floor(Math.random() * (n - 1));  
			System.out.println((alpha + 1) + " - точка перекрещивания");
			for(int i = 0; i < n; i++) {  
				if (i <= alpha) {
					child1[i] = parrent1[i];
					child2[i] = parrent2[i];		
				}
				else {
					child1[i] = parrent2[i];
					child2[i] = parrent1[i];
				}
			}
			System.out.print("потомок 1: " + Arrays.toString(child1) + "; ");
			System.out.println("потомок 2: " + Arrays.toString(child2));
			int child1N1 = 0; 
			int child2N1 = 0; 
			for(int i = 0; i < child1.length; i++) {  
				if(child1[i] == 1) {
					child1N1++;
				}
				if(child2[i] == 1) {
					child2N1++;
				}
			}
			System.out.println(child1N1 + " - количество единиц в хромосоме первого потомка");
			System.out.println(child2N1 + " - количество единиц в хромосоме второго потомка");
			if(child1N1 != n1) {
				System.out.println("Количество единиц в хромосоме первого потомка не равно n1");
				child1 = randomMixing(child1, parrent1, parrent2, child1N1, n1, n2);
			}
			if(child2N1 != n1) {
				System.out.println("Количество единиц в хромосоме второго потомка не равно n1");
				child2 = randomMixing(child2, parrent1, parrent2, child2N1, n1, n2); 
			}
		
	
			result.output((g + 1) + "-е поколение: ");
			System.out.println(Arrays.toString(child1) + " F = " + funcCount(child1, matrix));
			result.output(Arrays.toString(child1) + " F = " + funcCount(child1, matrix));
			System.out.println(Arrays.toString(child2) + " F = " + funcCount(child2, matrix));
			result.output(Arrays.toString(child2) + " F = " + funcCount(child2, matrix));
			System.out.println("После точечной мутации");
			result.output("После точечной мутации");
			mutation(child1);
			mutation(child2);
			System.out.println("потомок 1: " + Arrays.toString(child1) + " F = " + funcCount(child1, matrix));
			System.out.println("потомок 2: " + Arrays.toString(child2) + " F = " + funcCount(child2, matrix));
			result.output("потомок 1: " + Arrays.toString(child1) + " F = " + funcCount(child1, matrix));
			result.output("потомок 2: " + Arrays.toString(child2) + " F = " + funcCount(child2, matrix));
			System.out.println();
			for(int i = 0; i < n; i++) {
				parrent1[i] = child1[i];
				parrent2[i] = child2[i];
			}
			
			
			
			
		}
	}
	static int[] randomMixing(int[] child, int[] parrent1, int[] parrent2, int childN1, int n1, int n2) {
		ArrayList <Integer> childI1 = new ArrayList <Integer>(); 
		ArrayList <Integer> childI2 = new ArrayList <Integer>(); 
		for(int i = 0; i < child.length; i++) { 
			if(child[i] == 1) {
				childI1.add(i + 1); 
			}
			else {
				childI2.add(i + 1); 
			}
		}
		System.out.println(childI1.toString() + " - индексы с единицами");
		System.out.println(childI2.toString() + " - индексы с нулями");
		ArrayList <Integer> parrentI1 = new ArrayList <Integer>(); 
		ArrayList <Integer> parrentI2 = new ArrayList <Integer>(); 
		for(int i = 0; i < parrent1.length; i++) { 
			if (parrent1[i] == 1 && parrent2[i] == 1) {
				parrentI1.add(i + 1);
			}
			if (parrent1[i] == 0 && parrent2[i] == 0) {
				parrentI2.add(i + 1);
			}
		}
		System.out.println(parrentI1.toString() + " - индексы с единицами, которые есть в обоих родительских хромосомах");
		System.out.println(parrentI2.toString() + " - индексы с нулями, которые есть в обоих родительских хромосомах");
		ArrayList <Integer> I1com = new ArrayList<Integer>(); 
		ArrayList <Integer> I2com = new ArrayList<Integer>(); 
		for(int i = 0; i < childI1.size(); i++) {
			I1com.add(childI1.get(i));
		}
		for(int i = 0; i < childI2.size(); i++) {
			I2com.add(childI2.get(i));
		}
		I1com.removeAll(parrentI1);  
		I2com.removeAll(parrentI2);
		System.out.println(I1com.toString() + " - Вакантные локусы для 0");
		System.out.println(I2com.toString() + " - Вакантные локусы для 1");
		int s; 
		int x; 
		
		if (childI1.size() > childI2.size()) { 
			s = n2 - (child.length - childN1);
			System.out.println(s + " - количество недостающих 0");
			for(int i = 0; i < s; i++) { 
				x = (int) Math.floor(Math.random() * I1com.size());  
				child[I1com.get(x) - 1] = 0;
				I1com.remove(x); 
				
			}
		}
		else {
			s = n1 - childN1;
			System.out.println(s + " - количество недостающих 1");
			for(int i = 0; i < s; i++) { 
				x = (int) Math.floor(Math.random() * I2com.size());  
				child[I2com.get(x) - 1] = 1;
				I2com.remove(x); 
			}
		}
		return child;
	}
	static int funcCount(int[] a, int[][] matrix) { 
		int sum = 0; 
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				if(a[i] == 1 && a[j] == 0) {
					sum += matrix[i][j];
				}
			}
			
		}
		return sum;
	}
	static int[] mutation(int[] a) { 
		int i = (int) Math.floor(Math.random() * a.length);
		int t;
		if(a[i] == 0) {
			a[i] = 1;
			for(int j = 0; j < a.length; j++) {
				t = (int) Math.floor(Math.random() * a.length);
				if(t != i && a[t] != 0) {
					a[t] = 1 - a[t];
					j = a.length;;
				}
				else {
					j--;
					continue;
				}
			}
		}
		else {
			a[i] = 0;
			for(int j = 0; j < a.length; j++) {
				t = (int) Math.floor(Math.random() * a.length);
				if(t != i && a[t] != 1) {
					a[t] = 1 - a[t];
					j = a.length;;
				}
				else {
					j--;
					continue;
				}
			}
		}
		
		return a;
	} 
}