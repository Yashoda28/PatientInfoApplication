package com.example.yashoda.patientinfoapplication2.tables;

public class Emergency
{
    private int emergencyID;
    private String contactType;
    private String name;
    private String cellNumber;

    public Emergency()
    {}

    public Emergency(int emergencyID, String contactType, String name, String cellNumber) {
        this.emergencyID = emergencyID;
        this.contactType = contactType;
        this.name = name;
        this.cellNumber = cellNumber;
    }

    public void setEmergencyID(int emergencyID) {
        this.emergencyID = emergencyID;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public int getEmergencyID() {
        return emergencyID;
    }

    @Override
    public String toString() {
        return "Emergency{" +
                "emergencyID=" + emergencyID +
                ", contactType='" + contactType + '\'' +
                ", name='" + name + '\'' +
                ", cellNumber='" + cellNumber + '\'' +
                '}';
    }
}
