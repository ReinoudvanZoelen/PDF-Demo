package PdfBuilder.Models;

public class PersonalInformation{
    public  String fullname;
    public Address address;

    public PersonalInformation(String fullname, Address address) {
        this.fullname = fullname;
        this.address = address;
    }

    @Override
    public String toString() {
        return this.fullname + " \n" + 
        this.address.Streetname + " " + this.address.Housenumber + " \n" + 
        this.address.Zipcode + " " + this.address.City + " \n" + 
        this.address.Country;
    }

}