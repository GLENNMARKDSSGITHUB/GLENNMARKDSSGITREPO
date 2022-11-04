package com.dss.client;

import com.dss.configuration.feignclient.CustomFeignClientConfiguration;
import com.dss.dto.actors.ActorsDTO;
import com.dss.entity.actors.Actors;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@FeignClient(value = "${app.feign.config.name}", url = "${app.feign.config.url}", configuration = CustomFeignClientConfiguration.class)
public interface StoreClient {

    @PostMapping("/API/actor/add-actor.do")
    ResponseEntity<String> addActor(@RequestBody ActorsDTO actorsDto);

    @GetMapping("/API/actor/display-actors.do")
    ResponseEntity<List<Actors>> displayActors();

    @GetMapping("/API/actor/search-actor.do")
    ResponseEntity<List<Actors>> searchActorByActorName(HttpServletRequest request);

    @PutMapping("/API/actor/update-actor.do")
    ResponseEntity<String> updateActor(@RequestBody ActorsDTO actorsDto);

    @DeleteMapping("/API/actor/delete-actor.do")
    ResponseEntity<String> deleteActor(HttpServletRequest request);
}
