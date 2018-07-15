package mateusz.grabarski.cleanarchitecture.domain.factories;

import org.junit.Test;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;
import mateusz.grabarski.cleanarchitecture.domain.models.exceptions.ValidationMessageException;
import mateusz.grabarski.cleanarchitecture.domain.services.TimeProvider;
import mateusz.grabarski.cleanarchitecture.domain.validators.MessageValidator;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class MessageFactoryTest {

    public static final String MESSAGE_CONTENT = "message content";

    @Test
    public void givenMessageHaveCorrectContentAndPassingValidators() {
        // given
        MessageFactory factory = new MessageFactory(givenPassingValidator(), givenTimeProvider());

        // when
        Message message = factory.build(MESSAGE_CONTENT);

        // then
        assertEquals(MESSAGE_CONTENT, message.getMessageContent());
    }

    @Test(expected = ValidationMessageException.class)
    public void givenMessageHaveIncorrectMessageAndNotPassingValidatorShouldThrowException() {
        // given
        MessageFactory factory = new MessageFactory(givenNotPassingValidator(), givenTimeProvider());

        // when
        Message message = factory.build(MESSAGE_CONTENT);
    }

    private MessageValidator givenNotPassingValidator() {
        MessageValidator validator = mock(MessageValidator.class);
        doThrow(new ValidationMessageException("message error")).when(validator).verify(any(Message.class));
        return validator;
    }

    private MessageValidator givenPassingValidator() {
        return mock(MessageValidator.class);
    }

    private TimeProvider givenTimeProvider() {
        return mock(TimeProvider.class);
    }
}