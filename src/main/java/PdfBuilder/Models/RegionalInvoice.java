package PdfBuilder.Models;

import java.util.List;

public class RegionalInvoice {
    public  double TotalPriceBeforeTaxes;
    public  List<RegionalInvoiceLine> RegionalInvoiceLines;

    public RegionalInvoice(double TotalPriceBeforeTaxes, List<RegionalInvoiceLine> RegionalInvoiceLines) {
        this.TotalPriceBeforeTaxes = TotalPriceBeforeTaxes;
        this.RegionalInvoiceLines = RegionalInvoiceLines;
    }

}