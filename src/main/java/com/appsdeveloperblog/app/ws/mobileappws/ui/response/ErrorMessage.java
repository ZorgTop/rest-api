package com.appsdeveloperblog.app.ws.mobileappws.ui.response;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Date;

@AllArgsConstructor
@Data
public class ErrorMessage {
    private String message;
    private Date date;

}
