package com.finalproject.automated.refactoring.tool.lazy.classes.detection.service.implementation;

import com.finalproject.automated.refactoring.tool.lazy.classes.detection.service.LazyClassDetection;
import com.finalproject.automated.refactoring.tool.locs.detection.service.LocsDetection;
import com.finalproject.automated.refactoring.tool.model.ClassModel;
import com.finalproject.automated.refactoring.tool.nof.detection.service.NOFDetection;
import com.finalproject.automated.refactoring.tool.nom.detection.service.NOMDetection;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LazyClassDetectionImpl implements LazyClassDetection {

    @Autowired
    private LocsDetection locsDetection;

    @Autowired
    private NOMDetection nomDetection;

    @Autowired
    private NOFDetection nofDetection;

    private static final Integer FIRST_INDEX = 0;

    @Override
    public ClassModel detect(@NonNull ClassModel classModel, @NonNull Long threshold) {
        try {
            return detect(Collections.singletonList(classModel), threshold)
                    .get(FIRST_INDEX);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public List<ClassModel> detect(@NonNull List<ClassModel> classModels,
                                   @NonNull Long threshold) {
        return classModels.stream()
                .map(this::metricsCalculation)
                .filter(classModel -> isLazyClass(classModel, threshold))
                .collect(Collectors.toList());
    }

    private ClassModel metricsCalculation(ClassModel classModel) {
        Long nom = nomDetection.nomDetection(classModel);
        Long nof = nofDetection.nofDetection(classModel);
        classModel.setNom(nom);
        classModel.setNof(nof);

        return classModel;
    }

    private Boolean isLazyClass(ClassModel classModel, Long threshold) {
        return classModel.getNom() < threshold && classModel.getNof() < threshold;
    }
}
