package kr.osci.user.batch.service;

import kr.osci.user.batch.entity.BatchUserInfoEntity;
import kr.osci.user.batch.repository.BatchUserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchUserInfoService {

    private static final Logger log = LoggerFactory.getLogger(BatchUserInfoService.class);

    private final BatchUserInfoRepository batchUserInfoRepository;

    public BatchUserInfoService(BatchUserInfoRepository batchUserInfoRepository) {
        this.batchUserInfoRepository = batchUserInfoRepository;
    }

    public List<BatchUserInfoEntity> findAll() {
        return batchUserInfoRepository.findAll();
    }

    public BatchUserInfoEntity findById(Long id) {
        return batchUserInfoRepository.findById(id).orElse(null);
    }

}
