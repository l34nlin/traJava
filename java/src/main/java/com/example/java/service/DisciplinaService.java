package com.example.java.service;

import com.example.java.model.Disciplina;
import com.example.java.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Disciplina getDisciplinaById(Long id) {
        return disciplinaRepository.findById(id).orElse(null);
    }

    public Disciplina createDisciplina(Disciplina disciplina) {

        return disciplinaRepository.save(disciplina);
    }

    public Disciplina updateDisciplina(Long id, Disciplina disciplinaAtualizada) {
        Disciplina disciplinaExistente = disciplinaRepository.findById(id).orElse(null);

        if (disciplinaExistente != null) {
            disciplinaExistente.setNome(disciplinaAtualizada.getNome());
            return disciplinaRepository.save(disciplinaExistente);
        }
        return null;
    }

    public void deleteDisciplina(Long id) {
        disciplinaRepository.deleteById(id);
    }
}