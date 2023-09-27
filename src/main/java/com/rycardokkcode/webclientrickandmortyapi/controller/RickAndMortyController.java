package com.rycardokkcode.webclientrickandmortyapi.controller;

import com.rycardokkcode.webclientrickandmortyapi.client.RickAndMortyClient;
import com.rycardokkcode.webclientrickandmortyapi.response.CharacterResponse;
import com.rycardokkcode.webclientrickandmortyapi.response.EpisodeResponse;
import com.rycardokkcode.webclientrickandmortyapi.response.ListOfEpisodes;
import com.rycardokkcode.webclientrickandmortyapi.response.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class RickAndMortyController {

    RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    public Mono<CharacterResponse> getCharacterById (@PathVariable String id){
        return rickAndMortyClient.findAndCharacterById(id);
    }

    @GetMapping("/location/{id}")
    public Mono<LocationResponse> getLocationById (@PathVariable String id){
        return rickAndMortyClient.findAnLocationById(id);
    }

    @GetMapping("/episode/{id}")
    public Mono<EpisodeResponse> getEpisodeByid (@PathVariable String id){
        return rickAndMortyClient.findAnEpisodeById(id);
    }

    @GetMapping("/episode/{id}")
    public Flux<ListOfEpisodes> getAllEpisodes(){
        return rickAndMortyClient.getAllEpisodes();
    }

}
