package az.ordercompany.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum ErrorMessage {
    ORDER_NOT_FOUND("Order not found with id: %s"),
    CLIENT_ERROR("Client Error: occurred while making request"),
    SERVER_ERROR("Unexpected error occurred.");


    private final String message;

}
