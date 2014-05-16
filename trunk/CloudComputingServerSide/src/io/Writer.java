package io;

import generic.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer implements IWriter{
	
	private String path;
	
	public Writer(String path) {
		this.path=path;
	}

	@Override
	public void writeUsers(List<User> userList) {
		File file=new File(path); 
		try {
			BufferedWriter writer=new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < userList.size(); i++) {
				writer.write(userList.get(i).getName()+"$$||$$"+userList.get(i).getSurname()+"$$||$$"+userList.get(i).getNickname()+"$$||$$"+userList.get(i).getEmail()+"$$||$$"+userList.get(i).getPass()+"\n");
			}	
		writer.close();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
