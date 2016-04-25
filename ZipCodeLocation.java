/**
 * Assignment: Project 1
 * due date: 2/21/15
 * instructor: Dr. DePasquale
 * Submitted by: Ricardo Rigodon
 */

/**
 * the ZipCodeLocation class stores the values for zipcode, latitude
 * longitude, postal city, postal state, postal county and postal type.
 * It implements the comparable interface and uses compareto to sort the array.
 * It also has accessor methods to be used throughout the class and a string representation of the class
 *
 * @uthor Ricardo Rigodon
 */
public class ZipCodeLocation implements Comparable<ZipCodeLocation>
{

/**
 * holds the zipcode string
 */
private String zipCode;
/**
 * holds the latitude value
 */
private double latitude;
/**
 * holds the longitude value
 */
private double longitude;
/**
 * holds the postal city string.
 */
private String postalCity;
/**
 * holds the postal state string
 */
private String postalState;
/**
 * holds the postal county string
 */
private String postalCounty;
/**
 * holds the postal type string
 */
private String postalType;
/**
 * holds the  mode value used to change sorting
 */
private static int mode;

/**
 * creates a Zip code location object from the provided data.
 * 
 * @param zipcode	 the string name of the zipcode
 * @param latitude 	the value of the zipcode's latitude
 * @param longitude	the value of the zipcode's longitude
 * @param postalCity	a string name of the postal city
 * @param postalState	a string name of the postal state
 * @param postalCounty	a string name of the postal county
 * @param postalType	the postal type of the zipcode
 */

public ZipCodeLocation(String zipCode, double latitude, double longitude, String postalCity, String postalState, String postalCounty, String postalType)
{
  this.zipCode = zipCode;
  this.latitude = latitude;
  this.longitude = longitude;
  this.postalCity = postalCity;
  this.postalState = postalState;
  this.postalCounty = postalCounty;
  this.postalType = postalType;
  mode = 0;


}

/**
 * This method is for accessing the zipcode in the Processor class
 *
 * @return the zipcode of the zipcode location
 */
public String getzipCode()
{
  return zipCode;
}
/**
 * This method for is accessing the latitude in the Processor class
 *
 * @return the latitude of the zipcode location
 */
public Double getLatitude()
{
  return latitude;
}
/**
 * This method is for accessing the longitude in the Processor class
 *
 * @return  the longitude of the zipcode location 
 */
public Double getLongitude()
{
  return longitude;
}
/**
 * This method is for accessing the postal city in the Processor class
 *
 * @return the postal city of the zipcode location
 */
public String getpostalCity()
{
  return postalCity;
}
/**
 * This method is for accessing the postal state in the Processor class
 *
 * @return the postal state of the zipcode location 
 */
public String getpostalState()
{
  return postalState;
}
/**
 * This method is for accessing the postal county in the Processor class
 *
 * @return the postal county of the zipcode location
 */
public String getpostalCounty()
{
  return postalCounty;
}
/**
 * This method is for accessing the postal type in the Processor class
 *
 * @return the postal type of the zipcode location 
 */
public String getPostalType()
{
  return postalType;
}

/**
 * This method is intended to sort a particular method
 *
 * @param modeset value of the desired mode
 */

public static void setMode(int modeset)
{
  mode = modeset;
}


/**
 * The toString method gives the string representation of the zip code location. 
 */

public String toString()
{
  
   String result= (zipCode + " " + latitude + " " + longitude + " " + postalCity + " " + postalState + " " + postalCounty + " " + postalType);
   return result;
}

/**
 * This method compares two zipcode location objects 
 * and returns a value and is used to sort the array
 * It is used several time in tasks in the processor class
 *
 * @param zipcodelocationobject of zipcode location 
 * @return a value used to sort the array
 */

public int compareTo(ZipCodeLocation zipCodeLocationobject)
{
  switch(mode){
  case 1:
	if(postalState.compareTo(((ZipCodeLocation)zipCodeLocationobject).postalState) == 0)
	{
	 return postalCity.compareTo(zipCodeLocationobject.postalCity);
  }
    else if(postalState.compareTo(((ZipCodeLocation)zipCodeLocationobject).postalState) < 0)
    return -1;
  else if(postalState.compareTo(((ZipCodeLocation)zipCodeLocationobject).postalState) > 0)
    return 1;

  break;

  case 2:
    if(postalState.compareTo(((ZipCodeLocation)zipCodeLocationobject).postalState) == 0)
    {
      return postalCounty.compareTo(((ZipCodeLocation)zipCodeLocationobject).postalCounty);
    }
    else if(postalState.compareTo(((ZipCodeLocation)zipCodeLocationobject).postalState) < 0)
      return -1;
    else if(postalState.compareTo(((ZipCodeLocation)zipCodeLocationobject).postalState) > 0)
      return 1;

    break;

  case 3:
   if(postalType.compareTo(((ZipCodeLocation)zipCodeLocationobject).postalType) == 0)
   {
     return 0;
   }
   else if(postalType.compareTo(((ZipCodeLocation)zipCodeLocationobject).postalType) < 0)
     return -1;
   else if(postalType.compareTo(((ZipCodeLocation)zipCodeLocationobject).postalType) > 0)
    return 1;



    break;

 case 4:

  if(latitude < zipCodeLocationobject.getLatitude())
   {
    return -1;
   }
   else if(latitude > zipCodeLocationobject.getLatitude()) 
   {
    return 1;
   }
   else if(longitude < zipCodeLocationobject.getLongitude())
   {
     return -1;
   }
   else if(longitude > zipCodeLocationobject.getLongitude())
   {
     return 1;
   }
  else if(zipCode.compareTo(((ZipCodeLocation)zipCodeLocationobject).getzipCode()) < 0)
  {
    return -1;
  }
  else if(zipCode.compareTo(((ZipCodeLocation)zipCodeLocationobject).getzipCode())> 0)
  {
    return 1;
  }
   else
   return 0;
   
}

  return 0;

}
}











