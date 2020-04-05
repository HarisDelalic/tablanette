package ba.delalich.tablanette.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException {
    private String element;
    private int elementId;

    public ElementNotFoundException(String message, String element, int elementId) {
        super(message);
        this.element = element;
        this.elementId = elementId;
    }
}
