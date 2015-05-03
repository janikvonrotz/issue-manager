package ch.issueman.common;

import org.codehaus.jackson.annotate.JsonTypeInfo;

@SuppressWarnings("serial")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class AccessDeniedException extends Exception{

    public AccessDeniedException() {}

    public AccessDeniedException(String message){
       super(message);
    }
}
