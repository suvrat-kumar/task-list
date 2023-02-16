package com.codurance.training.tasks.command;

import com.codurance.training.tasks.service.HelperPrintService;

public class CommandExecutorImpl implements CommandExecutor {

    private final HelperPrintService helperPrintService;

    public CommandExecutorImpl(HelperPrintService helperPrintService) {
        this.helperPrintService = helperPrintService;
    }

    @Override
    public void help() {
        helperPrintService.help();
    }

    @Override
    public void error(String command) {
        helperPrintService.error(command);
    }

}
