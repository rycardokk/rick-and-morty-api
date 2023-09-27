package com.rycardokkcode.webclientrickandmortyapi.client;

import com.rycardokkcode.webclientrickandmortyapi.response.CharacterResponse;
import com.rycardokkcode.webclientrickandmortyapi.response.EpisodeResponse;
import com.rycardokkcode.webclientrickandmortyapi.response.ListOfEpisodes;
import com.rycardokkcode.webclientrickandmortyapi.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RickAndMortyClient {

    private final WebClient webClient;

    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<CharacterResponse> findAndCharacterById(String id){
        log.info("Buscando o personagem com o id [{}]", id);
        return webClient
                .get()
                .uri("/character/"+ id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parametros informados")))
                .bodyToMono(CharacterResponse.class);
    }


    public Mono<LocationResponse> findAnLocationById(String id){
        log.info("Buscando o localizacao com o id [{}]", id);
        return webClient
                .get()
                .uri("/location/"+ id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parametros informados")))
                .bodyToMono(LocationResponse.class);
    }

    public Mono<EpisodeResponse> findAnEpisodeById(String id){
        log.info("Buscando o episodio com o id [{}]", id);
        return webClient
                .get()
                .uri("/episode/"+ id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parametros informados")))
                .bodyToMono(EpisodeResponse.class);
    }

    public Flux<ListOfEpisodes> getAllEpisodes (){
        log.info("Listando todos os episodios");
        return webClient
                .get()
                .uri("/episode/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parametros informados")))
                .bodyToFlux(ListOfEpisodes.class);
    }

}
