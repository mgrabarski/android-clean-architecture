package mateusz.grabarski.cleanarchitecture.domain.factories;

import java.util.Date;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;
import mateusz.grabarski.cleanarchitecture.domain.validators.MessageValidator;

public class MessageFactory {

    private MessageValidator messageValidator;

    public MessageFactory(MessageValidator messageValidator) {
        this.messageValidator = messageValidator;
    }

    public Message build(String content) {
        Message message = new Message(content, new Date());
        messageValidator.verify(message);
        return message;
    }
}
