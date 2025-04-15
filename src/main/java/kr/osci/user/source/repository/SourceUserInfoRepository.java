package kr.osci.user.source.repository;

import kr.osci.user.source.entity.SourceUserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceUserInfoRepository extends JpaRepository<SourceUserInfoEntity, Long> {
}
