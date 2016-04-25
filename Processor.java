/**
* Processor.java 
* Project 1
* Ricardo Rigodon
* Instructor: Dr. DePasquale
* Due Date: 2/21/2015
*/ 

import java.util.*;
import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;

/**
* The processor class reads data from a comma seperated text file, catches exceptions, stores it in a set of comparable arrays, sorts them, and does
* tasks pertaining to the info inside them. Has a set of void methods to perform these tasks, prints results, and written to an output file named zipcodes.txt.
*
* @author Ricardo Rigodon
*/   


public class Processor
{
   ZipCodeLocation[] data = new ZipCodeLocation[42048]; // Creates an array of ZipCodeLocation objects determined by removing bad lines from array
   ZipCodeLocation[] data2; // Array that will take values from data and will be used for subsequent calculations

/*
Method to read Data from the text file instantiates Scanner and goes through text file to store temporary values to store into array
*/
   public void readData() 
   {

    String tempzipCode; //temporary zipCode to store value from text file
    Double tempLatitude=0.0; // temporary latitude to store parsed value from text file
    Double tempLongitude=0.0;// temporary longitude to store parsed value from text file
    String temppostalCity; // temporary postalCity to store value from text file
    String temppostalState;// temporary postalState to store value from text file
    String temppostalCounty;// temporary postalCounty to store value from text file
    String temppostalType; // temporary postalType to store value from text files
    int count = 0; //counts how many 'good' lines are placed into array
    int filelength = 42740; //length of file that is read
	
    try //try loop begins here, if exception is thrown it deals with it in the catch block at the end of the loop
    {

		
	Scanner postalScan = new Scanner(new URL("http://s3.amazonaws.com/depasquale/datasets/zipcodes.txt").openStream()).useDelimiter("\\n"); //Scanner to read from URL delimited by new lines

/* Loop starts from 0 and goes to filelength- i.e. how many lines in the file read from the web server. Grabs values, parses them, implements a try catch to catch exceptions such as NumberFormatException and
No Such Element exception as thrown by the scanner */
 
	for(int i=0; i< filelength; i++) 
  {
 	  String line = postalScan.next(); //Grabs a new comma seperated data value line for the scanner to read
	  Scanner scan = new Scanner(line).useDelimiter(","); //Uses line to read from and uses delimiter to seperate data via commas instead of the default whitespace the Scanner skips to read new tokens
	  
	  tempzipCode = scan.next(); //Grabs the next token and stores it in tempzipCode
	  
    String latitude = scan.next();
	  
    if(!latitude.equals("")) //If latitude is not an empty string, it will parse it and store it in tempLatitude
    {
	  tempLatitude = Double.parseDouble(latitude.replaceAll("\"",""));
	  }
	  
    String longitude = scan.next();
          
    if(!latitude.equals("")) //If latitude is not a blank string- avoids the Number Format Exception from parsing an empty string, it will parse it and store it in tempLongitude
    {
	  tempLongitude = Double.parseDouble(longitude.replaceAll("\"","")); //.replaceAll(...) removes Double Quotes from scanned value
    }
          /* Grabs tokens and gives values to temp variables in order of when they appear in the text file. */
	  
          temppostalCity = scan.next();
          temppostalState = scan.next();
          temppostalCounty = scan.next();
          temppostalType = scan.next();
         
	 /* Boolean check determines if line is good or not. If one or more missing lines are found, check is turned to false and the array is stopped from being made. The count variable that keeps track of how many
   lines are stored in the array is incremented or constant depending on whether or not a good line was placed into the array. An error message is produced to System.err of the missing value and the zipCode it is located at*/

	  boolean check = true;   //initial value true, becomes false if error is found          
	
         if(latitude.equals("")) //Checks if latitude has a blank line or not
          {
            System.err.println("Missing latitude was found at line containing this zip code" +tempzipCode); //Prints diagnostic error message for user to read
	          check= false;
          }
	       if(longitude.equals(""))
          {
            System.err.println("Missing longitude was found at line containing this zip code" +tempzipCode);
	          check= false;
          }
	        if(temppostalCity.replaceAll("\"","").equals("")) //Removes double quotes off of value, and checks to see if its equal to a blank string
          {
            System.err.println("Missing postal city was found at line containing this zip code" +tempzipCode);
	          check= false;
          }
	       if(temppostalState.replaceAll("\"","").equals(""))
          {
            System.err.println("Missing postal state was found at line containing this zip code" +tempzipCode);
	          check= false;
          }
         if(temppostalCounty.replaceAll("\"","").equals(""))
         {
           System.err.println("Missing postal county was found at line containing this zip code " +tempzipCode);
           check = false;
         }
         if(temppostalType.replaceAll("\"","").equals("")) 
          {
            System.err.println("Missing postal type was found at line containing this zip code" +tempzipCode);
            check = false;
          }
	  if(check)
	  {
	    data[count] = new ZipCodeLocation(tempzipCode, tempLatitude, tempLongitude, temppostalCity, temppostalState, temppostalCounty, temppostalType); // creates new array of temp data to be placed in array
	    count++; // increments count that keeps track of how many arrays have been created

      

	  }
    /* Takes data2 and gives the values in data to it to only keep the good lines in the array */
    data2 = new ZipCodeLocation[count];
    for(int c = 0; c<count; c++)
    {
      data2[c] = data[c];
    }  
	}
	
	
   }
   catch(IOException e) //Catches IOException thrown and prints a diagnostic message
   {
   
     System.out.println(e.getMessage()); //Prints diagnostic message pertaining to the exception thrown 

   }
   catch(NumberFormatException e) //Catches Number Format Exception thrown from trying to parse an empty string
   {
     System.out.println(e.getMessage());
   }
   catch(NoSuchElementException e) //Thrown from Scanner when no more tokens are found to read
   {
     System.out.println(e.getMessage());
   }
}

/** 
*Uses arrays.sort after using compareTo in ZipCodeLocation, sorts the array and returns it for subsequent calculations
*
*/
public void sortData() 
{
  Arrays.sort(data2);
}


/** 
*
* Method to sort Rhode Island cities ascending alphabetically, and print them with no duplicates.
* Grabs a String postalcity, another string temppostalcity which is set to the value
* postalcity previously had. Prints out postalcity iff temppostalcity is not equal to it at that point.
*
*@param writer- Print writer to write output to 'zipout.txt'
*@throws IOException - Possible Exception given from Writer
 */

public void sortRI(PrintWriter writer) throws IOException
{
  String postalcity = ""; //initialize postal city will later equal the postal city from the accesor method of ZipCodeLocation
  String temppostalcity = ""; // Becomes the previous postal city value in order to try and combat printing duplicates
  ZipCodeLocation.setMode(1);
  int count=0;
  writer.println("------------------------------------------------------------------------");
  writer.println("RHODE ISLAND CITIES");
  writer.println();
  Arrays.sort(data2);
  for(int i=0; i< data2.length; i++)
  {
    if(data2[i].getpostalState().replaceAll("\"","").equals("RI"))
    {
      postalcity = data2[i].getpostalCity().replaceAll("\"","");
      if(!postalcity.equals(temppostalcity))
      {
        writer.println(postalcity);
        count++;
      }
      temppostalcity = postalcity;
    }
  }
}

/**
* Count Counties goes through the array grabs a String value prints it out and goes through array
* and doesn't print another postal type until temppostaltype does not equal postaltype and
* the goal is to print all different types of postal Types found in the file.
*
*@param writer- Print writer to write output to 'zipout.txt'
*@throws IOException - Possible Exception given from Writer
*/

public void countCounties(PrintWriter writer) throws IOException
{
  String postalcounty = "";
  String temppostalcounty = "";
  ZipCodeLocation.setMode(2);
  int count = 0;
  writer.println("----------------------------------------------------------");
  writer.println("HOW MANY COUNTIES USPS DELIVERS TO");
  writer.println();
  Arrays.sort(data2);

  for(int i=0; i< data2.length; i++)
  {
    temppostalcounty = postalcounty;
    postalcounty = data2[i].getpostalCounty().replaceAll("\"","");

    if(!postalcounty.equals(temppostalcounty))
    {
      count++;
    }

  }
  writer.println(count);

}

/**
* Method sortNJ goes through array printing out a unique list of counties in NJ setting a String postalCounty to the value 
* of the array at that index. If temppostalcounty has the same value it moves on to the next value until all unique counties are printed.
*
*
*@param writer- Print writer to write output to 'zipout.txt'
*@throws IOException - Possible Exception given from Writer
*/
public void sortNJ(PrintWriter writer) throws IOException
{
  String postalcounty = ""; 
  String temppostalcounty = "";
  writer.println("------------------------------------------------------------------------");
  writer.println("NEW JERSEY COUNTIES");
  writer.println();
  ZipCodeLocation.setMode(2);
  Arrays.sort(data2);

  for(int i=0; i< data2.length; i++)
  {
    if(data2[i].getpostalState().replaceAll("\"","").equals("NJ"))
    {
      postalcounty = data2[i].getpostalCounty().replaceAll("\"","");
      if(!postalcounty.contains(temppostalcounty))
      {
        writer.println(postalcounty);
      }
      temppostalcounty = postalcounty;
    }
  }
}


/** 
*
*Goes through array, checks to see if city matches SPRINGFIELD, if it does it prints out the zipCode associated with the city. 
*
*
* @param Writer- writes output to 'zipout.txt' file
* @throws IOException- possible exception given from Writer
*/
public void findSpringfield(PrintWriter writer) throws IOException
{
  writer.println("------------------------------------------------------------------------");
  writer.println("SPRINGFIELD ZIP CODES");
  
  for(int i=0; i < data2.length ; i++)
  {
    if(data2[i].getpostalCity().replaceAll("\"","").contains("SPRINGFIELD")) //If postal city contains Springfield as its string or a substring of the city name
    {
      writer.println(data2[i].getzipCode().replaceAll("\"",""));

    }

  }
  writer.println("------------------------------------------------------------------------");
}


/**
*Goes through array of String ptypes which hold the postal types, reorders them using Arrays.sort, and goes through array printing out unique postal types.
* Similar function as to sortRI and sortNJ in terms of the way to go about solving it.
*
*@param Writer- writes output to 'zipout.txt' file
*@throws IOException
*/
public void countPosttypes(PrintWriter writer) throws IOException
{
  String postaltype = "";
  String temppostaltype = "PO BOX ONLY";
  writer.println("---------------------------------------------------------");
  writer.println("NUMBER OF POSTAL CODE TYPES");
  ZipCodeLocation.setMode(3); //Sets mode of compareTo to 3 for sorting
  Arrays.sort(data2);
  int pcount =0;
  
  for(int i=0; i< data2.length; i++)
  {
      
      postaltype = data2[i].getPostalType().replaceAll("\"","");
      if(postaltype.equals(temppostaltype))
      {
        pcount++;
      }
      if(!postaltype.equals(temppostaltype))
      {
        writer.println(postaltype + " : " +pcount);
      }
      temppostaltype = postaltype;

    
  }

}
/** 
* Method sameLatLon() accepts a parameter printWriter called writer, throws IOException,
* goes through array and starts checking if lat/lon pairs are equal. If they are print them
* and the zip codes. Else store the zip code, print the location, lat/lon and zip code.
*
*@param writer - Print writer
*@throws IOException
*/
public void sameLatLon(PrintWriter writer ) throws IOException
{
  writer.println();
  writer.println("---------------------------------------------------");
  writer.println("SAME LAT LON LOCATIONS");
  writer.println("----------------------------------------------------");
  ZipCodeLocation.setMode(4); //Changes mode to 4 for sorting
  Arrays.sort(data2); //Sort data
  Double latitude=0.0; //latitude to store 
  Double longitude=0.0; //longitude to store
  Double templat = 0.0; // temp lat to store
  Double templon = 0.0; // temp lon to store
  String tempZip = ""; // grabs zip code to print

  int i=0;
  for(int j= 1; j<data2.length; j++)
  {
    latitude = data2[j].getLatitude();
    longitude = data2[j].getLongitude();
    templat = data2[j-1].getLatitude();
    templon = data2[j-1].getLongitude();
    if(!data2[j-1].equals(tempZip))
    {
    if(latitude == templat && longitude == templon)
    {
      tempZip = data2[j-1].getzipCode();
      writer.println(tempZip);
    } 
    else
    {
      tempZip = data2[j].getzipCode();
      writer.println();
      writer.println(data2[j].getpostalCity().replaceAll("\"","") + " ," + data2[j].getpostalState().replaceAll("\"","") + ": "
        + data2[j].getLatitude() + " and " + data2[j].getLongitude());
      writer.println(tempZip);
    }
  } 
  }

}




public void printData()
{
  try
    {
      PrintWriter writer1 = new PrintWriter("zipcodes.txt"); //Makes a writer
      int i = 1;
        for(ZipCodeLocation data: data2)
        {
        writer1.println(data.toString() ); //prints out all of the customer's toStrings
    }


  writer1.close(); //Closes the writer

  }
  


  catch (IOException e) //e is an error
        {
        System.out.println(e.getMessage()); //Catches the exception from the scanner so if there is an error the line the error happens on will be displayed
        }

}


  

  
 

   
  

  



}

    
    
