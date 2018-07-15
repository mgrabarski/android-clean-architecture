package mateusz.grabarski.cleanarchitecture.domain.validators;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;

public interface MessageValidator {
    void verify(Message message);
}
