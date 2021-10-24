package com.project.autotest.fun.config;

import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.*;

import java.util.List;

public class CustomReporter implements Reporter, Formatter {

    public CustomReporter() {
    }

    @Override
    public void uri(String uri) {
    }

    @Override
    public void feature(Feature feature) {
    }

    @Override
    public void background(Background background) {
    }


    @Override
    public void scenarioOutline(ScenarioOutline scenarioOutline) {
    }

    @Override
    public void examples(Examples examples) {
    }

    @Override
    public void step(Step step) {
    }

    @Override
    public void eof() {
    }


    @Override
    public void done() {
    }

    @Override
    public void close() {
    }

    @Override
    public void syntaxError(String state, String event, List<String> legalEvents, String uri, Integer line) {
        // TODO Auto-generated method stub

    }

    @Override
    public void startOfScenarioLifeCycle(Scenario scenario) {
        // TODO Auto-generated method stub

    }

    @Override
    public void scenario(Scenario scenario) {
        // TODO Auto-generated method stub

    }

    @Override
    public void endOfScenarioLifeCycle(Scenario scenario) {
        // TODO Auto-generated method stub

    }

    @Override
    public void before(Match match, Result result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void result(Result result) {


    }

    @Override
    public void after(Match match, Result result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void match(Match match) {
        // TODO Auto-generated method stub

    }

    @Override
    public void embedding(String mimeType, byte[] data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void write(String text) {
        // TODO Auto-generated method stub

    }


}