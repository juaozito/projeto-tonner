package br.com.empresa.tonner;

import br.com.empresa.tonner.model.Printer;
import br.com.empresa.tonner.monitor.PrinterMonitor;
import br.com.empresa.tonner.service.EmailAlertService;
import br.com.empresa.tonner.service.SnmpService;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // Serviços
        SnmpService snmpService = new SnmpService();
        EmailAlertService alertService = new EmailAlertService();

        // Monitor
        PrinterMonitor monitor = new PrinterMonitor(snmpService, alertService);

        // Lista de Impressoras
        List<Printer> impressoras = new ArrayList<>();
        impressoras.add(new Printer("Impressora HP-RH", "192.168.1.50", "public", ".1.3.6.1.2.1.43.11.1.1.9.1.1", ".1.3.6.1.2.1.43.11.1.1.9.1.2"));

        // Execução
        System.out.println("Iniciando verificação...");
        for (Printer p : impressoras) {
            monitor.checkPrinter(p);
        }
    }
}