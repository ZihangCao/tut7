package templatesTutorial;


import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.*;



public class PrintInvitations{
	public static void main(String args[]) {
		//read data from xml file
		try {
			XMLDecoder friendsList = new XMLDecoder(
					new BufferedInputStream(
							new FileInputStream("friends.xml")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object friends = friendsList.readObject();
		friendsList.close();
		
		//initiate and instantiate velocity
		Velocity.init();
		VelocityContext context = new VelocityContect();
		//bind data to friend
		context.put("friend",friends);
		
		try {
			
			//call letter format template
			Template template = Velocity.getTamplate("invitationLetterFormat.vm");
		}
		catch(Exception e) {}
		

		//write to outputfile
		FileWriter out = new FileWriter("invitations.txt");
		template.merge(context,out);
		out.close();

	}
}