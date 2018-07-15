package mateusz.grabarski.cleanarchitecture.domain.models.validators;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;

public interface MessageValidator {
    void verify(Message message);
}
