package pl.codeconcept.e2d.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    private String message;

    private String token;


}
