package com.examp.smartfield.service;

import com.examp.smartfield.model.Crop;
import com.examp.smartfield.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    public Optional<Crop> getCropById(Long id) {
        return cropRepository.findById(id);
    }

    public Crop saveCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    public void deleteCrop(Long id) {
        cropRepository.deleteById(id);
    }
    public void deleteCropById(Long id) {
        cropRepository.deleteById(id);
    }

}
