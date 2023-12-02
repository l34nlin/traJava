package com.example.java.service;

import com.example.java.model.Curso;
import com.example.java.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso getCursoById(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso createCurso(Curso curso) {

        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Long id, Curso cursoAtualizado) {
        Curso cursoExistente = cursoRepository.findById(id).orElse(null);

        if (cursoExistente != null) {
            cursoExistente.setNome(cursoAtualizado.getNome());



            return cursoRepository.save(cursoExistente);
        }

        return null;
    }

    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }

}
