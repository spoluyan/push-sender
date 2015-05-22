package pw.spn.pushsender.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import pw.spn.pushsender.entity.AuthToken;

public interface AuthTokenRepository extends PagingAndSortingRepository<AuthToken, Long> {
    Long countByDevicePlatformIdIn(List<Integer> devicePlatformIds);

    Page<AuthToken> findByDevicePlatformIdInOrderByIdAsc(List<Integer> devicePlatformIds, Pageable pageable);
}
