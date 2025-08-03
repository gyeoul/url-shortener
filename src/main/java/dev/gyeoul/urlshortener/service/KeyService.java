package dev.gyeoul.urlshortener.service;

import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
import dev.gyeoul.urlshortener.config.NanoIdConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeyService {
    
    private final NanoIdConfig nanoIdConfig;
    
    public String generateNanoId() {
        return NanoIdUtils.randomNanoId(nanoIdConfig.getAlphabet(), nanoIdConfig.getLength());
    }
}
