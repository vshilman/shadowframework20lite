package io;

import generic.Mediator;
import generic.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.Substring;

public class Reader implements IReader{

	private String path;

	public Reader(String path) {
		this.path=path;
	}
	
	@Override
	public List<User> getUserList() {
		List<User> userList=new ArrayList<User>();
		File file=new File(path);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				createList(line, userList);
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}

	
	private void createList(String line, List<User> list){
		
		Substring sub=new Substring(line, "$$||$$");
		String name=sub.nextSubString();
		String surname=sub.nextSubString();
		String nickname=sub.nextSubString();
		String email=sub.nextSubString();
		String pass=sub.nextSubString();
		User u=Mediator.getMed().generateUser(name, surname, nickname, email, pass);
		list.add(u);
	}


}
