package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.config.SystemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author VHBin
 * @date 2021/12/27 - 12:45
 */

@RestController
@RequestMapping("/maintenance")
public class SystemController {
    @Autowired
    private SystemStatus status;

    @RequestMapping("")
    public Boolean respond() {
        return status.getStatus();
    }
}
