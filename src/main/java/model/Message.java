package model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Message {
    String to;
    String subject;
    String messageBody;
}
