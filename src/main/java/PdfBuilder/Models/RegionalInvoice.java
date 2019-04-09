package PdfBuilder.Models;

import java.math.BigDecimal;
import java.util.List;

public class RegionalInvoice {
    public BigDecimal TotalPriceBeforeTaxes;
    public List<RegionalInvoiceLine> RegionalInvoiceLines;

    public RegionalInvoice(List<RegionalInvoiceLine> RegionalInvoiceLines) {
        this.RegionalInvoiceLines = RegionalInvoiceLines;
        this.TotalPriceBeforeTaxes = calculatePriceBeforeTaxes();
    }

    public BigDecimal calculatePriceBeforeTaxes() {    
        System.out.println("Calculating regional invoice price before taxes");
        BigDecimal total = new BigDecimal(0);

        for (RegionalInvoiceLine ril : this.RegionalInvoiceLines) {
           total = total.add(ril.calculatePriceBeforeTaxes());
           System.out.println("Adding value " + ril.calculatePriceBeforeTaxes() + " to RegionalInvoice, now " + total);
        }

        System.out.println("Finished calculating regional invoice price before taxes");
        return total;
    }

}