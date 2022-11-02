/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.transformer.image;

import com.dss.entity.image.Images;
import com.dss.entity.movie.DssMovie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

/**
 * This class is a DSS2 Image Transformer
 */

public class ImageTransformer {

    public Images transformToImage(MultipartFile file, DssMovie movie, String filePath){
        return new Images(
                file.getOriginalFilename(),
                String.valueOf(file.getSize()),
                filePath,
                movie
        );
    }
}
