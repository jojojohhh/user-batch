package kr.osci.user.controller;

import kr.osci.user.batch.entity.BatchUserInfoEntity;
import kr.osci.user.batch.service.BatchUserInfoService;
import kr.osci.user.source.entity.SourceUserInfoEntity;
import kr.osci.user.source.service.SourceUserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestUserInfoController {

    private final SourceUserInfoService sourceUserInfoService;
    private final BatchUserInfoService batchUserInfoService;

    public RestUserInfoController(SourceUserInfoService sourceUserInfoService, BatchUserInfoService batchUserInfoService) {
        this.sourceUserInfoService = sourceUserInfoService;
        this.batchUserInfoService = batchUserInfoService;
    }

    @GetMapping("/source/users")
    public List<SourceUserInfoEntity> getSourceUserInfo() {
        return sourceUserInfoService.findAll();
    }

    @GetMapping("/source/users/{id}")
    public SourceUserInfoEntity getSourceUserInfo(@PathVariable Long id) {
        return sourceUserInfoService.findById(id);
    }

    @GetMapping("/batch/users")
    public List<BatchUserInfoEntity> getBatchUserInfo() {
        return batchUserInfoService.findAll();
    }

    @GetMapping("/batch/users/{id}")
    public BatchUserInfoEntity getBatchUserInfo(@PathVariable Long id) {
        return batchUserInfoService.findById(id);
    }

}
