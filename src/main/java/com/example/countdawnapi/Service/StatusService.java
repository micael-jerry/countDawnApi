package com.example.countdawnapi.Service;

import com.example.countdawnapi.model.Status;

public class StatusService {
    public static Status updateStatus(Status oldStatus,Status status){
        oldStatus.setAdmitted(status.getAdmitted());
        oldStatus.setPending(status.getPending());
        oldStatus.setRecaler(status.getRecaler());
        return oldStatus;
    }
}
