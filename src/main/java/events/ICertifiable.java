package events;

import participants.Participant;

public interface ICertifiable {
    void generateCertificate(Participant participant);
}