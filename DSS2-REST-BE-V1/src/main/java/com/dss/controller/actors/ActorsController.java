package com.dss.controller.actors;

import com.dss.dto.actors.ActorsDTO;
import com.dss.entity.actors.Actors;
import com.dss.service.actors.ActorsService;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/API/actor")
@Slf4j
public class ActorsController {
    private final DssCommonUtility commonUtil = new DssCommonUtility();

    @Autowired
    private ActorsService actorsService;

    @PostMapping("/add-actor.do")
    public ResponseEntity<String> addActor(@RequestBody ActorsDTO actorsDto){
        log.debug("ActorsController | addActor | Start ");
        log.debug("ActorsController | addActor | actorDTO: " + actorsDto.toString());
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = actorsService.addActor(actorsDto);
            log.debug(commonMsgDtl.getContent());
        }catch(Exception ex){
            log.error("ActorsController | addActor | Error msg : " + ex.getMessage());
        }finally{
            log.debug("ActorsController | addActor | End ");
        }
        return new ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK);
    }

    @GetMapping("/display-actors.do")
    public ResponseEntity<List<Actors>> displayActors(){
        log.debug("ActorsController | displayActors | Start ");
        List<Actors> actorList = null;
        try{
            DssCommonMessageDetails commonMsgDtl = actorsService.displayActors();
            if(commonMsgDtl.isSuccess()){
                actorList = (List<Actors>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(actorList));
                log.debug("RegistrationController | displayActors | registrations : \n" + commonUtil.gsonToJsonString(actorList));
            }else{
                log.error("ActorsController | displayActors  | actors : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            log.error("ActorsController | displayActors | Error msg : " + ex.getMessage());
        }finally{
            log.debug("ActorsController | displayActors | End ");
        }
        return new ResponseEntity<>(actorList, HttpStatus.OK);
    }

    @GetMapping("/search-actor.do")
    public ResponseEntity<List<Actors>> searchActorByActorName(HttpServletRequest request){
        log.debug("ActorsController | searchActorByActorName | Start");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        List<Actors> actorList = null;
        try{
            DssCommonMessageDetails commonMsgDtl = actorsService.searchActorByActorName(firstName, lastName);
            if(commonMsgDtl.isSuccess()){
                actorList = (List<Actors>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(actorList));
                log.debug("RegistrationController | searchActorByActorName | registration : \n" + commonUtil.gsonToJsonString(actorList));
            }else{
                log.error("ActorsController | searchActorByActorName  | actors : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            log.error("ActorsController | searchActorByActorName | Error msg : " + ex.getMessage());
        }finally{
            log.debug("ActorsController | searchActorByActorName | End ");
        }
        return new ResponseEntity<>(actorList, HttpStatus.OK);
    }

    @PutMapping("/update-actor.do")
    public ResponseEntity<String> updateActor(@RequestBody ActorsDTO actorsDto){
        log.debug("ActorsController | updateActor | Start ");
        log.debug("ActorsController | updateActor |  : " + actorsDto.toString());
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = actorsService.updateActor(actorsDto);
            log.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            log.error("ActorsController | updateActor | Error msg : " + ex.getMessage());
        }finally{
            log.debug("ActorsController | updateActor | End ");
        }
        return new ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-actor.do")
    public ResponseEntity<String> deleteActor(HttpServletRequest request){
        log.debug("ActorsController | deleteActor | Start ");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = actorsService.deleteActor(firstName, lastName);
            log.debug("ActorsController | deleteActor | getContent : " + commonMsgDtl.getContent());
        }catch (Exception ex){
            log.error("ActorsController | deleteActor | Error msg : " + ex.getMessage());
        }finally{
            log.debug("ActorsController | deleteActor | End ");
        }
        return new ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK);
    }
}
