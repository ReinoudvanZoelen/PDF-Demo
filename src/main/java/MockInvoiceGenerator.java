import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import PdfBuilder.Models.Address;
import PdfBuilder.Models.Invoice;
import PdfBuilder.Models.KilometerInvoice;
import PdfBuilder.Models.KilometerInvoiceLine;
import PdfBuilder.Models.RegionalInvoiceLine;
import PdfBuilder.Models.RoadType;
import PdfBuilder.Models.PersonalInformation;
import PdfBuilder.Models.RegionalInvoice;
import PdfBuilder.Models.SupplierInformation;
import PdfBuilder.Models.VehicleInvoice;

public class MockInvoiceGenerator {

    public static Invoice Generate(){
        String invoiceNumber = "20190300000123";
        Date invoiceDate = new Date(2019, 04, 02);
        Invoice invoice = new Invoice(invoiceNumber, invoiceDate, GeneratePersonalInformation(), GenerateSupplierInformation(), GenerateVehicleInvoices());
        return invoice;
    }

    private  static PersonalInformation GeneratePersonalInformation(){
        return new PersonalInformation("Tex de Wit", new Address("Hertogensingel", "31", "5341AB", "Oss", "The Netherlands"));
    }

    private static SupplierInformation GenerateSupplierInformation(){
        return new SupplierInformation("Rekeningrijden", new Address("Professor Goossenslaan", "1", "5022DM", "Tilburg", "The Netherlands"), "NL123456789B01", "123456789", "NL21ABNA0123456789");
    }

    private static RegionalInvoice GenerateRegionalInvoice(){
        List<RegionalInvoiceLine> regionalInvoiceLines = new ArrayList<RegionalInvoiceLine>();
        
        regionalInvoiceLines.add(new RegionalInvoiceLine("Tilburg", new Date(2019, 03, 01, 8, 42), 4.95, 4.95));
        regionalInvoiceLines.add(new RegionalInvoiceLine("Eindhoven", new Date(2019, 03, 01, 12, 01), 7.95, 7.95));
        regionalInvoiceLines.add(new RegionalInvoiceLine("Tilburg", new Date(2019, 03, 01, 17, 51), 4.95, 0));
        regionalInvoiceLines.add(new RegionalInvoiceLine("Tilburg", new Date(2019, 03, 02, 8, 32), 4.95, 4.95));
        regionalInvoiceLines.add(new RegionalInvoiceLine("Den Bosch", new Date(2019, 03, 02, 9, 26), 8.95, 8.95));
        regionalInvoiceLines.add(new RegionalInvoiceLine("Den Bosch", new Date(2019, 03, 02, 19, 26), 8.95, 0));

        Double totalprice = 123456.20;

        RegionalInvoice RegionalInvoice = new RegionalInvoice(totalprice, regionalInvoiceLines);
        return RegionalInvoice;
    }

    private static KilometerInvoice GenerateKilometerInvoice(){
        List<KilometerInvoiceLine> kilometerInvoiceLines = new ArrayList<KilometerInvoiceLine>();

        kilometerInvoiceLines.add(new KilometerInvoiceLine(RoadType.A, 111111, 0.005));
        kilometerInvoiceLines.add(new KilometerInvoiceLine(RoadType.B, 22222, 0.003));
        kilometerInvoiceLines.add(new KilometerInvoiceLine(RoadType.C, 3333, 0.002));
        kilometerInvoiceLines.add(new KilometerInvoiceLine(RoadType.D, 22, 0.001));
        kilometerInvoiceLines.add(new KilometerInvoiceLine(RoadType.E, 1, 0.000));
        
        return new KilometerInvoice(kilometerInvoiceLines);
    }

    private static List<VehicleInvoice> GenerateVehicleInvoices(){
        VehicleInvoice vehicleInvoice = new VehicleInvoice("Opel Corsa", "32-LP-VV", GenerateRegionalInvoice(), GenerateKilometerInvoice());
        List<VehicleInvoice> vehicleInvoices = new ArrayList<VehicleInvoice>();
        vehicleInvoices.add(vehicleInvoice);

        return vehicleInvoices;
    }
}