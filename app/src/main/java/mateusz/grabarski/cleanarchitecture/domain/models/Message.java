package mateusz.grabarski.cleanarchitecture.domain.models;

import java.util.Date;

import mateusz.grabarski.cleanarchitecture.domain.models.enums.MessageState;
import mateusz.grabarski.cleanarchitecture.domain.models.exceptions.IllegalMessageStateChange;

public class Message {

    private final String messageContent;
    private final Date createDate = new Date();
    private MessageState messageState = MessageState.PENDING;

    public Message(String messageContent) {
        this.messageContent = messageContent;
    }

    public boolean isPending() {
        return messageState == MessageState.PENDING;
    }

    public void send() {
        if (messageState != MessageState.PENDING) {
            throw new IllegalMessageStateChange("Only pending message can be sent.");
        }
        messageState = MessageState.SENT;
    }

    public void cancel() {
        if (messageState != MessageState.PENDING) {
            throw new IllegalMessageStateChange("Only pending message can be cancel");
        }
        messageState = MessageState.CANCELLED;
    }
}
