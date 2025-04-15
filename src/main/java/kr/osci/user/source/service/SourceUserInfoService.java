package kr.osci.user.source.service;

import kr.osci.user.source.entity.SourceUserInfoEntity;
import kr.osci.user.source.repository.SourceUserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceUserInfoService {

    private static final Logger log = LoggerFactory.getLogger(SourceUserInfoService.class);

    private final SourceUserInfoRepository sourceUserInfoRepository;

    public SourceUserInfoService(SourceUserInfoRepository sourceUserInfoRepository) {
        this.sourceUserInfoRepository = sourceUserInfoRepository;
    }

    public List<SourceUserInfoEntity> findAll() {
        return sourceUserInfoRepository.findAll();
    }

    public SourceUserInfoEntity findById(Long id) {
        return sourceUserInfoRepository.findById(id).orElse(null);
    }

}
