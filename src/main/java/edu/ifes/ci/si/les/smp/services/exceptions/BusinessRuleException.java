package edu.ifes.ci.si.les.smp.services.exceptions;

public class BusinessRuleException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessRuleException(String msg) {
        super(msg);
    }

    public BusinessRuleException(String msg, Throwable cause) {
        super(msg, cause);
    }

}