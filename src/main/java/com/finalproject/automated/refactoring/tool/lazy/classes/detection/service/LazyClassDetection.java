package com.finalproject.automated.refactoring.tool.lazy.classes.detection.service;

import com.finalproject.automated.refactoring.tool.model.ClassModel;
import lombok.NonNull;

import java.util.List;

public interface LazyClassDetection {
    ClassModel detect(@NonNull ClassModel classModel, @NonNull Long threshold);

    List<ClassModel> detect(@NonNull List<ClassModel> classModels, @NonNull Long threshold);
}
