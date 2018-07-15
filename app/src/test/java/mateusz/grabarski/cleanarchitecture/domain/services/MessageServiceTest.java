package mateusz.grabarski.cleanarchitecture.domain.services;

import org.junit.Test;

import mateusz.grabarski.cleanarchitecture.domain.factories.MessageFactory;
import mateusz.grabarski.cleanarchitecture.domain.models.Message;
import mateusz.grabarski.cleanarchitecture.domain.models.exceptions.ValidationMessageException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MessageServiceTest {

    public static final String MESSAGE_CONTENT = "message content";

    @Test
    public void messageShouldBeSaveAndSendAsMessageToApi() {
        // given
        MessageRepository repository = mock(MessageRepository.class);
        APIFasade fasade = mock(APIFasade.class);
        Message message = mock(Message.class);

        MessageFactory factory = mock(MessageFactory.class);
        when(factory.build(MESSAGE_CONTENT)).thenReturn(message);

        MessageService messageService = new MessageService(repository, fasade, factory);

        // when
        messageService.sendMessage(MESSAGE_CONTENT);

        // then
        verify(fasade, times(1)).sendMessage(message);
        verify(message).send();
        verify(repository, times(2)).saveMessage(message);
    }

    @Test(expected = ValidationMessageException.class)
    public void messageWithEmptyContentShouldNotBeSent() {
        // given
        MessageRepository repository = mock(MessageRepository.class);
        APIFasade fasade = mock(APIFasade.class);
        MessageFactory factory = mock(MessageFactory.class);

        when(factory.build(MESSAGE_CONTENT)).thenThrow(new ValidationMessageException("Exception message"));

        MessageService service = new MessageService(repository, fasade, factory);

        // when
        service.sendMessage(MESSAGE_CONTENT);
    }
}