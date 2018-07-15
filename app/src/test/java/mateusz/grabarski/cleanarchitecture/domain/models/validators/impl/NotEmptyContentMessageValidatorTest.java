package mateusz.grabarski.cleanarchitecture.domain.models.validators.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;
import mateusz.grabarski.cleanarchitecture.domain.models.exceptions.ValidationMessageException;
import mateusz.grabarski.cleanarchitecture.domain.models.validators.MessageValidator;

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
        return new Message("Message content", new Date());
    }

    private Message givenNewMessageWithNoContent() {
        return new Message("", new Date());
    }
}