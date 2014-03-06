/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class Server {
	
	private ServerSocket server;
	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	public ArrayList<NameScore> arr;
	private int newScore, oldScore;
	private String oldName;
	
	public static void main(String args[])
	{
		new Server().startRunning();
	}
	
	public void startRunning()
	{
		try {
			
			server = new ServerSocket(11243, 100);
			
			while(true) {
				try {
					connection = server.accept();
					System.out.println("Connection accepted");
					output = new ObjectOutputStream(connection.getOutputStream());
					output.flush();
					input = new ObjectInputStream(connection.getInputStream());
					
					
					
					String x = "";
					
					while(true) {
						
						try {
							 x = (String)input.readObject();
							 
						} catch (ClassNotFoundException ex) {
							System.err.println(ex);
						}
						
						
						
						if (x.equals("high")) {
							accessDB();
							
							
							for (int i = 0; i < 10; i++) {
								output.writeObject(arr.get(i));
								output.flush();
							}
							
						} else if(x.equals("score")) {
							
							func();
						}
						else {
							modifyDB(x);
						}
						
					}	
					
				}catch(EOFException ef) {
					System.out.println("Server ended connection");
				} finally {
					CloseCrap();
				}
			}
		} catch(IOException e) {
			System.err.println(e);
		}
		
	}
	
	private void modifyDB(String nm)
	{
		System.out.println(nm);
		try {
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "1234");
			PreparedStatement pst = connection.prepareStatement("Update high set value = ?, name = ? where value = ? and name = ?");
			pst.setInt(1, newScore);
			pst.setString(2, nm);
			pst.setInt(3, oldScore);
			pst.setString(4, oldName);

			boolean x = pst.execute();

			pst.close();
			connection.close();

		} catch (SQLException ex) {
			System.out.println(ex);
		}


	}
	
	private void func()
	{
		accessDB();
		
		int sc = 0;
		
		try {
			sc = input.readInt();
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
		NameScore ob = null;
		
		int i = 0;
		for ( i = 0; i < 10; i++) {
			ob = (NameScore) arr.get(i);
			
			if (ob.score < sc)
				break;
		}
		
		
		if (i < 10) {
			
			// telling client that Name is required
			ob = (NameScore) arr.get(9);
			System.out.println(i);
			
			newScore = sc;
			oldScore = ob.score;
			oldName = ob.Name;
			
			try {
				output.writeBoolean(true);
				output.flush();
				
			} catch(IOException ie) {
				ie.printStackTrace();
			}
			
		}
		else {
				try {
					output.writeBoolean(false);
					output.flush();
					
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
		
		
	}
	
	private void accessDB()
	{
		arr = new ArrayList<>();
		
		 try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			 System.out.println(ex);
		}
		 
		try {
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "1234");
			
			PreparedStatement pst = connection.prepareStatement("Select * from high");
			 ResultSet rs = pst.executeQuery();
			 
			 while (rs.next()) {
				 arr.add(new NameScore(rs.getInt(1), rs.getString(2)));
				
			 }
			 
			 pst.close();
			 rs.close();
			 connection.close();
			 
			 Collections.sort(arr, new NameScoreComparator());
			 
 		} catch (SQLException ex) {
			System.err.println(ex);
		}
		 
	}
	
	private void CloseCrap()
	{
		try {
			input.close();
			output.close();
			connection.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
