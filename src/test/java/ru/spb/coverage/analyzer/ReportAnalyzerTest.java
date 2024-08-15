package ru.spb.coverage.analyzer;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.analysis.IClassCoverage;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Collection;
import java.util.stream.Stream;

class ReportAnalyzerTest {

    @Test
    void shouldBuildCoverageReport() {
        //when
        var reportFile = new File("src/test/resources/reports/jacoco_report.exec");
        var sourceClassesDir = new File("src/test/resources/classes");

        //do
        var coverageBuilder = ReportAnalyzer.analyze(
                reportFile,
                sourceClassesDir
        );

        //verify
        Assertions.assertThat(coverageBuilder)
                .isNotNull()
                .extracting(CoverageBuilder::getClasses)
                .extracting(Collection::stream)
                .extracting(Stream::toList)
                .asInstanceOf(InstanceOfAssertFactories.list(IClassCoverage.class))
                .singleElement()
                .extracting(IClassCoverage::getSourceFileName)
                .isNotNull()
                .matches(classCoverage -> classCoverage.contains("SortUtils"));
    }
}