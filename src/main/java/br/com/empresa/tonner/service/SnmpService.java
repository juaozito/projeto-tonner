package br.com.empresa.tonner.service;

import br.com.empresa.tonner.model.Printer;
import org.snmp4j.*;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import java.io.IOException;

public class SnmpService {
    public int getTonerLevel(Printer printer, String oid) throws IOException {
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(printer.getCommunity()));
        target.setAddress(new UdpAddress(printer.getIpAddress() + "/161"));
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);

        TransportMapping<? extends Address> transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        transport.listen();

        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid)));
        pdu.setType(PDU.GET);

        ResponseEvent response = snmp.send(pdu, target);
        snmp.close();

        if (response != null && response.getResponse() != null) {
            return response.getResponse().get(0).getVariable().toInt();
        }
        return -1;
    }
}