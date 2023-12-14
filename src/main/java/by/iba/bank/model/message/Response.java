package by.iba.bank.model.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private ResponseStatus responseStatus;
    private String responseMessage;
    private String responseData;
}
