package PdfBuilder.Models;

import java.math.BigDecimal;
import java.math.MathContext;

public class KilometerInvoiceLine {
    public RoadType RoadType;
    public int DrivenDistanceInMeters;
    public BigDecimal PricePerKilometerBeforeTaxes;
    public BigDecimal AccountedPriceBeforeTaxes;

    public KilometerInvoiceLine(RoadType RoadType, int DrivenDistance, BigDecimal PricePerKilometerBeforeTaxes) {
        this.RoadType = RoadType;
        this.DrivenDistanceInMeters = DrivenDistance;
        this.PricePerKilometerBeforeTaxes = PricePerKilometerBeforeTaxes;

        this.AccountedPriceBeforeTaxes = this.calculatePriceBeforeTaxes();
    }

    public BigDecimal calculatePriceBeforeTaxes() {
        return this.PricePerKilometerBeforeTaxes
            .multiply(new BigDecimal(this.DrivenDistanceInMeters))
            .divide(new BigDecimal(1000))
            .round(new MathContext(2));
    }

    public BigDecimal getDrivenDistanceInKilometers() {
        return new BigDecimal(this.DrivenDistanceInMeters)
            .divide(new BigDecimal(1000));
    }
}