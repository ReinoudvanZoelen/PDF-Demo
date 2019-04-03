package PdfBuilder.Builders.Implementations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import PdfBuilder.Models.Invoice;
import PdfBuilder.Models.KilometerInvoice;
import PdfBuilder.Models.KilometerInvoiceLine;
import PdfBuilder.Models.RegionalInvoice;
import PdfBuilder.Models.RegionalInvoiceLine;
import PdfBuilder.Models.VehicleInvoice;

public class iTextPdfBuilder {

    private Document document;

    public iTextPdfBuilder(String path, String filename) throws FileNotFoundException {
        String destination = path + "/" + filename + ".pdf";
        PdfWriter writer = new PdfWriter(destination);
        PdfDocument pdf = new PdfDocument(writer);
        this.document = new Document(pdf);
        this.document.setFontSize(11);
    }

    public iTextPdfBuilder AddTitleImage() throws MalformedURLException {
        String imagePath = "C:/Repos/PDF-Demo/resources/header-rijksoverheid.png";
        ImageData data = ImageDataFactory.create(imagePath);
        Image image = new Image(data);
        image.setHeight(75);
        image.setWidth(110);
        image.setFixedPosition(280, 766);

        document.add(image);
        return this;
    }

    public iTextPdfBuilder AddParagraph(String text) {
        return this.AddStyledParagraph(text, ITextStyle.P);
    }

    public iTextPdfBuilder AddStyledParagraph(String text, ITextStyle textStyle) {
        Text styledKilometers = new Text(text).addStyle(ITextPdfStyler.GetStyle(textStyle));
        this.document.add(new Paragraph(styledKilometers));
        return this;
    }

    public iTextPdfBuilder AddTextboxParagraph(String text, float xposition, float yposition, int width) {
        Paragraph paragraph = new Paragraph(text);

        paragraph.setFixedPosition(xposition, yposition, width);
        paragraph.setTextAlignment(TextAlignment.RIGHT);
        paragraph.setBorder(new SolidBorder(1));
        paragraph.setPadding(10f);

        this.document.add(paragraph);
        return this;
    }

    public iTextPdfBuilder AddWhiteline() {
        Paragraph paragraph = new Paragraph("\n");
        paragraph.setMargin(0f);
        this.document.add(paragraph);
        return this;
    }

    public iTextPdfBuilder AddVehiclesTables(List<VehicleInvoice> vehicleInvoices) {
        for (VehicleInvoice vehicleInvoice : vehicleInvoices) {
            this.AddVehicleTable(vehicleInvoice);
        }

        return this;
    }

    private void AddVehicleTable(VehicleInvoice vehicleInvoice) {
        String vehicleInfo = vehicleInvoice.DisplayName + ", Kentekenplaat: " + vehicleInvoice.LicensePlate;
        this.AddStyledParagraph(vehicleInfo, ITextStyle.H1);

        this.AddStyledParagraph("Regio's", ITextStyle.H2);
        this.AddVehicleRegionalInvoiceTable(vehicleInvoice.RegionalInvoice);

        this.AddWhiteline();

        this.AddStyledParagraph("Kilometers", ITextStyle.H2);
        this.AddVehicleKilometerInvoiceTable(vehicleInvoice.KilometerInvoice);
    }

    private void AddVehicleRegionalInvoiceTable(RegionalInvoice regionalInvoice) {
        float[] pointColumnWidths = { 150F, 150F, 150F, 150F };
        Table table = new Table(pointColumnWidths);

        // Headers
        table.addCell(new Cell().add("Regio").setBold());
        table.addCell(new Cell().add("Gemonitord op").setBold());
        table.addCell(new Cell().add("Regioprijs").setBold());
        table.addCell(new Cell().add("Prijs ex. btw").setBold());

        // Content
        for (RegionalInvoiceLine ril : regionalInvoice.RegionalInvoiceLines) {
            table.addCell(ril.Region);
            table.addCell(this.formatAsSimpleDate(ril.RegistrationMoment));
            table.addCell(this.createCurrencyCell(ril.RegionalPriceBeforeTaxes));
            table.addCell(this.createCurrencyCell(ril.AccountedPriceBeforeTaxes));
        }

        // Totals
        table.addCell(new Cell().add("Totaal").setBold());
        table.addCell("");
        table.addCell("");
        table.addCell(new Cell().add(this.createCurrencyCell(regionalInvoice.TotalPriceBeforeTaxes)).setBold());

        this.document.add(table);
    }

    private void AddVehicleKilometerInvoiceTable(KilometerInvoice kilometerInvoice) {
        float[] pointColumnWidths = { 150F, 150F, 150F, 150F };
        Table table = new Table(pointColumnWidths);

        // Headers
        table.addCell(new Cell().add("Wegtype").setBold());
        table.addCell(new Cell().add("Afstand (in meter)").setBold());
        table.addCell(new Cell().add("Prijs per kilometer").setBold());
        table.addCell(new Cell().add("Prijs ex. btw").setBold());

        // Content
        for (KilometerInvoiceLine kilometerInvoiceLine : kilometerInvoice.KilometerInvoiceLines) {
            table.addCell(kilometerInvoiceLine.RoadType.toString());
            table.addCell(Double.toString(kilometerInvoiceLine.DrivenDistance));
            table.addCell(this.createCurrencyCell(kilometerInvoiceLine.PricePerKilometerBeforeTaxes));
            table.addCell(this.createCurrencyCell(kilometerInvoiceLine.AccountedPriceBeforeTaxes));
        }

        // Totals
        table.addCell(new Cell().add("Totaal").setBold());
        table.addCell("");
        table.addCell("");
        table.addCell(new Cell().add(this.createCurrencyCell(kilometerInvoice.TotalPriceBeforeTaxes).setBold()));

        this.document.add(table);
    }

    public void Build() {
        document.close();
    }

    public iTextPdfBuilder AddInvoiceTitle(String text) throws IOException {
        PdfFont font = PdfFontFactory.createFont(FontConstants.COURIER);

        Text styledText = new Text(text).addStyle(ITextPdfStyler.GetStyle(ITextStyle.H1));
        Paragraph paragraph = new Paragraph(styledText);

        this.document.add(paragraph);
        return this;
    }

    public iTextPdfBuilder AddInvoiceInformation(Invoice invoice) {
        float[] pointColumnWidths = { 150F, 150F };
        Table table = new Table(pointColumnWidths);
        table.setStrokeColor(Color.WHITE);

        table.addCell("Factuurnummer: ");
        table.addCell(invoice.invoiceNumberString);
        table.addCell("Factuurdatum: ");
        table.addCell(this.formatAsSimpleDate(invoice.invoiceDate));

        this.document.add(table);
        return this;
    }

    private Cell createCurrencyCell(double value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(value);

        Cell cell = new Cell().add(moneyString).setTextAlignment(TextAlignment.RIGHT);

        return cell;
    }

    private String formatAsSimpleDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}