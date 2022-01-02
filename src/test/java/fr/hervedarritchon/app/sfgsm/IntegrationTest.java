package fr.hervedarritchon.app.sfgsm;

import fr.hervedarritchon.app.sfgsm.SushiFusionGsmApp;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = SushiFusionGsmApp.class)
public @interface IntegrationTest {
}
