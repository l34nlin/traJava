package com.example.java.service;

import com.example.java.model.Aluno;
import com.example.java.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno getAlunoById(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não" + id +"nao encontrado"));
    }

    public Aluno createAluno(Aluno aluno) {
        validateAluno(aluno);
        aluno.setStatusAtivo(aluno.getNota()==0);
        aluno.setAprovado(aluno.getNota() >= 7);
        return alunoRepository.save(aluno);
    }

    public Aluno updateAluno(Long id, Aluno alunoAtualizado) {

        Aluno alunoExistente = getAlunoById(id);


        alunoExistente.setNome(alunoAtualizado.getNome());
        alunoExistente.setIdade(alunoAtualizado.getIdade());
        alunoExistente.setComentarios(alunoAtualizado.getComentarios());
        alunoExistente.setNota(alunoAtualizado.getNota());


        validateAluno(alunoExistente);
//se eu nao valida dnv saporr tem chance de mudar a nota para menos de 7 eeContinuar ativo
        alunoExistente.setAprovado(alunoExistente.getNota() >= 7);
        return alunoRepository.save(alunoExistente);
    }

    public void deleteAluno(Long id) {

        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("aluno não encontrado");
        }
    }

    private void validateAluno(Aluno aluno) {

        if (aluno.getNota() < 0 || aluno.getNota() > 10) {
            throw new IllegalArgumentException("a nota do alunor tend estar entre 0 e 10.");
        }
    }
}
