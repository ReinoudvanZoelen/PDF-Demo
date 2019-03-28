package PdfBuilder.Models;

import java.util.List;

public class KilometerInvoice {

    public List<KilometerInvoiceLine> KilometerInvoiceLines;
    public  double TotalPriceBeforeTaxes;

    public KilometerInvoice(List<KilometerInvoiceLine> KilometerInvoiceLines) {
        this.KilometerInvoiceLines = KilometerInvoiceLines;
        this.TotalPriceBeforeTaxes = this.calculateTotalPriceBeforeTaxes();
    }

    private double calculateTotalPriceBeforeTaxes() {
        return 10000;
    }
}