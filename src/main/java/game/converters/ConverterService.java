package game.converters;

import org.springframework.stereotype.Service;

@Service
public class ConverterService {
    private final TileConverter tileConverter;

    public ConverterService(TileConverter tileConverter) {
        this.tileConverter = tileConverter;
    }

    public TileConverter getTileConverter() {
        return tileConverter;
    }
}
