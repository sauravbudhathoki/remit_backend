package com.remit.remittance.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //yesle data ra errors ya aru field null vaye json ma pathaudaina
public class GlobalApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Object errors;
    private LocalDateTime timestamp;



    public GlobalApiResponse(){
        this.timestamp=LocalDateTime.now();
    }

    public GlobalApiResponse(boolean success, String message, T data,Object errors){
        this.success=success;
        this.message=message;
        this.data=data;
        this.errors=errors;
        this.timestamp=LocalDateTime.now();
    }
    public GlobalApiResponse(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static  <T> GlobalApiResponse<T> success(String message,T data){
        return new GlobalApiResponse<>(true,message,data,null);
    }
    public static <T> GlobalApiResponse<T> failure(String message,Object errors){
        return new GlobalApiResponse<>(false,message,null,errors);
    }


}
