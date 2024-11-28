package com.example.projetjeespringboot.Service;

import com.example.projetjeespringboot.Model.Enseignant;
import com.example.projetjeespringboot.Repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnseignantService {
    @Autowired
    private EnseignantRepository enseignantRepository;

    public Enseignant saveEnseignant(Enseignant enseignant)
    {
        return enseignantRepository.save(enseignant);
    }

    /*
    public Enseignant updateEnseignant(Enseigantn enseignant, String enseignantId)
    {
        //
    }
    */

    public void deleteEnseignantById(String enseignantId)
    {
        enseignantRepository.deleteById(enseignantId);
    }
}
