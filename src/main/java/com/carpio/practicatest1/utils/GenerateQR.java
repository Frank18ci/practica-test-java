package com.carpio.practicatest1.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.micrometer.common.util.StringUtils;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class GenerateQR {
    public static String generateSvg(String text,
                                     int moduleSize,
                                     int margin,
                                     ErrorCorrectionLevel ecLevel,
                                     String darkColor,
                                     String lightColor) throws WriterException {

        if (StringUtils.isEmpty(text)) throw new IllegalArgumentException("text requerido");

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ecLevel);
        // No especificamos ancho/alto para que ZXing nos devuelva la matriz en su tamaño natural
        BitMatrix matrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, 0, 0, hints);

        int matrixWidth = matrix.getWidth();
        int matrixHeight = matrix.getHeight();
        int canvasSize = (matrixWidth + margin * 2) * moduleSize;

        StringBuilder sb = new StringBuilder( (canvasSize * canvasSize) / 2 );

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\" viewBox=\"0 0 %d %d\" shape-rendering=\"crispEdges\">%n",
                canvasSize, canvasSize, canvasSize, canvasSize));

        // fondo
        sb.append(String.format("  <rect width=\"100%%\" height=\"100%%\" fill=\"%s\"/>%n", escapeXml(lightColor)));

        // dibujar cada módulo oscuro como <rect>
        int offset = margin * moduleSize;
        for (int y = 0; y < matrixHeight; y++) {
            for (int x = 0; x < matrixWidth; x++) {
                if (matrix.get(x, y)) {
                    int rx = offset + x * moduleSize;
                    int ry = offset + y * moduleSize;
                    sb.append(String.format("  <rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fill=\"%s\"/>%n",
                            rx, ry, moduleSize, moduleSize, escapeXml(darkColor)));
                }
            }
        }

        sb.append("</svg>\n");
        return sb.toString();
    }
    // Escapa caracteres mínimos; aquí sólo manejamos comillas (por si acaso)
    private static String escapeXml(String s) {
        if (s == null) return "";
        return s.replace("\"", "&quot;");
    }

    // Ejemplo sencillo: guarda el SVG en un fichero
    public static void main(String[] args) throws Exception {
        String link = args.length > 0 ? args[0] : "https://iglesiavisiondefe.org/";
        String svg = generateSvg(link,
                6,                       // moduleSize en px
                4,                       // margin en módulos
                ErrorCorrectionLevel.M,  // nivel de corrección
                "#000000",               // color oscuro
                "#FFFFFF");              // color claro

        try (FileWriter fw = new FileWriter("qrcode.svg")) {
            fw.write(svg);
        }
        System.out.println("SVG generado en qrcode.svg");
    }
}
