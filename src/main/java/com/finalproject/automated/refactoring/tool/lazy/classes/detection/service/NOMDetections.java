package com.finalproject.automated.refactoring.tool.lazy.classes.detection.service;

import com.finalproject.automated.refactoring.tool.model.ClassModel;
import lombok.NonNull;

public interface NOMDetections {

    Long nomDetections(@NonNull ClassModel classModel);
}
