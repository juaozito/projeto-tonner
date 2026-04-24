package br.com.empresa.tonner.model;

public class Printer {
    private String name;
    private String ipAddress;
    private String community;
    private String tonerOid;
    private String imagingUnitOid;

    public Printer(String name, String ipAddress, String community, String tonerOid, String imagingUnitOid) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.community = community;
        this.tonerOid = tonerOid;
        this.imagingUnitOid = imagingUnitOid;
    }

    public String getName() { return name; }
    public String getIpAddress() { return ipAddress; }
    public String getCommunity() { return community; }
    public String getTonerOid() { return tonerOid; }
    public String getImagingUnitOid() { return imagingUnitOid; }
}