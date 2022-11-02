/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.entity.image;

import com.dss.entity.movie.DssMovie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * This is an Entity Class for Images
 */

@Entity
@Table(name = "DSS_IMAGES")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IMAGE_ID", nullable = false)
    private Long imageId;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_SIZE")
    private String fileSize;

    @Column(name = "URL")
    private String url;

    @ManyToOne
    private DssMovie dss;

    public Images(String fileName, String fileSize, String url, DssMovie dss) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.url = url;
        this.dss = dss;
    }

    public Images(Long imageId, String fileName, String fileSize, String url) {
        this.imageId = imageId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.url = url;
    }
}
