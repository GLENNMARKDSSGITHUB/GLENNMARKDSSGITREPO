package com.dss.client.proxy.actors;

import com.dss.client.dto.actors.ActorsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${feign.actor-name}" , url ="${feign.actor-url}")
public interface ActorsProxy {

    @PostMapping("/API/actor/add-actor.do")
    String addActor(@RequestBody ActorsDTO actorsDto);

    @GetMapping("/API/actor/display-actors.do")
    List<ActorsDTO> displayActors();

    @GetMapping("/API/actor/search-actor.do/{firstName}/{lastName}")
    List<ActorsDTO> searchActorByActorName(@PathVariable("firstName") String firstName,
                                               @PathVariable("lastName") String lastName);

    @PutMapping("/API/actor/update-actor.do")
    String updateActor(@RequestBody ActorsDTO actorsDto);

    @DeleteMapping("/API/actor/delete-actor.do/{firstName}/{lastName}")
    String deleteActor(@PathVariable("firstName") String firstName,
                              @PathVariable("lastName") String lastName);
}
