package PdfBuilder.Models;

public class KilometerInvoiceLine {
    public  RoadType RoadType;
    public  double DrivenDistance;
    public  double PricePerKilometerBeforeTaxes;
    public  double AccountedPriceBeforeTaxes;

    public KilometerInvoiceLine(RoadType RoadType, double DrivenDistance, double PricePerKilometerBeforeTaxes) {
        this.RoadType = RoadType;
        this.DrivenDistance = DrivenDistance;
        this.PricePerKilometerBeforeTaxes = PricePerKilometerBeforeTaxes;
        
        this.AccountedPriceBeforeTaxes = this.DrivenDistance * this.PricePerKilometerBeforeTaxes;
    }
}