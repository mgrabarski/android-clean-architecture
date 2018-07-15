package mateusz.grabarski.cleanarchitecture.domain.services;

import mateusz.grabarski.cleanarchitecture.domain.factories.MessageFactory;
import mateusz.grabarski.cleanarchitecture.domain.models.Message;

public class MessageService {

    private MessageRepository messageRepository;
    private APIFasade apiFasade;
    private MessageFactory messageFactory;

    public MessageService(MessageRepository messageRepository, APIFasade apiFasade, MessageFactory messageFactory) {
        this.messageRepository = messageRepository;
        this.apiFasade = apiFasade;
        this.messageFactory = messageFactory;
    }

    public void sendMessage(String content) {
        Message messageToSend = messageFactory.build(content);

        messageRepository.saveMessage(messageToSend);
        apiFasade.sendMessage(messageToSend);

        messageToSend.send();
        messageRepository.saveMessage(messageToSend);
    }
}
