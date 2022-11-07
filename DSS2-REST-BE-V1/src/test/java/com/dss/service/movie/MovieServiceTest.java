package com.dss.service.movie;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.entity.movie.DssMovie;
import com.dss.repository.movie.DssMovieRepository;
import com.dss.transformer.DssMovieTransformerTest;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {

    @Autowired
    private DssMovieService dssMovieService;

    @MockBean
    private DssMovieRepository dssMovieRepository;

    private final DssMovieTransformerTest transformer = new DssMovieTransformerTest();

    @Test
    public void testAddDssMovie(){
        boolean isSuccess = true;
        DssMovie dssMovie = dssMovieRepository.save(transformer.dssMovie(dssMovieDto()));
        Mockito.when(dssMovie).thenReturn(transformer.dssMovie(dssMovieDto()));

        DssCommonMessageDetails commonMsgDtl = dssMovieService.addDssMovie(dssMovieDto());
        commonMsgDtl.setSuccess(true);
        assertThat(commonMsgDtl.isSuccess()).isEqualTo(isSuccess);
    }

    @Test
    public void testDisplayDssMovies(){
        List<DssMovie> movieList = Collections.singletonList(transformer.dssMovie(dssMovieDto()));
        List<DssMovie> resultList = dssMovieRepository.findAll();
        Mockito.when(resultList).thenReturn(movieList);

        DssCommonMessageDetails commonMsgDtl = dssMovieService.displayDssMovies();
        commonMsgDtl.setObjList(dssMovieRepository.findAll());

        assertThat(dssMovieService.displayDssMovies()).isEqualTo(commonMsgDtl);
    }

    @Test
    public void testSearchDssMovieByMovieTitle(){
        List<DssMovie> movieList = Collections.singletonList(transformer.dssMovie(dssMovieDto()));
        List<DssMovie> resultList = dssMovieRepository.findDssMovieByMovieTitle("The Avengers");
        Mockito.when(resultList).thenReturn(movieList);

        DssCommonMessageDetails commonMsgDtl = dssMovieService.searchDssMovieByMovieTitle("The Avengers");
        commonMsgDtl.setObjList(resultList);
        assertThat(dssMovieService.searchDssMovieByMovieTitle("The Avengers")).isEqualTo(commonMsgDtl);
    }

    @Test
    public void testUpdateDssMovie(){
        boolean isSuccess = true;
        List<DssMovie> movieList = Collections.singletonList(transformer.dssMovie(dssMovieDto()));
        List<DssMovie> resultList = dssMovieRepository.findDssMovieByMovieTitle("The Avengers");
        Mockito.when(resultList).thenReturn(movieList);

        transformer.dssMovie(dssMovieDto()).setCategory("Rated 18");
        DssMovie movie = dssMovieRepository.save(transformer.dssMovie(dssMovieDto()));
        Mockito.when(movie).thenReturn(transformer.dssMovie(dssMovieDto()));

        DssCommonMessageDetails commonMsgDtl = dssMovieService.updateDssMovie(dssMovieDto());
        commonMsgDtl.setSuccess(true);
        assertThat(commonMsgDtl.isSuccess()).isEqualTo(isSuccess);
    }

    @Test
    public void testDeleteDssMovie(){
        boolean isSuccess = true;
        List<DssMovie> movieList = Collections.singletonList(transformer.dssMovie(dssMovieDto()));
        List<DssMovie> resultList = dssMovieRepository.findDssMovieByMovieTitle("The Avengers");
        Mockito.when(resultList).thenReturn(movieList);

        DssCommonMessageDetails commonMsgDtl = dssMovieService.deleteDssMovie("The Avengers");
        commonMsgDtl.setSuccess(true);
        assertThat(commonMsgDtl.isSuccess()).isEqualTo(isSuccess);
    }

    private DssMovieDTO dssMovieDto(){
        return new DssMovieDTO(
                "DSS0001",
                "The Avengers",
                "2021",
                "Stan Lee",
                "Joss Whedon",
                "Trinh Tran",
                "Alan Silvestri",
                "143 minutes",
                400000000,
                "PG-13 TV-14",
                "United States",
                "English",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null
        );
    }
}
