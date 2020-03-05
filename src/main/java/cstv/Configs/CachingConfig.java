package cstv.Configs;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CachingConfig extends CachingConfigurerSupport {

    @Bean
    @Primary
    public CacheManager cacheManager1() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        List<String> caches = new ArrayList<>();
        caches.add("user-by-username");
        caches.add("all-players");
        caches.add("five-first-players");
        caches.add("all-teams-35");
        caches.add("all-teams-sorted");
        caches.add("all-players-by-team");
        caches.add("ten-first-teams");
        caches.add("five-first-teams");
        cacheManager.setCacheNames(caches);
        cacheManager.setCaffeine(caffeineCacheBuilder1());

        return cacheManager;
    }

    @Bean
    public CacheManager cacheManager2() {
        CaffeineCacheManager matchesCacheManager = new CaffeineCacheManager();

        List<String> matchesCaches = new ArrayList<>();
        matchesCaches.add("five-upc-matches-by-team");
        matchesCaches.add("five-upc-matches");
        matchesCaches.add("all-upc-matches");
        matchesCaches.add("five-ended-matches-by-team");
        matchesCaches.add("five-ended-matches");
        matchesCaches.add("thirty-ended-matches");
        matchesCaches.add("all-ended-matches");

        matchesCacheManager.setCacheNames(matchesCaches);
        matchesCacheManager.setCaffeine(caffeineCacheBuilder2());

        return matchesCacheManager;
    }

    /*
    *   As https://www.javadevjournal.com/spring-boot/spring-boot-with-caffeine-cache/,
    *   we add caffeineCacheBuilder to set expireTime and other main features
    */

    Caffeine<Object,Object> caffeineCacheBuilder1() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .expireAfterWrite(300, TimeUnit.SECONDS)
                .weakKeys()
                .recordStats();
    }

    Caffeine<Object,Object> caffeineCacheBuilder2() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .expireAfterWrite(90, TimeUnit.SECONDS)
                .weakKeys()
                .recordStats();
    }
}