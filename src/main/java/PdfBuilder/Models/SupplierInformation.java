package PdfBuilder.Models;

public class SupplierInformation{
    String companyName;
    Address address;
    String BTWNumberString;
    String KVKNumberString;
    String IBANString;

    public SupplierInformation(String CompanyName, Address Address, String BTWNumberString, String KVKNumberString, String IBANString) {
        this.companyName = CompanyName;
        this.address = Address;
        this.BTWNumberString = BTWNumberString;
        this.KVKNumberString = KVKNumberString;
        this.IBANString = IBANString;
    }

    @Override
    public String toString() {
        return this.companyName + " \n" + 
        this.address.Streetname + " " + this.address.Housenumber + " \n" + 
        this.address.Zipcode + " " + this.address.City + " \n" + 
        this.address.Country + " \n" + 
        "\n " + 
        "BTW nr.: " + this.BTWNumberString + " \n" + 
        "KvK nr.: " + this.KVKNumberString + " \n" +
        "IBAN: " + this.IBANString; 
    }
}