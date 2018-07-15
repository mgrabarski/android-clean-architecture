package mateusz.grabarski.cleanarchitecture.domain.services;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;

public interface MessageRepository {
    void saveMessage(Message message);
}
