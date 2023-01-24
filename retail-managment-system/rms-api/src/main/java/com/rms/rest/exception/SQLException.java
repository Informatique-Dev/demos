package com.rms.rest.exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
public class SQLException {

    private Exception ex;

    public SQLException(Exception ex) {
        this.ex = ex;
    }

    public String getError(){
        String msg = ex.getMessage();
        if (ex.getCause().getCause() instanceof java.sql.SQLException) {
            java.sql.SQLException e = (java.sql.SQLException) ex.getCause().getCause();
            if (e.getMessage().contains("Key")) {
                msg = e.getMessage().substring(e.getMessage().indexOf("Key"));
            }
        }
        return msg;
    }
}
