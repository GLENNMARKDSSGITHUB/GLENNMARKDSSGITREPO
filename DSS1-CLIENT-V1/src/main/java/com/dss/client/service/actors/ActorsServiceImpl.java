package com.dss.client.service.actors;

import com.dss.client.dto.actors.ActorsDTO;
import com.dss.client.proxy.actors.ActorsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorsServiceImpl implements ActorsService{

    @Autowired
    ActorsProxy actorsProxy;

    @Override
    public String addActor(ActorsDTO actorsDto) {
        return actorsProxy.addActor(actorsDto);
    }

    @Override
    public List<ActorsDTO> displayActors() {
        return actorsProxy.displayActors();
    }

    @Override
    public List<ActorsDTO> searchActorByActorName(String firstName, String lastName) {
        return actorsProxy.searchActorByActorName(firstName, lastName);
    }

    @Override
    public String updateActor(ActorsDTO actorsDto) {
        return actorsProxy.updateActor(actorsDto);
    }

    @Override
    public String deleteActor(String firstName, String lastName) {
        return actorsProxy.deleteActor(firstName, lastName);
    }
}
