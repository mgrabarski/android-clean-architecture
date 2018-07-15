package mateusz.grabarski.cleanarchitecture.domain.models;

import java.util.Date;

import mateusz.grabarski.cleanarchitecture.domain.models.enums.MessageState;
import mateusz.grabarski.cleanarchitecture.domain.models.exceptions.IllegalMessageStateChange;
import mateusz.grabarski.cleanarchitecture.domain.services.TimeProvider;

public class Message {

    private final String messageContent;
    private final TimeProvider timeProvider;
    private final Date createDate;
    private Date sentDate;
    private MessageState messageState = MessageState.PENDING;

    public Message(String messageContent, TimeProvider timeProvider) {
        this.messageContent = messageContent;
        this.timeProvider = timeProvider;
        this.createDate = timeProvider.getDate();
    }

    public boolean isPending() {
        return messageState == MessageState.PENDING;
    }

    public void send() {
        if (messageState != MessageState.PENDING) {
            throw new IllegalMessageStateChange("Only pending message can be sent.");
        }
        messageState = MessageState.SENT;
        sentDate = timeProvider.getDate();
    }

    public void cancel() {
        if (messageState != MessageState.PENDING) {
            throw new IllegalMessageStateChange("Only pending message can be cancel");
        }
        messageState = MessageState.CANCELLED;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getSentDate() {
        return sentDate;
    }
}
