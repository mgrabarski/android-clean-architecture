package mateusz.grabarski.cleanarchitecture.domain.validators.impl;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;
import mateusz.grabarski.cleanarchitecture.domain.models.exceptions.ValidationMessageException;
import mateusz.grabarski.cleanarchitecture.domain.validators.MessageValidator;

public class CompositeMessageValidator implements MessageValidator {

    private final MessageValidator[] validators;

    public CompositeMessageValidator(MessageValidator... validators) {
        this.validators = validators;
    }

    @Override
    public void verify(Message message) {
        if (validators == null) {
            throw new ValidationMessageException("No validators.");
        }
        for (MessageValidator validator : validators) {
            validator.verify(message);
        }
    }
}
