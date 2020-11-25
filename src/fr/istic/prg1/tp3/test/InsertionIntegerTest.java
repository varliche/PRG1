package fr.istic.prg1.tp3.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import fr.istic.prg1.tp3.InsertionInteger;

public class InsertionIntegerTest {
	public static void main(String[] args) throws FileNotFoundException {
		InsertionInteger ins = new InsertionInteger();
		System.out.println(ins.toString());
		File file = new File("fichier1.txt");
		Scanner scanner = new Scanner(file);
		ins.createArray(scanner);
		System.out.println(ins.toString());
		System.out.println(Arrays.toString(ins.toArray()));
		ins.insert(5);
		System.out.println(ins.toString());
		System.out.println(Arrays.toString(ins.toArray()));
	}
}
