package zawkin.me.asuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zawkin.me.asuna.entity.WaifuEntity;

public interface WaifuRepository extends JpaRepository<WaifuEntity, Long> {
}
