package kr.osci.user.batch.repository;

import kr.osci.user.batch.entity.BatchUserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchUserInfoRepository extends JpaRepository<BatchUserInfoEntity, Long> {
}
