package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ErrorMessageDtoString {
    private String timestamp;
    private int status;
    private String error;
    private Object message;
    private String path;
}
