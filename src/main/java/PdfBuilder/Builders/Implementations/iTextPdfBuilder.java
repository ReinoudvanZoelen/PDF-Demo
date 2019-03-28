package PdfBuilder.Builders.Implementations;

import PdfBuilder.Builders.IPdfBuilder;
import PdfBuilder.Models.KilometerInvoice;
import PdfBuilder.Models.KilometerInvoiceLine;
import PdfBuilder.Models.RegionalInvoice;
import PdfBuilder.Models.RegionalInvoiceLine;
import PdfBuilder.Models.VehicleInvoice;

import java.io.FileNotFoundException;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class iTextPdfBuilder implements IPdfBuilder {

    private Document document;

    public iTextPdfBuilder(String path, String filename) throws FileNotFoundException {
        String destination = path + "/" + filename + ".pdf";
        PdfWriter writer = new PdfWriter(destination);
        PdfDocument pdf = new PdfDocument(writer);
        this.document = new Document(pdf);
    }

    public IPdfBuilder AddNewPage() {
        AreaBreak areabreak = new AreaBreak();
        this.document.add(areabreak);
        return this;
    }

    public IPdfBuilder AddParagraph(String text) {
        Paragraph paragraph = new Paragraph(text);
        this.document.add(paragraph);
        return this;
    }

    public IPdfBuilder AddWhiteline() {
        Paragraph paragraph = new Paragraph("");
        this.document.add(paragraph);
        return this;
    }

    public IPdfBuilder AddVehiclesTables(List<VehicleInvoice> vehicleInvoices) {
       for (VehicleInvoice vehicleInvoice : vehicleInvoices) {
           this.AddVehicleTable(vehicleInvoice);
       }

       return this;
    }

    private void AddVehicleTable(VehicleInvoice vehicleInvoice){
        this.AddParagraph(vehicleInvoice.DisplayName + ", Kentekenplaat: " + vehicleInvoice.LicensePlate);
        
        this.AddParagraph("Regio's");
        this.AddVehicleRegionalInvoiceTable(vehicleInvoice.RegionalInvoice);
        
        this.AddWhiteline();

        this.AddParagraph("Kilometers");
        this.AddVehicleKilometerInvoiceTable(vehicleInvoice.KilometerInvoice);
    }

    private void AddVehicleRegionalInvoiceTable(RegionalInvoice regionalInvoice) {
        float [] pointColumnWidths = {150F, 150F, 150F,150F};   
        Table table = new Table(pointColumnWidths);  
        
        // Headers
        table.addCell("Regio");
        table.addCell("Gemonitord op");
        table.addCell("Regioprijs");
        table.addCell("Prijs ex. btw");

        // Content
        for (RegionalInvoiceLine ril : regionalInvoice.RegionalInvoiceLines){
            table.addCell(ril.Region);
            table.addCell(Long.toString(ril.RegistrationMoment.getTime()));
            table.addCell(Double.toString(ril.RegionalPriceBeforeTaxes));
            table.addCell(Double.toString(ril.AccountedPriceBeforeTaxes));
        }

        // Totals
        table.addCell("Totaal");
        table.addCell("");
        table.addCell("");
        table.addCell(Double.toString(regionalInvoice.TotalPriceBeforeTaxes));

        this.document.add(table);
    }

    private void AddVehicleKilometerInvoiceTable(KilometerInvoice kilometerInvoice) {
        float [] pointColumnWidths = {150F, 150F, 150F,150F};   
        Table table = new Table(pointColumnWidths);  
        
        // Headers
        table.addCell("Wegtype");
        table.addCell("Afstand (in meter)");
        table.addCell("Prijs per kilometer");
        table.addCell("Prijs ex. btw");

        // Content
        for (KilometerInvoiceLine kil : kilometerInvoice.KilometerInvoiceLines){
            table.addCell(kil.RoadType.toString());
            table.addCell(Double.toString(kil.DrivenDistance));
            table.addCell(Double.toString(kil.PricePerKilometerBeforeTaxes));
            table.addCell(Double.toString(kil.AccountedPriceBeforeTaxes));
        }

        // Totals
        table.addCell("Totaal");
        table.addCell("");
        table.addCell("");
        table.addCell(Double.toString(kilometerInvoice.TotalPriceBeforeTaxes));

        this.document.add(table);
    }

    public void Build() {
        document.close();
    }

}