package az.ordercompany.client;
import az.ordercompany.exception.CustomFeignException;

import com.fasterxml.jackson.databind.JsonNode;
import feign.Response;
import feign.codec.ErrorDecoder;

import static az.ordercompany.model.enums.ErrorMessage.CLIENT_ERROR;
import static az.ordercompany.util.MapperUtil.MAPPER_UTIL;
import static az.ordercompany.client.JsonNodeFieldName.*;

public class CustomErrorDecoder implements ErrorDecoder{
    @Override
    public Exception decode(String s, Response response) {
        var errorMessage = CLIENT_ERROR.getMessage();
        var statusCode = response.status();

        JsonNode jsonNode;
        try(var body = response.body().asInputStream()) {
            jsonNode = MAPPER_UTIL.map(body, JsonNode.class);
        } catch (Exception e) {
            throw new CustomFeignException(statusCode,CLIENT_ERROR.getMessage());
        }

        if (jsonNode.has(MESSAGE.getValue())) errorMessage = jsonNode.get(MESSAGE.getValue()).asText();
        return new CustomFeignException(statusCode,errorMessage);
    }


}
