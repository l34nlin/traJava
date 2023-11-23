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
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID: " + id));
    }

    public Aluno createAluno(Aluno aluno) {
        // Lógica de validação antes de salvar, se necessário
        // Exemplo: validar se a nota está no intervalo permitido
        validateAluno(aluno);

        // Inicializar o status de aprovação com base na nota
        aluno.setAprovado(aluno.getNota() >= 7);

        // Salvar o aluno
        return alunoRepository.save(aluno);
    }

    public Aluno updateAluno(Long id, Aluno alunoAtualizado) {
        // Buscar o aluno existente pelo ID
        Aluno alunoExistente = getAlunoById(id);

        // Atualizar os campos do aluno existente com os novos dados
        alunoExistente.setNome(alunoAtualizado.getNome());
        alunoExistente.setIdade(alunoAtualizado.getIdade());
        alunoExistente.setComentarios(alunoAtualizado.getComentarios());
        alunoExistente.setNota(alunoAtualizado.getNota());

        // Lógica de validação antes de salvar, se necessário
        validateAluno(alunoExistente);

        // Atualizar o status de aprovação com base na nota
        alunoExistente.setAprovado(alunoExistente.getNota() >= 7);

        // Salvar o aluno atualizado
        return alunoRepository.save(alunoExistente);
    }

    public void deleteAluno(Long id) {
        // Verificar se o aluno existe antes de excluir
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Aluno não encontrado com o ID: " + id);
        }
    }

    // Método privado para validar um aluno
    private void validateAluno(Aluno aluno) {
        // Lógica de validação, se necessário
        if (aluno.getNota() < 0 || aluno.getNota() > 10) {
            throw new IllegalArgumentException("A nota do aluno deve estar entre 0 e 10.");
        }
    }
}
