package ru.spb.coverage;

import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.tools.ExecFileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ReportAnalyzer {

    private static Logger log = LoggerFactory.getLogger(ReportAnalyzer.class);

    public CoverageBuilder analyze(String reportFilePath, String sourceClassesDir) {
        var execFileLoader = new ExecFileLoader();
        var coverageBuilder = new CoverageBuilder();
        var sourceDir = new File(sourceClassesDir);

        try {
            execFileLoader.load(new File(reportFilePath));
            var analyzer = new Analyzer(execFileLoader.getExecutionDataStore(), coverageBuilder);
            analyzer.analyzeAll(sourceDir);
        } catch (IOException e) {
            log.error("Something goes wrong during analyzing classes");
            throw new CoveragePluginException("Can't analyze .exec report" ,e);
        }

        return coverageBuilder;
    }
}
