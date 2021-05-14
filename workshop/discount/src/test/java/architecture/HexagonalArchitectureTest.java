package architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "devoxxfr2020", importOptions = {ImportOption.DoNotIncludeTests.class})
public class HexagonalArchitectureTest {

    //@ArchTest
    public static ArchRule enforceHexagonalArchitechture =
            classes().that()
                    .resideInAPackage("devoxxfr2020.cashregister.domain..")
                    .should()
                    .onlyAccessClassesThat()
                    .resideInAnyPackage(
                            "devoxxfr2020.cashregister.domain..",
                            "java.util..",
                            "java.lang.."
                    );

}
