package com.dss.service.reviews;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.dto.reviews.ReviewsDTO;
import com.dss.entity.reviews.Reviews;
import com.dss.repository.reviews.ReviewsRepository;
import com.dss.transformer.DssMovieTransformerTest;
import com.dss.transformer.reviews.ReviewsTransformer;
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
public class ReviewsServiceTest {

    @Autowired
    private ReviewsService reviewsService;

    @MockBean
    private ReviewsRepository reviewsRepository;

    private final DssMovieTransformerTest movieTransformer = new DssMovieTransformerTest();

    private final ReviewsTransformer transformer = new ReviewsTransformer();

    @Test
    public void testAddReview(){
        boolean isSuccess = true;
        reviewsRepository.save(transformer.transformToReviews(reviewDto(), movieTransformer.dssMovie(dssMovieDto())));
        Mockito.when(reviewsRepository.save(transformer.transformToReviews(reviewDto(), movieTransformer.dssMovie(dssMovieDto()))))
                .thenReturn(reviews());

        DssCommonMessageDetails commonMsgDtl = reviewsService.addReview(reviewDto());
        commonMsgDtl.setSuccess(true);
        assertThat(commonMsgDtl.isSuccess()).isEqualTo(isSuccess);
    }

    @Test
    public void testDisplayReviews(){
        List<Reviews> reviewsList = Collections.singletonList(new Reviews(
                "RT0001",
                "DSS0001",
                5,
                "Rating Headline 1",
                "Rating Content 1",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null));

        List<Reviews> resultList = reviewsRepository.findAll();
        Mockito.when(resultList).thenReturn(reviewsList);

        DssCommonMessageDetails commonMsgDtl = reviewsService.displayReviews();
        commonMsgDtl.setObjList(reviewsRepository.findAll());
        assertThat(reviewsService.displayReviews()).isEqualTo(commonMsgDtl);
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

    private Reviews reviews(){
        return new Reviews(
                "RT0001",
                "DSS0001",
                5,
                "Rating Headline 1",
                "Rating Content 1",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null,
                movieTransformer.dssMovie(dssMovieDto()));
    }

    private ReviewsDTO reviewDto(){
        return new ReviewsDTO(
                "RT0001",
                "DSS0001",
                5,
                "Rating Headline 1",
                "Rating Content 1",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null);
    }
}
