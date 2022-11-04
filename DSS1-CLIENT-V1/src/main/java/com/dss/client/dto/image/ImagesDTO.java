/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.client.dto.image;

import com.dss.client.dto.movie.DssMovieDTO;

/**
 * This is an DTO Class for Images
 */

public class ImagesDTO {

    private Long imageId;
    private String fileName;
    private String fileSize;
    private String url;
    private DssMovieDTO dss;

    public ImagesDTO(String fileName, String fileSize, String url, DssMovieDTO dss) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.url = url;
        this.dss = dss;
    }

    public ImagesDTO(Long imageId, String fileName, String fileSize, String url) {
        this.imageId = imageId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.url = url;
    }
}
