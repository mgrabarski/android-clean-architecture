package mateusz.grabarski.cleanarchitecture.domain.validators.impl;

import org.junit.Test;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;
import mateusz.grabarski.cleanarchitecture.domain.models.exceptions.ValidationMessageException;
import mateusz.grabarski.cleanarchitecture.domain.validators.MessageValidator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CompositeMessageValidatorTest {

    @Test
    public void validatorIsPassingWhenInnerValidatorsPass() {
        // given
        Message message = givenAnyMessage();
        MessageValidator validator = new CompositeMessageValidator(givenPassingValidator(), givenPassingValidator());

        // when
        validator.verify(message);

        // then
    }

    @Test(expected = ValidationMessageException.class)
    public void validatorNotPassingWhenOneOfInnerValidatorNotPassing() {
        // given
        Message message = givenAnyMessage();
        MessageValidator messageValidator = new CompositeMessageValidator(givenPassingValidator(), givenNotPassingValidator());

        // when
        messageValidator.verify(message);

        // then
    }

    @Test
    public void validatorPassingWhenNoInnerValidatorsIn() {
        // given
        Message message = givenAnyMessage();
        MessageValidator messageValidator = new CompositeMessageValidator();

        // when
        messageValidator.verify(message);

        // then
    }

    @Test
    public void callsValidateOnAllInnerValidators() {
        // given
        Message message = mock(Message.class);
        MessageValidator validator1 = mock(MessageValidator.class);
        MessageValidator validator2 = mock(MessageValidator.class);
        MessageValidator composite = new CompositeMessageValidator(validator1, validator2);

        // when
        composite.verify(message);

        // then
        verify(validator1, times(1)).verify(message);
        verify(validator2, times(1)).verify(message);
    }

    private Message givenAnyMessage() {
        return mock(Message.class);
    }

    private MessageValidator givenPassingValidator() {
        return mock(MessageValidator.class);
    }

    private MessageValidator givenNotPassingValidator() {
        MessageValidator messageValidator = mock(MessageValidator.class);
        doThrow(new ValidationMessageException("error message")).when(messageValidator).verify(any(Message.class));
        return messageValidator;
    }
}