package com.dss.client.service.reviews;

import com.dss.client.dto.reviews.ReviewsDTO;
import com.dss.client.entity.reviews.Reviews;
import com.dss.client.proxy.reviews.ReviewsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService{

    @Autowired
    ReviewsProxy reviewsProxy;

    @Override
    public String addReview(ReviewsDTO reviewsDto) {
        return reviewsProxy.addReview(reviewsDto);
    }

    @Override
    public List<Reviews> displayReviews() {
        return reviewsProxy.displayReviews();
    }
}
