package dev.gyeoul.urlshortener.repository;

import dev.gyeoul.urlshortener.jooq.tables.pojos.Url;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import dev.gyeoul.urlshortener.jooq.tables.Jurl;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UrlRepository {
    
    private final DSLContext dsl;

    public Optional<Url> findByShorten(String shorten) {
        return dsl
                .selectFrom(Jurl.url)
                .where(Jurl.url.SHORTEN.eq(shorten))
                .fetchOptionalInto(Url.class);
    }

    @Transactional
    public Url save(String shorten, String original) {
        LocalDateTime now = LocalDateTime.now();
        
        return dsl
                .insertInto(Jurl.url)
                .set(Jurl.url.SHORTEN, shorten)
                .set(Jurl.url.ORIGINAL, original)
                .set(Jurl.url.CREATED_AT, now)
                .set(Jurl.url.UPDATED_AT, now)
                .returning()
                .fetchOneInto(Url.class);
    }

    @Transactional
    public boolean deleteById(Long id) {
        return dsl
                .deleteFrom(Jurl.url)
                .where(Jurl.url.ID.eq(id))
                .execute() > 0;
    }
}
