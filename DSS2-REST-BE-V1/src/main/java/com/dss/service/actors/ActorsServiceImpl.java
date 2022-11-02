/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service.actors;

import com.dss.dto.actors.ActorsDTO;
import com.dss.entity.actors.Actors;
import com.dss.entity.movie.DssMovie;
import com.dss.repository.actors.ActorsRepository;
import com.dss.repository.movie.DssMovieRepository;
import com.dss.transformer.actors.ActorsTransformer;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonMethods;
import com.dss.util.enums.UserRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * This class is a service implementation for DSS Movie Actors
 * @see #addActor(ActorsDTO)
 * @see #displayActors()
 * @see #searchActorByActorName(String, String)
 * @see #updateActor(ActorsDTO)
 * @see #deleteActor(String, String)
 */

@Service
public class ActorsServiceImpl implements ActorsService{
    private static final Logger logger = LoggerFactory.getLogger(ActorsServiceImpl.class);
    private final ActorsTransformer transformer = new ActorsTransformer();
    private final DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
    private final DssCommonMethods commonMethods = new DssCommonMethods();

    @Autowired
    private ActorsRepository actorsRepository;

    @Autowired
    private DssMovieRepository dssMovieRepository;

    @Override
    public DssCommonMessageDetails addActor(ActorsDTO actorDto) {
        logger.debug("ActorsServiceImpl | addActor | Start ");
        try{
            List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(actorDto.getMovieId());
            List<Actors> actorsList = actorsRepository.findActorsByActorName(actorDto.getFirstName(), actorDto.getLastName());
            if(!movieList.isEmpty()){
                if(actorsList.isEmpty()){
                    actorDto.setActorId(commonMethods.actorIdGeneration(actorsRepository.maxActorId()));
                    actorsRepository.save(transformer.transformToActor(actorDto,  movieList.get(0)));
                    commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_001_ADD_ACTOR, actorDto.getFirstName() + " " + actorDto.getLastName()));
                    commonMsgDtl.setSuccess(true);
                }else{
                    commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_001_ACTOR_EXIST, actorDto.getFirstName() + " " + actorDto.getLastName()));
                    commonMsgDtl.setSuccess(false);
                }
            }else{
                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_002_MOVIE_NOT_EXIST, actorDto.getMovieId()));
                commonMsgDtl.setSuccess(false);
            }
        }catch(Exception ex){
            logger.error("ActorsServiceImpl | addActor | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ActorsServiceImpl | addActor | End ");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails displayActors() {
        logger.debug("ActorsServiceImpl | displayActors | Start ");
        try{
            List<Actors> actorsList = actorsRepository.findAll();
            if(!actorsList.isEmpty()){
                commonMsgDtl.setObjList(transformer.transformToActor((actorsList)));
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_002_NO_DISPLAY_RECORDS);
                commonMsgDtl.setSuccess(false);
            }
        }catch(Exception ex){
            logger.error("ActorsServiceImpl | displayActors | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ActorsServiceImpl | displayActors | End ");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails searchActorByActorName(String firstName, String lastName) {
        logger.debug("ActorsServiceImpl | searchActorByActorName | Start ");
        try{
            List<Actors> actorsList = actorsRepository.findActorsByActorName(firstName, lastName);
            if(!actorsList.isEmpty()){
                commonMsgDtl.setObjList(transformer.transformToActor((actorsList)));
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_002_NO_DISPLAY_RECORDS);
                commonMsgDtl.setSuccess(false);
            }
        }catch(Exception ex){
            logger.error("ActorsServiceImpl | searchActorByActorName | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ActorsServiceImpl | searchActorByActorName | End ");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails updateActor(ActorsDTO actorDto) {
        logger.debug("ActorsServiceImpl | updateActor | Start ");
        try{
            List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(actorDto.getMovieId());
            List<Actors> actorsList = actorsRepository.findActorByActorId(actorDto.getActorId());
            if(!movieList.isEmpty()){
                if(!actorsList.isEmpty()){
                    actorDto.setLastModificationDate(new Date());
                    actorDto.setLastModifiedBy(UserRoles.ROLE_ADMIN.toString());
                    actorsRepository.save(transformer.transformToActor(actorDto,  movieList.get(0)));
                    commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_002_UPDATE_ACTOR, actorDto.getFirstName() + " " + actorDto.getLastName()));
                    commonMsgDtl.setSuccess(true);
                }else{
                    commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_002_NO_DISPLAY_RECORDS);
                    commonMsgDtl.setSuccess(false);
                }
            }else{
                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_002_MOVIE_NOT_EXIST, actorDto.getMovieId()));
                commonMsgDtl.setSuccess(false);
            }
        }catch(Exception ex){
            logger.error("ActorsServiceImpl | updateActor | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ActorsServiceImpl | updateActor | End ");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails deleteActor(String firstName, String lastName) {
        logger.debug("ActorsServiceImpl | updateActor | Start ");
        try{
            List<Actors> actorsList = actorsRepository.findActorsByActorName(firstName, lastName);
            if(!actorsList.isEmpty()){
                actorsRepository.delete(actorsList.get(0));
                commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_003_DELETE_ACTOR, firstName + " " + lastName));
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_002_NO_DISPLAY_RECORDS);
                commonMsgDtl.setSuccess(false);
            }
        }catch(Exception ex){
            logger.error("ActorsServiceImpl | updateActor | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ActorsServiceImpl | updateActor | End ");
        }
        return commonMsgDtl;
    }
}
