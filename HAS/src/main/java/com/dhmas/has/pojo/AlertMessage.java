package com.dhmas.HAS.pojo;

public class AlertMessage implements java.io.Serializable{
    private GeneralInfo generalInfo;
    private StatusLocalis statusLocalis;

    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
    }

    public StatusLocalis getStatusLocalis() {
        return statusLocalis;
    }

    public void setStatusLocalis(StatusLocalis statusLocalis) {
        this.statusLocalis = statusLocalis;
    }

    public AlertMessage() {

    }

    public AlertMessage(GeneralInfo generalInfo, StatusLocalis statusLocalis) {
        this.generalInfo = generalInfo;
        this.statusLocalis = statusLocalis;
    }
}
