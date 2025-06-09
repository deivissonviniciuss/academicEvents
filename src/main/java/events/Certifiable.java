package events;

import participants.Participant;

public interface Certifiable {
    void generateCertificate(Participant participant);
}