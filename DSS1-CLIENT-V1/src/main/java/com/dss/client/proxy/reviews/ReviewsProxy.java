package com.dss.client.proxy.reviews;


import com.dss.client.configuration.CustomFeignClientConfiguration;
import com.dss.client.dto.reviews.ReviewsDTO;
import com.dss.client.entity.reviews.Reviews;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "${feign.review-name}" , url ="${feign.review-url}", configuration = CustomFeignClientConfiguration.class)
public interface ReviewsProxy {

    @PostMapping("/API/reviews/add-review.do")
    String addReview(@RequestBody ReviewsDTO reviewsDto);

    @GetMapping("/API/reviews/display-reviews.do")
    List<Reviews> displayReviews();
}
