package mateusz.grabarski.cleanarchitecture.domain.validators.impl;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;
import mateusz.grabarski.cleanarchitecture.domain.models.exceptions.ValidationMessageException;
import mateusz.grabarski.cleanarchitecture.domain.validators.MessageValidator;

public class NotEmptyContentMessageValidator implements MessageValidator {
    @Override
    public void verify(Message message) {
        if (message.getMessageContent() == null || message.getMessageContent().length() == 0) {
            throw new ValidationMessageException("Message content can not be empty.");
        }
    }
}
