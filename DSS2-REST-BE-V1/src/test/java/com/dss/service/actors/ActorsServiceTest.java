package com.dss.service.actors;

import com.dss.dto.actors.ActorsDTO;
import com.dss.entity.actors.Actors;
import com.dss.transformer.actors.ActorsTransformerTest;
import com.dss.repository.actors.ActorsRepository;
import com.dss.service.actors.ActorsService;
import com.dss.util.enums.UserRoles;
import com.dss.util.utils.DssCommonMessageDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActorsServiceTest {

    @Autowired
    private ActorsService actorsService;

    @MockBean
    private ActorsRepository actorsRepository;

    private final ActorsTransformerTest transformer = new ActorsTransformerTest();

    @Test
    public void testAddActor(){
        boolean isSuccess = true;

        Actors actor = actorsRepository.save(transformer.transformToActor(actorDto()));
        Mockito.when(actor).thenReturn(transformer.transformToActor(actorDto()));

        DssCommonMessageDetails commonMsgDtl = actorsService.addActor(actorDto());
        commonMsgDtl.setSuccess(true);
        assertThat(commonMsgDtl.isSuccess()).isEqualTo(isSuccess);
    }

    @Test
    public void testDisplayActor(){
        List<Actors> actorsList = Collections.singletonList(transformer.transformToActor(actorDto()));
        List<Actors> resultList = actorsRepository.findAll();
        Mockito.when(resultList).thenReturn(actorsList);

        DssCommonMessageDetails commonMsgDtl = actorsService.displayActors();
        commonMsgDtl.setObjList(actorsRepository.findAll());

        assertThat(commonMsgDtl.getObjList()).isEqualTo(actorsList);
    }

    @Test
    public void testSearchActor(){
        List<Actors> actorsList = Collections.singletonList(transformer.transformToActor(actorDto()));
        List<Actors> resultList = actorsRepository.findActorsByActorName("Robert", "Downey Jr.");
        Mockito.when(resultList).thenReturn(actorsList);

        DssCommonMessageDetails commonMsgDtl = actorsService.searchActorByActorName("Robert", "Downey Jr.");
        commonMsgDtl.setObjList(resultList);
        assertThat(actorsService.searchActorByActorName("Robert", "Downey Jr.")).isEqualTo(commonMsgDtl);
    }

    @Test
    public void testUpdateActor(){
        boolean isSuccess = true;
        List<Actors> actorsList = Collections.singletonList(transformer.transformToActor(actorDto()));
        List<Actors> resultList = actorsRepository.findActorsByActorName("Robert", "Downey Jr.");
        Mockito.when(resultList).thenReturn(actorsList);

        transformer.transformToActor(actorDto()).setFirstName("Glenn Mark");
        Actors actor = actorsRepository.save(transformer.transformToActor(actorDto()));
        Mockito.when(actor).thenReturn(transformer.transformToActor(actorDto()));

        DssCommonMessageDetails commonMsgDtl = actorsService.updateActor(actorDto());
        commonMsgDtl.setSuccess(true);
        assertThat(commonMsgDtl.isSuccess()).isEqualTo(isSuccess);
    }

    @Test
    public void testDeleteActor(){
        boolean isSuccess = true;
        List<Actors> actorsList = Collections.singletonList(transformer.transformToActor(actorDto()));
        List<Actors> resultList = actorsRepository.findActorsByActorName("Robert", "Downey Jr.");
        Mockito.when(resultList).thenReturn(actorsList);

        DssCommonMessageDetails commonMsgDtl = actorsService.deleteActor("Robert", "Downey Jr.");
        commonMsgDtl.setSuccess(true);
        assertThat(commonMsgDtl.isSuccess()).isEqualTo(isSuccess);
        assertTrue(resultList.isEmpty());
    }

    private ActorsDTO actorDto(){
        return new ActorsDTO(
                "AC0003",
                "DSS0001",
                "Robert",
                "Downey Jr.",
                "Male",
                45,
                "Tony Stark/Iron Man",
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                new Date(), UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null);
    }

}
