package com.abc.project1.ExceptionHandling;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExceptionResponse {
    private String errorMessage;
    private String requestedURI;
    private String possibleCause;
}
