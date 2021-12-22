package com.example.demo.controller;

import com.example.demo.dto.TargetMethodDto;
import com.example.demo.service.TransferService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TransferController {
    public static final  Logger LOGGER = LoggerFactory.getLogger(TransferController.class);

    private TransferService transferService;

    @PostMapping("/transfer")
    @ResponseBody
    public ResponseEntity<Object> testTargetMethod(@RequestBody TargetMethodDto targetMethod) {
        Object result = null;
        try {
            result = transferService.invokeTargetMethodByBean(targetMethod.getClassName(), targetMethod.getMethodName(), targetMethod.getParams());
        } catch (Exception e) {
            LOGGER.warn("TESTING METHOD: {} ERROR", targetMethod.getMethodName(), e);
        }
        return new ResponseEntity<>(result, OK);
    }
}
