package com.examp.smartfield.service;

import com.examp.smartfield.model.Delete;
import com.examp.smartfield.repository.DeleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeleteService {

    @Autowired
    private DeleteRepository deleteRepository;

    public List<Delete> getAllDeletes() {
        return deleteRepository.findAll();
    }

    public Optional<Delete> getDeleteById(Integer id) {
        return deleteRepository.findById(id);
    }

    public Delete saveDelete(Delete delete) {
        return deleteRepository.save(delete);
    }

    public void deleteById(Integer id) {
        deleteRepository.deleteById(id);
    }
}
