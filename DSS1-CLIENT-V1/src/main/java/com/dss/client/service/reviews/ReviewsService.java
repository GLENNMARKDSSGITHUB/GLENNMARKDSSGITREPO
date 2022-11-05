package com.dss.client.service.reviews;

import com.dss.client.dto.reviews.ReviewsDTO;
import com.dss.client.entity.reviews.Reviews;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ReviewsService {

    String addReview(@RequestBody ReviewsDTO reviewsDto);

    List<Reviews> displayReviews();
}
