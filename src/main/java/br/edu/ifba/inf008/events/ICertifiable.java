package br.edu.ifba.inf008.events;

import br.edu.ifba.inf008.participants.Participant;

public interface ICertifiable {
    void generateCertificate(Participant participant);
}