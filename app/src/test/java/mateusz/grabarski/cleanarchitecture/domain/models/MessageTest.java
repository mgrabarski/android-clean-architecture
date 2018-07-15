package mateusz.grabarski.cleanarchitecture.domain.models;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import mateusz.grabarski.cleanarchitecture.domain.models.exceptions.IllegalMessageStateChange;
import mateusz.grabarski.cleanarchitecture.domain.services.TimeProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageTest {

    public static final String MESSAGE_CONTENT = "message content";

    @Test
    public void createdNewMessageShouldHavePendingState() {
        // given

        // when
        Message message = giveNewMessage();

        // then
        assertTrue(message.isPending());
    }

    @Test
    public void messageAfterSendShouldHaveSentState() {
        // given
        Message message = giveNewMessage();

        // when
        message.send();
    }

    @Test
    public void messageAfterCancelShouldHaveCancelState() {
        // given
        Message message = giveNewMessage();

        // when
        message.cancel();
    }

    @Test(expected = IllegalMessageStateChange.class)
    public void canceledMassageCanNotBeSendShouldBeThrowException() {
        // given
        Message message = giveNewMessage();
        message.cancel();

        // when
        message.send();
    }

    @Test(expected = IllegalMessageStateChange.class)
    public void sentMessageCanNotBeCancelShouldBeThrowException() {
        // given
        Message message = giveNewMessage();
        message.send();

        // when
        message.cancel();
    }

    @Test(expected = IllegalMessageStateChange.class)
    public void sendMessageCanNotBeSendSecondTimeShouldBeThrownException() {
        // given
        Message message = giveNewMessage();
        message.send();

        // when
        message.send();
    }

    @Test(expected = IllegalMessageStateChange.class)
    public void canceledMessageCanNotBeCancelSecondTime() {
        // given
        Message message = giveNewMessage();
        message.cancel();

        // when
        message.cancel();
    }

    @Test
    public void messageShouldHaveSanedCorrectSentDate() {
        // given
        Date sendingDate = getCreateDateWithHourAndMinutes(16,0);
        TimeProvider timeProvider = givenMockTimeProvider(sendingDate);
        Message message = new Message(MESSAGE_CONTENT, timeProvider);

        // when
        message.send();

        // then
        assertEquals(sendingDate, message.getSentDate());
    }

    private TimeProvider givenMockTimeProvider(Date date) {
        TimeProvider timeProvider = mock(TimeProvider.class);
        when(timeProvider.getDate()).thenReturn(date);
        return timeProvider;
    }

    private Date getCreateDateWithHourAndMinutes(int hour, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    private Message giveNewMessage() {
        return new Message(MESSAGE_CONTENT, mock(TimeProvider.class));
    }
}