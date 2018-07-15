package mateusz.grabarski.cleanarchitecture.domain.validators.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;
import mateusz.grabarski.cleanarchitecture.domain.models.exceptions.ValidationMessageException;
import mateusz.grabarski.cleanarchitecture.domain.validators.MessageValidator;

public class LengthMessageValidatorTest {

    private MessageValidator mMessageValidator;

    @Before
    public void setUp() throws Exception {
        mMessageValidator = new LengthMessageValidator();
    }

    @Test
    public void messageCreatedBefore18WithMessageContentLessThen140ChartsShouldBePositiveValidate() {
        // given
        Message message = givenMessageWithLessThen140Charts(getCreateDate(16, 0));

        // when
        mMessageValidator.verify(message);
    }

    @Test(expected = ValidationMessageException.class)
    public void messageCreatedBefore18WithMessageContentMoreThen140ChartsShouldNotBePositiveValidateThrownException() {
        // given
        Message message = givenMessageWithMoreThen140Charts(getCreateDate(16, 0));

        // when
        mMessageValidator.verify(message);
    }

    @Test
    public void messageCreatedAfter18WithMessageContentLessThen140ChartShouldBePositiveValidate() {
        // given
        Message message = givenMessageWithLessThen140Charts(getCreateDate(20, 0));

        // when
        mMessageValidator.verify(message);
    }

    @Test(expected = ValidationMessageException.class)
    public void messageCreatedBefore18WithMessageContentZeroLengthShouldNotPassThrowException() {
        // given
        Message message = new Message("", getCreateDate(16, 0));

        // when
        mMessageValidator.verify(message);
    }

    private Message givenMessageWithLessThen140Charts(Date createDate) {
        return new Message(getStringWithLength(100), createDate);
    }

    private Message givenMessageWithMoreThen140Charts(Date createDate) {
        return new Message(getStringWithLength(200), createDate);
    }

    private Date getCreateDate(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    private String getStringWithLength(int length) {
        return new String(new char[length]);
    }
}