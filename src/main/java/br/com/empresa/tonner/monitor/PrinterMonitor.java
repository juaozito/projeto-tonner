package br.com.empresa.tonner.monitor;

import br.com.empresa.tonner.model.Printer;
import br.com.empresa.tonner.service.AlertService;
import br.com.empresa.tonner.service.SnmpService;
import java.io.IOException;

public class PrinterMonitor {
    private final SnmpService snmpService;
    private final AlertService alertService;

    public PrinterMonitor(SnmpService snmpService, AlertService alertService) {
        this.snmpService = snmpService;
        this.alertService = alertService;
    }

    public void checkPrinter(Printer printer) {
        try {
            System.out.println("Verificando: " + printer.getName());

            int tonerLevel = snmpService.getTonerLevel(printer, printer.getTonerOid());
            if (tonerLevel != -1 && tonerLevel <= 1) {
                alertService.sendAlert("ALERTA CRÍTICO", printer.getName() + " com toner em " + tonerLevel + "%!");
            }

            int imagingLevel = snmpService.getTonerLevel(printer, printer.getImagingUnitOid());
            if (imagingLevel != -1 && imagingLevel <= 10) {
                alertService.sendAlert("ALERTA DE MANUTENÇÃO", printer.getName() + " com unidade de imagem em " + imagingLevel + "%!");
            }
        } catch (IOException e) {
            System.err.println("Erro ao conectar na impressora " + printer.getName() + ": " + e.getMessage());
        }
    }
}