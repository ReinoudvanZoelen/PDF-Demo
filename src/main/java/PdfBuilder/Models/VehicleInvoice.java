package PdfBuilder.Models;

import java.math.BigDecimal;

public class VehicleInvoice {
    public  String DisplayName;
    public  String LicensePlate;
    
    public RegionalInvoice RegionalInvoice;
    public KilometerInvoice KilometerInvoice;

    public VehicleInvoice(String DisplayName, String LicensePlate, RegionalInvoice RegionalInvoice, KilometerInvoice KilometerInvoice) {
        this.DisplayName = DisplayName;
        this.LicensePlate = LicensePlate;
        this.RegionalInvoice = RegionalInvoice;
        this.KilometerInvoice = KilometerInvoice;
    }

	public BigDecimal calculatePriceBeforeTaxes() {
        return this.RegionalInvoice.calculatePriceBeforeTaxes()
        .add(this.KilometerInvoice.calculatePriceBeforeTaxes());
	}
}