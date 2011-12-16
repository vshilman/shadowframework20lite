package codeconverter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileStringUtility {

	public static List<String> loadTextFile(String filename) {

		List<String> list=new LinkedList<String>();

		try {
			BufferedReader reader=new BufferedReader(new FileReader(new File(
					filename)));
			String line=reader.readLine();
			while (line != null) {
				list.add(line);
				line=reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.err
					.println("BasicTextFileIO.loadTextFile(String): Non posso trovare il file!");
		} catch (IOException e) {
			System.err
					.println("BasicTextFileIO.loadTextFile(String): Non posso leggere il file!");
		}

		return list;
	}

	public static void writeTextFile(String filename, String[] output) {

		try {
			BufferedWriter writer=new BufferedWriter(new FileWriter(new File(
					filename)));
			for (int i=0; i < output.length; i++) {
				writer.write(output[i] + "\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.err
					.println("BasicTextFileIO.loadTextFile(String): Non posso creare o aprire il file!");
		} catch (IOException e) {
			System.err
					.println("BasicTextFileIO.loadTextFile(String): Non posso scrivere il file!");
		}

	}
}
