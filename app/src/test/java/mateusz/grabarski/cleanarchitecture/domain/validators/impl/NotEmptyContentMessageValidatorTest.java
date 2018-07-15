package mateusz.grabarski.cleanarchitecture.domain.validators.impl;

import org.junit.Before;
import org.junit.Test;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;
import mateusz.grabarski.cleanarchitecture.domain.models.exceptions.ValidationMessageException;
import mateusz.grabarski.cleanarchitecture.domain.services.TimeProvider;
import mateusz.grabarski.cleanarchitecture.domain.validators.MessageValidator;

import static org.mockito.Mockito.mock;

public class NotEmptyContentMessageValidatorTest {

    private MessageValidator mMessageValidator;

    @Before
    public void setUp() throws Exception {
        mMessageValidator = new NotEmptyContentMessageValidator();
    }

    @Test
    public void validateMessageWithNotEmptyMessageContent() {
        // given
        Message message = givenNewMessageWithContent();

        // when
        mMessageValidator.verify(message);
    }

    @Test(expected = ValidationMessageException.class)
    public void validateMessageWithEmptyMessageContent() {
        // given
        Message message = givenNewMessageWithNoContent();

        // when
        mMessageValidator.verify(message);
    }

    private Message givenNewMessageWithContent() {
        return new Message("Message content", mock(TimeProvider.class));
    }

    private Message givenNewMessageWithNoContent() {
        return new Message("", mock(TimeProvider.class));
    }
}