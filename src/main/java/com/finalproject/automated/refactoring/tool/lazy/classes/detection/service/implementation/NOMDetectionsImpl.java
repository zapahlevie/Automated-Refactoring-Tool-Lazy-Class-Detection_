package com.finalproject.automated.refactoring.tool.lazy.classes.detection.service.implementation;

import com.finalproject.automated.refactoring.tool.lazy.classes.detection.service.NOMDetections;
import com.finalproject.automated.refactoring.tool.model.ClassModel;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class NOMDetectionsImpl implements NOMDetections {

    @Override
    public Long nomDetections(@NonNull ClassModel classModel) {
        if(classModel.getMethodModels() == null){
            return 0L;
        }
        return Long.valueOf(classModel.getMethodModels().size());
    }
}
