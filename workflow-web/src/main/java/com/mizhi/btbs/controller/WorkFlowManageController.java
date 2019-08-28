package com.mizhi.btbs.controller;

import com.mizhi.btbs.domain.dto.WfReDeploymentDTO;
import com.mizhi.btbs.response.ApiResponse;
import com.mizhi.btbs.service.WorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wf-manage")
public class WorkFlowManageController {

    @Autowired
    private WorkFlowService workFlowService;

    @PostMapping("edit")
    public ApiResponse edit(@RequestBody WfReDeploymentDTO wfReDeploymentDTO) {
        workFlowService.edit(wfReDeploymentDTO);
        return ApiResponse.create();
    }

    @PostMapping("{id}/delete")
    public ApiResponse delete(@PathVariable String id) {
        workFlowService.delete(id);
        return ApiResponse.create();
    }

    @GetMapping("{id}")
    public ApiResponse load(@PathVariable String id) {
        workFlowService.load(id);
        return ApiResponse.create();
    }
}
