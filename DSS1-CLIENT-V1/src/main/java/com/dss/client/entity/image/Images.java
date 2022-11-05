/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.client.entity.image;

import com.dss.client.entity.movie.DssMovie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This is an Entity Class for Images
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Images {
    private Long imageId;
    private String fileName;
    private String fileSize;
    private String url;

    private DssMovie dss;
}
