package mateusz.grabarski.cleanarchitecture.domain.models.validators.impl;

import java.util.Calendar;
import java.util.Date;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;
import mateusz.grabarski.cleanarchitecture.domain.models.exceptions.ValidationMessageException;
import mateusz.grabarski.cleanarchitecture.domain.models.validators.MessageValidator;

public class LengthMessageValidator implements MessageValidator {
    @Override
    public void verify(Message message) {
        if (message.getMessageContent() == null || message.getMessageContent().length() == 0) {
            throw new ValidationMessageException("Message content is empty");
        }

        Date createDate = message.getCreateDate();

        if (isBefore18(createDate) && message.getMessageContent().length() > 140) {
            throw new ValidationMessageException("Message before 18 can not more then 140 charts");
        }
    }

    private boolean isBefore18(Date createDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createDate);
        return calendar.get(Calendar.HOUR_OF_DAY) < 18;
    }
}
