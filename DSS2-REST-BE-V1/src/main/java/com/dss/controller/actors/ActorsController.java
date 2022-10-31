package com.dss.controller.actors;

import com.dss.dto.actors.ActorsDTO;
import com.dss.entity.actors.Actors;
import com.dss.service.actors.ActorsService;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/API/actor")
public class ActorsController {
    private static final Logger logger = LoggerFactory.getLogger(ActorsController.class);
    private final DssCommonUtility commonUtil = new DssCommonUtility();

    @Autowired
    private ActorsService actorsService;

    @PostMapping("/add-actor.do")
    public String addActor(@RequestBody ActorsDTO actorsDto){
        logger.debug("ActorsController | addActor | Start ");
        logger.debug("ActorsController | addActor | actorDTO: " + actorsDto.toString());
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = actorsService.addActor(actorsDto);
            logger.debug(commonMsgDtl.getContent());
        }catch(Exception ex){
            logger.error("ActorsController | addActor | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ActorsController | addActor | End ");
        }
        return commonMsgDtl.getContent();
    }

    @GetMapping("/display-actors.do")
    public String displayActors(){
        logger.debug("ActorsController | displayActors | Start ");
        DssCommonMessageDetails commonMsgDtl = actorsService.displayActors();
        try{
            if(commonMsgDtl.isSuccess()){
                List<Actors> actorList = (List<Actors>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(actorList));
                logger.debug("RegistrationController | displayActors | registrations : \n" + commonUtil.gsonToJsonString(actorList));
            }else{
                logger.error("ActorsController | displayActors  | actors : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            logger.error("ActorsController | displayActors | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ActorsController | displayActors | End ");
        }
        return commonMsgDtl.getContent();
    }

    @GetMapping("/search-actor.do")
    public String searchActorByActorName(HttpServletRequest request){
        logger.debug("ActorsController | searchActorByActorName | Start");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        logger.debug("ActorsController | searchActorByActorName | firstName : " + firstName + " lastName : " + lastName);
        DssCommonMessageDetails commonMsgDtl = actorsService.searchActorByActorName(firstName, lastName);
        try{
            if(commonMsgDtl.isSuccess()){
                List<Actors> actorList = (List<Actors>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(actorList));
                logger.debug("RegistrationController | searchActorByActorName | registration : \n" + commonUtil.gsonToJsonString(actorList));
            }else{
                logger.error("ActorsController | searchActorByActorName  | actors : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            logger.error("ActorsController | searchActorByActorName | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ActorsController | searchActorByActorName | End ");
        }
        return commonMsgDtl.getContent();
    }

    @PutMapping("/update-actor.do")
    public String updateActor(@RequestBody ActorsDTO actorsDto){
        logger.debug("ActorsController | updateActor | Start ");
        logger.debug("ActorsController | updateActor |  : " + actorsDto.toString());
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = actorsService.updateActor(actorsDto);
            logger.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            logger.error("ActorsController | updateActor | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ActorsController | updateActor | End ");
        }
        return commonMsgDtl.getContent();
    }

    @DeleteMapping("/delete-actor.do")
    public String deleteActor(HttpServletRequest request){
        logger.debug("ActorsController | deleteActor | Start ");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = actorsService.deleteActor(firstName, lastName);
            logger.debug("ActorsController | deleteActor | getContent : " + commonMsgDtl.getContent());
        }catch (Exception ex){
            logger.error("ActorsController | deleteActor | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ActorsController | deleteActor | End ");
        }
        return commonMsgDtl.getContent();
    }
}
