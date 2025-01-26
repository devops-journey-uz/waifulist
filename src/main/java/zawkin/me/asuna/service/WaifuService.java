package zawkin.me.asuna.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zawkin.me.asuna.dto.WaifuDTO;
import zawkin.me.asuna.entity.WaifuEntity;
import zawkin.me.asuna.repository.WaifuRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WaifuService {

    private final WaifuRepository waifuRepository;

    public WaifuEntity addWaifu(WaifuDTO waifuDTO) {
        WaifuEntity waifu = WaifuEntity.builder()
                .name(waifuDTO.getName())
                .anime(waifuDTO.getAnime())
                .description(waifuDTO.getDescription())
                .createdDate(LocalDateTime.now())
                .build();
        return waifuRepository.save(waifu);
    }

    public List<WaifuDTO> getAllWaifus() {
        return waifuRepository.findAll()
                .stream()
                .map(waifu -> {
                    WaifuDTO dto = new WaifuDTO();
                    dto.setId(waifu.getId());
                    dto.setName(waifu.getName());
                    dto.setAnime(waifu.getAnime());
                    dto.setDescription(waifu.getDescription());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public void deleteWaifu(Long id) {
        waifuRepository.deleteById(id);
    }
}

