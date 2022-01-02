package fr.hervedarritchon.app.sfgsm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElementMapperTest {

    private ElementMapper elementMapper;

    @BeforeEach
    public void setUp() {
        elementMapper = new ElementMapperImpl();
    }
}
