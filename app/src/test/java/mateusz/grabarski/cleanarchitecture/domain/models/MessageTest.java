package mateusz.grabarski.cleanarchitecture.domain.models;

import org.junit.Test;

import mateusz.grabarski.cleanarchitecture.domain.models.exceptions.IllegalMessageStateChange;

import static org.junit.Assert.*;

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

    private Message giveNewMessage() {
        return new Message(MESSAGE_CONTENT);
    }
}