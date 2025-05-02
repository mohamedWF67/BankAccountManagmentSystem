package com.mycompany.bankaccountmanagmentsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class CustomerServiceRequest {
    private String requestID;
    private String requestType;
    private String description;
    private String status;
    private Date dateSubmitted;
    private Client submittedBy;
    private ArrayList<String> notes;

    public CustomerServiceRequest(Client client, String type, String description) {
        this.requestID = UUID.randomUUID().toString();
        this.requestType = type;
        this.description = description;
        this.status = "Submitted";
        this.dateSubmitted = new Date();
        this.submittedBy = client;
        this.notes = new ArrayList<>();
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public String getRequestSummary() {
        return String.format("Request ID: %s\nType: %s\nStatus: %s\nSubmitted by: %s\nDate: %s",
                requestID, requestType, status, submittedBy.getClientInfo(), dateSubmitted);
    }

    public void addNote(String note) {
        notes.add(note);
    }

    public void escalate() {
        this.status = "Escalated";
        addNote("Request was escalated.");
    }

    public boolean isResolved() {
        return "Resolved".equalsIgnoreCase(status) || "Closed".equalsIgnoreCase(status);
    }

    public void closeRequest(CustomerServiceRequest req) {
        req.updateStatus("Closed");
        req.addNote("Request closed.");
    }
    public void close() {
    this.status = "Closed";
    addNote("Request closed.");
    }

}
