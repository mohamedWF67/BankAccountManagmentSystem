package com.mycompany.bankaccountmanagmentsystem;

public class ServiceRequest {

    public CustomerServiceRequest submitRequest(Client client, String type, String description) {
        CustomerServiceRequest request = new CustomerServiceRequest(client, type, description);
        System.out.println("Request submitted:\n" + request.getRequestSummary());
        return request;
    }

    public void closeRequest(CustomerServiceRequest request) {
        request.updateStatus("Closed");
        request.addNote("Request closed by system.");
        System.out.println("Request " + request.getRequestSummary() + " has been closed.");
    }

    public void addCommentToRequest(CustomerServiceRequest request, String comment) {
        request.addNote(comment);
        System.out.println("Comment added to request " + request.getRequestSummary());
    }
}
