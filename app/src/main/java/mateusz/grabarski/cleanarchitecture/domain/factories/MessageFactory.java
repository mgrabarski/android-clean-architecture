package mateusz.grabarski.cleanarchitecture.domain.factories;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;
import mateusz.grabarski.cleanarchitecture.domain.services.TimeProvider;
import mateusz.grabarski.cleanarchitecture.domain.validators.MessageValidator;

public class MessageFactory {

    private MessageValidator messageValidator;
    private TimeProvider timeProvider;

    public MessageFactory(MessageValidator messageValidator, TimeProvider timeProvider) {
        this.messageValidator = messageValidator;
        this.timeProvider = timeProvider;
    }

    public Message build(String content) {
        Message message = new Message(content, timeProvider);
        messageValidator.verify(message);
        return message;
    }
}
