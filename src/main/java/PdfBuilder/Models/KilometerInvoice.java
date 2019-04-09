package PdfBuilder.Models;

import java.math.BigDecimal;
import java.util.List;

public class KilometerInvoice {

    public List<KilometerInvoiceLine> KilometerInvoiceLines;
    public BigDecimal TotalPriceBeforeTaxes;

    public KilometerInvoice(List<KilometerInvoiceLine> KilometerInvoiceLines) {
        this.KilometerInvoiceLines = KilometerInvoiceLines;
        this.TotalPriceBeforeTaxes = this.calculatePriceBeforeTaxes();
    }

    public BigDecimal calculatePriceBeforeTaxes() {
        System.out.println("Calculating Kilometer invoice price before taxes");
        BigDecimal total = new BigDecimal(0);

        for (KilometerInvoiceLine kil : this.KilometerInvoiceLines) {
            total = total.add(kil.calculatePriceBeforeTaxes());
            System.out.println("Adding value " + kil.calculatePriceBeforeTaxes() + " to KilometerInvoice, now " + total);
        }
        
        System.out.println("Finished calculating Kilometer invoice price before taxes");
        return total;
    }
}