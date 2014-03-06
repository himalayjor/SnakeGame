
import java.io.IOException;

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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Client {
	
	private Socket connection;
        private ObjectOutputStream output;
        private ObjectInputStream input;
	
	private void closeCrap() 
	{
		try {
			output.close();
			input.close();
			connection.close();
			System.out.println("client closed");
		} catch( IOException ie) {
			ie.printStackTrace();
		}
	}
	
	public boolean setScore(int scoree)
	{
		
		boolean val = false;
		try {
			
			connection = new Socket("localhost", 11243);
			
			
			output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();

			input = new ObjectInputStream(connection.getInputStream());

			output.writeObject("score");
			output.flush();

			output.writeInt(scoree);
			output.flush();
			
			System.out.println("score sent");
			 val = input.readBoolean();

		}  catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		} finally {
			closeCrap();
		}
		
		return val;
		
	}
	
	public void submitName(String nm)
	{
		
		try {
			connection = new Socket("localhost", 11243);
			output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();
			input = new ObjectInputStream(connection.getInputStream());
			
			output.writeObject(nm);
			output.flush();
			
			
		}  catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
	
		} finally {
			closeCrap();
		}
	}
	
	public ArrayList<NameScore> StartRunning()
	{
		ArrayList<NameScore> arr = new ArrayList<NameScore>();

		try {
			connection = new Socket("localhost", 11243);

			//connection = new Socket(InetAddress.getByName("localhost"), 11243);
			output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();
			input = new ObjectInputStream(connection.getInputStream());
			
			output.writeObject("high");
			output.flush();
			
			try {
				NameScore ob;

				for (int i = 0; i < 10; i++) {
					ob  =  (NameScore) input.readObject();
					arr.add(ob);

				}
			}catch (ClassNotFoundException ex) {
				Logger.getLogger(highScore.class.getName()).log(Level.SEVERE, null, ex);
			}
				
		}  catch(EOFException ef) {
			System.out.println(ef);
		}catch(IOException e) {
			e.printStackTrace();
		} finally {
			closeCrap();
		}
		
		return arr;
	}
}
