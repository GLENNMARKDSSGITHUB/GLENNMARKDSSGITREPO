package com.dss.client.controller.actors;

import com.dss.client.dto.actors.ActorsDTO;
import com.dss.client.service.actors.ActorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/actor")
public class ActorsController {

    @Autowired
    private ActorsService actorsService;

    @PostMapping("/add-actor.do")
    String addActor(@RequestBody ActorsDTO actorsDto){
        return actorsService.addActor(actorsDto);
    }

    @GetMapping("/display-actors.do")
    List<ActorsDTO> displayActors(){
        return actorsService.displayActors();
    }

    @GetMapping("/search-actor.do/{firstName}/{lastName}")
    List<ActorsDTO> searchActorByActorName(@PathVariable("firstName") String firstName,
                                           @PathVariable("lastName") String lastName){
        return actorsService.searchActorByActorName(firstName, lastName);
    }

    @PutMapping("/update-actor.do")
    String updateActor(@RequestBody ActorsDTO actorsDto){
        return actorsService.updateActor(actorsDto);
    }

    @DeleteMapping("/delete-actor.do/{firstName}/{lastName}")
    String deleteActor(@PathVariable("firstName") String firstName,
                       @PathVariable("lastName") String lastName){
        return actorsService.deleteActor(firstName, lastName);
    }
}
