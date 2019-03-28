package PdfBuilder.Models;

public class Address{
    public   String Streetname;
    public   String Housenumber;
    public  String Zipcode;
    public   String City;
    public  String Country;

    public Address(String Streetname, String Housenumber, String Zipcode, String City, String Country) {
        this.Streetname = Streetname;
        this.Housenumber = Housenumber;
        this.Zipcode = Zipcode;
        this.City = City;
        this.Country = Country;
    }
   
}