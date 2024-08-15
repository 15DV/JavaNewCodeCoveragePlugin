package ru.spb.coverage.analyzer;

import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.tools.ExecFileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ReportAnalyzer {

    private ReportAnalyzer() {
    }

    private static final Logger log = LoggerFactory.getLogger(ReportAnalyzer.class);

    public static CoverageBuilder analyze(File reportFile, File sourceClassesDir) {
        var execFileLoader = new ExecFileLoader();
        var coverageBuilder = new CoverageBuilder();

        try {
            execFileLoader.load(reportFile);
            var analyzer = new Analyzer(execFileLoader.getExecutionDataStore(), coverageBuilder);
            analyzer.analyzeAll(sourceClassesDir);
        } catch (IOException e) {
            log.error("Something goes wrong during analyzing classes");
            throw new CoveragePluginException("Can't analyze .exec report", e);
        }

        return coverageBuilder;
    }
}
