package util;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import model.entities.Client;
import model.entities.Invoice;
import model.entities.MobileLine;

public class EmailTemplates {

    private static final DateTimeFormatter FECHA_FMT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final NumberFormat COLONES_FMT =
            NumberFormat.getCurrencyInstance(new Locale("es", "CR"));

    public static String invoiceTemplate(
            Client client,
            MobileLine line,
            Invoice invoice
    ) {
        return buildTemplate(
                "Factura mensual Link360 Telecom",
                "Estimado cliente, se ha generado su factura mensual con los siguientes detalles:",
                "FACTURA GENERADA",
                "#2980b9",
                client,
                line,
                invoice
        );
    }

    public static String paymentConfirmedTemplate(
            Client client,
            MobileLine line,
            Invoice invoice
    ) {
        return buildTemplate(
                "Pago confirmado",
                "Estimado cliente, su pago ha sido registrado correctamente.",
                "PAGADA",
                "#27ae60",
                client,
                line,
                invoice
        );
    }

    public static String overdueInvoiceTemplate(
            Client client,
            MobileLine line,
            Invoice invoice
    ) {
        return buildTemplate(
                "Factura vencida",
                "Estimado cliente, su factura se encuentra vencida. Le recomendamos realizar el pago cuanto antes.",
                "VENCIDA",
                "#e74c3c",
                client,
                line,
                invoice
        );
    }

    private static String buildTemplate(
            String title,
            String message,
            String status,
            String statusColor,
            Client client,
            MobileLine line,
            Invoice invoice
    ) {

        String invoiceDate = invoice.getInvoiceDate().format(FECHA_FMT);
        String dueDate = invoice.getDueDate().format(FECHA_FMT);

        String paymentDate = invoice.getPaymentDate() != null
                ? invoice.getPaymentDate().format(FECHA_FMT)
                : "Pendiente";

        String clientFullName = client.getName() + " "
                + client.getFirstSurname() + " "
                + client.getSecondSurname();

        return ""
                + "<div style='background:#071B2C;padding:30px 0;font-family:Arial,sans-serif;'>"

                + "<div style='width:390px;margin:auto;background:#ffffff;border-radius:18px;"
                + "padding:25px;box-shadow:0 2px 12px rgba(0,0,0,0.25);text-align:center;'>"

                + "<div style='font-size:45px;'>📡</div>"

                + "<h2 style='margin:5px 0;color:#0A2E42;'>Link360 Telecom</h2>"

                + "<p style='color:#777;margin-top:4px;'>"
                + "Servicios móviles 4G / 5G y fibra óptica"
                + "</p>"

                + "<div style='border-top:2px dashed #999;margin:15px 0;'></div>"

                + "<h3 style='color:#444;margin-bottom:10px;'>"
                + title
                + "</h3>"

                + "<p style='text-align:left;font-size:14px;line-height:1.7;color:#333;'>"
                + message
                + "</p>"

                + "<div style='text-align:left;font-size:14px;line-height:1.8;color:#222;'>"

                + "<p><strong>Cliente:</strong><br>"
                + clientFullName
                + "</p>"

                + "<p><strong>Identificación:</strong><br>"
                + client.getIdentification()
                + "</p>"

                + "<p><strong>Correo:</strong><br>"
                + client.getEmail()
                + "</p>"

                + "<p><strong>Línea:</strong><br>"
                + line.getPhoneNumber()
                + "</p>"

                + "<p><strong>Tecnología:</strong> "
                + line.getTechnology()
                + "</p>"

                + "<p><strong>Tipo de línea:</strong> "
                + line.getLineType()
                + "</p>"

                + "<div style='border-top:1px solid #ddd;margin:18px 0;'></div>"

                + "<p><strong>Número de factura:</strong><br>"
                + invoice.getInvoiceNumber()
                + "</p>"

                + "<p><strong>Fecha de factura:</strong><br>"
                + invoiceDate
                + "</p>"

                + "<p><strong>Fecha de vencimiento:</strong><br>"
                + dueDate
                + "</p>"

                + "<p><strong>Monto:</strong><br>"
                + COLONES_FMT.format(invoice.getAmount())
                + "</p>"

                + "<p><strong>Impuestos:</strong><br>"
                + COLONES_FMT.format(invoice.getTaxes())
                + "</p>"

                + "<p><strong>Descuentos aplicados:</strong><br>"
                + COLONES_FMT.format(invoice.getAppliedDiscounts())
                + "</p>"

                + "<p><strong>Puntos redimidos:</strong><br>"
                + invoice.getRedeemedPoints()
                + "</p>"

                + "<p><strong>Monto final:</strong><br>"
                + "<span style='font-size:18px;font-weight:bold;color:#0A2E42;'>"
                + COLONES_FMT.format(invoice.getFinalAmount())
                + "</span></p>"

                + "<p><strong>Fecha de pago:</strong><br>"
                + paymentDate
                + "</p>"

                + "<p><strong>Estado:</strong> "
                + "<span style='color:" + statusColor + ";font-weight:bold;'>"
                + status
                + "</span></p>"

                + "</div>"

                + "<div style='border-top:2px dashed #999;margin:20px 0;'></div>"

                + "<p style='font-size:12px;color:#999;'>"
                + "Gracias por utilizar los servicios de Link360 Telecom."
                + "</p>"

                + "</div>"
                + "</div>";
    }
}