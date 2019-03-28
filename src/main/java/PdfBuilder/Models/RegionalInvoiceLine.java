package PdfBuilder.Models;

import java.util.Date;

public class RegionalInvoiceLine {
    public  String Region;
    public   Date RegistrationMoment;
    public  double RegionalPriceBeforeTaxes;
    public  double AccountedPriceBeforeTaxes;

    public RegionalInvoiceLine(String Region, Date RegistrationMoment, double RegionalPriceBeforeTaxes, double AccountedPriceBeforeTaxes) {
        this.Region = Region;
        this.RegistrationMoment = RegistrationMoment;
        this.RegionalPriceBeforeTaxes = RegionalPriceBeforeTaxes;
        this.AccountedPriceBeforeTaxes = AccountedPriceBeforeTaxes;
    }
}