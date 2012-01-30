package codeconverter.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * A Common Use class to access files
 * 
 * @author Alessandro Martinelli
 */
public class FileStringUtility {

	/**
	 * Load a file and extract all its lines as a List of Strings
	 * 
	 * @param filename the file name
	 * @return a list of {@link String} representing the file content 
	 */
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

	/**
	 * Write an array of strings into a file
	 * @param filename the file name
	 * @param output the array of Strings
	 */
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
	
	/**
	 * Write a string into a file 
	 * 
	 * @param filename the file name
	 * @param output the output string
	 */
	public static void writeTextFile(String filename, String output) {

		try {
			BufferedWriter writer=new BufferedWriter(new FileWriter(new File(
					filename)));
			writer.write(output);
			writer.close();
		} catch (FileNotFoundException e) {
			System.err
					.println("BasicTextFileIO.loadTextFile(String): Non posso creare o aprire il file!");
		} catch (IOException e) {
			System.err
					.println("BasicTextFileIO.loadTextFile(String): Non posso scrivere il file!");
		}

	}
	
	/**
	 * An easy tool to read a file and put all its content into a String.
	 * '\n' are removed from the text Removed. 
	 * 
	 * @param filename
	 * @return
	 */
	public static String loadTextFileOnOneString(String filename) {

		StringWriter writer=new StringWriter();

		try {
			BufferedReader reader=new BufferedReader(new FileReader(new File(
					filename)));
			String line=reader.readLine();
			while (line != null) {
				writer.write(line);
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

		return writer.toString();
	}
}
