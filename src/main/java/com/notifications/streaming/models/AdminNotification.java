package com.notifications.streaming.models;

public class AdminNotification {

    private String Message;
    private String PlanName;
    private String OrganizationName;
    private String Status;

    @Override
    public String toString() {
        return "AdminNotification [Message=" + Message + ", PlanName=" + PlanName + ", OrganizationName="
                + OrganizationName + ", Status=" + Status + "]";
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String planName) {
        PlanName = planName;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

}
