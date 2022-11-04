package com.dss.client;

import com.dss.configuration.feignclient.CustomFeignClientConfiguration;
import com.dss.dto.reviews.ReviewsDTO;
import com.dss.entity.reviews.Reviews;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@FeignClient(value = "${app.feign.config.name}", url = "${app.feign.config.url}", configuration = CustomFeignClientConfiguration.class)
public interface StoreClient {

    @PostMapping("/API/reviews/add-review.do")
    ResponseEntity<String> addReview(@RequestBody ReviewsDTO reviewsDto);

    @GetMapping("/API/reviews/display-reviews.do")
    ResponseEntity<List<Reviews>> displayReviews();

    @GetMapping("/API/reviews/search-review.do")
    ResponseEntity<List<Reviews>> searchReview(HttpServletRequest request);

    @PutMapping("/API/reviews/update-review.do")
    ResponseEntity<String> updateReview(@RequestBody ReviewsDTO reviewsDto);
}
