package fr.hervedarritchon.app.sfgsm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import fr.hervedarritchon.app.sfgsm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ElementDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElementDTO.class);
        ElementDTO elementDTO1 = new ElementDTO();
        elementDTO1.setId(1L);
        ElementDTO elementDTO2 = new ElementDTO();
        assertThat(elementDTO1).isNotEqualTo(elementDTO2);
        elementDTO2.setId(elementDTO1.getId());
        assertThat(elementDTO1).isEqualTo(elementDTO2);
        elementDTO2.setId(2L);
        assertThat(elementDTO1).isNotEqualTo(elementDTO2);
        elementDTO1.setId(null);
        assertThat(elementDTO1).isNotEqualTo(elementDTO2);
    }
}
