/**
* Application.java 
* Project 1
* Ricardo Rigodon
* Instructor: Dr. DePasquale
* Due Date: 2/21/2015
*/

import java.io.*;

public class Application
{


/**
* Application invokes methods from the Processor class through a Processor object. Used as 'Driver' class to access methods in Processor. 
*
*
* @author Ricardo Rigodon
* @author Christopher Mei
*/

public static void main (String   []  args) throws IOException
{
  
  Processor process = new Processor(); // creates new Processor object for use of methods
  String file = "zipout.txt"; // name of output file
  FileWriter fileWriter = new FileWriter(file);
  BufferedWriter buffWriter = new BufferedWriter(fileWriter);
  PrintWriter writer = new PrintWriter(buffWriter);
 
  process.readData(); //runs readData from processor
  process.sortData(); // runs sortData from processor
  process.sortRI(writer); // runs sortRI from processor
  process.findSpringfield(writer); // runs findSpringfield from processor
  process.sortNJ(writer); //runs sortNJ from processor
  process.countCounties(writer); // runs countCounties from processor
  process.countPosttypes(writer); // runs countPosttypes from processor
  process.sameLatLon(writer); // runs sameLatLon from processor


  writer.close(); // closes writer

  
  

  












}


}
