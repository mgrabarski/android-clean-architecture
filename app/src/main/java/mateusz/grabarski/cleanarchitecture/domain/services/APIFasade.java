package mateusz.grabarski.cleanarchitecture.domain.services;

import mateusz.grabarski.cleanarchitecture.domain.models.Message;

public interface APIFasade {
    void sendMessage(Message message);
}
