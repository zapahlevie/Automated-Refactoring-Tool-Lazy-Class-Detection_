package com.finalproject.automated.refactoring.tool.lazy.classes.detection.service.implementation;

import com.finalproject.automated.refactoring.tool.model.ClassModel;
import com.finalproject.automated.refactoring.tool.model.MethodModel;
import com.finalproject.automated.refactoring.tool.model.PropertyModel;
import com.finalproject.automated.refactoring.tool.nof.detection.service.NOFDetection;
import com.finalproject.automated.refactoring.tool.nom.detection.service.NOMDetection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LazyClassDetectionImplTest {

    @Autowired
    private LazyClassDetectionImpl lazyClassDetection;

    @MockBean
    private NOMDetection nomDetection;

    @MockBean
    private NOFDetection nofDetection;

    private static final Integer FIRST_INDEX = 0;
    private static final Integer SECOND_INDEX = 1;
    private static final Integer LAZY_CLASS_COUNT = 1;
    private static final Integer EMPTY_COUNT = 0;

    private static final Long THRESHOLD = 5L;
    private static final Long FIRST_INDEX_NOM = 2L;
    private static final Long FIRST_INDEX_NOF = 1L;
    private static final Long SECOND_INDEX_NOM = 5L;
    private static final Long SECOND_INDEX_NOF = 6L;

    private List<ClassModel> classModels;

    @Before
    public void setUp() {
        classModels = createClassModels();

        when(nomDetection.nomDetection(eq(classModels.get(FIRST_INDEX))))
                .thenReturn(FIRST_INDEX_NOM);
        when(nomDetection.nomDetection(eq(classModels.get(SECOND_INDEX))))
                .thenReturn(SECOND_INDEX_NOM);

        when(nofDetection.nofDetection(eq(classModels.get(FIRST_INDEX))))
                .thenReturn(FIRST_INDEX_NOF);
        when(nofDetection.nofDetection(eq(classModels.get(SECOND_INDEX))))
                .thenReturn(SECOND_INDEX_NOF);
    }

    @Test
    public void detect_singleClass_success() {
        ClassModel classModel = lazyClassDetection.detect(classModels.get(FIRST_INDEX), THRESHOLD);
        assertNotNull(classModel);
        assertEquals(classModels.get(FIRST_INDEX), classModel);
        assertEquals("TestClassImpl", classModel.getName());
        assertEquals(2, classModel.getNom().intValue());
        assertEquals(1, classModel.getNof().intValue());
    }

    @Test
    public void detect_singleClass_success_notLazyClass() {
        ClassModel classModel = lazyClassDetection.detect(classModels.get(SECOND_INDEX), THRESHOLD);
        assertNull(classModel);
    }

    private List<ClassModel> createClassModels() {
        List<ClassModel> classModels = new ArrayList<>();
        classModels.add(
                ClassModel.builder()
                        .packageName("com.finalproject.automated.refactoring.tool")
                        .imports(Arrays.asList("import java.util.ArrayList", "import java.util.Arrays;"))
                        .fullContent("this is content; \n and this is too; \n plus; \n hmm;")
                        .keywords(Collections.singletonList("public"))
                        .name("TestClassImpl")
                        .extend("")
                        .implement("TestClass")
                        .attributes(Arrays.asList("Integer time"))
                        .methodModels(Arrays.asList(
                                MethodModel.builder()
                                        .keywords(Collections.singletonList("public"))
                                        .name("call")
                                        .parameters(null)
                                        .exceptions(Arrays.asList("Exception", "IOException"))
                                        .body("")
                                        .build(),
                                MethodModel.builder()
                                        .keywords(Collections.singletonList("public"))
                                        .returnType("MyResponse<Integer>")
                                        .name("go")
                                        .parameters(Collections.singletonList(
                                                PropertyModel.builder()
                                                        .type("Integer")
                                                        .name("time")
                                                        .build()))
                                        .body("")
                                        .build()

                        ))
                        .build()
        );
        classModels.add(
                ClassModel.builder()
                        .packageName("com.finalproject.automated.refactoring.tool")
                        .imports(Arrays.asList("import java.util.ArrayList", "import java.util.Arrays;"))
                        .fullContent("")
                        .keywords(Collections.singletonList("public"))
                        .name("TestClassImpl")
                        .extend("")
                        .implement("TestClass")
                        .attributes(Arrays.asList(
                                "Integer number",
                                "Integer phone",
                                "Date time",
                                "Boolean available",
                                "String addr"))
                        .methodModels(Arrays.asList(
                                MethodModel.builder()
                                        .keywords(Collections.singletonList("public"))
                                        .name("call")
                                        .parameters(null)
                                        .exceptions(Arrays.asList("Exception", "IOException"))
                                        .body("")
                                        .build(),
                                MethodModel.builder()
                                        .keywords(Collections.singletonList("public"))
                                        .returnType("MyResponse<Integer>")
                                        .name("go")
                                        .parameters(Collections.singletonList(
                                                PropertyModel.builder()
                                                        .type("Integer")
                                                        .name("time")
                                                        .build()))
                                        .body("")
                                        .build(),
                                MethodModel.builder()
                                        .keywords(Collections.singletonList("public"))
                                        .name("visit")
                                        .parameters(null)
                                        .exceptions(Arrays.asList("Exception", "IOException"))
                                        .body("")
                                        .build(),
                                MethodModel.builder()
                                        .keywords(Collections.singletonList("public"))
                                        .returnType("MyResponse<Integer>")
                                        .name("leave")
                                        .parameters(Collections.singletonList(
                                                PropertyModel.builder()
                                                        .type("Integer")
                                                        .name("time")
                                                        .build()))
                                        .body("")
                                        .build(),
                                MethodModel.builder()
                                        .keywords(Collections.singletonList("public"))
                                        .returnType("MyResponse<Integer>")
                                        .name("repeat")
                                        .parameters(Collections.singletonList(
                                                PropertyModel.builder()
                                                        .type("Integer")
                                                        .name("time")
                                                        .build()))
                                        .body("")
                                        .build()
                        ))
                        .build()
        );

        return classModels;
    }

    private ClassModel createClassModel1() {
        return null;
    }

    private ClassModel createClassModel2() {
        return null;

    }
}
