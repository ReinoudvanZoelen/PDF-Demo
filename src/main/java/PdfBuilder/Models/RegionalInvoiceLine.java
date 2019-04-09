package PdfBuilder.Models;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;

public class RegionalInvoiceLine {
    public String Region;
    public Date RegistrationMoment;
    public BigDecimal RegionalPriceBeforeTaxes;
    public BigDecimal AccountedPriceBeforeTaxes;

    public RegionalInvoiceLine(String Region, Date RegistrationMoment, BigDecimal RegionalPriceBeforeTaxes,
            BigDecimal AccountedPriceBeforeTaxes) {
        this.Region = Region;
        this.RegistrationMoment = RegistrationMoment;
        this.RegionalPriceBeforeTaxes = RegionalPriceBeforeTaxes;
        this.AccountedPriceBeforeTaxes = AccountedPriceBeforeTaxes;
    }

    public BigDecimal calculatePriceBeforeTaxes() {
        return AccountedPriceBeforeTaxes
        .round(new MathContext(2));
    }
}