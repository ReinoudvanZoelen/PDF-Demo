package PdfBuilder.Builders.Implementations;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.layout.Style;

public class ITextPdfStyler {

    private static Color primaryColor = new DeviceRgb(46, 116, 181);
    private static Color accentColor = new DeviceRgb(46, 116, 181);

    public static Style GetStyle(ITextStyle pdfTextStyle) {


        switch (pdfTextStyle) {
            case H1: {
                return new Style().setFontSize(16).setFontColor(primaryColor).setItalic();
            }
            case H2: {
                return new Style().setFontSize(14).setFontColor(accentColor);
            }
            case H3: {
                return new Style().setFontSize(12).setFontColor(accentColor);
            }
            case P: {
                return new Style().setFontSize(11).setFontColor(Color.BLACK);
            }
            default: {
                return GetStyle(ITextStyle.P);
            }
        }
    }
}
